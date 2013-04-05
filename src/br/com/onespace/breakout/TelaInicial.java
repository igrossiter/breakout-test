package br.com.onespace.breakout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TelaInicial extends Tela {
	private Texture textura;
	private TextureRegion regiao;
	private float counter;
	
	public TelaInicial(Breakout game) {
		super(game);
	}
	
	public void show(){
		super.show();
		
		textura = new Texture("data/inicial.png");
		textura.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		regiao = new TextureRegion(textura, 0, 100, 800, 600);
		
		counter = 0;

	}
	
	public void render(float delta) {
		super.render(delta);
		
		String dtlog = Float.toString(counter);
		
		Gdx.app.log(Breakout.LOG, dtlog);
		counter += delta;
		
		spriteBatch.begin();
		spriteBatch.draw(regiao, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		spriteBatch.end();
		
		if (counter > .5f)
			game.setScreen(game.getTelaMenu());
	}
	
	public void dispose() {
		super.dispose()	;
		textura.dispose();
	}
}
