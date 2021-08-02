package com.api.zendesk;


import com.api.zendesk.controller.ConnectionController;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
public class MockRest {


    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ConnectionController connectionController = new ConnectionController();

    @Test
    public void testWorking() {

        JSONObject obj = new JSONObject();
        obj.put("id", 1);
        ResponseEntity<String> serviceResponse =
                new ResponseEntity<String>(obj.toString(), HttpStatus.ACCEPTED);
        Mockito.when(restTemplate.getForEntity("zz", String.class))
                .thenReturn(serviceResponse);


        String response =
                connectionController.connect("zz");

        System.out.println("\nTest 1 Rest Template is working");
                Assert.assertEquals(response, serviceResponse.getBody());
    }

    @Test
    public void testError() {

        JSONObject obj = new JSONObject();
        obj.put("id", 1);

        HttpClientErrorException httpClientErrorException = new HttpClientErrorException(HttpStatus.NOT_FOUND);

        ResponseEntity<String> serviceResponse =
                new ResponseEntity<String>(obj.toString(), HttpStatus.ACCEPTED);
        Mockito.when(restTemplate.getForEntity("zz", String.class))
                .thenThrow(httpClientErrorException);

        System.out.println("\nTest 2 Rest Template with exception :::");
        Assert.assertThrows(HttpClientErrorException.class, () -> connectionController.connect("zz"));
    }

    @Test
    public void testBadGateway() {

        JSONObject obj = new JSONObject();
        obj.put("id", 1);

        HttpServerErrorException httpClientErrorException = new HttpServerErrorException(HttpStatus.BAD_GATEWAY);

        ResponseEntity<String> serviceResponse =
                new ResponseEntity<String>(obj.toString(), HttpStatus.ACCEPTED);
        Mockito.when(restTemplate.getForEntity("zz", String.class))
                .thenThrow(httpClientErrorException);

        System.out.println("\nTest 3 Rest Template with exception :::");
        Assert.assertThrows(HttpServerErrorException.class, () -> connectionController.connect("zz"));
    }

}