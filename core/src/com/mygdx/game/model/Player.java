package com.mygdx.game.model;

import java.util.ArrayList;

/**
 * Created by Sash on 02.05.2018.
 */

public class Player {
    private String name;
    Resources resources;
    private Ship currentShip;
    public Player()
    {

    }
    /*public Player(String name,int money,Ship currentShip) {
        this.name = name;

        this.currentShip = currentShip;
    }*/
    public Player(String name,Ship currentShip) {
        this.name = name;
        this.resources=new Resources();
        this.currentShip = currentShip;
    }
       public void generateName()
    {
        Integer number=(int)(Math.random()*89999999+10000000);
        name="Player"+number.toString();
    }

    public int getMoney() {
        return resources.getMoney();
    }

    public Ship getCurrentShip() {
        return currentShip;
    }

    public String getName() {
        return name;
    }

    public void setMoney(int money) {
        resources.setMoney(money);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrentShip(Ship currentShip) {
        this.currentShip = currentShip;
    }

    private class Resources
    {
        private int money;
        ArrayList<Ship> shipList;
        ArrayList<Weapon> weaponList;

        public Resources()
        {
            money=1000;
            shipList=new ArrayList<Ship>();
            weaponList=new ArrayList<Weapon>();
        }

        public ArrayList<Ship> getShipList() {
            return shipList;
        }

        public ArrayList<Weapon> getWeaponList() {
            return weaponList;
        }

        public void setShipList(ArrayList<Ship> shipList) {
            this.shipList = shipList;
        }

        public void setWeaponList(ArrayList<Weapon> weaponList) {
            this.weaponList = weaponList;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }
    }
}
