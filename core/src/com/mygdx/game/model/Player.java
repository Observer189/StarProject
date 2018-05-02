package com.mygdx.game.model;

/**
 * Created by Sash on 02.05.2018.
 */

public class Player {
    private String name;
    private int money;
    private Ship ship;
    public Player()
    {

    }
    public Player(String name,int money,Ship ship) {
        this.name = name;
        this.money = money;
        this.ship = ship;
    }
       public void generateName()
    {
        Integer number=(int)(Math.random()*89999999+10000000);
        name="Player"+number.toString();
    }

    public int getMoney() {
        return money;
    }

    public Ship getShip() {
        return ship;
    }

    public String getName() {
        return name;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }
}
