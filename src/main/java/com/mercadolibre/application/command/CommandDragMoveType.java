package com.mercadolibre.application.command;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommandDragMoveType {
    private final int width;
    private final int height;

    // up movement
    private final CommandPoint up;


    // down movement
    private final CommandPoint down;
    private final CommandPoint downLeft;
    private final CommandPoint downRight;

    // movement rigth or left
    private final CommandPoint left;
    private final CommandPoint right;
    public CommandDragMoveType(int width, int height){
        this.width = width;
        this.height = height;

        up = new CommandPoint(-1,0,width,height);

        // down movement
        down = new CommandPoint(1,0,width,height);
        downLeft  = new CommandPoint(1,-1,width,height);
        downRight = new CommandPoint(1,1,width,height);

        // movement rigth or left
        left = new CommandPoint(0,-1,width,height);;
        right = new CommandPoint(0,1,width,height);;

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
