package br.com.onespace.breakout;

import com.badlogic.gdx.math.Vector2;

public class Ball extends GameObject {

	public Ball(int width, int height) {
		super(width, height);
	}
	
	public void reflect(boolean x, boolean y){
		Vector2 velocity = this.getVelocity();
		
		if(x) velocity.x *= -1;
		if(y) velocity.y *= -1;
		
		this.setVelocity(velocity);
	}
	
	public float getRandonDirection() {
		double direcao = 0;
		direcao = Math.random()*100;
		
		if (direcao < 25){
			return 45f;
		} else if(direcao < 50){
			return 135f;
		} else if(direcao < 75){
			return 225f;
		} else if (direcao <= 100){
			return 315f;
		} else {
			return 45f;
		}
	}

}
