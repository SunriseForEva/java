package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Task;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class MovableButtonFromLeftTop extends Task<Void> {
	private String fileName = "file:res/pajarito3v1.png";
	Image image ;
	Rectangle p ;
	
	Button movableButton = new Button(";)");
	IntegerProperty  x , y;  
	int a = 0,b = 0; 
	boolean stop = false;
	
	public MovableButtonFromLeftTop()
	{
		image = new Image(fileName);
		p = new Rectangle();

        p.setLayoutX(0);
        p.setLayoutY(0);
        p.setWidth(70);
        p.setHeight(51);
		
        p.setFill(new ImagePattern(image, 0, 0, 1, 1, true));
        
		p.setLayoutX(a);
		p.setLayoutX(b);
	}
	
	@Override
	protected Void call() {
		
		try{
			
		while(true){
			while(a<350){
				x = new SimpleIntegerProperty(a++);
				y = new SimpleIntegerProperty(b++);
				Thread.sleep(10);
				p.layoutXProperty().bind(x);
				p.layoutYProperty().bind(y);
			}
			
			while( b >= 0 ){
				x = new SimpleIntegerProperty(a);
				y = new SimpleIntegerProperty(b--);
				Thread.sleep(10);
				p.layoutXProperty().bind(x);
				p.layoutYProperty().bind(y);
			}
			
			while( a >= 0 ){
				x = new SimpleIntegerProperty(a--);
				y = new SimpleIntegerProperty(b);
				Thread.sleep(10);
				p.layoutXProperty().bind(x);
				p.layoutYProperty().bind(y);
			}
			
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	public Button getMovableButton() {
		return movableButton;
	}

	public Rectangle getP() {
		return p;
	}
	
	

}
