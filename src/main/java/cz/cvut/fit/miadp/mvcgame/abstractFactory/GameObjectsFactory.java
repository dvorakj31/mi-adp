package cz.cvut.fit.miadp.mvcgame.abstractFactory;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.GameModel;
import cz.cvut.fit.miadp.mvcgame.model.gameModels.*;
import cz.cvut.fit.miadp.mvcgame.model.gameModels.objects.*;
import cz.cvut.fit.miadp.mvcgame.strategy.IMovingStrategy;
import cz.cvut.fit.miadp.mvcgame.strategy.RealisticMoveStrategy;
import cz.cvut.fit.miadp.mvcgame.strategy.SimpleMoveStrategy;

import java.util.Random;

public class GameObjectsFactory implements IGameObjectsFactory {
    private IMovingStrategy activeMoveStrategy;
    private GameModel model;
    private Random r = new Random();

    public GameObjectsFactory(GameModel model) {
        this.model = model;
        this.activeMoveStrategy = model.getMovingStrategy();
    }

    @Override
    public AbstractCannon createCannon() {
        AbstractCannon cannon = new Cannon(this);
        cannon.setPosX(MvcGameConfig.INIT_CANNON_X);
        cannon.setPosY(MvcGameConfig.INIT_CANNON_Y);
        return cannon;
    }

    @Override
    public AbstractEnemy createEnemy() {
        int posY = r.nextInt(MvcGameConfig.MAX_Y);
        int posX = MvcGameConfig.MAX_X / 4 + r.nextInt(3 * MvcGameConfig.MAX_X / 4) - 20;
        return new Enemy(posX, posY);
    }

    @Override
    public AbstractCollision createCollision(int x, int y) {
        return new Collision(x, y);
    }

    @Override
    public AbstractMissile createMissile() {
        AbstractCannon cannon = this.model.getCannon();
        return new Missile(cannon.getPosX(), cannon.getPosY(), cannon.getAngle(), cannon.getPower(),
                this.activeMoveStrategy);
    }

    @Override
    public AbstractGameInfo createGameInfo() {
        return new GameInfo(this.model);
    }

    @Override
    public void activateSimpleMoveStrategy() {
        this.activeMoveStrategy = new SimpleMoveStrategy();
        this.model.setMovingStrategy(this.activeMoveStrategy);
    }

    @Override
    public void activateRealisticMoveStrategy() {
        this.activeMoveStrategy = new RealisticMoveStrategy();
        this.model.setMovingStrategy(this.activeMoveStrategy);
    }

    @Override
    public void changeMoveStrategy() {
        if(this.activeMoveStrategy instanceof SimpleMoveStrategy)
            activateRealisticMoveStrategy();
        else
            activateSimpleMoveStrategy();
    }
}
