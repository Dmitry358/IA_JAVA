package com.example;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;

@WebServlet("/echo")
public class EchoServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws IOException {
    req.setCharacterEncoding("UTF-8");
    String input = req.getParameter("userInput");

    resp.setContentType("text/plain");
    resp.setCharacterEncoding("UTF-8");
    resp.getWriter().print("Hai scritto: " + input);
  }
}
