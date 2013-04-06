package br.com.onespace.breakout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class TelaJogo extends Tela {
	// largura do bloco igual a largura da tela / pelo numero de colunas
	public static final int BLOCO_W = 32;
	// altura  igual (altura da tela - espaço do field) / pelo numero de linhas
	public static final int BLOCO_H = 8;
	private Ball ball;
	private Paddle paddle;
	private Field field;
	public ShapeRenderer sr;

	public TelaJogo(Breakout game) {
		super(game);
	}
	
	public void show() {
		super.show();
		sr = new ShapeRenderer();
		
		field = new Field(game);
		field.gerarMapa();
		Gdx.app.log(Breakout.LOG, "field gerado");
		
		String teste;
	}
	
	@Override
	public void render(float dt) {
		Gdx.app.log(Breakout.LOG, "rendering");
		clear();
		update();
		draw(dt);
	}
	
	private void draw(float dt) {
		drawBall(dt);
		drawPaddle(dt);
		drawField(dt);
		
	}

	private void drawField(float dt) {
		field.constroi(field.getMapa(), sr);
		Gdx.app.log(Breakout.LOG, "construindo field");
		
	}

	private void drawPaddle(float dt) {
		// TODO Auto-generated method stub
		
	}

	private void drawBall(float dt) {
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
