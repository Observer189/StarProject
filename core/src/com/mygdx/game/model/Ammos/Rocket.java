package com.mygdx.game.model.Ammos;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.Ammo;
import com.mygdx.game.model.Ship;

/**
 * Created by Sash on 30.05.2018.
 */

public class Rocket extends Ammo {
    public static int counter=0;
    float angle;
    float internalArcLength;
    float externalArcLength;
    public Rocket(TextureRegion textureRegion, float x, float y, float rotation) {
        super(textureRegion, x, y, 2f, 5f, 2f, 75,500,rotation);
        counter++;
        angle =0;
        internalArcLength=0;
        externalArcLength=0;
    }

    @Override
    public void move(Ship enemyShip) {
        super.move(enemyShip);

        /*if(enemyShip.getCenterX()>getCenterX()&&enemyShip.getCenterY()>getCenterY())
        {
            angle=0;

        }
        else if (enemyShip.getCenterX()>getCenterX()&&enemyShip.getCenterY()<getCenterY())
        {
            angle=90;
        }
        else if (enemyShip.getCenterX()<getCenterX()&&enemyShip.getCenterY()<getCenterY())
        {
            angle=180;
        }
        else if (enemyShip.getCenterX()<getCenterX()&&enemyShip.getCenterY()<getCenterY())
        {
            angle=270;
        }*/
        angle=(float)(Math.toDegrees(Math.atan2(getCenterY()-enemyShip.getCenterY(),getCenterX()-enemyShip.getCenterX())));
        angle = (angle < 0) ? angle + 360 : angle;
        angle = (angle < 270) ? angle + 90 : angle+90-360;

        externalArcLength=Math.abs(angle-getRotation());
        internalArcLength=360-externalArcLength;
        if(angle>getRotation()) {
            if (internalArcLength > externalArcLength) {
                if (getRotation() == 360)
                    setRotation(0);
                setRotation(getRotation() + 1f);
                System.out.println("Turn +");
            } else if (internalArcLength < externalArcLength) {
                if (getRotation() == 0)
                    setRotation(360);
                setRotation(getRotation() - 1f);
                System.out.println("Turn -");
            }
        }
        else if(angle<getRotation()) {
            if (internalArcLength < externalArcLength) {
                if (getRotation() == 360)
                    setRotation(0);
                setRotation(getRotation() + 1f);
                System.out.println("Turn +");
            } else if (internalArcLength > externalArcLength) {
                if (getRotation() == 0)
                    setRotation(360);
                setRotation(getRotation() - 1f);
                System.out.println("Turn -");
            }
        }
        System.out.println("Angle:"+angle+" "+"Rotation:"+getRotation());
        System.out.println(internalArcLength+" "+externalArcLength);





    }

}
