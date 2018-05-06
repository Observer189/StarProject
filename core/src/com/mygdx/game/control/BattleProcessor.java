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
    float xPos;//позиции камеры
    float yPos;
    public BattleProcessor(Joystick joystick,float xPos,float yPos)
    {
        this.joystick=joystick;
        this.xPos=xPos-Battle.widthCamera/2;
        this.yPos=yPos-Battle.heightCamera/2;

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
        joystick.create(screenX/convX+xPos,Battle.heightCamera-screenY/convY+yPos);//конвертация координат получаемых с InputProcessor в координаты экрана

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        joystick.setActive(false);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        joystick.dynamicPart.setCenter(screenX/convX+xPos,Battle.heightCamera-screenY/convY+yPos);

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
