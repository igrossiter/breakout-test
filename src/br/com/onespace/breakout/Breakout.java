package br.com.onespace.breakout;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class Breakout extends Game {
	public static final String VERSION = "versão 0.0.1";
	public static final String LOG = Breakout.class.getSimpleName();
	public float h;
	public float w;

	@Override
	public void create() {
		h = Gdx.graphics.getHeight();
		w = Gdx.graphics.getWidth();
		setScreen(getTelaInicial());
	}
	
	public TelaInicial getTelaInicial() {
		return new TelaInicial(this);
	}
	
	public TelaMenu getTelaMenu() {
		return new TelaMenu(this);
	}
	
	public TelaJogo getTelaJogo() {
		return new TelaJogo(this);
	}

}
