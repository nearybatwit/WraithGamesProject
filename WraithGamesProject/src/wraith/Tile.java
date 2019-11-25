package wraith;

import javafx.scene.shape.Rectangle;

public class Tile {
	public int xPos;
	private int yPos;
	public Rectangle tileRect;
	public Tile(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}

	public int getXPos() {
		return xPos;
	}
	
	public void setXPos(int newXPos) {
		xPos = newXPos;
	}
	
	public int getYPos() {
		return yPos;
	}
	
	public void setYPos(int newYPos) {
		yPos= newYPos;
	}
}
