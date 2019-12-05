package wraith;

import javafx.geometry.Insets;
import java.awt.TextField;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.*; 
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.shape.*;


public class Field extends Application{
public final int[][] tileArrayInts = {
		{0,1,0,0,0},
		{0,1,0,1,0},
		{0,1,0,1,0},
		{0,1,0,1,0},
		{1,0,0,1,1}
		
};
public final Tile[][] tileArray = new Tile[5][5];
	@Override
	public void start(Stage arg0) throws Exception {
		
		arg0.setTitle("Wraith Games Tower Defense");
		
		Image bg = new Image("PlayScreenBG.png"); 
		ImageView bgView = new ImageView(bg);
		
		Button playbtn = new Button("Play!");
		playbtn.setMaxHeight(100);
		playbtn.setMaxWidth(250);
		
		StackPane base = new StackPane();
		
		base.getChildren().addAll(bgView, playbtn);
		
		Scene playScene = new Scene(base, 600, 500);
		arg0.setScene(playScene);
		arg0.show();
		
		 
		
		
		Pane grid = new Pane();
		
		arg0.setTitle("Field test");
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setMinSize(500, 500);
		
		
		
		//BackgroundFill fill = new BackgroundFill(new Color(.66, .6, .45, 1), new CornerRadii(0), new Insets(10,10,10,10));
		//grid.setBackground(new Background(fill));
		
		
		
		for(int i = 0; i<5; i++) {
			for(int j = 0; j < 5; j++) {
				if(tileArrayInts[i][j] == 0) {
					tileArray[i][j] = new PathTile(i,j);
					//grid.add(tileArray[i][j].tileRect, i, j);
					//root.getChildren().add(tileArray[i][j].tileRect);
				}
				else {
					tileArray[i][j] = new TowerTile(i,j);
					//grid.add(tileArray[i][j].tileRect, i, j);
					//root.getChildren().add(tileArray[i][j].tileRect);
				}
			}
		}
		Player player = new Player(100, "Test");
		Group g = new Group();
		FileInputStream file = new FileInputStream("C:\\Images\\map.png");
		Image mapImage = new Image(file);
		//ImageView mapView = new ImageView(mapImage);
		BackgroundImage backMap = new BackgroundImage(mapImage, null, null, null, null);
		Background map = new Background(backMap);
		grid.setBackground(map);
		
		
		playbtn.setOnAction(e -> arg0.setScene(new Scene(grid, 500, 500)));
		arg0.show();
		
		Tower test = new Tower(50, 150, 300, 30);
		Tower test2 = new Tower(150, 350, 300, 30);
		System.out.printf("hp: %d%n", player.getHp());
		Timer timer = new Timer();
		ArrayList<Enemy> field = new ArrayList<Enemy>();
		ArrayList<Tower> towers = new ArrayList<Tower>();
		towers.add(test);
		towers.add(test2);
		for(Tower t : towers) {
			grid.getChildren().add(t.getNode());
		}
		//Game ends at 10 minutes
		AnimationTimer gameLoop = new AnimationTimer() {
			long lastAttack = 0;
			@Override
			//now = current frame timestamp in nanoseconds
			public void handle(long now) {
				
				
				int rand = (int) (Math.random() *240)+1;
				if(rand == 1) {
					sendEnemies(true, field, grid);	
				}
				for(Tower t : towers) {
					
					if(now-t.getLastAttack()>1000000000) {
						attackT(t, field, grid);
						t.setLastAttack(now);
					}
				}
				for(int i = 0; i < field.size(); i++) {
					if(field.get(i).attackIfFinished(player)) {
						System.out.printf("hp: %d%n", player.getHp());
						grid.getChildren().remove(field.get(i).getNode());
						field.remove(i);
						i--;
						
					}
				}
				
			}
			
		};
		//---------------------------------------------------------------------------------------------------------------------------------------------------
		primaryStage.setTitle("Wraith Games Tower Defense");
		
		Image gameOverScreen = new Image("Wraith Games Game Over.png");
		ImageView gameOverScreenView = new ImageView(gameOverScreen);
		
		Button mainMenubtn = new Button("Main Menu");
		mainMenubtn.setMaxHeight(100);
		mainMenubtn.setMaxWidth(250);
		
		StackPane mainMenuBase = new StackPane();
		
		mainMenuBase.getChildren().addAll(gameOverScreenView, mainMenubtn);
		
		Scene gameOverScene = new Scene(mainMenuBase, 600, 500);
		primaryStage.setScene(gameOverScene);
		primaryStage.show();
		
		
					
		
		
		
		
		//---------------------------------------------------------------------------------------------------------------------------------------------------
		
		
		gameLoop.start();

	}
	
	public static void main(String[] args) {
		launch(args);
	}
	/**
	 * 
	 * @param enemy type of enemy sent
	 * @param speed time to cross one tile (100px)
	 */
	public static void enemyPath(Enemy enemy, double speed) {
		
		TranslateTransition t1 = new TranslateTransition(Duration.millis(speed*4), enemy.getNode());
		t1.setByX(400);
		TranslateTransition t2 = new TranslateTransition(Duration.millis(speed*2), enemy.getNode());
		t2.setByY(200);
		TranslateTransition t3 = new TranslateTransition(Duration.millis(speed*4), enemy.getNode());
		t3.setByX(-400);
		TranslateTransition t4 = new TranslateTransition(Duration.millis(speed*2), enemy.getNode());
		t4.setByY(200);
		TranslateTransition t5 = new TranslateTransition(Duration.millis(speed*4), enemy.getNode());
		t5.setByX(500);
		
		SequentialTransition seqT = new SequentialTransition(t1,t2,t3,t4,t5);
		seqT.setDelay(Duration.millis(0));
		seqT.play();
		
	}
	
	/*Idea:
	*store path tiles in linked list, so that they are ordered by position on map 
	*the enemyPath method will then translate the enemies one by one to each tile
	*at each tile the position of the enemy is saved so the towers can retrieve it to attack
	*/
	
	/*
	 *Idea 2:
	 *get (x,y) position of enemy nodes (circles) and use that to attack
	 *towers attack by check if (x,y) of each enemy fits into its range, then attacks
	 *the field contains all enemies, enemies are removed from the field when they reach the end of the board or when their health reaches 0 
	 */
	
	public void sendEnemies(boolean wave, ArrayList<Enemy> field, Pane grid) {
		
		
			Enemy sent = new Enemy(1,10,10,100,1000,"Enemy");
			grid.getChildren().add(sent.getNode());
			enemyPath(sent, sent.getSpeed());
			field.add(sent);
			
			
		
	}
	public void attackT(Tower t, ArrayList<Enemy> field, Pane grid) {
		
		for(int i = 0; i<field.size(); i++) {
			if(inRange(t,field.get(i))) {
				Circle c = new Circle(10, Color.BLUE);
				c.setCenterX(t.getX());
				c.setCenterY(t.getY());
				grid.getChildren().add(c);
				TranslateTransition tt = new TranslateTransition(Duration.millis(250), c);
				tt.setByY(field.get(i).getNode().getTranslateY()-t.getY());
				tt.setByX(field.get(i).getNode().getTranslateX()-t.getX());
				FadeTransition ft = new FadeTransition(Duration.millis(1), c);
				ft.setFromValue(1);
				ft.setToValue(0);
				SequentialTransition seqT = new SequentialTransition(c,tt,ft);
			
				//grid.getChildren().remove(c);
				seqT.play();
				t.attack(field.get(i));
				if(field.get(i).checkHealth(field)) {
					grid.getChildren().remove(field.get(i).getNode());
					field.remove(i);
				}
				return;
			}
		}
	}
	public boolean inRange(Tower t, Enemy e) {
		double distance = Math.sqrt(Math.abs((t.getX()-e.getNode().getTranslateX())*(t.getX()-e.getNode().getTranslateX()) + (t.getY() - e.getNode().getTranslateY())*(t.getY() - e.getNode().getTranslateY())));
		if(distance<=t.getAOE()) {
			return true;
		}
		return false;
	}
	public void enemyKilled() {
		
	}

}




