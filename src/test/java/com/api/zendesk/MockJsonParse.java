package com.api.zendesk;


import com.api.zendesk.controller.ConnectionController;
import com.api.zendesk.controller.MainController;
import com.google.gson.Gson;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
public class MockJsonParse {

    @Mock
    MainController mainController;

   @InjectMocks
    ConnectionController connectionController = new ConnectionController();

    @Mock
    Gson gson;
    @Mock
    RestTemplate restTemplate;

    @Test
    public void testJson()
    {
        connectionController.restTemplate = this.restTemplate;
    mainController.connectionController = this.connectionController;
        Mockito.when(gson.fromJson(ArgumentMatchers.anyString(),ArgumentMatchers.any())).thenThrow(JSONException.class);
     //   Mockito.doThrow(JSONException.class).when(mainController).getSingleTicket(ArgumentMatchers.anyString(),ArgumentMatchers.anyInt());
       Mockito.verify(mainController).getSingleTicket("test",1);
       //  Assert.assertThrows(JSONException.class,() -> mainController.getSingleTicket("test",1));
    }
}