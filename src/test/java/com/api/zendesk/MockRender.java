package com.api.zendesk;

import com.api.zendesk.controller.RenderController;
import com.api.zendesk.model.Ticket;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MockRender {

    @Mock
    RenderController renderController;

    @Test
    public void testRender()
    {

        List<Ticket> temp = new ArrayList<>();
        Mockito.doNothing().when(renderController).renderAll(ArgumentMatchers.isA(List.class));
        renderController.renderAll(temp);
    Mockito.verify(renderController).renderAll(temp);
    }
    @Test
    public void testRenderException()
    {

        List<Ticket> temp = new ArrayList<>();

        Mockito.doThrow(IllegalStateException.class).when(renderController).renderAll(ArgumentMatchers.isA(List.class));

         Assert.assertThrows(IllegalStateException.class,()->renderController.renderAll(temp));

    }
}