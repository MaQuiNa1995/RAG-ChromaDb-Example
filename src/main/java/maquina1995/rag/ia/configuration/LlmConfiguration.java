package maquina1995.rag.ia.configuration;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chroma.vectorstore.ChromaApi;
import org.springframework.ai.chroma.vectorstore.ChromaVectorStore;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.OllamaEmbeddingModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.databind.ObjectMapper; 

/**
 * Configuracion de los bean de spring
 * 
 * @author MaQuiNa1995
 *
 */
@Configuration
public class LlmConfiguration {

	/**
	 * Creación del objeto ChatClient que es el encargado de la interacción con ollama que ejecuta el LLM que usamos
	 * 
	 * @param chatModel
	 * @return ChatClient
	 */
    @Bean
    public ChatClient chatClient(OllamaChatModel chatModel) {
        return ChatClient.builder(chatModel).build();
    }
    
    /**
     * Configuracion del modelo que generara los registros en la base de datos vectorial en este caso usamos el modelo <code>all-minilm</code>
     * 
     * @param ollamaApi
     * @return
     */
    @Bean
    public EmbeddingModel embeddingModel(OllamaApi ollamaApi) {
        OllamaOptions options = OllamaOptions.builder().model("all-minilm").build();
        
        return OllamaEmbeddingModel.builder().ollamaApi(ollamaApi).defaultOptions(options).build();
    }
    
    /**
     * Creación de la api de la base de datos de chromaDB
     * 
     * @param chromaBaseUrl url del contenedor
     * @param restClientBuilder el builder del cliente rest
     * @param objectMapper el objeto para deserializar o serializar de jackson
     * @return
     */
    @Bean
    public ChromaApi chromaApi(@Value("${spring.ai.vectorstore.chroma.base-url:http://localhost:8000}") String chromaBaseUrl, RestClient.Builder restClientBuilder, ObjectMapper objectMapper) {
        return new ChromaApi(chromaBaseUrl,restClientBuilder, objectMapper);
    }

    /**
     * Creacion del objeto encargado de la interaccion con la base de datos chromaDB usando la api y el modelo que hemos creado antes
     * 
     * @param chromaApi
     * @param embeddingModel
     * @return
     */
    @Bean
    public ChromaVectorStore chromaVectorStore(ChromaApi chromaApi, EmbeddingModel embeddingModel) {

    	
        return ChromaVectorStore.builder(chromaApi,embeddingModel)
                .tenantName("default")
                .databaseName("default")
                // Debes de darle el nombre de la colección a la que se va a conectar
                .collectionName("prueba")
                .build();
    }
}