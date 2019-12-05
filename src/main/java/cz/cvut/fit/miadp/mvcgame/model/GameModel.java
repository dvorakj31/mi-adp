package cz.cvut.fit.miadp.mvcgame.model;

import cz.cvut.fit.miadp.mvcgame.abstractFactory.GameObjectsFactory;
import cz.cvut.fit.miadp.mvcgame.abstractFactory.IGameObjectsFactory;
import cz.cvut.fit.miadp.mvcgame.command.AbstractGameCommand;
import cz.cvut.fit.miadp.mvcgame.command.UndoCommand;
import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.gameModels.*;
import cz.cvut.fit.miadp.mvcgame.observer.IObservable;
import cz.cvut.fit.miadp.mvcgame.observer.IObserver;
import cz.cvut.fit.miadp.mvcgame.state.IShootingMode;
import cz.cvut.fit.miadp.mvcgame.state.SimpleShootingMode;
import cz.cvut.fit.miadp.mvcgame.strategy.IMovingStrategy;
import cz.cvut.fit.miadp.mvcgame.strategy.RealisticMoveStrategy;
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
    private IShootingMode shootingMode;

    private class Memento {
        private final AbstractGameInfo info;
        private int score;
        private AbstractCannon cannon;
        private ArrayList<AbstractEnemy> enemies;
        private IMovingStrategy strategy;

        Memento(int score, AbstractCannon cannon, ArrayList<AbstractEnemy> enemies,
                IMovingStrategy strategy, AbstractGameInfo info) {
            this.score = score;
            this.cannon = cannon;
            this.enemies = enemies;
            this.strategy = strategy;
            this.info = info;
        }
    }

    public GameModel() {
        this.strategy = new SimpleMoveStrategy();
        this.shootingMode = new SimpleShootingMode();
        this.goFactory = new GameObjectsFactory(this);
        this.info = goFactory.createGameInfo();

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
        ret.add(this.info);
        return ret;
    }

    @Override
    public void moveCannonUp() {
        this.cannon.moveUp();
        this.notifyMyObservers();
    }

    @Override
    public void moveCannonDown() {
        this.cannon.moveDown();
        this.notifyMyObservers();
    }

    @Override
    public void cannonShoot() {
        this.missiles.addAll(this.cannon.shoot());
        this.notifyMyObservers();
    }

    @Override
    public void cannonPowerDec() {
        this.cannon.decreasePower();
        this.notifyMyObservers();
    }

    @Override
    public void cannonPowerInc() {
        this.cannon.increasePower();
        this.notifyMyObservers();
    }

    @Override
    public void cannonAimUp() {
        this.cannon.aimUp();
        this.notifyMyObservers();
    }

    @Override
    public void cannonAimDown() {
        this.cannon.aimDown();
        this.notifyMyObservers();
    }

    @Override
    public void cannonToggleShootingMode() {
        this.shootingMode.nextMode(this.cannon);
        this.notifyMyObservers();
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
        try {

            ArrayList<AbstractEnemy> cloned_enemies = new ArrayList<>();
            for(int i = 0; i < this.enemies.size(); i++)
                cloned_enemies.add((AbstractEnemy)this.enemies.get(i).clone());
            return new Memento(this.score, (AbstractCannon)this.cannon.clone(), cloned_enemies, this.strategy,
                    (AbstractGameInfo)this.info.clone());
        } catch(CloneNotSupportedException c) {
            return null;
        }

    }

    @Override
    public void registerCommand(AbstractGameCommand cmd) {
        this.unexecutedCmds.add(cmd);
    }

    @Override
    public void undoLastCmd() {
        try {
            AbstractGameCommand cmd = this.executedCmds.pop();
            if (cmd instanceof UndoCommand)
                cmd = this.executedCmds.pop();
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
        try {
            this.executeCommands();
            this.moveGameObjects();
        } catch (Exception e) {

        }
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
            for (int j = 0; j < enemies.size(); j++) {
                if (missiles.get(i).collide(enemies.get(j))) {
                    collidedMissiles.add(missiles.get(i));
                    collidedEnemies.add(enemies.get(j));
                    this.score++;
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

    @Override
    public void changeMovingStrategy() {
        this.goFactory.changeMoveStrategy();
        this.notifyMyObservers();
    }

    public void setMovingStrategy(IMovingStrategy strategy) {
        this.strategy = strategy;
    }

    public int getScore() {
        return this.score;
    }

    public IShootingMode getShootingMode() {
        return this.cannon.getShootingMode();
    }

    public float getPower() {
        return this.cannon.getPower();
    }

}
