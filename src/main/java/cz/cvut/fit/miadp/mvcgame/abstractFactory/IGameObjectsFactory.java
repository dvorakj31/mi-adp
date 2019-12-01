package cz.cvut.fit.miadp.mvcgame.abstractFactory;

import cz.cvut.fit.miadp.mvcgame.model.gameModels.*;

public interface IGameObjectsFactory {
    AbstractCannon createCannon();
    AbstractEnemy createEnemy();
    AbstractCollision createCollision();
    AbstractMissile createMissile();
    AbstractGameInfo createGameInfo();
    void activateSimpleMoveStrategy();
    void activateRealisticMoveStrategy();
}
