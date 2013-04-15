package br.com.onespace.breakout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class TelaJogo extends Tela {
	private static final float BALL_VELOCITY = 450f;
	private Ball ball;
	private Paddle paddle;
	private Field field;
	public ShapeRenderer sr;
	public Bloco[][] blocos;
	private Rectangle tela;
	private float telaBottom;
	private float telaTop;
	private float telaLeft;
	private float telaRight;
	private TextureRegion spriteBloco1;
	private TextureRegion spriteBloco2;
	private TextureRegion spriteBola;
	private SpriteBatch spriteBatch;

	public TelaJogo(Breakout game) {
		super(game);
	}

	public void show() {
		super.show();
		sr = new ShapeRenderer();
		
		tela = new Rectangle();
		tela.set(0, 0, game.getW(), game.getH());
		telaLeft = tela.x;
		telaRight = tela.x + tela.width;
		telaBottom = tela.y;
		telaTop = tela.y + tela.height;
		
		
		field = new Field(game);
		field.gerarMapa();
		//para ter certeza do mapa e suas posições logo no início
		field.constroiBlocos();

		//declara o array de blocos
		blocos = new Bloco[field.getEixoX()][field.getEixoY()];
		blocos = field.getArrayDeBlocos();


		ball = new Ball(field.getBlockWidth() / 4, field.getBlockWidth() / 4);
		
		spriteBatch = game.getSpriteBatch();
		
		spriteBloco1 = new TextureRegion(game.getTextura(), 0, 0, 80, 30);
		spriteBloco2 = new TextureRegion(game.getTextura(), 85, 0, 80, 30);
		spriteBola  = new TextureRegion(game.getTextura(), 0, 35, 41, 76);

		reset();
	}

	private void reset() {
		// centraliza a bola
		ball.move((game.getW() - ball.getWidth()) / 2,
				(game.getH() - ball.getHeight()) / 2);

		// pega a velocidade da bola
		Vector2 velocity = ball.getVelocity();
		// edita a velocidade
		/*
		 * atenção aqui, diferentemente do pong, o jogo aqui se desenvolve no
		 * movimento vertical, e não horizontal isso faz com o que o eixo Y
		 * defina velocididade enquanto o eixo x define o ângulo da curva.
		 */
		velocity.set(0, BALL_VELOCITY);
		// set the angle, teste
		velocity.setAngle(ball.getRandonDirection());
		ball.setVelocity(velocity);

	}

	@Override
	public void render(float dt) {
		//Gdx.app.log(Breakout.LOG, "rendering");
		clear();
		update(dt);
		draw(dt);
	}

	private void draw(float dt) {
		drawBall(dt);
		drawPaddle(dt);
		//drawField(dt);
		drawSprites(dt);

	}

	private void drawSprites(float dt) {
		spriteBatch.begin();
		spriteBatch.draw(spriteBola, ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight() + 15f);
		for (Bloco[] bl : blocos){
			for (Bloco b : bl) {
				spriteBatch.draw(spriteBloco1 ,b.getX(), b.getY(), b.getWidth(), b.getHeight());
			}
		}
		spriteBatch.end();
	}

	private void drawField(float dt) {
		field.drawnBlocos(sr);
		//Gdx.app.log(Breakout.LOG, "construindo field");

	}

	private void drawPaddle(float dt) {
		// TODO Auto-generated method stub

	}

	private void drawBall(float dt) {
		sr.begin(ShapeType.FilledRectangle);
		sr.filledRect(ball.getX(), ball.getY(), ball.getWidth(),
				ball.getHeight());
		sr.end();

	}

	private void update(float dt) {
		// TODO Auto-generated method stub
		updateBall(dt);
		updatePaddle();
		updateField(dt);
	}

	private void updateField(float dt) {
		//field.constroiBlocos();
		blocos = field.getArrayDeBlocos();
	}

	private void updatePaddle() {
		// TODO Auto-generated method stub

	}

	private void updateBall(float dt) {
		ball.integrate(dt);
		ball.updateBounds();
		
		//colisão com a tela
		if (ball.bottom() < telaBottom) {
			ball.move(ball.getX(), telaBottom);
			ball.reflect(false, true);
		}
		
		if (ball.left() < telaLeft) {
			ball.move(telaLeft, ball.getY());
			ball.reflect(true, false);					
		}
		
		if (ball.top() > telaTop) {
			ball.move(ball.getX(), telaTop - ball.getHeight());
			ball.reflect(false, true);					
		}
		
		if (ball.right() > telaRight) {
			ball.move(telaRight - ball.getWidth(), ball.getY());
			ball.reflect(true, false);
		}

		// colisões com blocos
		for (int i = 0; i < field.getEixoX(); i++) {
			for (int j = 0; j < field.getEixoY(); j++) {
				Bloco b = blocos[i][j];

				//blocos[i][j].bottom();
				// detecta colisão com os blocos
				if (ball.getBounds().overlaps(b.getBounds())) {

					if (ball.top() > b.bottom()
							&& b.top() > ball.top()
							&& b.bottom() > ball.bottom()
							&& ball.right() > b.left()
							&& ball.left() < b.right()) {
						ball.move(ball.getX(), b.bottom() - ball.getHeight());
						ball.reflect(false, true);
						field.hit(i,j);
						
					}

					if (ball.bottom() < b.top()
							&& ball.top() > b.top() 
							&& ball.right() > b.left()
							&& ball.left() < b.right()) {
						ball.move(ball.getX(), b.top());
						ball.reflect(false, true);
						field.hit(i,j);
					}
					
					if (ball.left() < b.right()
							&& ball.right() > b.right()
							&& ball.top() > b.bottom()
							&& ball.bottom() < b.top()) {
						ball.move(b.right(), ball.getY());
						ball.reflect(true, true);
						field.hit(i,j);
					}
					
					if (ball.right() > b.left()
							&& ball.left() < b.left()
							&& ball.top() > b.bottom()
							&& ball.bottom() < b.top()) {
						ball.move(b.left() - ball.getWidth(), ball.getY());
						ball.reflect(true, true);
						field.hit(i,j);
					}

				}

			}
		}
	}

	@Override
	public void clear() {
		Gdx.gl.glClearColor(0f, .3f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

}
