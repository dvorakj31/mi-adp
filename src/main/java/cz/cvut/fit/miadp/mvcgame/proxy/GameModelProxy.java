package cz.cvut.fit.miadp.mvcgame.proxy;

import cz.cvut.fit.miadp.mvcgame.command.AbstractGameCommand;
import cz.cvut.fit.miadp.mvcgame.model.GameObject;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;
import cz.cvut.fit.miadp.mvcgame.observer.IObserver;
import cz.cvut.fit.miadp.mvcgame.strategy.IMovingStrategy;

import java.util.ArrayList;

public class GameModelProxy implements IGameModel {

    private final IGameModel model;

    public GameModelProxy(IGameModel model) {
        this.model = model;
    }

    @Override
    public ArrayList<GameObject> getGameObjects() {
        return this.model.getGameObjects();
    }

    @Override
    public void moveCannonUp() {
        this.model.moveCannonUp();
    }

    @Override
    public void moveCannonDown() {
        this.model.moveCannonDown();
    }

    @Override
    public void cannonShoot() {
        this.model.cannonShoot();
    }

    @Override
    public void cannonPowerDec() {
        this.model.cannonPowerDec();
    }

    @Override
    public void cannonPowerInc() {
        this.model.cannonPowerInc();
    }

    @Override
    public void cannonAimUp() {
        this.model.cannonAimUp();
    }

    @Override
    public void cannonAimDown() {
        this.model.cannonAimDown();
    }

    @Override
    public void cannonToggleShootingMode() {
        this.model.cannonToggleShootingMode();
    }

    @Override
    public void registerObserver(IObserver obs) {
        this.model.registerObserver(obs);
    }

    @Override
    public void unregisterObserver(IObserver obs) {
        this.model.registerObserver(obs);
    }

    @Override
    public void notifyMyObservers() {
        this.model.notifyMyObservers();
    }

    @Override
    public void setMemento(Object o) {
        this.model.setMemento(o);
    }

    @Override
    public Object createMemento() {
        return this.model.createMemento();
    }

    @Override
    public void registerCommand(AbstractGameCommand cmd) {
        this.model.registerCommand(cmd);
    }

    @Override
    public void undoLastCmd() {
        this.model.undoLastCmd();
    }

    @Override
    public IMovingStrategy getMovingStrategy() {
        return this.model.getMovingStrategy();
    }

    @Override
    public void timeTick() {
        this.model.timeTick();
    }

    @Override
    public void executeCommands() {
        this.model.executeCommands();
    }

    @Override
    public void changeMovingStrategy() {
        this.model.changeMovingStrategy();
    }
}
