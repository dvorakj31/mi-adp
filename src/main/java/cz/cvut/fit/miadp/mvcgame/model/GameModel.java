package cz.cvut.fit.miadp.mvcgame.model;

import cz.cvut.fit.miadp.mvcgame.abstractFactory.GameObjectsFactory;
import cz.cvut.fit.miadp.mvcgame.abstractFactory.IGameObjectsFactory;
import cz.cvut.fit.miadp.mvcgame.command.AbstractGameCommand;
import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.controller.GameController;
import cz.cvut.fit.miadp.mvcgame.model.gameModels.*;
import cz.cvut.fit.miadp.mvcgame.observer.IObservable;
import cz.cvut.fit.miadp.mvcgame.observer.IObserver;
import cz.cvut.fit.miadp.mvcgame.strategy.IMovingStrategy;
import cz.cvut.fit.miadp.mvcgame.strategy.SimpleMoveStrategy;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.IntStream;

public class GameModel implements IObservable, IGameModel {

    private int score;
    private Timer time;

    private AbstractCannon cannon;
    private AbstractGameInfo info;
    private ArrayList<AbstractEnemy> enemies = new ArrayList<>();
    private ArrayList<AbstractMissile> missiles = new ArrayList<>();
    private ArrayList<AbstractCollision> collisions = new ArrayList<>();;
    private IMovingStrategy strategy;
    private ArrayList<IObserver> observers = new ArrayList<IObserver>();
    private IGameObjectsFactory goFactory;
    private LinkedBlockingQueue<AbstractGameCommand> unexecutedCmds = new LinkedBlockingQueue<>();
    private Stack<AbstractGameCommand> executedCmds = new Stack<>();


    public ArrayList<AbstractMissile> getMissiles() {
        return this.missiles;
    }

    public ArrayList<AbstractEnemy> getEnemies() {
        return this.enemies;
    }

    public ArrayList<AbstractCollision> getCollisions() {
        return this.collisions;
    }

    private class Memento {
        private int score;
        private AbstractCannon cannon;
        private ArrayList<AbstractEnemy> enemies;
        private IMovingStrategy strategy;

        Memento(int score, AbstractCannon cannon, ArrayList<AbstractEnemy> enemies,
                IMovingStrategy strategy) {
            this.score = score;
            this.cannon = cannon;
            this.enemies = enemies;
            this.strategy = strategy;
        }
    }

    public GameModel() {

        this.goFactory = new GameObjectsFactory(this);
        this.strategy = new SimpleMoveStrategy();

        initGame();
        initTimer();
    }

    private void initGame() {
        this.score = 0;
        this.cannon = this.goFactory.createCannon();
        this.info = this.goFactory.createGameInfo();
        IntStream.range(0, MvcGameConfig.MAX_ENEMIES).forEach(i -> this.enemies.add(this.goFactory.createEnemy()));
    }

    private void initTimer() {
        this.time = new Timer();
        this.time.schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                timeTick();
            }
        }, 0, MvcGameConfig.TIME_TICK);
    }

    public AbstractCannon getCannon() {
        return this.cannon;
    }


    @Override
    public ArrayList<GameObject> getGameObjects() {
        ArrayList<GameObject> ret = new ArrayList<>();
        ret.add(this.cannon);
        ret.addAll(this.enemies);
        ret.addAll(this.missiles);
        ret.addAll(this.collisions);
        return ret;
    }

    @Override
    public void moveCannonUp() {
        this.cannon.setY(this.cannon.getPosY() - MvcGameConfig.MOVE_SPEED);
        this.notifyMyObservers();
    }

    @Override
    public void moveCannonDown() {
        this.cannon.setY(this.cannon.getPosY() + MvcGameConfig.MOVE_SPEED);
        this.notifyMyObservers();
    }

    @Override
    public void cannonShoot() {
        AbstractMissile missile = this.goFactory.createMissile();
        this.missiles.add(missile);
        this.notifyMyObservers();
    }

    @Override
    public void cannonToggleShootingMode() {

    }

    @Override
    public void registerObserver(IObserver obs) {
            this.observers.add(obs);
    }

    @Override
    public void unregisterObserver(IObserver obs) {
        this.observers.remove(obs);
    }

    @Override
    public void notifyMyObservers() {
        for (IObserver obs : this.observers)
            obs.update();
    }

    @Override
    public void setMemento(Object memento) {
        Memento m = (Memento)memento;
        this.score = m.score;
        this.strategy = m.strategy;
        this.enemies = m.enemies;
        this.cannon = m.cannon;
    }

    @Override
    public Object createMemento() {
        return new Memento(this.score, this.cannon, this.enemies, this.strategy);
    }

    @Override
    public void registerCommand(AbstractGameCommand cmd) {
        this.unexecutedCmds.add(cmd);
    }

    @Override
    public void undoLastCmd() {
        try {
            AbstractGameCommand cmd = this.executedCmds.pop();
            cmd.unexecute();
        } catch (Exception e) {

        }
    }

    @Override
    public IMovingStrategy getMovingStrategy() {
        return this.strategy;
    }

    @Override
    public void timeTick() {
        this.executeCommands();
        this.moveGameObjects();
    }

    private void moveGameObjects() {
        this.moveMissiles();
        this.checkCollisions();
        this.removeInvisible();

        this.notifyMyObservers();
    }

    private void checkCollisions() {
        ArrayList<AbstractMissile> collidedMissiles = new ArrayList<>();
        ArrayList<AbstractEnemy> collidedEnemies = new ArrayList<>();
        for(int i = 0; i < missiles.size(); i++) {
            for (AbstractEnemy enemy : enemies) {
                if (missiles.get(i).collide(enemy)) {
                    collidedMissiles.add(missiles.get(i));
                    collidedEnemies.add(enemy);
                }
            }
        }

        for (AbstractEnemy enemy : collidedEnemies) {
            this.collisions.add(this.goFactory.createCollision(enemy.posX, enemy.posY));
        }

        missiles.removeAll(collidedMissiles);
        enemies.removeAll(collidedEnemies);

    }

    private void removeInvisible() {
        ArrayList<AbstractMissile> missilesToRemove = new ArrayList<>();
        for(AbstractMissile m: this.missiles) {
            if (m.getPosX() < 0 || m.getPosX() > MvcGameConfig.MAX_X ||
                    m.getPosY() < 0 || m.getPosY() > MvcGameConfig.MAX_Y)
                missilesToRemove.add(m);
        }
        this.missiles.removeAll(missilesToRemove);

        ArrayList<AbstractCollision> collisionsToRemove = new ArrayList<>();
        for(AbstractCollision collision: this.collisions) {
            if (collision.getAge() > MvcGameConfig.COLLISION_LIFETIME)
                collisionsToRemove.add(collision);
        }

        this.collisions.removeAll(collisionsToRemove);
    }

    private void moveMissiles() {
        for (int i = 0; i < this.missiles.size(); i++) {
            this.missiles.get(i).move();
        }
    }

    @Override
    public void executeCommands() {
        while(!this.unexecutedCmds.isEmpty()) {
            AbstractGameCommand cmd = unexecutedCmds.poll();
            this.executedCmds.push(cmd);
            cmd.doExecute();
        }
    }

    public void setMovingStrategy(IMovingStrategy strategy) {
        this.strategy = strategy;
        notifyMyObservers();
    }
}
