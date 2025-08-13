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

    resp.setContentType("text/html;charset=UTF-8");
    resp.getWriter().println("<html><body>");
    resp.getWriter().println("<form action='/process' method='post'>");
    resp.getWriter().println("<input type='text' name='userInput' value='" + escapeHtml(input) + "' />");
    resp.getWriter().println("<button type='submit'>Invia</button>");
    resp.getWriter().println("</form>");
    resp.getWriter().println("<textarea rows='10' cols='50' readonly>" + escapeHtml(input) + "</textarea>");
    resp.getWriter().println("</body></html>");
  }

  // Metodo semplice per evitare problemi XSS con input utente
  private String escapeHtml(String s) {
    return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
      .replace("\"", "&quot;").replace("'", "&#x27;");
  }
}
