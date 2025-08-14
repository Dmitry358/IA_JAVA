package com.example.webapp;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class HuggingFaceClient {

  // Assicurati di usare una variabile d’ambiente in produzione
  private static final String API_TOKEN = System.getenv("HUGGINGFACE_TOKEN");
  private static final String MODEL_URL = "https://api-inference.huggingface.co/models/Helsinki-NLP/opus-mt-en-ru";

  public static String processText(String inputText) {
    if (API_TOKEN == null || API_TOKEN.isBlank()) {
      System.err.println("❌ Errore: token Hugging Face mancante.");
      return "Errore: token API non configurato.";
    }

    if (inputText == null || inputText.trim().isEmpty()) {
      System.err.println("❌ Testo di input vuoto o nullo.");
      return "Errore: nessun testo da tradurre.";
    }

    System.out.println("📥 Testo ricevuto da tradurre: " + inputText);

    JSONObject jsonBody = new JSONObject();
    jsonBody.put("inputs", inputText);
    String inputJson = jsonBody.toString();

    try {
      URL url = new URL(MODEL_URL);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("POST");
      connection.setRequestProperty("Authorization", "Bearer " + API_TOKEN);
      connection.setRequestProperty("Content-Type", "application/json");
      connection.setRequestProperty("Accept", "application/json");
      connection.setDoOutput(true);

      // Invia il JSON nel body della richiesta
      try (OutputStream os = connection.getOutputStream()) {
        byte[] input = inputJson.getBytes("utf-8");
        os.write(input, 0, input.length);
      }

      int responseCode = connection.getResponseCode();
      System.out.println("🌐 Hugging Face HTTP Response Code: " + responseCode);

      if (responseCode >= 400) {
        // Legge lo stream di errore per capire cosa è andato storto
        try (BufferedReader errorReader = new BufferedReader(
          new InputStreamReader(connection.getErrorStream(), "utf-8"))) {
          String line;
          StringBuilder errorMsg = new StringBuilder();
          while ((line = errorReader.readLine()) != null) {
            errorMsg.append(line.trim());
          }
          System.err.println("📛 Risposta errore Hugging Face: " + errorMsg);
        }
        return "Errore nella richiesta a Hugging Face (codice " + responseCode + ")";
      }

      // Legge la risposta con la traduzione
      try (BufferedReader br = new BufferedReader(
        new InputStreamReader(connection.getInputStream(), "utf-8"))) {
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
          response.append(line.trim());
        }

        JSONArray jsonArray = new JSONArray(response.toString());
        if (jsonArray.length() > 0) {
          JSONObject firstObject = jsonArray.getJSONObject(0);
          return firstObject.getString("translation_text");
        } else {
          return "Nessuna traduzione trovata.";
        }
      }

    } catch (IOException e) {
      System.err.println("❌ Errore IO durante la comunicazione con Hugging Face:");
      e.printStackTrace();
      return "Errore di comunicazione con Hugging Face: " + e.getMessage();
    } catch (Exception e) {
      System.err.println("❌ Errore generale:");
      e.printStackTrace();
      return "Errore generico durante la traduzione.";
    }
  }
}
