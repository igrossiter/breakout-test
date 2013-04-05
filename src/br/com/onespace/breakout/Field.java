package br.com.onespace.breakout;

public class Field {
	private boolean[][] mapa;
	private static final int EIXO_X = 10;
	private static final int EIXO_Y = 20;
	private boolean mapaFeito;

	/*
	 * a ideia aqui é utilizar esse mapa para definir qual bloco aparece e qual
	 * não aparece na fase do jogo
	 * 
	 * podemos inserir esse mapa via construtor ou podemos gerar aleatoriamente.
	 * 
	 * para fins de simplificação vamos gerar automaticamente os mapas.
	 */

	public Field() {
		// TODO Auto-generated constructor stub
		mapa = new boolean[EIXO_X][EIXO_Y];
		mapaFeito = false;
		gerarMapa();
	}

	public void gerarMapa() {
		for (int i = 0; i < EIXO_X; i++) {
			for (int j = 0; j < EIXO_Y; j++) {
				boolean teste = false;
				if ( Math.random() * 10 > 4f) {
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

	private boolean isMapaSet() {
		if (mapaFeito) {
			return true;
		} else {
			return false;
		}
	}

}
