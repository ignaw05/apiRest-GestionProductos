# --- Etapa 1: Build (Construcción) ---
# Usamos una imagen oficial de Gradle que incluye JDK 21
FROM gradle:8.5-jdk21 AS build

# Establecemos el directorio de trabajo
WORKDIR /app

# 1. Copiamos solo los archivos de build para cachear las dependencias
# Si estos archivos no cambian, Docker usará la capa cachead
COPY build.gradle settings.gradle .

# 2. Descargamos las dependencias
# Usamos --no-daemon para un entorno de CI/Docker
RUN gradle dependencies --no-daemon

# 3. Copiamos el resto del código fuente
COPY src ./src

# 4. Construimos la aplicación, saltando los tests
RUN gradle build -x test --no-daemon


# --- Etapa 2: Run (Ejecución) ---
# Usamos una imagen de JRE (Runtime) minimalista basada en Ubuntu
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Creamos un usuario 'spring' sin privilegios por seguridad
RUN groupadd -r spring && useradd -r -s /bin/false -g spring spring

# Copiamos el .jar desde la etapa 'build'
# El wildcard (*) se encarga de cualquier versión en el nombre del archivo
COPY --from=build /app/build/libs/*.jar app.jar

# Damos permisos al nuevo usuario
RUN chown spring:spring app.jar

# Cambiamos al usuario sin privilegios
USER spring

# Exponemos el puerto 8080 (esto es documentación, Spring usa 8080 por defecto)
EXPOSE 8080

# --- Comando de ejecución ---
# Render.com establece una variable de entorno $PORT
# Este comando le dice a Spring que escuche en el puerto que Render le asigne.
# Usamos 'sh -c' para que la variable ${PORT} se expanda correctamente.
ENTRYPOINT ["sh", "-c", "java -jar -Dserver.port=${PORT} app.jar"]