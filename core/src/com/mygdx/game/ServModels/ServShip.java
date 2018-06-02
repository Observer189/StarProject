package com.mygdx.game.ServModels;

/**
 * Created by Sash on 02.06.2018.
 */

public class ServShip {
    private String name;
    //private ServFixingPoint[] fixingPoints;
    public ServShip()
    {

    }
    public ServShip(String name/*,ServFixingPoint[] fixingPoints*/)
    {
        this.name=name.toString();
       // this.fixingPoints=fixingPoints;
    }
    public ServShip(ServShip ship)
    {
       this.name=ship.getName();
       //this.fixingPoints=ship.getFixingPoints();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public ServFixingPoint[] getFixingPoints() {
        return fixingPoints;
    }



    public void setFixingPoints(ServFixingPoint[] fixingPoints) {
        this.fixingPoints = fixingPoints;
    }*/
}
