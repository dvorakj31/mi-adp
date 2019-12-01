package cz.cvut.fit.miadp.mvcgame.bridge;

import cz.cvut.fit.miadp.mvcgame.model.Position;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class JavaFxGraphics implements IGameGraphicsImplementor {

    private final GraphicsContext gc;

    public JavaFxGraphics(javafx.scene.canvas.GraphicsContext gc) {
        this.gc = gc;
    }

    @Override
    public void drawImage(String path, Position pos) {
        Image fitIcon = new Image(path);
        this.gc.drawImage(fitIcon, pos.getX(), pos.getY());
    }

    @Override
    public void drawText(String text, Position pos) {

    }

    @Override
    public void drawRectangle(Position leftTop, Position rightBottom) {

    }
}