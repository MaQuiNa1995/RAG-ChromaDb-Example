package maquina1995.rag.ia.jackson;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JacksonService {

    private final ObjectMapper objectMapper;

    public Map<String, String> jsonToMap(String json) {
        
        TypeReference<Map<String, String>> typeRef = 
            new TypeReference<Map<String, String>>() {};

        try {
            return objectMapper.readValue(json, typeRef);
        } catch (IOException e) {
            System.err.println("Error al des-serializar el JSON con Jackson: " + e.getMessage());
            return Map.of(); 
        }
    }
}
