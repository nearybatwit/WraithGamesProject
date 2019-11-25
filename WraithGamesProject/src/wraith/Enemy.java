package wraith;
import java.util.ArrayList;

public class Enemy {

	//Added damage

	public int damage;
	public int rewardOnDeath;
	public PathTile currTile;   //may be redundant if we have enemies in PathTile
	public int level;
	public double hp; 
	public double speed;
	public String type;

	public Enemy() {

		this.damage = 0;
		this.rewardOnDeath = 0;
		this.currTile = new PathTile(0, 0);
		this.level = 0;
		this.hp = 0;
		this.speed = 0;
		this.type = "super";
	}

	public Enemy(int damage, int rewardOnDeath, PathTile currTile, int level, double hp, double speed, String type) {

		this.damage = damage;
		this.rewardOnDeath = rewardOnDeath;
		this.currTile = currTile;
		this.level = level;
		this.hp = hp;
		this.speed = speed;
		this.type = type;
	}

	/**
	 * Getters & Setters
	 */

	public int getRewardOnDeath() {
		return rewardOnDeath;
	}

	public void  setRewardOnDeath(int newRewardOnDeath) {
		rewardOnDeath= newRewardOnDeath;
	}

	/*public Tile getCurrentTile() {
		return currTile;
	}
	public void setCurrentTile( Tile newCurrTile){
		currTile= newCurrTile;
	}

	 */

	public int getLevel() {
		return level;
	}

	public void setLevel(int newLevel) {
		level= newLevel;
	}

	public double getHP() {
		return hp;
	}

	public void setHP(int newHP) {
		hp = newHP;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double newSpeed) {
		speed=newSpeed;
	}

	public String getType() {
		return type;
	}

	public void setType(String newType) {
		type = newType;
	}
	
	/**
	 * @param p will be called with 'this' in player class
	 * called "endReached()" in class chart
	 */
	public void attack(Player p) {
		p.damage(this.damage);
	}

	public void move(ArrayList<Tile> pathPositions) {
		//need to know positions of the path tiles
		//currTile= currTile + speed;

	}

	
	public void damage(int dmg) {
		hp-=dmg;

	}

}