# 使用官方 OpenJDK 17 作為基礎映像
FROM openjdk:17

# 設定工作目錄
WORKDIR /app

# 將當前目錄中的所有檔案複製到容器的工作目錄
COPY target/bunnySugar-0.0.1-SNAPSHOT.jar app.jar
COPY src/main/resources/application.properties /app/application.properties
COPY src/main/resources/serviceAccountKey.json /app/src/main/resources/serviceAccountKey.json

# 暴露應用運行的端口
EXPOSE 8087

# 設定環境變數以指定 Spring Boot 的啟動參數
ENV JAVA_OPTS=""

# 入口點，運行 Spring Boot 應用
ENTRYPOINT ["java", "-jar", "app.jar"]

COPY .env .env
