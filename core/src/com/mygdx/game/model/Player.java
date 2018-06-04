package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.ServModels.ServPlayer;
import com.mygdx.game.model.Ships.Dashing;
import com.mygdx.game.model.Weapons.Machinegun;

import java.util.ArrayList;

/**
 * Created by Sash on 02.05.2018.
 */

public class Player {
    private String name;
    public Resources resources;
    private Ship currentShip;
    private TextureAtlas textureAtlas;
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
        resources.shipList.add(currentShip);
    }
    public Player(ServPlayer servPlayer)
    {
        name=servPlayer.getName();
        resources=new Resources();
        setMoney(servPlayer.getMoney());
        textureAtlas=new TextureAtlas(Gdx.files.internal("TexturePack.atlas"));
        currentShip=new Dashing(textureAtlas,0,0);

        resources.shipList.add(currentShip);
        resources.weaponList.add(new Machinegun(textureAtlas,0,0));
        for(int i=0;i<currentShip.fixingPoints.length;i++)
        {
            currentShip.fixingPoints[i].setWeapon(resources.weaponList.get(0).weaponByName());
        }
    }
       public void generateName()
    {
        Integer number=(int)(Math.random()*89999999+10000000);
        name="Player"+number.toString();
    }

    public int getMoney() {
        return this.resources.getMoney();
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

    public ArrayList<Ship> getShipList() {
        return resources.getShipList();
    }
    public void setShipList(ArrayList<Ship> shipList) {
        resources.shipList = shipList;
    }

    public class Resources
    {
        private int money;
        public ArrayList<Ship> shipList;
        public ArrayList<Weapon> weaponList;

        public Resources()
        {
            money=50000;
            shipList=new ArrayList<Ship>();
            weaponList=new ArrayList<Weapon>();
        }
        public void ShipAdd(Ship ship){shipList.add(ship);}

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
        //public void addShip(Ship ship){shipList.add(ship);}

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

    }
}
