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
        super(textureRegion, x, y, 2f, 5f, 2f, 30,rotation);
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
        internalArcLength= (angle < 180) ? angle : 360-angle;
        internalArcLength+=(getRotation() < 180) ? getRotation() : 360-getRotation();
        externalArcLength=Math.abs(angle-getRotation());
        if(internalArcLength>externalArcLength)
        {
           setRotation(getRotation()+1);
        }
        else if(internalArcLength>externalArcLength)
        {
            setRotation(getRotation()-1);
        }
        System.out.println(internalArcLength+" "+externalArcLength);





    }

}
