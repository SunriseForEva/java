package application;

import javafx.beans.property.IntegerProperty;
import javafx.concurrent.Task;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 * Created by yarik on 15.10.15.
 */
public abstract class GameObject extends Task<Void>{
    protected IntegerProperty posX,posY;
    protected String pic ;
    protected Rectangle recForGameObjec;

    public GameObject(String namePic){
        pic = namePic;
        Image bonusImage = new Image(pic);
        recForGameObjec = new Rectangle();
        recForGameObjec.setLayoutX(0);
        recForGameObjec.setLayoutY(0);
        recForGameObjec.setWidth(0);
        recForGameObjec.setHeight(0);

        recForGameObjec.setFill(new ImagePattern(bonusImage, 0, 0, 1, 1, true));
    }

    public abstract Rectangle getGameObject();
}
