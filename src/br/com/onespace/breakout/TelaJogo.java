package br.com.onespace.breakout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class TelaJogo extends Tela {
	private Ball ball;
	private Paddle paddle;
	private Field field;

	public TelaJogo(Breakout game) {
		super(game);
	}
	
	public void show() {
		super.show();
		
		field = new Field();
		field.gerarMapa();
		Gdx.app.log(Breakout.LOG, "field gerado");
		
		String teste;
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 20; j++) {
				teste = "x: " +  i + "y: " + j + " " + Boolean.toString(field.getMapa()[i][j]);
				Gdx.app.log(Breakout.LOG, teste);
			}
		}
	}
	
	public void render() {
		clear();
		update();
		draw();
	}
	
	private void draw() {
		drawBall();
		drawPaddle();
		drawField();
		
	}

	private void drawField() {
		// TODO Auto-generated method stub
		
	}

	private void drawPaddle() {
		// TODO Auto-generated method stub
		
	}

	private void drawBall() {
		// TODO Auto-generated method stub
		
	}

	private void update() {
		// TODO Auto-generated method stub
		updateBall();
		updatePaddle();
		updateField();
	}

	private void updateField() {
		// TODO Auto-generated method stub
		
	}

	private void updatePaddle() {
		// TODO Auto-generated method stub
		
	}

	private void updateBall() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		Gdx.gl.glClearColor( 0f, .3f, 0f, 1f );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
	}

}
