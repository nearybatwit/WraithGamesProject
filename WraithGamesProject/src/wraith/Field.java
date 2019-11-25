package wraith;

import javafx.geometry.Insets;
import java.awt.TextField;
import java.io.FileInputStream;

import javafx.animation.*; 
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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
		AnchorPane grid = new AnchorPane();
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
		Group g = new Group();
		FileInputStream file = new FileInputStream("C:\\Images\\tan.png");
		Image map = new Image(file);
		ImageView mapView = new ImageView(map);
		
		//mapView.setX(500);
		//mapView.setY(500);
		
		Group root = new Group(mapView);
		Circle circle = new Circle();
		circle.setRadius(50);
		circle.setFill(Color.BLACK);
		//grid.add(circle, 0, 0);
		//Group root = new Group(circle);
		
		arg0.setScene(new Scene(root, 800, 600));
		arg0.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
