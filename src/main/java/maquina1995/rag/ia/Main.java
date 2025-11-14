package maquina1995.rag.ia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import maquina1995.rag.ia.service.EmbeddingService;

/**
 * Main con ejemplo de uso de LLM con la tecnica RAG (Todo explicado mas adelante en el codigo)
 * 
 * @author MaQuiNa1995
 *
 */
@Log4j2
@SpringBootApplication
@RequiredArgsConstructor
public class Main implements CommandLineRunner {

	/**
	 * Objeto propio de Spring boot encargado de la comunicacion con el modelo
	 */
	private final ChatClient chatClient;
	/**
	 * Servicio que usamos para la comunicacion con el repository para interactuar con la base de datos 
	 */
	private final EmbeddingService embeddingService;

	/**
	 * Método main tipico de spring
	 * @param args
	 */
	public static void main(String[] args) {
		new SpringApplicationBuilder(Main.class).web(WebApplicationType.NONE)
				.run(args);
	}

	/**
	 * Metodo main de springboot ejecutado justo despues de que levante el contexto de spring
	 */
	@Override
	public void run(String... args) throws Exception {

		/**
		 * Parte del codigo en la que hacemos la llamada a la base de datos para guardar
		 * los datos en chromaDB para sacar el contexto para crear el contexto para el
		 * prompt del LLM (Large Language Model, o Modelo de Lenguaje Grande)
		 * 
		 * Esto solo debería de ejecutarse 1 sola vez cuando el contenedor de docker del
		 * ChromaDB está vacio ya que esto popula los datos que luego son usados por el
		 * LLM
		 * 
		 * Está comentado por eso nada mas
		 */
		//embeddingService.createEmbeddings();

		String userQuestion = "";

		// Simple objeto para leer la entrada del usuario por consola
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

			System.out.println(
					"""
					Formula tu pregunta:
					Un ejemplo de pregunta podría ser: "En que mazmorras me puede caer un collar"
					""");
			// Leemos la pregunta del usuario
			userQuestion = reader.readLine();

		} catch (IOException exception) {
			log.error(exception.getMessage());
			System.exit(-1);
		}

		/**
		 * Aqui nos ayudamos del LLM para extraer los datos importantes de la pregunta
		 * del usuario userQuestion
		 * 
		 * Basicamente le decimos que nos cree un JSON con la informacion importante que
		 * necesitamos para filtrar luego en la base de datos chromaDB
		 * 
		 * Podemos usar 2 estrategias para esto: - Usar un LLM para que nos extraiga la
		 * informacion (Que es este caso) - Usar un NLP (natural Language Processing)
		 * que es una librería para clasificar y extraer palabras de un texto
		 * 
		 * El paso de usar un NLP es mas complicado ya que requiere de entrar un modelo
		 * especifico con muchos datos para que reconozca las diferentes palabras y
		 * poder ser clasificadas luego en un json
		 * 
		 */
		String metadataFilter = chatClient.prompt()
				.user("""
						Eres un sistema de extracción de metadatos para un videojuego.
						Tu tarea es analizar la consulta del usuario y extraer las siguientes categorías de información:
						1. tipo (string): Tipo general de equipo (collar, armadura, anillo, capa)
						2. clase (string): La clase de armadura (Tela, Cuero, Malla, Placas).
						3. mazmorra (string): Nombre de la mazmorra.

						Si no encuentras una categoría, usa "null".
						La salida debe ser un objeto JSON VÁLIDO.

						Consulta del usuario:
						 """ + userQuestion) // Prompt
				.call() // Hacemos la llamada a nuestro contenedor Ollama
				.content() // Obtenemos la respuesta del LLM en formato string
				.toLowerCase(); // Aqui nos aseguramos que todo esté en minusculas para curarnos en salud en
								// tema de filtro que en la base de datos esté tela y en el filtro salga Tela

		/**
		 * En este paso nos valemos del json que se genero en metadataFilter y de la
		 * pregunta del usuario para encontrar en base de datos entradas relevantes para
		 * construir el contexto para el prompt final
		 * 
		 * basicamente esto es lo que se llama RAG (Retrieval-Augmented Generation o
		 * Generación Aumentada por Recuperación) consiste en obtener de una base de
		 * datos vectorial a traves de filtros (Retrieval) entradas relevantes para usar
		 * de contexto (Augmented) para que el LLM posteriormente nos genere una
		 * respuesta en base a esos datos y no a su conocimiento (Generation)
		 */
		String context = embeddingService.getContextForChatbot(userQuestion, metadataFilter);

		/**
		 * Con ese contexto que hemos generado construimos el prompt final dando algunas
		 * indicaciones claras para que el LLM genere justo lo que queremos con el
		 * contexto dado
		 */
		String finalPrompt = String.format(
				"""
						---
						Contexto:
						---
						%s
						---
						Pregunta del Usuario:
						%s
						---
						Responde en formato lista sin nada mas añadido solo contesta con el contexto que te he pasado arriba, no busques en tu conocimiento propio

						NUNCA devuelvas la consulta completa del usuario como un valor de campo de mazmorra

						La salida debe ser una lista vertical
						""",
				context, userQuestion);

		/**
		 * Aqui como anteriormente hicimos llamamos al LLM y le pasamos el prompt final
		 */
		String llmResponse = chatClient.prompt()
				.user(finalPrompt)
				.call()
				.content();

		// Mostramos la respuesta del LLM
		System.out.println(llmResponse);

	}
}