package wraith;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Player extends Application{
	double hp=100;
	boolean hellMode = false;
	boolean pause = false;
	String name;
	int bank;
	int waveNumber;
	int gameSpeed;
	public static void main(String[] args) {
		launch(args);
		
		
	}
	
	public boolean pause(boolean pause) { 
		
		
		return false;
	}
	
	public int addmoney(int money) {
		
		
		return money;
	}
	
	public int subMoney(int money) {
		
		
		return money;
	}
	
	public void buildBoard() {
		
		
	}
	
	public void updateBoard() {
		
	}
	
	public void loop() {
		boolean pause = false;
	}
	
	public void damage(int dmg) {
		this.hp-=dmg;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Wraith Games");
        Button btn = new Button();
        btn.setText("Play");
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("F");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
		
		
	}

	
	

}
