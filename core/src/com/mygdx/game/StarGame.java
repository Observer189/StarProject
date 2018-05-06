package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.model.Player;
import com.mygdx.game.utils.Assets;
import com.mygdx.game.view.Battle;
import com.mygdx.game.view.ConnectToBattle;
import com.mygdx.game.view.MainMenu;

public class StarGame extends Game {

	private Screen menu;
	private Screen battle;
	private Assets assets;
	SpriteBatch batch;
	Player player;
	@Override
	public void create () {
		TextureAtlas textureAtlas=new TextureAtlas();
		batch = new SpriteBatch();
		player=new Player();
		assets=new Assets();
		menu = new MainMenu(batch,this);
		battle=new ConnectToBattle(batch,this,textureAtlas,player);

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
