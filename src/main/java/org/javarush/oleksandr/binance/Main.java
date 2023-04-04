package org.javarush.oleksandr.binance;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder()
                .executor(Executors.newFixedThreadPool(1))
                .build();

        HttpRequest.Builder httpRequestBuilder = HttpRequest.newBuilder()
                .uri(URI.create("https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT"))
                .GET();

        HttpRequest httpRequest = httpRequestBuilder.build();
        HttpRequest httpRequest2 = httpRequestBuilder.GET().build();

        System.out.println("OK");

        CompletableFuture<HttpResponse<String>> future = httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString());

        future.thenApply(HttpResponse::body).thenAccept(System.out::println);

        System.out.println("OK");

        future.thenApply(HttpResponse::body).thenAccept(System.out::println).join();




//        JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
//
//        double price = jsonObject.get("price").getAsDouble();
//        String symbol = jsonObject.get("symbol").getAsString();
//
//        System.out.println(symbol);
//        System.out.println(price);
//
     }
}