package com.example;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.OutputStream;
import java.net.InetSocketAddress;

public class App {
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
                        <h1 style="background-color: red;">AAAAAAAAAAAAAAAAAAAA</h1>
                        <p>Pagina HTML servita da un'app Maven su Render.com.</p>
                    </body>
                    </html>
                    """;
        exchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
        exchange.sendResponseHeaders(200, html.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(html.getBytes());
        os.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
