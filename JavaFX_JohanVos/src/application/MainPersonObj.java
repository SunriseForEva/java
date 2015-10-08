package application;


import java.util.LinkedList;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class MainPersonObj extends GameObject {
	LinkedList<BonusObj> allBonus = new LinkedList<BonusObj>();
	int a = 0,b = 0; 
	int score;
	HBox scoreVisual;
	Score currentScore;
	
	public MainPersonObj(String namePic,Score score)
	{
		super(namePic);
		Image mainPersonImage = new Image(pic);
		
		currentScore = score;
		scoreVisual = new HBox(5);
		scoreVisual = currentScore.getVisScore();

		recForGameObjec.setLayoutX(0);
		recForGameObjec.setLayoutY(0);
		recForGameObjec.setWidth(100);
		recForGameObjec.setHeight(70);

		recForGameObjec.setFill(new ImagePattern(mainPersonImage, 0, 0, 1, 1, true));

		recForGameObjec.setFocusTraversable(true);
	}
	
	@Override
	protected Void call() {
//		try{
//			
//		while(true){
//			while(a<350){
//				x = new SimpleIntegerProperty(a++);
//				y = new SimpleIntegerProperty(b++);
//				Thread.sleep(10);
//				p.layoutXProperty().bind(x);
//				p.layoutYProperty().bind(y);
//			}
//			
//			while( b >= 0 ){
//				x = new SimpleIntegerProperty(a);
//				y = new SimpleIntegerProperty(b--);
//				Thread.sleep(10);
//				p.layoutXProperty().bind(x);
//				p.layoutYProperty().bind(y);
//			}
//			
//			while( a >= 0 ){
//				x = new SimpleIntegerProperty(a--);
//				y = new SimpleIntegerProperty(b);
//				Thread.sleep(10);
//				p.layoutXProperty().bind(x);
//				p.layoutYProperty().bind(y);
//			}
//			
//		}
//		
//		}catch(Exception e){
//			e.printStackTrace();
//		}
		
		handleAct();
		
		return null;
	}
	
	private void handleAct(){
		javafx.event.EventHandler<KeyEvent> movePesron = new javafx.event.EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.DOWN) && b<=715 ){
					
					b+=5;
					
					posX = new SimpleIntegerProperty(a);
					posY = new SimpleIntegerProperty(b);
					recForGameObjec.layoutXProperty().bind(posX);
					recForGameObjec.layoutYProperty().bind(posY);
				}
				
				if(event.getCode().equals(KeyCode.UP) && b>=5 ){
					
					b-=5;
					
					posX = new SimpleIntegerProperty(a);
					posY = new SimpleIntegerProperty(b);
					recForGameObjec.layoutXProperty().bind(posX);
					recForGameObjec.layoutYProperty().bind(posY);
				}
				
				if(event.getCode().equals(KeyCode.LEFT) && a>=5 ){
					
					a-=5;
					
					posX = new SimpleIntegerProperty(a);
					posY = new SimpleIntegerProperty(b);
					recForGameObjec.layoutXProperty().bind(posX);
					recForGameObjec.layoutYProperty().bind(posY);
				}
				
				if(event.getCode().equals(KeyCode.RIGHT) && a<=950 ){
					
					a+=5;
					
					posX = new SimpleIntegerProperty(a);
					posY = new SimpleIntegerProperty(b);
					recForGameObjec.layoutXProperty().bind(posX);
					recForGameObjec.layoutYProperty().bind(posY);
				}
				
				checkPositionPerson();
				isPlayerWinTheGame();
			}
		};

		recForGameObjec.addEventHandler(KeyEvent.KEY_PRESSED, movePesron);
	}

	public Rectangle getGameObject() {
		return recForGameObjec;
	}

	public LinkedList<BonusObj> getAllBonus() {
		return allBonus;
	}

	public void setAllBonus(LinkedList<BonusObj> allBonus) {
		this.allBonus = allBonus;
	}
	
	private void checkPositionPerson(){
		
		for(int i = 0; i < allBonus.size() ; ++i){
			if(((recForGameObjec.getLayoutX() -  allBonus.get(i).getGameObject().getLayoutX())<=25 && (recForGameObjec.getLayoutX() -  allBonus.get(i).getGameObject().getLayoutX())>=-20) &&
					((allBonus.get(i).getGameObject().getLayoutY() - recForGameObjec.getLayoutY())<=25)&&(allBonus.get(i).getGameObject().getLayoutY() - recForGameObjec.getLayoutY())>=-20)
			{
				allBonus.get(i).getGameObject().setVisible(false);	
				allBonus.remove(allBonus.get(i));
				i--;
				score += 100;
				
				currentScore.setScore(score);
				currentScore.setVisScore();
				scoreVisual = new HBox(5);
				scoreVisual = currentScore.getVisScore();
			}
		}
	}

	public HBox getScoreVisual() {
		return scoreVisual;
	}

	private void isPlayerWinTheGame(){
		if(allBonus.size() <= 0){
			System.out.println("You win the game !!!"
					+ "Your bonus is " + score);
			recForGameObjec.setVisible(false);
		}
	}
}
