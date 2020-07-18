package com.mercadolibre.application.command;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandPointTest {


    @Test
    public void setX() {
        CommandPoint commandPoint =  new CommandPoint();
        commandPoint.setX(1);
        commandPoint.setY(1);
        commandPoint.setMaxHeight(1);
        commandPoint.setMaxWidth(1);
        Assert.assertEquals(
                commandPoint.getX()+
                commandPoint.getY()+
                commandPoint.getMaxHeight()+
                commandPoint.getMaxWidth()
                ,4
                );

    }
}