/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicenta.pos.electronic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import java.io.StringReader;
import java.io.StringWriter;
/**
 *
 * @author Pablo Porras
 */
public class Response {
    
     public ResponseWinService GetUpLoadFile(UpLoad item) {

        ResponseWinService result = null;
        try {
            // Convert the instance to a JSON string
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = null;
            jsonString = objectMapper.writeValueAsString(item);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/api/GetUpLoadFile"))
                    .POST(HttpRequest.BodyPublishers.ofString(jsonString))
                    .header("Content-type", "application/json")
                    .build();

            CompletableFuture<HttpResponse<String>> responseFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

            String responseBody = responseFuture.thenApply(HttpResponse::body).get(5, TimeUnit.SECONDS);
            result = objectMapper.readValue(responseBody, ResponseWinService.class);
        } catch (InterruptedException | ExecutionException | TimeoutException | JsonProcessingException e) {
            e.printStackTrace();
        }

        return result;
    }
    
      public String formatXml(String xml) throws Exception {
        // Parsear el string XML a un documento XML
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringElementContentWhitespace(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(xml)));

        // Configurar el transformador para la indentación
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        // Transformar el documento XML a un string con indentación
        DOMSource source = new DOMSource(document);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        transformer.transform(source, result);

        return writer.toString();
    }
}
