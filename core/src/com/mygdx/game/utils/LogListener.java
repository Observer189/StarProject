package com.mygdx.game.utils;

import com.badlogic.gdx.Input;

public class LogListener implements Input.TextInputListener {
    String text;
    public Boolean Show=false;
    public Boolean ShowPass=false;


    @Override
    public void input(String text){
        this.text=text;
        if (this.text.equals(null)||this.text.contains(" ")||this.text.length()<4){
            Show=true;

        }else ShowPass=true;

    }

    @Override
    public void canceled(){
        Show=true;
    }



}