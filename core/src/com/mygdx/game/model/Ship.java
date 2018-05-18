package com.mygdx.game.model;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.mygdx.game.view.Battle;

/**
 * Created by Sash on 01.05.2018.
 */

public class Ship extends GameObject {

    int cost;
    private boolean isShipInRedZone;
    private boolean isAlive;
    private float maxHp;
    private float currentHp;
    private float speedX;
    private float speedY;
    private float velocity;
    private float maxSpeed;

    private int rotationPosition;

    private Integer currentExplosionFrame;
    private int explosionCounter;
    TextureRegion explosionRegion;

    private String name;

    Vector2 movementVector;
    FixingPoint[] fixingPoints;

    public Ship(TextureRegion textureRegion, float x, float y, float width, float height, String name, int cost, float maxHp, float velocity, float maxSpeed, FixingPoint[] fixingPoints) {
        super(textureRegion, x, y, width, height);

        this.cost = cost;
        this.maxHp = maxHp;
        currentHp = maxHp;
        this.velocity = velocity;
        this.maxSpeed = maxSpeed;
        this.name = name;
        isShipInRedZone = false;
        isAlive=true;
        rotationPosition=1;
        setRotation(270);
        currentExplosionFrame=0;
        explosionCounter=7;
        movementVector = new Vector2(0, 0);
        this.fixingPoints = fixingPoints;
    }

    public FixingPoint[] getFixingPoints() {
        return fixingPoints;
    }

    public void setFixingPoints(FixingPoint[] fixingPoints) {
        this.fixingPoints = fixingPoints;
    }


    public void draw(SpriteBatch batch,TextureAtlas textureAtlas) {
        if (currentHp>0) {
            for (int i = 0; i < fixingPoints.length; i++) {
                fixingPoints[i].draw(batch);
            }

            super.draw(batch);

        } else
        {
            destructShip(batch,textureAtlas);
        }


    }
    public void destructShip(SpriteBatch batch,TextureAtlas textureAtlas)
    {
        if(currentExplosionFrame<=17) {
            if (explosionCounter == 7) {
                currentExplosionFrame++;
                explosionRegion = textureAtlas.findRegion("Explosion" + currentExplosionFrame.toString());
                explosionCounter = 0;
            }
            batch.begin();
            batch.draw(explosionRegion, getX(), getY(), getWidth(), getHeight());
            batch.end();
            explosionCounter++;
        }
        else
        {
            isAlive=false;
        }
    }



    public void shot()
    {
        for(int i=0;i<fixingPoints.length;i++)
        {
            fixingPoints[i].shot();
        }
    }

    public void move(Ship enemyShip, Map map) {
        if(currentHp>0) {
            speedX = speedX + velocity * movementVector.x;
            speedY = speedY + velocity * movementVector.y;
            //speedX=2;
            //speedY=2;
            if (speedX > maxSpeed) speedX = maxSpeed;
            if (speedX < -maxSpeed) speedX = -maxSpeed;
            if (speedY > maxSpeed) speedY = maxSpeed;
            if (speedY < -maxSpeed) speedY = -maxSpeed;
            bounds.setPosition(bounds.getX() + speedX * Battle.delta, bounds.getY() + speedY * Battle.delta);


            if ((bounds.getX() > 0 + map.getWidth() * 0.85) || (bounds.getX() < 0 + map.getWidth() * 0.15) || (bounds.getY() > 0 + map.getHeight() * 0.85) || (bounds.getY() < 0 + map.getHeight() * 0.15)) {
                isShipInRedZone = true;
            } else isShipInRedZone = false;

            for (int i = 0; i < fixingPoints.length; i++) {
                fixingPoints[i].update(this,enemyShip, map);
            }

            if ((bounds.getX() > 0 + map.getWidth() * 0.95) || (bounds.getX() < 0 + map.getWidth() * 0.05) || (bounds.getY() > 0 + map.getHeight() * 0.95) || (bounds.getY() < 0 + map.getHeight() * 0.05)) {
                currentHp = 0;
            }

        }

    }
    public void setMovementVector(Vector2 movementVector) {

        this.movementVector.x = movementVector.x;

        this.movementVector.y = movementVector.y;
    }



    public void act(Ship enemyShip, Map map, Vector2 vector)
    {
        setMovementVector(vector);
        shot();
        move(enemyShip,map);
    }

    public void setRotationPosition(int rotationPosition) {
        this.rotationPosition = rotationPosition;
        if(rotationPosition==1)
        {
            setRotation(270);
            for (int i = 0; i < fixingPoints.length; i++) {
                fixingPoints[i].weapon.setRotationPosition(1);
            }
        }
        if(rotationPosition==2)
        {
            setRotation(90);
            for (int i = 0; i < fixingPoints.length; i++) {
                fixingPoints[i].weapon.setRotationPosition(2);
            }
        }
    }


    public float getSpeedX() {
        return speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public boolean getIsShipInRedZone() {
        return isShipInRedZone;
    }


    public boolean getIsAlive() {
        return isAlive;
    }

    public float getCurrentHp() {
        return currentHp;
    }

    public Drawable getImg(){
        if (name=="Pulsate"){
           Skin skin=new Skin();
           skin.addRegions(new TextureAtlas(Gdx.files.internal("TexturePack.atlas")));
           return skin.getDrawable("1");
        } else {
            Skin skin=new Skin();
            skin.addRegions(new TextureAtlas(Gdx.files.internal("TexturePack.atlas")));
            return skin.getDrawable(name);

        }



    }



    public float getMaxSpeed(){return  maxSpeed;}
    public float getVelocity(){return velocity;}
    public int getCost(){return cost;}
    public float getMaxHp(){return maxHp;}

    public void setCurrentHp(float currentHp) {
        this.currentHp = currentHp;
    }

    public String getName() {return name; }



    public int getFixingPointsDigit(){return fixingPoints.length;}

    public Vector2 getMovementVector() {
        return movementVector;
    }

    @Override
    public String toString() {

        return getName();
    }
    @Override
    public boolean equals(Object o) {
        return getName().equals(o.toString());
    }
}
