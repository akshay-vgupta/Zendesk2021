package com.api.zendesk.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class ConnectionController {

    @Autowired
    public RestTemplate restTemplate;

    String response;
    JSONObject obj;

    //Connects to Zendesk API URI via resttemplate
    public String connect(String uri) {
        ResponseEntity<String> responseEntity;
        try {
            responseEntity = restTemplate.getForEntity(uri, String.class);
            obj = new JSONObject(responseEntity.getBody());
        } catch (HttpStatusCodeException ex) {

            System.out.println("STATUS CODE::"+ex.getRawStatusCode());
            System.out.println("TRY AGAIN:"+ex.getLocalizedMessage());

            throw ex;
        }
        catch (RestClientException e)
        {
            System.out.println("\nERROR IS::"+e.getMostSpecificCause());
            throw e;
        }
        return obj.toString();

    }
}