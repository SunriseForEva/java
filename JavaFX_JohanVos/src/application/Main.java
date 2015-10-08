package application;
	
import java.util.LinkedList;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;


public class Main extends Application {
	Score gamescore = new Score(0);
	
	@Override
	public void start(Stage primaryStage) {
		try {
			MainPersonObj b1 = new MainPersonObj("file:res/pajarito3v1.png",gamescore);
			LinkedList<BonusObj> allBonus = new LinkedList<BonusObj>();
			for (int i = 0; i <= 8; i++) {
				allBonus.add(new BonusObj("file:res/bonus.gif"));
			}
			
			b1.setAllBonus(allBonus);
			
			Pane root = new Pane();
			for(BonusObj obj : allBonus){
				root.getChildren().add(obj.getGameObject());
			}
			try{
				root.getChildren().add(gamescore.getVisScore());
			}catch(NullPointerException e){
				e.printStackTrace();
			}
			
			root.getChildren().add(b1.getGameObject());
			root.setBackground(new Background(new BackgroundImage(new Image("file:res/background.jpg"), 
					BackgroundRepeat.REPEAT,
					BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT,
					BackgroundSize.DEFAULT )));
			Scene scene = new Scene(root,1024,768);
			
			Thread thread = new  Thread(b1);
			thread.start();
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
