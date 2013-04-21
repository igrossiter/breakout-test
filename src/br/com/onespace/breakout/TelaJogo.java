package br.com.onespace.breakout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

public class TelaJogo extends Tela {
	private static final float BALL_VELOCITY = 250f;
	private static final float BALL_MAX_VELOCITY = 450f;
	private static final float PADDLE_VELOCITY = 350f;
	private static final float BALL_ANGLE = 90f;
	private static final float MODIFICADOR_VELOCIDADE = 1.05f;
	private Player player;
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
	private TextureRegion spriteCor;
	private SpriteBatch spriteBatch;

	public TelaJogo(Breakout game) {
		super(game);
	}

	public void show() {
		super.show();
		player = new Player(5);
		sr = new ShapeRenderer();

		tela = new Rectangle();
		tela.set(0, 0, game.getW(), game.getH());
		telaLeft = tela.x;
		telaRight = tela.x + tela.width;
		telaBottom = tela.y;
		telaTop = tela.y + tela.height;

		field = new Field(game);
		field.gerarMapa();
		// para ter certeza do mapa e suas posições logo no início
		field.constroiBlocos();

		// declara o array de blocos
		blocos = new Bloco[field.getEixoX()][field.getEixoY()];
		blocos = field.getArrayDeBlocos();

		ball = new Ball(field.getBlockWidth() / 4, field.getBlockWidth() / 4);
		paddle = new Paddle((int) blocos[0][0].getWidth(),
				(int) ball.getHeight());

		spriteBatch = game.getSpriteBatch();

		spriteBloco1 = new TextureRegion(game.getTextura(), 0, 0, 80, 30);
		spriteBloco2 = new TextureRegion(game.getTextura(), 84, 0, 80, 30);
		spriteBola = new TextureRegion(game.getTextura(), 0, 35, 41, 41);
		spriteCor = new TextureRegion(game.getTextura(), 84, 34, 85, 81);

		reset();
	}

	private void reset() {
		// centraliza a bola

		if (!player.isVivo())
			game.setScreen(game.getTelaMenu());

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

		// centraliza o paddle
		paddle.move((game.getW() - paddle.getWidth()) / 2, game.getH() * 0.1f);

	}

	@Override
	public void render(float dt) {
		// Gdx.app.log(Breakout.LOG, "rendering");
		clear();
		update(dt);
		draw(dt);
	}

	private void draw(float dt) {
		// drawBall(dt);
		drawPaddle(dt);
		// drawField(dt);
		spriteBatch.begin();
		drawSpritesBall(dt);
		drawSpritesBlocos(dt);
		drawSpritesPaddle(dt);
		drawSpritesCor(dt);
		spriteBatch.end();

	}

	private void drawSpritesCor(float dt) {
		for (int i = 0; i < player.getLives(); i++) {
			spriteBatch.draw(spriteCor,
					i * spriteCor.getRegionWidth() / 5 + 5f, 10f,
					spriteCor.getRegionWidth() / 5,
					spriteCor.getRegionHeight() / 5);
		}
	}

	private void drawSpritesPaddle(float dt) {
		// TODO Auto-generated method stub

	}

	private void drawSpritesBlocos(float dt) {
		for (Bloco[] bl : blocos) {
			for (Bloco b : bl) {
				if (b.getSpriteId() == 1) {
					spriteBatch.draw(spriteBloco1, b.getX(), b.getY(),
							b.getWidth(), b.getHeight());
				} else {
					spriteBatch.draw(spriteBloco2, b.getX(), b.getY(),
							b.getWidth(), b.getHeight());
				}
			}
		}
	}

	private void drawSpritesBall(float dt) {
		spriteBatch.draw(spriteBola, ball.getX(), ball.getY(), ball.getWidth(),
				ball.getHeight());
	}

	private void drawPaddle(float dt) {
		sr.begin(ShapeType.FilledRectangle);
		sr.filledRect(paddle.getX(), paddle.getY(), paddle.getWidth(),
				paddle.getHeight());
		sr.end();

	}

	private void update(float dt) {
		// TODO Auto-generated method stub
		updateBall(dt);
		updatePaddle(dt);
		updateField(dt);
		handleInput();
	}

	private void handleInput() {
		if (Gdx.input.isKeyPressed(Keys.P))
			reset();

	}

	private void updateField(float dt) {
		// field.constroiBlocos();
		blocos = field.getArrayDeBlocos();
	}

	private void updatePaddle(float dt) {
		boolean moveLeft = false, moveRight = false;
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			moveLeft = true;
			moveRight = false;
		}

		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			moveLeft = false;
			moveRight = true;
		}

		/*
		 * o else if e else aqui é importante para que o paddle não continue o
		 * movimento se nenhuma tecla estiver pressionada. no caso de ambos
		 * false, a velocidade é setada para 0
		 */
		if (moveLeft) {
			paddle.setVelocity(-PADDLE_VELOCITY, 0f);
		} else if (moveRight) {
			paddle.setVelocity(PADDLE_VELOCITY, 0f);
		} else {
			paddle.setVelocity(0f, 0f);
		}

		// colisão com a lateral

		if (paddle.right() > telaRight) {
			paddle.move(telaRight - paddle.getWidth(), paddle.getY());
		}

		if (paddle.left() < telaLeft) {
			paddle.move(telaLeft, paddle.getY());
		}

		paddle.integrate(dt);
		paddle.updateBounds();

	}

	private void updateBall(float dt) {
		ball.integrate(dt);
		ball.updateBounds();

		// colisão com a tela
		if (ball.bottom() < telaBottom) {
			// ball.move(ball.getX(), telaBottom);
			// ball.reflect(false, true);
			player.falha();
			reset();
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

		// colisão com paddle

		if (ball.getBounds().overlaps(paddle.getBounds())) {
			if (ball.bottom() < paddle.top() && ball.top() > paddle.top()) {
				ball.move(ball.getX(), paddle.top());
				ball.reflect(false, true);

				// alteração do angulo dependendo da colisão
				// float difference = ball.getCenterX() - paddle.getCenterX();
				// aqui preciso de um numero de 0 a 1 e não de -1 a 1, por isso
				// tiro a diferença do ponto x do paddle
				float difference = ball.getCenterX() - paddle.getX();
				float position = difference / paddle.getWidth();
				if (position > 1f)
					position = 1f;
				if (position < 0f)
					position = 0f;
				// inverter a posição
				position = 1f - position;
				float angulo = BALL_ANGLE * position;

				angulo = angulo + 45;

				String txtAng = Float.toString(angulo);
				String txtPos = Float.toString(position);
				String txtdifference = Float.toString(difference);

				Gdx.app.log(game.LOG, "difference: " + txtdifference);
				Gdx.app.log(game.LOG, "position: " + txtPos);
				Gdx.app.log(game.LOG, "angulo: " + txtAng);

				Vector2 velocity = ball.getVelocity();
				velocity.setAngle(angulo);
				velocity.mul(MODIFICADOR_VELOCIDADE);

				ball.setVelocity(velocity);
			}
		}

		// colisões com blocos
		// for (int i = 0; i < field.getEixoX(); i++) {
		// for (int j = 0; j < field.getEixoY(); j++) {
		// Bloco b = blocos[i][j];

		// blocos[i][j].bottom();
		// detecta colisão com os blocos

		for (Bloco[] bl : blocos) {
			for (Bloco b : bl) {
				if (ball.getBounds().overlaps(b.getBounds())) {
					// colisão na parte inferior do bloco
					if (ball.top() > b.bottom() && b.top() > ball.top()
							&& b.bottom() > ball.bottom()
							&& ball.right() > b.left()
							&& ball.left() < b.right()) {
						ball.move(ball.getX(), b.bottom() - ball.getHeight());
						ball.reflect(false, true);
						// field.hit(i,j);
						b.hit();
					}
					// colisão na parte superior do bloco
					if (ball.bottom() < b.top() && ball.top() > b.top()
							&& ball.right() > b.left()
							&& ball.left() < b.right()) {
						ball.move(ball.getX(), b.top());
						ball.reflect(false, true);
						// field.hit(i,j);
						b.hit();
					}
					// colisão no lado direito do bloco
					if (ball.left() < b.right() && ball.right() > b.right()
							&& ball.top() > b.bottom()
							&& ball.bottom() < b.top()) {
						ball.move(b.right(), ball.getY());
						ball.reflect(true, false);
						// field.hit(i,j);
						b.hit();
					}
					// colisão no lado esquerdo do bloco
					if (ball.right() > b.left() && ball.left() < b.left()
							&& ball.top() > b.bottom()
							&& ball.bottom() < b.top()) {
						ball.move(b.left() - ball.getWidth(), ball.getY());
						ball.reflect(true, false);
						// field.hit(i,j);
						b.hit();
					}
				}
			}

			// }

			// }
		}
	}

	@Override
	public void clear() {
		Gdx.gl.glClearColor(0f, .01f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

}
