package br.com.onespace.breakout;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {
	private Vector2 position = new Vector2();
	private Vector2 velocity = new Vector2();
	private Rectangle bounds = new Rectangle();
	private float centerX;
	private float centerY;
	
	protected GameObject(int width, int height) {
		bounds.setWidth(width);
		bounds.setHeight(height);
	}
	
	public Rectangle getBounds(){
		updateBounds();
		return bounds;
	}
	
	public void setBounds(Rectangle bounds){
		this.bounds = bounds;
	}
	
	public void updateBounds(){
		bounds.set(position.x, position.y, bounds.width, bounds.height);
	}
	
	public float getX(){
		return position.x;
	}
	
	public float getY(){
		return position.y;
	}
	
	public float getVelocityX(){
		return velocity.x;
	}
	
	public float getVelocityY(){
		return velocity.y;
	}
	
	public float getHeight(){
		return bounds.height;
	}
	
	public float getWidth(){
		return bounds.width;
	}
	
	public float bottom(){
		return bounds.y;
	}
	
	public float top(){
		return bounds.y + bounds.height;
	}
	
	public float left(){
		return bounds.x;
	}
	
	public float right(){
		return bounds.x + bounds.width;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}
	
	public void setVelocity(float x, float y) {
		velocity.set(x, y);
	}
	
	//a intenção desse método é utilizar o dt
	//para padronizar a alteração de posição de
	//acordo com o framerate
	public void integrate(float dt){
		position.add(velocity.x * dt, velocity.y * dt);
	}
	
	//move o objeto para uma pocisão específica
	public void move(float x, float y){
		position.set(x, y);
	}
	
	//move o objeto adicionando ao objeto uma posição
	//relativa a posição atual
	public void translate(float x, float y){
		position.add(x, y);
	}

	public float getCenterX() {
		centerX = this.getX() + (this.getWidth() / 2);
		return centerX;
	}

	public float getCenterY() {
		centerY = (this.getY() + (this.getHeight()) / 2);
		return centerY;
	}
}
