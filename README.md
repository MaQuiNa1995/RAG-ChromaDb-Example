# Aplicacion para la muestra de la tecnica RAG (Retrieval augmented generation)

El codigo está con javadoc explicado y tengo otro proyecto que te explica las interacciones con ollama y mistral: https://github.com/MaQuiNa1995/simple-chat-ia

Nos vamos a centrar en el cometido del proyecto que es enseñar a usar la técnica RAG (Generación Aumentada por Recuperación o Retrieval Augmented Generation)

Basicamente trata de construir un conexto para que la respuesta del LLM sea lo mas precisa posible usando nuestra fuente de conocimientos
esto quiere decir que si por ejemplo el lenguaje a sido entrenado con datos de 2010 por poner un ejemplo y le preguntamos por cosas recientes no va a tener la respuesta o al menos no la va a responder bien

para explicar esto vamos a poner un ejemeplo que basicamente es el mismo que la aplicacion que tenemos en este repositorio

En el juego world of warcraft tenemos por temporada 8 mazmorras con diferentes objetos que caen al derrotar a los jefes de cada mazmorra como esto está en continuo cambio el LLM que usamos puede que no tenga los datos
precisos de las mazmorras u objetos que caen en ellas ahi es cuando entra el RAG

Para que responda el LLM con precision le debemos construir un contexto para que el LLM a partir de el construya la respuesta del usuario con datos que nosotros hemos preparado en una base de datos (normalmente vectorial aunque tambien podrías tenerlo en una lista, csv o cualquier formato en realidad)

Los componentes minimos necesarios para hacer esta tecnica son 3
- LLM en nuestro caso un Ollama con el modelo `mistral:latest`
- Base de datos vectorial en nuestro caso ChromaDB con el modelo `all-minilm`
- Aplicacion java que actua de orquestador sobre los otros componentes

Pongamos una imagen del flujo 

![alt text](https://github.com/MaQuiNa1995/RAG-ChromaDb-Example/blob/master/doc/diagrama.svg?raw=true)

En la imagen como podemos ver:

1- Usuario pregunta a la aplicacion java a traves de un prompt creado con el del usuario: `En que mazmorras me puede caer un collar`

```
Eres un sistema de extracción de metadatos para un videojuego.
Tu tarea es analizar la consulta del usuario y extraer las siguientes categorías de información:
1. tipo (string): Tipo general de equipo (collar, armadura, anillo, capa)
2. clase (string): La clase de armadura (Tela, Cuero, Malla, Placas).
3. mazmorra (string): Nombre de la mazmorra.

Si no encuentras una categoría, usa "null".
La salida debe ser un objeto JSON VÁLIDO.

Consulta del usuario: En que mazmorras me puede caer un collar
```

Como nota adicional lo facil y rapido es usar el LLM para que nos saque la informacion pero tambien podemos usar librerias de NLP (Natural language processing) para sacar la informacion clave pero requiere de un entrenamiento de un modelo propio 

2- La aplicacion java usa el LLM para sacar la informacion clave:

3- El LLM nos devuelve un json con la informacion clave del usuario por la que podemos luego buscar en la base de datos

```
{
  "tipo": "collar",
  "clase": "null",
  "mazmorra": "null"
}
```

4- Hacemos la consulta a chromaDB al ser una base de datos vectorial cuando nosotros hacemos una consulta esta lo busca por similitud (Esto está explicado en el codigo) a mas exacta la busqueda mayor coeficiente (0-1) y tambien usando los filtros que creamos a traves del json que comentamos antes
5- ChromaDB nos devuelve los documentos (registros) que ha encontrado con los filtros dados y creamos la String contexto

```
El objeto Colgante de impenitente es un Collar que cae en la mazmorra Salas de la Expiación
---
El objeto Recuerdo manchado de sangre es un Collar que cae en la mazmorra Priorato de la Llama Sagrada
---
El objeto Amplificador bellamente grabado es un Collar que cae en la mazmorra Tazavesh: Gambito de So'leah
---
El objeto Megamedallón de M.A.M.A. es un Collar que cae en la mazmorra Operación: Compuerta
---
El objeto Cabujón del vuelo infinito es un Collar que cae en la mazmorra Tazavesh: So'leah's Gambit
```


6- Creamos el prompt final con los datos que recuperamos de chroma 

```
---
Contexto:
---
El objeto Colgante de impenitente es un Collar que cae en la mazmorra Salas de la Expiación
---
El objeto Recuerdo manchado de sangre es un Collar que cae en la mazmorra Priorato de la Llama Sagrada
---
El objeto Amplificador bellamente grabado es un Collar que cae en la mazmorra Tazavesh: Gambito de So'leah
---
El objeto Megamedallón de M.A.M.A. es un Collar que cae en la mazmorra Operación: Compuerta
---
El objeto Cabujón del vuelo infinito es un Collar que cae en la mazmorra Tazavesh: So'leah's Gambit
---
Pregunta del Usuario:
En que mazmorras me puede caer un collar
---
Responde en formato lista sin nada mas añadido solo contesta con el contexto que te he pasado arriba, no busques en tu conocimiento propio

NUNCA devuelvas la consulta completa del usuario como un valor de campo de mazmorra

La salida debe ser una lista vertical
```

7- Devolvemos la respuesta

```
Mazmorras donde puede caer un collar:
- Colgante de impenitente: Salas de la Expiación
- Recuerdo manchado de sangre: Priorato de la Llama Sagrada
- Amplificador bellamente grabado: Tazavesh: Gambito de So'leah
- Megamedallón de M.A.M.A.: Operación: Compuerta
- Cabujón del vuelo infinito: Tazavesh: So'leah's Gambit
```

Ahora explicaremos como montar los contenedores docker de ollama y de chromaDB

El docker compose le tienes en la raiz del proyecto pero aqui te le doy:

```
version: '3.8'

services:
  chroma:
    container_name: chromadb-server
    image: 'chromadb/chroma:latest'
    ports:
      - '8000:8000'

  ollama:
    # Use the official latest image
    image: ollama/ollama:latest
    # Define the container name for easy management
    container_name: ollama
    # Set the restart policy: container restarts automatically unless manually stopped
    restart: unless-stopped
    environment:
      # Configure allowed origins for accessing the Ollama API. '*' allows all origins.
      - OLLAMA_ORIGINS=*
    ports:
      # Map the host machine's port 11434 to the container's port 11434
      - "11434:11434"
    volumes:
      # Mount the host machine's ~/.ollama/data directory to the container's /root/.ollama directory
      # This is where Ollama stores model files and metadata, enabling persistent storage.
      - ~/.ollama/data:/root/.ollama
    deploy:
      # --- Resource Deployment Configuration Section ---
      resources:
        reservations:
          # GPU resource reservation configuration (Enable only if needed and an NVIDIA GPU is configured)
          devices:
            # Specify using the nvidia driver
            - driver: nvidia
              # Use all detected NVIDIA GPUs
              count: all
              # Request GPU capabilities
              capabilities: [gpu]
      # --- End of GPU Configuration Section ---
```

Para iniciar los contenedores: `docker-compose up`

Cuando tengamos corriendo los contenedores tendremos que ejecutar ciertos comandos dentro de cada uno:

Empezamos por Ollama

tendremos que descargar el modelo que queramos usar en nuestro caso mistral:latest

`ollama pull mistral:latest`

El modelo que elijas debe ser el mismo que pongas en la aplicación en el application properties

Ahora pasamos a ChromaDB

tendremos que tener el curl desde consola local y hacer una peticion al contenedor para que cree la base de datos

`curl -X POST "http://localhost:8000/api/v2/tenants/default/databases/default/collections" -H "Content-Type: application/json" -d "{\"name\": \"prueba\", \"get_or_create\": true}"`

Con ese comando le decimos a chromaDB que cree la coleccion en base de datos `prueba`

Nos debería devolver algo parecido a esto:

{"id":"f8cb138d-8eb7-4cac-a918-79460592540a","name":"prueba","configuration_json":{"hnsw":{"space":"l2","ef_construction":100,"ef_search":100,"max_neighbors":16,"resize_factor":1.2,"sync_threshold":1000},"spann":null,"embedding_function":null},"schema":{"defaults":{"string":{"fts_index":{"enabled":false,"config":{}},"string_inverted_index":{"enabled":true,"config":{}}},"float_list":{"vector_index":{"enabled":false,"config":{"space":"l2","source_key":"#document","hnsw":{"ef_construction":100,"max_neighbors":16,"ef_search":100,"num_threads":16,"batch_size":100,"sync_threshold":1000,"resize_factor":1.2}}}},"sparse_vector":{"sparse_vector_index":{"enabled":false,"config":{"embedding_function":{"type":"unknown"},"bm25":false}}},"int":{"int_inverted_index":{"enabled":true,"config":{}}},"float":{"float_inverted_index":{"enabled":true,"config":{}}},"bool":{"bool_inverted_index":{"enabled":true,"config":{}}}},"keys":{"#document":{"string":{"fts_index":{"enabled":true,"config":{}},"string_inverted_index":{"enabled":false,"config":{}}}},"#embedding":{"float_list":{"vector_index":{"enabled":true,"config":{"space":"l2","source_key":"#document","hnsw":{"ef_construction":100,"max_neighbors":16,"ef_search":100,"num_threads":16,"batch_size":100,"sync_threshold":1000,"resize_factor":1.2}}}}}}},"metadata":null,"dimension":null,"tenant":"default","database":"default","log_position":0,"version":0}

Si por algun casual quieres borrarla:

`curl -X DELETE "http://localhost:8000/api/v2/tenants/default/databases/default/collections/prueba"`

Ahora en el main cuando queramos probar la aplicacion no te olvides de descomentar la linea 

`//embeddingService.createEmbeddings();`

y de comentarla en proximas ejecuciones ya que este metodo meterá toda la info en chromaDB
















