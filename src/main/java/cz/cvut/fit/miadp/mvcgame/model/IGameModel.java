package cz.cvut.fit.miadp.mvcgame.model;

import cz.cvut.fit.miadp.mvcgame.command.AbstractGameCommand;
import cz.cvut.fit.miadp.mvcgame.observer.IObserver;
import cz.cvut.fit.miadp.mvcgame.strategy.IMovingStrategy;

public interface IGameModel {
    void getGameObjects();
    void moveCannonUp();
    void moveCannonDown();
    void cannonShoot();
    void cannonToggleShootingMode();

    void registerObserver(IObserver obs);
    void unregisterObserver(IObserver obs);
    void notifyMyObservers();

    void setMemento(Object o);
    Object createMemento();

    void registerCommand(AbstractGameCommand cmd);
    void undoLastCmd();

    IMovingStrategy getMovingStrategy();

    void timeTick();

    void executeCommands();
}
