package application;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class BonusObj extends GameObject{

	public BonusObj(String namePic){
		super(namePic);

		Image bonusImage = new Image(pic);

		Random rm = new Random();

		recForGameObjec = new Rectangle();
		recForGameObjec.setLayoutX(rm.nextInt(950));
		recForGameObjec.setLayoutY(rm.nextInt(710));
		recForGameObjec.setWidth(56);
		recForGameObjec.setHeight(66);

		recForGameObjec.setFill(new ImagePattern(bonusImage,0,0,1,1,true));
	}



	public Rectangle getGameObject() {
		return recForGameObjec;
	}

	@Override
	protected Void call() throws Exception {
		return null;
	}
}
