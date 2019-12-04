package cz.cvut.fit.miadp.mvcgame.view;

import cz.cvut.fit.miadp.mvcgame.bridge.IGameGraphics;
import cz.cvut.fit.miadp.mvcgame.bridge.IGameGraphicsImplementor;
import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.controller.GameController;
import cz.cvut.fit.miadp.mvcgame.model.GameModel;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameModels.AbstractCannon;
import cz.cvut.fit.miadp.mvcgame.model.gameModels.AbstractCollision;
import cz.cvut.fit.miadp.mvcgame.model.gameModels.AbstractEnemy;
import cz.cvut.fit.miadp.mvcgame.model.gameModels.AbstractMissile;
import cz.cvut.fit.miadp.mvcgame.observer.IObserver;

import cz.cvut.fit.miadp.mvcgame.proxy.GameModelProxy;
import javafx.scene.image.Image;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

public class GameView implements IObserver {
    private final GameModel model;
    private IGameGraphics gr;
    private final String cannonPath = "images/cannon.png";
    private final String missilePath = "images/missile.png";
    private final String enemy1Path = "images/enemy1.png";
    private final String enemy2Path = "images/enemy2.png";
    private final String collisionPath = "images/collision.png";

    public GameView(GameModel model) {
        this.model = model;
    }

    public GameController createController() {
        return new GameController(new GameModelProxy(this.model));
    }

    @Override
    public void update() {
        render();
    }

    public void setGraphics(IGameGraphics gr) {
        this.gr = gr;
    }

    private void render() {
        this.gr.clearRect(0, 0, MvcGameConfig.MAX_X, MvcGameConfig.MAX_Y);
        AbstractCannon cannon = this.model.getCannon();
        this.gr.drawImage(cannonPath, new Position(cannon.getX(), cannon.getY()));

        ArrayList<AbstractMissile> missiles = this.model.getMissiles();
        for (int i = 0; i < missiles.size(); i++)
            this.gr.drawImage(missilePath, new Position(missiles.get(i).getPosX(), missiles.get(i).getPosY()));

        ArrayList<AbstractEnemy> enemies = this.model.getEnemies();
        for (int i = 0; i < enemies.size(); i++)
            this.gr.drawImage((i % 2 == 0 ? enemy1Path : enemy2Path),
                    new Position(enemies.get(i).getPosX(), enemies.get(i).getPosY()));

        ArrayList<AbstractCollision> collisions = this.model.getCollisions();
        for (int i = 0; i < collisions.size(); i++)
            this.gr.drawImage(collisionPath, new Position(collisions.get(i).getPosX(), collisions.get(i).getPosY()));
    }
}
