package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.utils.Assets;
import com.mygdx.game.view.MainMenu;

public class StarGame extends Game {

	private Screen menu,log;

	private Assets assets;
	SpriteBatch batch;
	@Override
	public void create () {
		assets=new Assets();
		menu = new MainMenu(batch,this);


		((MainMenu)menu).setTextureAtlas(assets.getManager().get("TexturePack.atlas", TextureAtlas.class));
		setScreen(menu);
		batch=new SpriteBatch();
	}


	
	@Override
	public void dispose () {
		menu.dispose();
		assets.dispose();
	}
}
