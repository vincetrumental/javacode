 // My microservice !!!!
/* Copyright © 2016 Oracle and/or its affiliates. All rights reserved. */
package com.example.rest;
 
import java.util.Optional;
import java.util.Properties;
import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
 
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
 
@SpringBootApplication
public class App {
 
    // Get PORT and HOST from Environment or set default
    public static final Optional<String> host;
    public static final Optional<String> port;
    public static final Properties myProps = new Properties();
 
    static {
        host = Optional.ofNullable(System.getenv("HOSTNAME"));
        port = Optional.ofNullable(System.getenv("PORT"));
    }
 
    /**
     * Start the server.
     */
    public static void main(String[] args) {
        // Set properties
        myProps.setProperty("server.address", host.orElse("localhost"));
        myProps.setProperty("server.port", port.orElse("8080"));
 
        SpringApplication app = new SpringApplication(App.class);
        app.setDefaultProperties(myProps);
        app.run(args);
 
        System.out.println("Server started");
 
        //runGetRequest();
        for (int i=0; i<10; i++){
          runGetRequest(i);
        } 
    }
 
    /**
     * Performs a simple GET request and prints the result to the log.
     */
    private static void runGetRequest(int ycoord) {
 
    	// sample URL
        //String url = "http://129.157.179.180:3000/shield/33/45/yellow/vincetrumental";
     String url = "http://129.157.179.180:3000/fighters/45/"+ycoord+"/yellow/vincetrumental";
     //http://129.157.179.180:3000/fighters/45/y-coordinate_goes_here/Your_squad_color_goes_here/Your_microservice_name_goes_here   
     CloseableHttpResponse response = null;
 
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            response = httpclient.execute(httpGet);
            String content = EntityUtils.toString(response.getEntity());
            System.out.println("Server response: " + content);
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException ie) {
                System.out.println(ie);
            }
        }    
    }
}
