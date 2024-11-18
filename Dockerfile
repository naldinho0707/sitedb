FROM openjdk:17-jdk-alpine

# vai montar uma máquina (pequena) virtual só para rodar o java
# porque o render não tem suporte nativo para java

# criar uma pasta dentro da máquina virtual
WORKDIR /app

# comando copy vai o arquivo pom.xml e joga para pasta app
#  que esta representada pelo ponto  "."  pasta raiz

# copiar a descrição do projeto
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
COPY src ./src

# da a permisão para porra toda  pode usar o +x  777 é para tudo
RUN chmod 777  mvnw

# quero gerar o war é o maven, executar:
RUN ./mvnw package

RUN ls -l ./target

# cmd executar o java (arquivo war) na pasta target do projeto sitedb: target/sitebd.war
CMD ["java","-jar","target/sitebd-0.0.1-SNAPSHOT.war"]

