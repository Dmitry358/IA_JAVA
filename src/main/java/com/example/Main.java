/*import org.apache.catalina.Context;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;

public class Main {
  public static void main(String[] args) throws Exception {
    int port = 8080;

    Tomcat tomcat = new Tomcat();
    tomcat.setPort(port);

    // Creo temp directory per la webapp con struttura corretta
    Path tempWebappDir = Files.createTempDirectory("webapp");
    File webappDir = tempWebappDir.toFile();

    // Creo la cartella WEB-INF (anche vuota)
    File webInf = new File(webappDir, "WEB-INF");
    webInf.mkdir();

    // Copio index.html da resources nella cartella root della webapp
    try (var in = Main.class.getClassLoader().getResourceAsStream("index.html")) {
      if (in == null) {
        System.err.println("ERRORE: index.html non trovato in resources!");
        System.exit(1);
      }
      File index = new File(webappDir, "index.html");
      Files.copy(in, index.toPath(), StandardCopyOption.REPLACE_EXISTING);
      System.out.println("index.html copiato in: " + index.getAbsolutePath());
    }

    Context ctx = tomcat.addWebapp("", webappDir.getAbsolutePath());

    // REGISTRA LA SERVLET passando il nome della classe (non un’istanza!)
    Wrapper wrapper = ctx.addServlet("EchoServlet", "com.example.EchoServlet");
    wrapper.addMapping("/echo");
    wrapper.setLoadOnStartup(1);

    tomcat.start();
    System.out.println("Server running at http://localhost:" + port);
    tomcat.getServer().await();
  }
}
*/