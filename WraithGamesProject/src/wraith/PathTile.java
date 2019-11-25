package wraith;
import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PathTile extends Tile{
	
	
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	
	
	public PathTile(int xPos, int yPos) {
		super(xPos, yPos);
		tileRect = new Rectangle(100, 100, new Color(.66, .6, .45, 1));
	}

	public void addEnemy(Enemy enemy) {
		enemies.add(enemy);
	}
	
	//This implementation will damage by tile, rather than by radius.
	//params could be replace with a Tower type for simplicity
	public void damage(int dmg) {
		for(Enemy enemy : enemies) {
			enemy.damage(dmg);
		}
	}
	//Change the class diagram arrow
	
	
}