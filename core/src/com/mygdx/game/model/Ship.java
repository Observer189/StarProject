package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.mygdx.game.utils.TextManager;
import com.mygdx.game.utils.Vector2D;
import com.mygdx.game.view.Battle;

import static java.lang.Float.NaN;

/**
 * Created by Sash on 01.05.2018.
 */

public class Ship extends GameObject {

    int cost;
    private boolean isShipInRedZone;
    private int maxHp;
    private int currentHp;
    private float speedX;
    private float speedY;
    private float velocity;
    private float maxSpeed;
    private String name;
    Vector2 movementVector;
    FixingPoint[] fixingPoints;

    public Ship(TextureRegion textureRegion, float x, float y, float width, float height, String name, int cost, int maxHp, float velocity, float maxSpeed, FixingPoint[] fixingPoints) {
        super(textureRegion, x, y, width, height);

        this.cost = cost;
        this.maxHp = maxHp;
        currentHp = maxHp;
        this.velocity = velocity;
        this.maxSpeed = maxSpeed;
        this.name = name;
        isShipInRedZone = false;
        movementVector = new Vector2(0, 0);
        this.fixingPoints = fixingPoints;
    }

    public FixingPoint[] getFixingPoints() {
        return fixingPoints;
    }

    public void setFixingPoints(FixingPoint[] fixingPoints) {
        this.fixingPoints = fixingPoints;
    }

    @Override
    public void draw(SpriteBatch batch) {
        for(int i=0;i<fixingPoints.length;i++)
        {
            fixingPoints[i].draw(batch);
        }
        super.draw(batch);



    }
    public void shot()
    {
        for(int i=0;i<fixingPoints.length;i++)
        {
            fixingPoints[i].shot();
        }
    }

    public void move(Map map) {
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

        for(int i=0;i<fixingPoints.length;i++)
        {
            fixingPoints[i].update(this);
        }

    }

    public void setMovementVector(Vector2 movementVector) {

        this.movementVector.x = movementVector.x;

        this.movementVector.y = movementVector.y;
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
    public int getMaxHp(){return maxHp;}

    public String getName() {return name; }
    public int getFixingPointsDigit(){return fixingPoints.length;}
}
