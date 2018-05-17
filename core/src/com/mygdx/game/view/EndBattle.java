package com.mygdx.game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.model.Player;
import com.mygdx.game.utils.TextManager;

/**
 * Created by Sash on 11.05.2018.
 */

public class EndBattle implements Screen {
    MainMenu mainMenu;
    Game game;
    Player player;
    Stage stage;
    Button.ButtonStyle btnStyle;
    Button btn;
    BitmapFont font;
    TextManager textManager;
    public EndBattle(Player player,Game game,MainMenu mainMenu)
        {
            this.mainMenu=mainMenu;
            this.game=game;
            this.player=player;
        }
    @Override
    public void show() {
        textManager=new TextManager(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        font=textManager.fontInitialize(Color.BLUE,1);
        Skin skin = new Skin();
        skin.addRegions(new TextureAtlas(Gdx.files.internal("TexturePack.atlas")));
        btnStyle=new Button.ButtonStyle();
        btnStyle.up=skin.getDrawable("Back-up");
        btnStyle.down=skin.getDrawable("Back-down");
        btn.setSize(Gdx.graphics.getWidth()/3,Gdx.graphics.getWidth()/3);
        btn.setPosition(Gdx.graphics.getWidth()/2-btn.getWidth()/2,Gdx.graphics.getHeight()/2-btn.getHeight()/2);
        btn=new Button(btnStyle);
         btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainMenu.music.play();
                game.setScreen(mainMenu);


            }
        });

        stage=new Stage();
        stage.addActor(btn);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();

    }
    @Override

    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
