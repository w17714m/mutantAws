package com.mercadolibre.application.command;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommandPoint implements Cloneable{
    private int x;
    private int y;
    private int maxWidth;
    private int maxHeight;

    public CommandPoint(int x , int y){
        this.x=x;
        this.y=y;
    }

    public CommandPoint drag(CommandPoint point) {
        if (!canDrag(point)) return this;
        this.x += point.x;
        this.y += point.y;
        return this;
    }

    public boolean canDrag(CommandPoint point) {
        return this.x + point.x >= 0 &&
                this.y + point.y >= 0 &&
                this.x + point.x <= point.getMaxWidth()&&
                this.y + point.y <= point.getMaxHeight();
    }

    @SneakyThrows
    public Object clone(){
        Object obj=null;
        obj=super.clone();
        return obj;
    }

}
