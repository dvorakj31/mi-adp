package cz.cvut.fit.miadp.mvcgame.model;

import cz.cvut.fit.miadp.mvcgame.command.AbstractGameCommand;
import cz.cvut.fit.miadp.mvcgame.observer.IObserver;
import cz.cvut.fit.miadp.mvcgame.strategy.IMovingStrategy;

import java.util.ArrayList;

public interface IGameModel {
    ArrayList<GameObject> getGameObjects();
    void moveCannonUp();
    void moveCannonDown();
    void cannonShoot();
    void cannonPowerDec();
    void cannonPowerInc();
    void cannonAimUp();
    void cannonAimDown();
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

    void changeMovingStrategy();
}
