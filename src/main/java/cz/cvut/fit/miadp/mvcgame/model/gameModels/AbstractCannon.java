package cz.cvut.fit.miadp.mvcgame.model.gameModels;

import cz.cvut.fit.miadp.mvcgame.model.GameObject;
import cz.cvut.fit.miadp.mvcgame.visitor.IVisitor;

public abstract class AbstractCannon extends GameObject {

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visitCannon(this);
    }

    public void setX(int x) {
        this.posX = x;
    }

    public void setY(int y) {
        this.posY = y;
    }
}
