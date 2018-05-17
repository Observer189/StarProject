package com.mygdx.game.utils;

import com.badlogic.gdx.Input;


public class PassListener implements Input.TextInputListener {
        String text;
        public Boolean Show=true;
        public  Boolean Success=false;


        @Override
        public void input(String text){
                this.text=text;
                if (this.text.equals(null)||this.text.contains(" ")||this.text.length()<4){

                        Show=true;

                }else  {
                        Show=false;
                        Success=true;
                }


        }

        @Override
        public void canceled(){
                Show=true;
        }



}