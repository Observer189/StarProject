package com.mygdx.game.model;

/**
 * Created by Sash on 01.05.2018.
 */

public class Coord {
    private Float x;
    private Float y;
    private Float rotation;
    public Coord()
    {

    }
    public Coord(Float x,Float y,Float rotation)
    {
        this.x=x;
        this.y=y;
        this.rotation=rotation;
    }
    public Coord(Coord coord)
    {
        x=coord.getX();
        y=coord.getY();
        rotation=coord.getRotation();
    }
    public void setX(float x)
    {
        this.x=x;
    }
    public void setY(float y)
    {
        this.y=y;
    }

    public void setRotation(Float rotation) {
        this.rotation = rotation;
    }

    public Float getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    public Float getRotation() {
        return rotation;
    }
}