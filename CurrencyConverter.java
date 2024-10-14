package com.jeffersonmontoya;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;

public class CurrencyConverter {
    private static final String API_KEY = "53ab5c7dd72f35e1b9b82692";
    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/";

    public double convertir(String paisOrigen, String paisDestino, double cantidad) {
        String monedaOrigen = paisOrigen.split(" - ")[0];
        String monedaDestino = paisDestino.split(" - ")[0];

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL + monedaOrigen))
                    .header("Authorization", "Bearer " + API_KEY)
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JSONObject jsonObject = new JSONObject(response.body());
            double tasaCambio = jsonObject.getJSONObject("rates").getDouble(monedaDestino);

            return cantidad * tasaCambio;
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(CurrencyConverter.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
}