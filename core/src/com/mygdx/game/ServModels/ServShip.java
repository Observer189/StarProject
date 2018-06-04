package com.mygdx.game.ServModels;

import com.mygdx.game.model.Weapon;

/**
 * Created by Sash on 02.06.2018.
 */

public class ServShip {
    private String name;
    //private String weaponName;
    //private ServFixingPoint[] fixingPoints;
    public ServShip()
    {

    }
    public ServShip(String name/*,String weaponName,ServFixingPoint[] fixingPoints*/)
    {
        this.name=name.toString();
        //this.weaponName=weaponName;
        // this.fixingPoints=fixingPoints;
    }
    public ServShip(ServShip ship)
    {
        this.name=ship.getName();
        //this.weaponName=ship.getWeaponName();
        //this.fixingPoints=ship.getFixingPoints();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }*/
    /*public ServFixingPoint[] getFixingPoints() {
        return fixingPoints;
    }



    public void setFixingPoints(ServFixingPoint[] fixingPoints) {
        this.fixingPoints = fixingPoints;
    }*/


    public String toString() {
        return name;
    }
}

