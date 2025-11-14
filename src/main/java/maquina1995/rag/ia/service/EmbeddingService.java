package maquina1995.rag.ia.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import maquina1995.rag.ia.jackson.JacksonService;
import maquina1995.rag.ia.repository.EmbeddingRepository;

/**
 * 
 * @author MaQuiNa1995
 *
 */
@Service
@RequiredArgsConstructor
public class EmbeddingService {

	private final EmbeddingRepository embeddingRepository;
	private final JacksonService jacksonService;
	
	public void createEmbeddings() {

		// =========================================================================
		// 1. ACCESORIOS (Collares, Anillos, Capa)
		// =========================================================================
		// Collares
		Map<String, Object> metadataCollares = new HashMap<>();
		metadataCollares.put("tipo", "collar");		
		metadataCollares.put("mazmorra", "Tazavesh: Gambito de So'leah");
		embeddingRepository.save("collar-" + UUID.randomUUID().toString(), "El objeto Amplificador bellamente grabado es un Collar que cae en la mazmorra Tazavesh: Gambito de So'leah", metadataCollares);
		metadataCollares.put("mazmorra", "Tazavesh: So'leah's Gambit");
		embeddingRepository.save("collar-" + UUID.randomUUID().toString(), "El objeto Cabujón del vuelo infinito es un Collar que cae en la mazmorra Tazavesh: So'leah's Gambit", metadataCollares);
		metadataCollares.put("mazmorra", "Salas de la Expiación");
		embeddingRepository.save("collar-" + UUID.randomUUID().toString(), "El objeto Colgante de impenitente es un Collar que cae en la mazmorra Salas de la Expiación", metadataCollares);
		metadataCollares.put("mazmorra", "Priorato de la Llama Sagrada");
		embeddingRepository.save("collar-" + UUID.randomUUID().toString(), "El objeto Recuerdo manchado de sangre es un Collar que cae en la mazmorra Priorato de la Llama Sagrada", metadataCollares);
		metadataCollares.put("mazmorra", "Operación: Compuerta");
		embeddingRepository.save("collar-" + UUID.randomUUID().toString(), "El objeto Megamedallón de M.A.M.A. es un Collar que cae en la mazmorra Operación: Compuerta", metadataCollares);

		// Anillos
		Map<String, Object> metadataAnillos = new HashMap<>();
		metadataAnillos.put("tipo", "anillo");
		
		metadataAnillos.put("mazmorra", "Ecodomo Al'dani");
        embeddingRepository.save("anillo-" + UUID.randomUUID().toString(),"El objeto Susurros de K'aresh es un Anillo que cae en la mazmorra Ecodomo Al'dani", metadataAnillos);
        metadataAnillos.put("mazmorra", "Tazavesh: Gambito de So'leah");
        embeddingRepository.save("anillo-" + UUID.randomUUID().toString(), "El objeto Sello de la panoplia es un Anillo que cae en la mazmorra Tazavesh: Gambito de So'leah", metadataAnillos);
        metadataAnillos.put("mazmorra", "Tazavesh: So'leah's Gambit");
        embeddingRepository.save("anillo-" + UUID.randomUUID().toString(), "El objeto Sello de estrellas en colapso es un Anillo que cae en la mazmorra Tazavesh: So'leah's Gambit", metadataAnillos);
        metadataAnillos.put("mazmorra", "Priorato de la Llama Sagrada");
        embeddingRepository.save("anillo-" + UUID.randomUUID().toString(), "El objeto Sortija de nigromante radiante es un Anillo que cae en la mazmorra Priorato de la Llama Sagrada",  metadataAnillos);
        metadataAnillos.put("mazmorra", "Salas de la Expiación");
        embeddingRepository.save("anillo-" + UUID.randomUUID().toString(), "El objeto Sello de la falsa acusadora es un Anillo que cae en la mazmorra Salas de la Expiación", metadataAnillos);

		// Capa
		embeddingRepository.save("info-" + UUID.randomUUID().toString(), "El Mantón reshii es la única capa y no tiene otros drops de mazmorras míticas+ ni Raid.", Map.of("tipo", "capa"));


		// =========================================================================
		// 2. ARMADURA DE TELA (Cloth)
		// =========================================================================
		Map<String, Object> metadataTela = new HashMap<>();
		metadataTela.put("tipo", "armadura");
		metadataTela.put("clase", "tela");

		metadataTela.put("mazmorra", "Ecodomo Al'dani");
		embeddingRepository.save("tela-" + UUID.randomUUID().toString(), "El objeto Envolturas de devoto del páramo es un Casco de Tela que cae en la mazmorra Ecodomo Al'dani", metadataTela);
		metadataTela.put("mazmorra", "Priorato de la Llama Sagrada");
		embeddingRepository.save("tela-" + UUID.randomUUID().toString(), "El objeto Corona de llamas elisias es un Casco de Tela que cae en la mazmorra Priorato de la Llama Sagrada", metadataTela);
		metadataTela.put("mazmorra", "Tazavesh: So'leah's Gambit");
		embeddingRepository.save("tela-" + UUID.randomUUID().toString(), "El objeto Capucha de destino ramificado es un Casco de Tela que cae en la mazmorra Tazavesh: So'leah's Gambit", metadataTela);
		metadataTela.put("mazmorra", "Tazavesh: Gambito de So'leah");
		embeddingRepository.save("tela-" + UUID.randomUUID().toString(), "El objeto Bufas armoniosas son Hombreras de Tela que caen en la mazmorra Tazavesh: Gambito de So'leah", metadataTela);
		metadataTela.put("mazmorra", "Rompealbas");
		embeddingRepository.save("tela-" + UUID.randomUUID().toString(), "El objeto Jinetas ennegrecidas de fanático son Hombreras de Tela que caen en la mazmorra Rompealbas", metadataTela);
		metadataTela.put("mazmorra", "Priorato de la Llama Sagrada");
		embeddingRepository.save("tela-" + UUID.randomUUID().toString(), "El objeto Vestiduras de celador ferviente es un Pecho de Tela que cae en la mazmorra Priorato de la Llama Sagrada", metadataTela);
		metadataTela.put("mazmorra", "Operación: Compuerta");
		embeddingRepository.save("tela-" + UUID.randomUUID().toString(), "El objeto Chaqueta de goma de saboteador es un Pecho de Tela que cae en la mazmorra Operación: Compuerta", metadataTela);
		metadataTela.put("mazmorra", "Tazavesh: Gambito de So'leah");
		embeddingRepository.save("tela-" + UUID.randomUUID().toString(), "El objeto Toga de tratos a medianoche es un Pecho de Tela que cae en la mazmorra Tazavesh: Gambito de So'leah", metadataTela);
		metadataTela.put("mazmorra", "Salas de la Expiación");
		embeddingRepository.save("tela-" + UUID.randomUUID().toString(), "El objeto Embozo de luz de pecado es un Pecho de Tela que cae en la mazmorra Salas de la Expiación", metadataTela);
		metadataTela.put("mazmorra", "Ecodomo Al'dani");
		embeddingRepository.save("tela-" + UUID.randomUUID().toString(), "El objeto Gasa de auxiliar Al'dani son Muñequeras de Tela que caen en la mazmorra Ecodomo Al'dani", metadataTela);
		metadataTela.put("mazmorra", "Tazavesh: Gambito de So'leah");
		embeddingRepository.save("tela-" + UUID.randomUUID().toString(), "El objeto Brazales contables de subastador son Muñequeras de Tela que caen en la mazmorra Tazavesh: Gambito de So'leah", metadataTela);
		metadataTela.put("mazmorra", "Ecodomo Al'dani");
		embeddingRepository.save("tela-" + UUID.randomUUID().toString(), "El objeto Caricia de oráculo antiguo son Guantes de Tela que caen en la mazmorra Ecodomo Al'dani", metadataTela);
		metadataTela.put("mazmorra", "Salas de la Expiación");
		embeddingRepository.save("tela-" + UUID.randomUUID().toString(), "El objeto Mitones de modelador maligno de piedra son Guantes de Tela que caen en la mazmorra Salas de la Expiación", metadataTela);
		metadataTela.put("mazmorra", "Tazavesh: Gambito de So'leah");
		embeddingRepository.save("tela-" + UUID.randomUUID().toString(), "El objeto Guantes cifrados son Guantes de Tela que caen en la mazmorra Tazavesh: Gambito de So'leah", metadataTela);
		metadataTela.put("mazmorra", "Priorato de la Llama Sagrada");
		embeddingRepository.save("tela-" + UUID.randomUUID().toString(), "El objeto Fajín de criado vinculado al honor es una Cintura de Tela que cae en la mazmorra Priorato de la Llama Sagrada", metadataTela);
		metadataTela.put("mazmorra", "Ara-Kara, Ciudad de los Ecos");
		embeddingRepository.save("tela-" + UUID.randomUUID().toString(), "El objeto Cincho de excavador es una Cintura de Tela que cae en la mazmorra Ara-Kara, Ciudad de los Ecos", metadataTela);
		metadataTela.put("mazmorra", "Salas de la Expiación");
		embeddingRepository.save("tela-" + UUID.randomUUID().toString(), "El objeto Cordón de la palabra oscura es una Cintura de Tela que cae en la mazmorra Salas de la Expiación", metadataTela);
		metadataTela.put("mazmorra", "Tazavesh: Gambito de So'leah");
		embeddingRepository.save("tela-" + UUID.randomUUID().toString(), "El objeto Cordón de carga pandimensional es una Cintura de Tela que cae en la mazmorra Tazavesh: Gambito de So'leah", metadataTela);
		metadataTela.put("mazmorra", "Ara-Kara, Ciudad de los Ecos");
		embeddingRepository.save("tela-" + UUID.randomUUID().toString(), "El objeto Perneras de venomante arcaicas son Piernas de Tela que caen en la mazmorra Ara-Kara, Ciudad de los Ecos", metadataTela);
		metadataTela.put("mazmorra", "Operación: Compuerta");
		embeddingRepository.save("tela-" + UUID.randomUUID().toString(), "El objeto Pantalones de filosfixia son Piernas de Tela que caen en la mazmorra Operación: Compuerta", metadataTela);
		metadataTela.put("mazmorra", "Tazavesh: So'leah's Gambit");
		embeddingRepository.save("tela-" + UUID.randomUUID().toString(), "El objeto Leotardos de hiperluz son Piernas de Tela que caen en la mazmorra Tazavesh: So'leah's Gambit", metadataTela);
		metadataTela.put("mazmorra", "Rompealbas");
		embeddingRepository.save("tela-" + UUID.randomUUID().toString(), "El objeto Zapatillas membranosas son Pies de Tela que caen en la mazmorra Rompealbas", metadataTela);
		metadataTela.put("mazmorra", "Operación: Compuerta");
		embeddingRepository.save("tela-" + UUID.randomUUID().toString(), "El objeto Calcetines mortales anticonductivos son Pies de Tela que caen en la mazmorra Operación: Compuerta", metadataTela);
		metadataTela.put("mazmorra", "Salas de la Expiación");
		embeddingRepository.save("tela-" + UUID.randomUUID().toString(), "El objeto Zapatillas de estación leudada son Pies de Tela que caen en la mazmorra Salas de la Expiación", metadataTela);
		metadataTela.put("mazmorra", "Tazavesh: So'leah's Gambit");
		embeddingRepository.save("tela-" + UUID.randomUUID().toString(), "El objeto Sandalias de astucia de decodificador son Pies de Tela que caen en la mazmorra Tazavesh: So'leah's Gambit", metadataTela);



		// =========================================================================
		// 3. ARMADURA DE CUERO (Leather)
		// =========================================================================
		Map<String, Object> metadataCuero = new HashMap<>();
		metadataCuero.put("tipo", "armadura");
		metadataCuero.put("clase", "cuero");


		metadataCuero.put("mazmorra", "Priorato de la Llama Sagrada");
		embeddingRepository.save("cuero-" + UUID.randomUUID().toString(), "El objeto Yelmo de la cruzada honrada es un Casco de Cuero que cae en la mazmorra Priorato de la Llama Sagrada", metadataCuero);
		metadataCuero.put("mazmorra", "Operación: Compuerta");
		embeddingRepository.save("cuero-" + UUID.randomUUID().toString(), "El objeto Máscara de filtrado de la Estación de Bombeo es un Casco de Cuero que cae en la mazmorra Operación: Compuerta", metadataCuero);
		metadataCuero.put("mazmorra", "Ara-Kara, Ciudad de los Ecos");
		embeddingRepository.save("cuero-" + UUID.randomUUID().toString(), "El objeto Máscara susurrante es un Casco de Cuero que cae en la mazmorra Ara-Kara, Ciudad de los Ecos", metadataCuero);
		metadataCuero.put("mazmorra", "Tazavesh: So'leah's Gambit");
		embeddingRepository.save("cuero-" + UUID.randomUUID().toString(), "El objeto Tricornio con puntas elegantes es un Casco de Cuero que cae en la mazmorra Tazavesh: So'leah's Gambit", metadataCuero);
		metadataCuero.put("mazmorra", "Salas de la Expiación");
		embeddingRepository.save("cuero-" + UUID.randomUUID().toString(), "El objeto Caperuza de sombras refractadas es un Casco de Cuero que cae en la mazmorra Salas de la Expiación", metadataCuero);
		metadataCuero.put("mazmorra", "Rompealbas");
		embeddingRepository.save("cuero-" + UUID.randomUUID().toString(), "El objeto Cubrehombros de las alas cortadas son Hombreras de Cuero que caen en la mazmorra Rompealbas", metadataCuero);
		metadataCuero.put("mazmorra", "Operación: Compuerta");
		embeddingRepository.save("cuero-" + UUID.randomUUID().toString(), "El objeto Protectores de chatarra mecanizados son Hombreras de Cuero que caen en la mazmorra Operación: Compuerta", metadataCuero);
		metadataCuero.put("mazmorra", "Ecodomo Al'dani");
		embeddingRepository.save("cuero-" + UUID.randomUUID().toString(), "El objeto Manto de destino herido son Hombreras de Cuero que caen en la mazmorra Ecodomo Al'dani", metadataCuero);
		metadataCuero.put("mazmorra", "Tazavesh: Gambito de So'leah");
		embeddingRepository.save("cuero-" + UUID.randomUUID().toString(), "El objeto Guardahombros de ritmo interrumpido son Hombreras de Cuero que caen en la mazmorra Tazavesh: Gambito de So'leah", metadataCuero);
		metadataCuero.put("mazmorra", "Priorato de la Llama Sagrada");
		embeddingRepository.save("cuero-" + UUID.randomUUID().toString(), "El objeto Sobrevesta seráfica de la Ordenación es un Pecho de Cuero que cae en la mazmorra Priorato de la Llama Sagrada", metadataCuero);
		metadataCuero.put("mazmorra", "Ecodomo Al'dani");
		embeddingRepository.save("cuero-" + UUID.randomUUID().toString(), "El objeto Jubón de acechacuero reforzado es un Pecho de Cuero que cae en la mazmorra Ecodomo Al'dani", metadataCuero);
		metadataCuero.put("mazmorra", "Tazavesh: Gambito de So'leah");
		embeddingRepository.save("cuero-" + UUID.randomUUID().toString(), "El objeto Jubón fractal de So'azmi es un Pecho de Cuero que cae en la mazmorra Tazavesh: Gambito de So'leah", metadataCuero);
		metadataCuero.put("mazmorra", "Operación: Compuerta");
		embeddingRepository.save("cuero-" + UUID.randomUUID().toString(), "El objeto Envolturas de alga perturbada son Muñequeras de Cuero que caen en la mazmorra Operación: Compuerta", metadataCuero);
		metadataCuero.put("mazmorra", "Ara-Kara, Ciudad de los Ecos");
		embeddingRepository.save("cuero-" + UUID.randomUUID().toString(), "El objeto Ataduras de flageloalfazaque irrompibles son Muñequeras de Cuero que caen en la mazmorra Ara-Kara, Ciudad de los Ecos", metadataCuero);
		metadataCuero.put("mazmorra", "Rompealbas");
		embeddingRepository.save("cuero-" + UUID.randomUUID().toString(), "El objeto Braciles de asaltante urdidor son Muñequeras de Cuero que caen en la mazmorra Rompealbas", metadataCuero);
		metadataCuero.put("mazmorra", "Tazavesh: Gambito de So'leah");
		embeddingRepository.save("cuero-" + UUID.randomUUID().toString(), "El objeto Brazales de clasificación autónoma son Muñequeras de Cuero que caen en la mazmorra Tazavesh: Gambito de So'leah", metadataCuero);
		metadataCuero.put("mazmorra", "Tazavesh: Gambito de So'leah");
		embeddingRepository.save("cuero-" + UUID.randomUUID().toString(), "El objeto Manijas de mamporros son Guantes de Cuero que caen en la mazmorra Tazavesh: Gambito de So'leah", metadataCuero);
		metadataCuero.put("mazmorra", "Salas de la Expiación");
		embeddingRepository.save("cuero-" + UUID.randomUUID().toString(), "El objeto Guantes de fijación inquietante son Guantes de Cuero que caen en la mazmorra Salas de la Expiación", metadataCuero);
		metadataCuero.put("mazmorra", "Rompealbas");
		embeddingRepository.save("cuero-" + UUID.randomUUID().toString(), "El objeto Cinturón de congregante sombrío es una Cintura de Cuero que cae en la mazmorra Rompealbas", metadataCuero);
		metadataCuero.put("mazmorra", "Salas de la Expiación");
		embeddingRepository.save("cuero-" + UUID.randomUUID().toString(), "El objeto Ceñidor de devoción oscura es una Cintura de Cuero que cae en la mazmorra Salas de la Expiación", metadataCuero);
		metadataCuero.put("mazmorra", "Tazavesh: Gambito de So'leah");
		embeddingRepository.save("cuero-" + UUID.randomUUID().toString(), "El objeto Cinturón de pólvora de Venza es una Cintura de Cuero que cae en la mazmorra Tazavesh: Gambito de So'leah", metadataCuero);
		metadataCuero.put("mazmorra", "Ara-Kara, Ciudad de los Ecos");
		embeddingRepository.save("cuero-" + UUID.randomUUID().toString(), "El objeto Musleras de gasa son Piernas de Cuero que caen en la mazmorra Ara-Kara, Ciudad de los Ecos", metadataCuero);
		metadataCuero.put("mazmorra", "Salas de la Expiación");
		embeddingRepository.save("cuero-" + UUID.randomUUID().toString(), "El objeto Calzas manchadas de babas son Piernas de Cuero que caen en la mazmorra Salas de la Expiación", metadataCuero);
		metadataCuero.put("mazmorra", "Tazavesh: So'leah's Gambit");
		embeddingRepository.save("cuero-" + UUID.randomUUID().toString(), "El objeto Calzones de luz estelar anómala son Piernas de Cuero que caen en la mazmorra Tazavesh: So'leah's Gambit", metadataCuero);
		metadataCuero.put("mazmorra", "Priorato de la Llama Sagrada");
		embeddingRepository.save("cuero-" + UUID.randomUUID().toString(), "El objeto Botas de guardián incondicional son Pies de Cuero que caen en la mazmorra Priorato de la Llama Sagrada", metadataCuero);
		metadataCuero.put("mazmorra", "Tazavesh: So'leah's Gambit");
		embeddingRepository.save("cuero-" + UUID.randomUUID().toString(), "El objeto Botines de reposición titánica son Pies de Cuero que caen en la mazmorra Tazavesh: So'leah's Gambit", metadataCuero);

		// =========================================================================
		// 4. ARMADURA DE MALLA (Mail)
		// =========================================================================
		Map<String, Object> metadataMalla = new HashMap<>();
		metadataMalla.put("tipo", "armadura");
		metadataMalla.put("clase", "malla");
		
		metadataMalla.put("mazmorra", "Ara-Kara, Ciudad de los Ecos");
		embeddingRepository.save("malla-" + UUID.randomUUID().toString(), "El objeto Celada vinculada a la cripta es un Casco de Malla que cae en la mazmorra Ara-Kara, Ciudad de los Ecos", metadataMalla);
		metadataMalla.put("mazmorra", "Salas de la Expiación");
		embeddingRepository.save("malla-" + UUID.randomUUID().toString(), "El objeto Máscara del usurpador de Nathria es un Casco de Malla que cae en la mazmorra Salas de la Expiación", metadataMalla);
		metadataMalla.put("mazmorra", "Tazavesh: So'leah's Gambit");
		embeddingRepository.save("malla-" + UUID.randomUUID().toString(), "El objeto Diadema de terror dracónico es un Casco de Malla que cae en la mazmorra Tazavesh: So'leah's Gambit", metadataMalla);
		metadataMalla.put("mazmorra", "Rompealbas");
		embeddingRepository.save("malla-" + UUID.randomUUID().toString(), "El objeto Manto de plaga sombría son Hombreras de Malla que caen en la mazmorra Rompealbas", metadataMalla);
		metadataMalla.put("mazmorra", "Ecodomo Al'dani");
		embeddingRepository.save("malla-" + UUID.randomUUID().toString(), "El objeto Cubrehombros de morador del páramo consumidos son Hombreras de Malla que caen en la mazmorra Ecodomo Al'dani", metadataMalla);
		metadataMalla.put("mazmorra", "Salas de la Expiación");
		embeddingRepository.save("malla-" + UUID.randomUUID().toString(), "El objeto Manto de semblantes efímeros son Hombreras de Malla que caen en la mazmorra Salas de la Expiación", metadataMalla);
		metadataMalla.put("mazmorra", "Operación: Compuerta");
		embeddingRepository.save("malla-" + UUID.randomUUID().toString(), "El objeto Misiles ocultos de rachacielos son Hombreras de Malla que caen en la mazmorra Operación: Compuerta", metadataMalla);
		metadataMalla.put("mazmorra", "Tazavesh: So'leah's Gambit");
		embeddingRepository.save("malla-" + UUID.randomUUID().toString(), "El objeto Guardahombros adaptados de Hylbrande son Hombreras de Malla que caen en la mazmorra Tazavesh: So'leah's Gambit", metadataMalla);
		metadataMalla.put("mazmorra", "Ecodomo Al'dani");
		embeddingRepository.save("malla-" + UUID.randomUUID().toString(), "El objeto Guerrera de venganza prometida es un Pecho de Malla que cae en la mazmorra Ecodomo Al'dani", metadataMalla);
		metadataMalla.put("mazmorra", "Operación: Compuerta");
		embeddingRepository.save("malla-" + UUID.randomUUID().toString(), "El objeto Abrigo explosivo y chamuscado de Bront es un Pecho de Malla que cae en la mazmorra Operación: Compuerta", metadataMalla);
		metadataMalla.put("mazmorra", "Salas de la Expiación");
		embeddingRepository.save("malla-" + UUID.randomUUID().toString(), "El objeto Camisote de exterminador desbocado es un Pecho de Malla que cae en la mazmorra Salas de la Expiación", metadataMalla);
		metadataMalla.put("mazmorra", "Tazavesh: So'leah's Gambit");
		embeddingRepository.save("malla-" + UUID.randomUUID().toString(), "El objeto Abrazo del vinculador de reliquias es un Pecho de Malla que cae en la mazmorra Tazavesh: So'leah's Gambit", metadataMalla);
		metadataMalla.put("mazmorra", "Tazavesh: Gambito de So'leah");
		embeddingRepository.save("malla-" + UUID.randomUUID().toString(), "El objeto Brazales de ocultación confiscados son Muñequeras de Malla que caen en la mazmorra Tazavesh: Gambito de So'leah", metadataMalla);
		metadataMalla.put("mazmorra", "Priorato de la Llama Sagrada");
		embeddingRepository.save("malla-" + UUID.randomUUID().toString(), "El objeto Ataduras de barón consagradas son Muñequeras de Malla que caen en la mazmorra Priorato de la Llama Sagrada", metadataMalla);
		metadataMalla.put("mazmorra", "Priorato de la Llama Sagrada");
		embeddingRepository.save("malla-" + UUID.randomUUID().toString(), "El objeto Mandiletes de vínculo sagrado son Guantes de Malla que caen en la mazmorra Priorato de la Llama Sagrada", metadataMalla);
		metadataMalla.put("mazmorra", "Ara-Kara, Ciudad de los Ecos");
		embeddingRepository.save("malla-" + UUID.randomUUID().toString(), "El objeto Garras de icor contaminado son Guantes de Malla que caen en la mazmorra Ara-Kara, Ciudad de los Ecos", metadataMalla);
		metadataMalla.put("mazmorra", "Tazavesh: Gambito de So'leah");
		embeddingRepository.save("malla-" + UUID.randomUUID().toString(), "El objeto Aporreadores de mazo son Guantes de Malla que caen en la mazmorra Tazavesh: Gambito de So'leah", metadataMalla);
		metadataMalla.put("mazmorra", "Tazavesh: Gambito de So'leah");
		embeddingRepository.save("malla-" + UUID.randomUUID().toString(), "El objeto Cinturón de catálogo con descuento es una Cintura de Malla que cae en la mazmorra Tazavesh: Gambito de So'leah", metadataMalla);
		metadataMalla.put("mazmorra", "Operación: Compuerta");
		embeddingRepository.save("malla-" + UUID.randomUUID().toString(), "El objeto Generador de poder portátil es una Cintura de Malla que cae en la mazmorra Operación: Compuerta", metadataMalla);
		metadataMalla.put("mazmorra", "Tazavesh: Gambito de So'leah");
		embeddingRepository.save("malla-" + UUID.randomUUID().toString(), "El objeto Calzón de distorsión orbital son Piernas de Malla que caen en la mazmorra Tazavesh: Gambito de So'leah", metadataMalla);
		metadataMalla.put("mazmorra", "Priorato de la Llama Sagrada");
		embeddingRepository.save("malla-" + UUID.randomUUID().toString(), "El objeto Caminapiras divinas son Piernas de Malla que caen en la mazmorra Priorato de la Llama Sagrada", metadataMalla);
		metadataMalla.put("mazmorra", "Rompealbas");
		embeddingRepository.save("malla-" + UUID.randomUUID().toString(), "El objeto Escarpes de aplastarresistencias son Pies de Malla que caen en la mazmorra Rompealbas", metadataMalla);
		metadataMalla.put("mazmorra", "Ecodomo Al'dani");
		embeddingRepository.save("malla-" + UUID.randomUUID().toString(), "El objeto Grebas de la pareja salvaje son Pies de Malla que caen en la mazmorra Ecodomo Al'dani", metadataMalla);
		metadataMalla.put("mazmorra", "Salas de la Expiación");
		embeddingRepository.save("malla-" + UUID.randomUUID().toString(), "El objeto Escarpes de piel de fragmento son Pies de Malla que caen en la mazmorra Salas de la Expiación", metadataMalla);
		metadataMalla.put("mazmorra", "Tazavesh: Gambito de So'leah");
		embeddingRepository.save("malla-" + UUID.randomUUID().toString(), "El objeto Escarpes de tiempo medido son Pies de Malla que caen en la mazmorra Tazavesh: Gambito de So'leah", metadataMalla);

		// =========================================================================
		// 5. ARMADURA DE PLACAS (Plate)
		// =========================================================================
		Map<String, Object> metadataPlaca = new HashMap<>();
		metadataPlaca.put("tipo", "armadura");
		metadataPlaca.put("clase", "placas");

		metadataPlaca.put("mazmorra", "Rompealbas");
		embeddingRepository.save("placas-" + UUID.randomUUID().toString(), "El objeto Gran yelmo de behemoth planeador es un Casco de Placas que cae en la mazmorra Rompealbas", metadataPlaca);
		metadataPlaca.put("mazmorra", "Salas de la Expiación");
		embeddingRepository.save("placas-" + UUID.randomUUID().toString(), "El objeto Casco de comandante del aire es un Casco de Placas que cae en la mazmorra Salas de la Expiación", metadataPlaca);
		metadataPlaca.put("mazmorra", "Tazavesh: So'leah's Gambit");
		embeddingRepository.save("placas-" + UUID.randomUUID().toString(), "El objeto Mirada autoritaria de Colagarfio es un Casco de Placas que cae en la mazmorra Tazavesh: So'leah's Gambit", metadataPlaca);
		metadataPlaca.put("mazmorra", "Ara-Kara, Ciudad de los Ecos");
		embeddingRepository.save("placas-" + UUID.randomUUID().toString(), "El objeto Bufas de monarca de enjambre son Hombreras de Placas que caen en la mazmorra Ara-Kara, Ciudad de los Ecos", metadataPlaca);
		metadataPlaca.put("mazmorra", "Priorato de la Llama Sagrada");
		embeddingRepository.save("placas-" + UUID.randomUUID().toString(), "El objeto Sobrehombros piroforjados de reanimador son Hombreras de Placas que caen en la mazmorra Priorato de la Llama Sagrada", metadataPlaca);
		metadataPlaca.put("mazmorra", "Salas de la Expiación");
		embeddingRepository.save("placas-" + UUID.randomUUID().toString(), "El objeto Espaldares de orgullo desatado son Hombreras de Placas que caen en la mazmorra Salas de la Expiación", metadataPlaca);
		metadataPlaca.put("mazmorra", "Tazavesh: So'leah's Gambit");
		embeddingRepository.save("placas-" + UUID.randomUUID().toString(), "El objeto Bufas de carne de piedra son Hombreras de Placas que caen en la mazmorra Tazavesh: So'leah's Gambit", metadataPlaca);
		metadataPlaca.put("mazmorra", "Ara-Kara, Ciudad de los Ecos");
		embeddingRepository.save("placas-" + UUID.randomUUID().toString(), "El objeto Coselete de sangreseda experimental es un Pecho de Placas que cae en la mazmorra Ara-Kara, Ciudad de los Ecos", metadataPlaca);
		metadataPlaca.put("mazmorra", "Rompealbas");
		embeddingRepository.save("placas-" + UUID.randomUUID().toString(), "El objeto Caparazón de sacerdote oscuro es un Pecho de Placas que cae en la mazmorra Rompealbas", metadataPlaca);
		metadataPlaca.put("mazmorra", "Operación: Compuerta");
		embeddingRepository.save("placas-" + UUID.randomUUID().toString(), "El objeto Placa sumergible de buceafango es un Pecho de Placas que cae en la mazmorra Operación: Compuerta", metadataPlaca);
		metadataPlaca.put("mazmorra", "Salas de la Expiación");
		embeddingRepository.save("placas-" + UUID.randomUUID().toString(), "El objeto Coraza de influencia sobrenatural es un Pecho de Placas que cae en la mazmorra Salas de la Expiación", metadataPlaca);
		metadataPlaca.put("mazmorra", "Tazavesh: So'leah's Gambit");
		embeddingRepository.save("placas-" + UUID.randomUUID().toString(), "El objeto Placas de guerra de explosión de nova es un Pecho de Placas que cae en la mazmorra Tazavesh: So'leah's Gambit", metadataPlaca);
		metadataPlaca.put("mazmorra", "Ecodomo Al'dani");
		embeddingRepository.save("placas-" + UUID.randomUUID().toString(), "El objeto Braciles de acceso al ecodomo son Muñequeras de Placas que caen en la mazmorra Ecodomo Al'dani", metadataPlaca);
		metadataPlaca.put("mazmorra", "Priorato de la Llama Sagrada");
		embeddingRepository.save("placas-" + UUID.randomUUID().toString(), "El objeto Guardabrazos de flamaforja son Muñequeras de Placas que caen en la mazmorra Priorato de la Llama Sagrada", metadataPlaca);
		metadataPlaca.put("mazmorra", "Tazavesh: Gambito de So'leah");
		embeddingRepository.save("placas-" + UUID.randomUUID().toString(), "El objeto Avambrazos de verificación son Muñequeras de Placas que caen en la mazmorra Tazavesh: Gambito de So'leah", metadataPlaca);
		metadataPlaca.put("mazmorra", "Ara-Kara, Ciudad de los Ecos");
		embeddingRepository.save("placas-" + UUID.randomUUID().toString(), "El objeto Guanteletes de devorador son Guantes de Placas que caen en la mazmorra Ara-Kara, Ciudad de los Ecos", metadataPlaca);
		metadataPlaca.put("mazmorra", "Operación: Compuerta");
		embeddingRepository.save("placas-" + UUID.randomUUID().toString(), "El objeto Rascadores de andamio de arrancador son Guantes de Placas que caen en la mazmorra Operación: Compuerta", metadataPlaca);
		metadataPlaca.put("mazmorra", "Tazavesh: Gambito de So'leah");
		embeddingRepository.save("placas-" + UUID.randomUUID().toString(), "El objeto Agarre irrompible de Achillite son Guantes de Placas que caen en la mazmorra Tazavesh: Gambito de So'leah", metadataPlaca);
		metadataPlaca.put("mazmorra", "Rompealbas");
		embeddingRepository.save("placas-" + UUID.randomUUID().toString(), "El objeto Faja de ardides sombríos es una Cintura de Placas que cae en la mazmorra Rompealbas", metadataPlaca);
		metadataPlaca.put("mazmorra", "Operación: Compuerta");
		embeddingRepository.save("placas-" + UUID.randomUUID().toString(), "El objeto Proyector de contratista de Ventura y Cía. es una Cintura de Placas que cae en la mazmorra Operación: Compuerta", metadataPlaca);
		metadataPlaca.put("mazmorra", "Ecodomo Al'dani");
		embeddingRepository.save("placas-" + UUID.randomUUID().toString(), "El objeto Faja de fe absoluta es una Cintura de Placas que cae en la mazmorra Ecodomo Al'dani", metadataPlaca);
		metadataPlaca.put("mazmorra", "Tazavesh: Gambito de So'leah");
		embeddingRepository.save("placas-" + UUID.randomUUID().toString(), "El objeto Cincho improvisado es una Cintura de Placas que cae en la mazmorra Tazavesh: Gambito de So'leah", metadataPlaca);
		metadataPlaca.put("mazmorra", "Salas de la Expiación");
		embeddingRepository.save("placas-" + UUID.randomUUID().toString(), "El objeto Pilares impresionantes de Halkias son Piernas de Placas que caen en la mazmorra Salas de la Expiación", metadataPlaca);
		metadataPlaca.put("mazmorra", "Tazavesh: Gambito de So'leah");
		embeddingRepository.save("placas-" + UUID.randomUUID().toString(), "El objeto Saltadores cuánticos son Piernas de Placas que caen en la mazmorra Tazavesh: Gambito de So'leah", metadataPlaca);
		metadataPlaca.put("mazmorra", "Priorato de la Llama Sagrada");
		embeddingRepository.save("placas-" + UUID.randomUUID().toString(), "El objeto Botos de placas devotas son Pies de Placas que caen en la mazmorra Priorato de la Llama Sagrada", metadataPlaca);
		metadataPlaca.put("mazmorra", "Tazavesh: Gambito de So'leah");
		embeddingRepository.save("placas-" + UUID.randomUUID().toString(), "El objeto Botines resistentes a la intemperie implacables son Pies de Placas que caen en la mazmorra Tazavesh: Gambito de So'leah", metadataPlaca);
	}
	
    public String getContextForChatbot(String userQuery, String filterTipo) {

    	Map<String, String> metadataMap = jacksonService.jsonToMap(filterTipo);
        
		return embeddingRepository.getContextForChatbot(userQuery, metadataMap);
        
    }
}
