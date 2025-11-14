package maquina1995.rag.ia.jackson;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

/**
 * Clase usada para todo lo relacionado con Serializacion y De-serializacion usando jackson
 * 
 * @author MaQuiNa1995
 *
 */
@Component
@RequiredArgsConstructor
public class JacksonService {

	/**
	 * Objeto auto inyectado por spring de jackson
	 */
    private final ObjectMapper objectMapper;

    /**
     * Método usado para la de-serializacion del json generado por el LLM en un mapa clave valor
     * 
     * @param json en formato {@link String} generado por el LLM
     * @return {@link Map} con la clave valor de cada par del json 
     */
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
