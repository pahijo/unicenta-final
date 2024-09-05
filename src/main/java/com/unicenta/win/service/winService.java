/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicenta.win.service;

import com.unicenta.win.service.winServicesModel.UpLoad;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unicenta.win.service.ResponseTrasactionID;
/**
 *
 * @author Pablo Porras
 */
public class winService {

    public static ResponseTrasactionID GetUpLoadFile(UpLoad item) {
        ResponseTrasactionID result = null;
        // Convert the instance to a JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(item);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/GetUpLoadFile"))
                .POST(HttpRequest.BodyPublishers.ofString(jsonString))
                .header("Content-type", "application/json")
                .build();
        CompletableFuture<HttpResponse<String>> responseFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        try {
            String responseBody = responseFuture.thenApply(HttpResponse::body).get(5, TimeUnit.SECONDS);
            result = objectMapper.readValue(responseBody, ResponseTrasactionID.class);
        } catch (InterruptedException | ExecutionException | TimeoutException | JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static ResponseDocumentBase GetdocumentStatusFileRequest(TransactionDto item) {
        ResponseDocumentBase result = null;
        // Convert the instance to a JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(item);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/GetdocumentStatus"))
                .POST(HttpRequest.BodyPublishers.ofString(jsonString))
                .header("Content-type", "application/json")
                .build();
        CompletableFuture<HttpResponse<String>> responseFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        try {
            String responseBody = responseFuture.thenApply(HttpResponse::body).get(5, TimeUnit.SECONDS);
            result = objectMapper.readValue(responseBody, ResponseDocumentBase.class);
        } catch (InterruptedException | ExecutionException | TimeoutException | JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
