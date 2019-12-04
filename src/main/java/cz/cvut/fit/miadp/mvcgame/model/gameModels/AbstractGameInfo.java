package cz.cvut.fit.miadp.mvcgame.model.gameModels;

import cz.cvut.fit.miadp.mvcgame.model.GameObject;
import cz.cvut.fit.miadp.mvcgame.visitor.IVisitor;

public abstract class AbstractGameInfo extends GameObject {
    private String text;

    public String getText() {
        return this.text;
    }

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visitGameInfo(this);
    }
}
