package cz.cvut.fit.miadp.mvcgame.abstractFactory;

import cz.cvut.fit.miadp.mvcgame.model.GameModel;
import cz.cvut.fit.miadp.mvcgame.model.gameModels.*;

public interface IGameObjectsFactory {
    AbstractCannon createCannon();
    AbstractEnemy createEnemy();
    AbstractCollision createCollision(int x, int y);
    AbstractMissile createMissile();
    AbstractGameInfo createGameInfo();
    void activateSimpleMoveStrategy();
    void activateRealisticMoveStrategy();

    void changeMoveStrategy();
}
