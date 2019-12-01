package cz.cvut.fit.miadp.mvcgame.abstractFactory;

import cz.cvut.fit.miadp.mvcgame.model.gameModels.*;
import cz.cvut.fit.miadp.mvcgame.strategy.IMovingStrategy;

public class GameObjectsFactory_A implements IGameObjectsFactory {
    private IMovingStrategy activeMoveStrategy;

    @Override
    public AbstractCannon createCannon() {
        return null;
    }

    @Override
    public AbstractEnemy createEnemy() {
        return null;
    }

    @Override
    public AbstractCollision createCollision() {
        return null;
    }

    @Override
    public AbstractMissile createMissile() {
        return null;
    }

    @Override
    public AbstractGameInfo createGameInfo() {
        return null;
    }

    @Override
    public void activateSimpleMoveStrategy() {

    }

    @Override
    public void activateRealisticMoveStrategy() {

    }


}
