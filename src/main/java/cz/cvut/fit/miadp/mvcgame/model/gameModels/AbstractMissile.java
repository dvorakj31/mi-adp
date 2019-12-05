package cz.cvut.fit.miadp.mvcgame.model.gameModels;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.strategy.IMovingStrategy;
import cz.cvut.fit.miadp.mvcgame.visitor.IVisitor;

public abstract class AbstractMissile extends LifetimeLimitedGO {
    private IMovingStrategy movingStrategy;
    private int initX;
    private int initY;
    private float initAngle;
    private float initPower;

    public AbstractMissile(int x, int y, float angle, float power, IMovingStrategy strategy) {
        this.posX = this.initX = x;
        this.posY = this.initY = y;
        this.initAngle = angle;
        this.initPower = power;
        this.movingStrategy = strategy;
    }

    public void move() {
        Position pos = this.movingStrategy.updatePosition(this.getPos(), this.initPower, this.initAngle, this.getAge());
        setPosX(pos.getX());
        setPosY(pos.getY());
    }

    public boolean collide(AbstractEnemy enemy) {
        return (Math.abs(this.posX - enemy.getPosX()) < MvcGameConfig.COLLISION_DIVERGENCE) &&
                (Math.abs(this.posY - enemy.getPosY()) < MvcGameConfig.COLLISION_DIVERGENCE);
    }

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visitMissile(this);
    }
}
