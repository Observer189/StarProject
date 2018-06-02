package com.mygdx.game.ServModels;

/**
 * Created by Sash on 02.06.2018.
 */

public class ServWeapon {
    private String name;
    public ServWeapon()
    {

    }
    public ServWeapon(String name)
    {
        this.name=name;
    }
    public ServWeapon(ServWeapon weapon)
    {
        this.name=weapon.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
