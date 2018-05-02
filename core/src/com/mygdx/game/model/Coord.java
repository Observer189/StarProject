package com.mygdx.game.model;

/**
 * Created by Sash on 01.05.2018.
 */

public class Coord {
    private Integer x;
    private Integer y;
    public Coord()
    {

    }
    public Coord(Integer x,Integer y)
    {
        this.x=x;
        this.y=y;
    }
    public void setX(int x)
    {
        this.x=x;
    }
    public void setY(int y)
    {
        this.y=y;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }
}
