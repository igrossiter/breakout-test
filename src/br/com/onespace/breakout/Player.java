package br.com.onespace.breakout;

public class Player {
	private int lives;
	private int score;
	private boolean vivo;
	
	public Player(int lives) {
		if (lives < 0 ) lives = 1;
		this.lives = lives;
		setScore(0);
		vivo = true;
	}
	
	public int getLives() {
		return lives;
	}
	public void setLives(int lives) {
		this.lives = lives;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	public void falha(){
		lives--;
		if (lives <= 0) morrer();
	}

	private void morrer() {
		vivo = false;
	}

	public boolean isVivo() {
		return vivo;
	}
	
}
