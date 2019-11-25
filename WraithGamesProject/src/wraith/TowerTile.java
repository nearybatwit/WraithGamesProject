package wraith;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TowerTile extends Tile {

	
	private Tower tower;
	private boolean hasTower;

	//Need to display the image used;
	
	public TowerTile(int x, int y){
		//Some kind of basic dirt image
		super(x,y);
		hasTower = false;
		tileRect = new Rectangle(100, 100, new Color(.5,.72,.37,1));
	}
	
	/*public TowerTile() {
		//Allows setting of a new image.
	}*/ 
	
	public Tower getTower() {
		if(this.hasTower) {
			return tower;
		}
		else {
			return null;
		}
	}
	public void setTower(Tower tower) {
		this.tower = tower;
		this.hasTower = true;
	}
	public boolean getHasTower() {
		return this.hasTower;
	}

}
