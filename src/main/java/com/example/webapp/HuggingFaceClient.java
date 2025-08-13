package com.example.webapp;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class HuggingFaceClient {
  private static final String API_TOKEN = "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";
  private static final String MODEL_URL = "https://api-inference.huggingface.co/models/Helsinki-NLP/opus-mt-en-ru";

  public static String processText(String inputText) {
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

      try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
          response.append(line.trim());
        }

        // Parsing corretto del JSON
        JSONArray jsonArray = new JSONArray(response.toString());
        if (jsonArray.length() > 0) {
          JSONObject firstObject = jsonArray.getJSONObject(0);
          return firstObject.getString("translation_text");
        } else {
          return "Nessuna traduzione trovata.";
        }
      }
    }
    catch (IOException e) {
      e.printStackTrace();
      return "Errore nella comunicazione con Hugging Face.";
    }
    catch (Exception e) {
      e.printStackTrace();
      return "Errore nel parsing della risposta.";
    }
  }
}
