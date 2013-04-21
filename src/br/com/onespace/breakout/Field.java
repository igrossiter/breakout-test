package br.com.onespace.breakout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class Field {
	private boolean[][] mapa;
	private static final int EIXO_X = 10;
	private static final int EIXO_Y = 8;
	private boolean mapaFeito;
	private boolean mapaConstruido;
	private Breakout game;
	private int blockWidth;
	private int blockHeight;
	private Bloco[][] b;
	private Vector2 pos;
	// tamanho do campo verticalmente e horizontal
	private float fieldWidth;
	private float fieldHeight;

	/*
	 * a ideia aqui é utilizar esse mapa para definir qual bloco aparece e qual
	 * não aparece na fase do jogo
	 * 
	 * podemos inserir esse mapa via construtor ou podemos gerar aleatoriamente.
	 * 
	 * para fins de simplificação vamos gerar automaticamente os mapas.
	 */

	public Field(Breakout game) {
		// TODO Auto-generated constructor stub
		mapa = new boolean[EIXO_X][EIXO_Y];
		b = new Bloco[EIXO_X][EIXO_Y];
		pos = new Vector2();
		fieldWidth = game.getW();
		fieldHeight = game.getH() * .4f;
		blockWidth = (int) fieldWidth / EIXO_X;
		blockHeight = (int) fieldHeight / EIXO_Y;
		mapaFeito = false;
		mapaConstruido = false;
		this.game = game;
		gerarMapa();
		
		for (int i = 0; i < EIXO_X; i++) {
			for (int j = 0; j < EIXO_Y; j++) {
				// if (mapa[i][j]) {
					b[i][j] = new Bloco(blockWidth, blockHeight);
					//movo todos os blocos para fora da tela, só volta os true
					b[i][j].move(1000f, 1000f);
				// }
			}
		}
	}

	/*
	 * TODO sinto que isso está errado, o tamanho do bloco deveria ser definido
	 * no objeto bloco?
	 */

	public int getBlockWidth() {
		return blockWidth;
	}

	public int getBlockHeight() {
		return blockHeight;
	}

	public int getEixoX() {
		return EIXO_X;
	}

	public int getEixoY() {
		return EIXO_Y;
	}

	/**
	 * deve ser utilizado apos construir o field
	 * 
	 * @return array de objetos blocos
	 */
	public Bloco[][] getArrayDeBlocos() {
		return b;
	}

	public void gerarMapa() {
		for (int i = 0; i < EIXO_X; i++) {
			for (int j = 0; j < EIXO_Y; j++) {
				boolean teste = false;
				if (Math.random() * 10 > 4f) {
					teste = true;
				}
				mapa[i][j] = teste;
			}
		}

		mapaFeito = true;
	}

	public boolean[][] getMapa() {

		if (isMapaSet()) {

			return mapa;

		} else {
			gerarMapa();
			return mapa;
		}
	}

	public boolean isMapaSet() {
		if (mapaFeito) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Constroi Campo baseado em mapa passado em parâmetro
	 * 
	 * verifica quais campos do mapa ainda estão true e desenha os blocos para o
	 * frame
	 * 
	 * @param mapa
	 */
	public void drawnBlocos(ShapeRenderer sr) {
		// TODO gerar blocos para cada ponto true no mapa.
		sr.begin(ShapeRenderer.ShapeType.FilledRectangle);

		// aqui começa o loop
		for (int i = 0; i < EIXO_X; i++) {
			for (int j = 0; j < EIXO_Y; j++) {

				if (mapa[i][j] && mapaConstruido) {

					sr.setColor(0.1f * j, 0.1f * j, 0.1f * i, 1);
					// imprimos o bloco
					sr.filledRect(b[i][j].getX(), b[i][j].getY(),
							b[i][j].getWidth(), b[i][j].getHeight());
					// Gdx.app.log(Breakout.LOG, "Imprimindo bloco " + i + " x "
							//+ j + " na posicao "
							//+ b[i][j].getPosition().toString());
				}
			}
		}
		// finalizamos o loop
		sr.end();
	}

	public void constroiBlocos() {
		if (!mapaConstruido) {
			for (int i = 0; i < EIXO_X; i++) {
				for (int j = 0; j < EIXO_Y; j++) {
					if (mapa[i][j]) {
						
						b[i][j].move(i * (fieldWidth / EIXO_X), j
								* (fieldHeight / EIXO_Y) + (game.getH() * .6f));
					}
				}
			}
			mapaConstruido = true;
			
		}

	}


	public void hit(int i, int j) {
		this.mapa[i][j] = false;
		b[i][j].move(1000f, 1000f);
	}
}
