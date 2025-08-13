package com.example.webapp;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class HuggingFaceClient {

  // ✅ Usa variabile d’ambiente invece di scrivere il token nel codice
  private static final String API_TOKEN = System.getenv("HUGGINGFACE_TOKEN");
  private static final String MODEL_URL = "https://api-inference.huggingface.co/models/Helsinki-NLP/opus-mt-en-ru";

  public static String processText(String inputText) {
    if (API_TOKEN == null || API_TOKEN.isBlank()) {
      System.err.println("❌ Errore: variabile d'ambiente HUGGINGFACE_TOKEN non trovata.");
      return "Errore: token non trovato. Verifica variabili d'ambiente su Render.";
    }

    String inputJson = "{\"inputs\": \"" + inputText + "\"}";

    try {
      URL url = new URL(MODEL_URL);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("POST");
      connection.setRequestProperty("Authorization", "Bearer " + API_TOKEN);
      connection.setRequestProperty("Content-Type", "application/json");
      connection.setDoOutput(true);

      try (OutputStream os = connection.getOutputStream()) {
        byte[] input = inputJson.getBytes("utf-8");
        os.write(input, 0, input.length);
      }

      int responseCode = connection.getResponseCode();

      // ✅ Stampa i codici di risposta per debugging
      System.out.println("🌐 Hugging Face HTTP Response Code: " + responseCode);

      if (responseCode == 401) {
        System.err.println("❌ Errore 401: token non autorizzato o invalido.");
        return "Errore: token non valido. Controlla il token Hugging Face.";
      }

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
      System.err.println("❌ Errore generale durante il parsing della risposta:");
      e.printStackTrace();
      return "Errore nel parsing della risposta.";
    }
  }
}
