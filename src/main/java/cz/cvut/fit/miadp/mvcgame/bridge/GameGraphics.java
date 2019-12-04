package cz.cvut.fit.miadp.mvcgame.bridge;

import cz.cvut.fit.miadp.mvcgame.model.Position;

public class GameGraphics implements IGameGraphics {
    private IGameGraphicsImplementor implementor;

    public GameGraphics(IGameGraphicsImplementor implementor) {
        this.implementor = implementor;
    }

    @Override
    public void drawImage(String path, Position pos) {
        implementor.drawImage(path, pos);
    }

    @Override
    public void drawText(String text, Position pos) {
        implementor.drawText(text, pos);
    }

    @Override
    public void drawRectangle(Position leftTop, Position rightBottom) {
        implementor.drawRectangle(leftTop, rightBottom);
    }

    @Override
    public void clearRect(int i, int i1, int maxX, int maxY) {
        implementor.clearRect(i, i1, maxX, maxY);
    }
}
