package com.example;
/*
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.OutputStream;
import java.net.InetSocketAddress;

//public class App {
public class zTMP {
  public static void main(String[] args) throws Exception {
    int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));
    HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
    server.createContext("/", new HtmlHandler());
    server.setExecutor(null);
    server.start();
    System.out.println("Server in ascolto su porta " + port);
  }

  static class HtmlHandler implements HttpHandler {
    public void handle(HttpExchange exchange) {
      try {
        String html = """
          
                    <!DOCTYPE html>
                    <html>
                    <head><title>Render + Java</title></head>
                    <body>
                        <h1 style=\"background-color: red;\">AAAAAAAAAAAAAAAAAAAA</h1>
                        <p>Pagina HTML servita da unapp Maven su Render.com.</p>
                    </body>
                    </html>
                    """;
        exchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
        exchange.sendResponseHeaders(200, html.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(html.getBytes());
        os.close();
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
*/
/*
*
* <project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                             http://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>IA_JAVA</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>  <!-- o 11 o 21 in base al JDK -->
        <maven.compiler.target>17</maven.compiler.target>
    </properties>

    <build>
        <plugins>

            <!-- Compiler plugin -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.11.0</version>
                <configuration>
                    <source>${maven.compiler.source}</source>
                    <target>${maven.compiler.target}</target>
                </configuration>
            </plugin>

            <!-- Jar plugin per rendere eseguibile il jar -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <version>3.3.0</version>
                <configuration>
                    <archive>
                        <manifest>
                            <mainClass>com.example.App</mainClass> <!-- Cambia se usi un altro package -->
                        </manifest>
                    </archive>
                </configuration>
            </plugin>

        </plugins>
    </build>

</project>
*/
