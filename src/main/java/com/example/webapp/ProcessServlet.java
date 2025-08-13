package com.example.webapp;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ProcessServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String input = req.getParameter("userInput");
    if (input == null) input = "";

    // Chiamata al modello ML tramite HuggingFace API
    String output = HuggingFaceClient.processText(input);

    resp.setContentType("text/html;charset=UTF-8");
    resp.getWriter().println("<html><body>");
    resp.getWriter().println("<h2>Traduzione Inglese ➝ Russo (HuggingFace)</h2>");
    resp.getWriter().println("<form action='/process' method='post'>");
    resp.getWriter().println("<input type='text' name='userInput' value='" + escapeHtml(input) + "' style='width:300px;' />");
    resp.getWriter().println("<button type='submit'>Invia</button>");
    resp.getWriter().println("</form>");
    resp.getWriter().println("<p>Risultato:</p>");
    resp.getWriter().println("<textarea rows='10' cols='50' readonly>" + escapeHtml(output) + "</textarea>");
    resp.getWriter().println("</body></html>");
  }

  // Metodo per prevenire XSS
  private String escapeHtml(String s) {
    return s.replace("&", "&amp;")
      .replace("<", "&lt;")
      .replace(">", "&gt;")
      .replace("\"", "&quot;")
      .replace("'", "&#x27;");
  }

  // GET per mostrare il form iniziale
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("text/html;charset=UTF-8");
    resp.getWriter().println("<html><body>");
    resp.getWriter().println("<h2>Traduzione Inglese ➝ Russo (HuggingFace)</h2>");
    resp.getWriter().println("<form action='/process' method='post'>");
    resp.getWriter().println("<input type='text' name='userInput' style='width:300px;' />");
    resp.getWriter().println("<button type='submit'>Invia</button>");
    resp.getWriter().println("</form>");
    resp.getWriter().println("</body></html>");
  }
}
