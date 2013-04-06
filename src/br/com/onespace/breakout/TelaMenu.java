package br.com.onespace.breakout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class TelaMenu extends Tela {
	private BitmapFont font;
	private String msg = "Aperte ENTER para continuar";
	private float msgW;
	private float msgH;
	
	public TelaMenu(Breakout game) {
		super(game);
	}

	public void show() {
		super.show();
		iniciarFont();
		//stuff
	}
	
	private void iniciarFont() {
		font = new BitmapFont();
		font.setColor(1, 1, 1, 1);
		msgW = font.getBounds(msg).width;
		msgH = font.getBounds(msg).height;
		
	}

	public void render(float delta) {
		super.render(delta);
		draw();
		handleInput();
	}
	
	private void draw() {
		// TODO Auto-generated method stub
		spriteBatch.begin();
		font.draw(spriteBatch, msg, (game.getW() - msgW) / 2, (game.getH() - msgH) / 2);
		spriteBatch.end();
	}

	private void handleInput() {
		// TODO Auto-generated method stub
		if (Gdx.input.isKeyPressed(Keys.ENTER)){
			game.setScreen(game.getTelaJogo());
		}
	}

}
