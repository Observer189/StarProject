package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.Ships.Dakkar;
import com.mygdx.game.model.Ships.Dashing;
import com.mygdx.game.model.Ships.Pulsate;
import com.mygdx.game.utils.Assets;
import com.mygdx.game.view.LoginView;
import com.mygdx.game.view.MainMenu;

public class StarGame extends Game {

	private Screen menu,log;

	private Assets assets;
	Player player;
	SpriteBatch batch;
	@Override
	public void create () {
		assets=new Assets();
		player=new Player("NaN",new Dashing(new TextureAtlas(Gdx.files.internal("TexturePack.atlas")),0,0));
		menu = new MainMenu(batch,this,player);


		((MainMenu)menu).setTextureAtlas(assets.getManager().get("TexturePack.atlas", TextureAtlas.class));
		log=new LoginView(batch,this);
		setScreen(log);
		batch=new SpriteBatch();
	}



	@Override
	public void dispose () {
		menu.dispose();
		assets.dispose();
	}
}
