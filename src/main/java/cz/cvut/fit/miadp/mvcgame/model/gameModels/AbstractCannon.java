package cz.cvut.fit.miadp.mvcgame.model.gameModels;

import cz.cvut.fit.miadp.mvcgame.abstractFactory.IGameObjectsFactory;
import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.GameObject;
import cz.cvut.fit.miadp.mvcgame.state.DoubleShootingMode;
import cz.cvut.fit.miadp.mvcgame.state.IShootingMode;
import cz.cvut.fit.miadp.mvcgame.state.SimpleShootingMode;
import cz.cvut.fit.miadp.mvcgame.visitor.IVisitor;

import java.util.ArrayList;

public abstract class AbstractCannon extends GameObject {

    private float power = 1.0f;
    private IShootingMode mode;
    private float angle = 0.0f;

    private SimpleShootingMode singleMode = new SimpleShootingMode();
    private DoubleShootingMode doubleMode = new DoubleShootingMode();

    private IGameObjectsFactory goFactory;

    public AbstractCannon(IGameObjectsFactory factory) {
        this.mode = singleMode;
        this.goFactory = factory;
    }

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visitCannon(this);
    }

    public void moveUp() {
        this.posY = this.getPosY() - MvcGameConfig.MOVE_SPEED;
    }

    public void moveDown() {
        this.posY = this.getPosY() + MvcGameConfig.MOVE_SPEED;
    }

    public void aimUp() {
        this.angle -= MvcGameConfig.ANGLE_STEP;
    }

    public void aimDown() {
        this.angle += MvcGameConfig.ANGLE_STEP;
    }

    public void increasePower() {
        if (this.power < MvcGameConfig.MAX_POWER)
            this.power += MvcGameConfig.POWER_STEP;
    }

    public void decreasePower() {
        if (this.power - MvcGameConfig.POWER_STEP > 0.0f)
            this.power -= MvcGameConfig.POWER_STEP;
    }

    public ArrayList<AbstractMissile> shoot() {
        return mode.shoot(this);
    }

    public AbstractMissile simpleShot() {
        return goFactory.createMissile();
    }

    public void toggleMode() {
        if (this.mode == this.singleMode)
            this.mode = this.doubleMode;
        else
            this.mode = this.singleMode;
    }

    public float getAngle() {
        return this.angle;
    }

    public float getPower() {
        return this.power;
    }

    public IShootingMode getShootingMode() {
        return this.mode;
    }

}
