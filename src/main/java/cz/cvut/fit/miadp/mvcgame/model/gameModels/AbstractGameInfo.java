package cz.cvut.fit.miadp.mvcgame.model.gameModels;

import cz.cvut.fit.miadp.mvcgame.MvcGame;
import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.GameModel;
import cz.cvut.fit.miadp.mvcgame.model.GameObject;
import cz.cvut.fit.miadp.mvcgame.visitor.IVisitor;

public abstract class AbstractGameInfo extends GameObject {
    private final GameModel model;

    public AbstractGameInfo(GameModel model) {
        this.model = model;
        this.setPosX(MvcGameConfig.INIT_CANNON_X + 20);
        this.setPosY(20);
    }

    public String getText() {
        return "Score: " + this.model.getScore()
                + " Angle: " + this.model.getCannon().getAngle()
                + " Power: " + this.model.getPower()
                + " Move strategy: " + this.model.getMovingStrategy().getName()
                + " Shoot mode: " + this.model.getShootingMode().getName();
    }



    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visitGameInfo(this);
    }
}
