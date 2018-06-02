package com.mygdx.game.ServModels;



/**
 * Created by Sash on 02.06.2018.
 */

public class ServFixingPoint {
    private ServWeapon weapon;
    public ServFixingPoint()
    {

    }
    public ServFixingPoint(ServWeapon weapon)
    {
        this.weapon=weapon;
    }
    public ServFixingPoint(ServFixingPoint servFixingPoint)
    {
        weapon=servFixingPoint.getWeapon();
    }

    public ServWeapon getWeapon() {
        return weapon;
    }

    public void setWeapon(ServWeapon weapon) {
        this.weapon = weapon;
    }


}
