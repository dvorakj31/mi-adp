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
        return new Cannon();
    }

    @Override
    public AbstractEnemy createEnemy() {
        int posY = r.nextInt(MvcGameConfig.MAX_Y);
        int posX = this.model.getCannon().getPosX() * 2 + r.nextInt(MvcGameConfig.MAX_Y - (this.model.getCannon().getPosX() * 2));
        return new Enemy(posX, posY);
    }

    @Override
    public AbstractCollision createCollision(int x, int y) {
        return new Collision(x, y);
    }

    @Override
    public AbstractMissile createMissile() {
        AbstractCannon cannon = this.model.getCannon();
        return new Missile(cannon.getPosX(), cannon.getPosY());
    }

    @Override
    public AbstractGameInfo createGameInfo() {
        return new GameInfo();
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
}
