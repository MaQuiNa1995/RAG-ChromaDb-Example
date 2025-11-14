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

@Configuration
public class LlmConfiguration {

    @Bean
    public ChatClient chatClient(OllamaChatModel chatModel) {
        return ChatClient.builder(chatModel).build();
    }
    
    @Bean
    public EmbeddingModel embeddingModel(OllamaApi ollamaApi) {
        OllamaOptions options = OllamaOptions.builder().model("all-minilm").build();
        
        return OllamaEmbeddingModel.builder().ollamaApi(ollamaApi).defaultOptions(options).build();
    }
    
    @Bean
    public ChromaApi chromaApi(@Value("${spring.ai.vectorstore.chroma.base-url:http://localhost:8000}") String chromaBaseUrl,RestClient.Builder restClientBuilder, ObjectMapper objectMapper) {
        return new ChromaApi(chromaBaseUrl,restClientBuilder, objectMapper);
    }

    @Bean
    public ChromaVectorStore chromaVectorStore(ChromaApi chromaApi, EmbeddingModel embeddingModel) {

        return ChromaVectorStore.builder(chromaApi,embeddingModel)
                .tenantName("default")
                .databaseName("default")
                .collectionName("prueba")
                .build();
    }
}