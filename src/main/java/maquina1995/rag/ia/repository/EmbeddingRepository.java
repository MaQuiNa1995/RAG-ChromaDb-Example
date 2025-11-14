package maquina1995.rag.ia.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest; // Usamos el SearchRequest de Spring AI
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

/**
 * Clase encargada para la interacción con chromaDB para la persistencia y consulta
 * 
 * @author MaQuiNa1995
 *
 */
@Component
@RequiredArgsConstructor
public class EmbeddingRepository {

	private final VectorStore vectorStore;

	/**
	 * Esta variable define el factor de similitud con la que busca en la base de datos vectorial
	 */
	public final static double SIMILARITY_THRESHOLD = 0.2;
	public final static int TOP_K = 5;

	/**
	 * Metodo usado para guardar en base de datos
	 * 
	 * @param id clave UUID 
	 * @param texto la descripcion del registro en base de datos
	 * @param metadata mapa que define los metadata del registro
	 */
	public void save(String id, String texto, Map<String, Object> metadata) {

		/**
		 * Creamos un clon del metadata que nos pasan por parámetro para no ensuciar la referencia porque re-usamos la variable (ver el service)
		 */
		Map<String, Object> clonedMetadata = new HashMap<>(metadata);
		// Le añadimos el id
		clonedMetadata.put("id", id);

		// Creamos el registro de BD con el texto y los metadatos asociados
		Document document = new Document(texto, clonedMetadata);

		// Llamamos al vectorStore para guardar el documento
		vectorStore.add(List.of(document));

		System.out.println("Documento con ID: " + id + " agregado al VectorStore (ChromaDB).");
	}

	/**
	 * Busca en ChromaDB documentos relevantes para la pregunta del usuario y
	 * construye el contexto.
	 * 
	 * @param userQuery La pregunta del usuario (String).
	 * @param filter    Opcional: Filtra por metadatos (ej. Map.of("tipo", "tela")).
	 * @return Una cadena de texto con el contexto concatenado para el LLM.
	 */
	public String getContextForChatbot(String userQuery, Map<String, String> metadataFilters) {

		// Creamos el SearchRequest que es basicamente el objeto para hacer la consulta
		SearchRequest searchRequest = SearchRequest.builder()
				.query(userQuery)
				.topK(TOP_K)
				.similarityThreshold(SIMILARITY_THRESHOLD)
				.build();

		// Le pasamos la query a la base de datos y obtenemos los documentos relevantes con la similitud que hemos configurado en la query
		List<Document> relevantDocuments = vectorStore.similaritySearch(searchRequest);

		if (relevantDocuments.isEmpty()) {
			return "No se encontró información relevante sobre este tema.";
		}

		// --- Definición del Predicado de Filtrado Dinámico ---
		List<Document> filteredDocuments = relevantDocuments.stream()
				.filter(doc -> {
					// Si el mapa de filtros está vacío o es null, no aplicamos filtro.
					if (metadataFilters == null || metadataFilters.isEmpty()) {
						return true;
					}

					// Verificar que cada filtro en metadataFilters coincida con el metadata del documento.
					return metadataFilters.entrySet()
							.stream()
							.allMatch(filterEntry -> {
								String filterKey = filterEntry.getKey();
								String filterValue = filterEntry.getValue();

								// Ignorar filtros donde el LLM devolvió "null" o un valor vacío.
								if (filterValue == null || filterValue.equalsIgnoreCase("null") || filterValue.trim()
										.isEmpty()) {
									return true;
								}

								// Obtener el valor de metadata del documento para la clave actual
								Object docMetadataValue = doc.getMetadata()
										.get(filterKey);

								// Comparación: True si el valor del documento coincide con el valor del filtro.
								return docMetadataValue != null
										&& Objects.equals(docMetadataValue.toString(), filterValue);
							});
				})
				.collect(Collectors.toList());


		if (filteredDocuments.isEmpty()) {
			return "Se encontraron documentos por similitud, pero ninguno coincidió con los filtros de metadatos proporcionados.";
		}

		// aqui construimos el contexto con 3 guiones separando cada uno de los documentos encontrados
		String context = filteredDocuments.stream()
				.map(Document::getText)
				.collect(Collectors.joining("\n---\n"));

		return context;
	}

}