package br.com.onespace.breakout;

import java.util.Random;

public class Bloco extends GameObject{
	private int spriteId;
	private Random random;
	
	public Bloco(int w, int h) {
		super(w, h);
		random = new Random();
		this.spriteId = random.nextInt(2)+1;
	}

	public int getSpriteId() {
		return spriteId;
	}
	
	public void hit() {
		this.move(1000f, 1000f);
	}

}
