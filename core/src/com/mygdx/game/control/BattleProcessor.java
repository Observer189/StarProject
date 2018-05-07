package com.mygdx.game.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.utils.Joystick;
import com.mygdx.game.view.Battle;

/**
 * Created by Sash on 01.05.2018.
 */

public class BattleProcessor implements InputProcessor {
    Joystick joystick;
    float convX= Gdx.graphics.getWidth()/ Battle.widthCamera;
    float convY=Gdx.graphics.getHeight()/Battle.heightCamera;

    public static float offsetX;//переменные определяющие смещение статичной части относительно позиции камеры
    public static float offsetY;
    public static float offsetDynamicX;//переменные определяющие смещение динамической части относительно статичной
    public static float offsetDynamicY;

    public BattleProcessor(Joystick joystick)
    {
        this.joystick=joystick;



    }
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        joystick.create(screenX/convX+ (Battle.camX-Battle.widthCamera/2),Battle.heightCamera-screenY/convY+ (Battle.camY-Battle.heightCamera/2));//конвертация координат получаемых с InputProcessor в координаты экрана
        offsetX=joystick.staticPart.getCenterX()-(Battle.camX-Battle.widthCamera/2);
        offsetY=joystick.staticPart.getCenterY()-(Battle.camY-Battle.heightCamera/2);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        joystick.setActive(false);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        //joystick.staticPart.setCenter((Battle.camX-Battle.widthCamera/2)+offsetX,(Battle.camY-Battle.heightCamera/2)+offsetY);
        joystick.dynamicPart.setCenter(screenX/convX+ (Battle.camX-Battle.widthCamera/2),Battle.heightCamera-screenY/convY+ (Battle.camY-Battle.heightCamera/2));//смещение динамической части относительно статичной
        offsetDynamicX=joystick.dynamicPart.getCenterX()-joystick.staticPart.getCenterX();
        offsetDynamicY=joystick.dynamicPart.getCenterY()-joystick.staticPart.getCenterY();
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
