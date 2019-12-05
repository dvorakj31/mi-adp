package cz.cvut.fit.miadp.mvcgame.visitor;

import cz.cvut.fit.miadp.mvcgame.bridge.IGameGraphics;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameModels.*;

public class GameRenderer implements IVisitor {

    private IGameGraphics graphics;
    private final String cannonPath = "images/cannon.png";
    private final String missilePath = "images/missile.png";
    private final String enemy1Path = "images/enemy1.png";
    private final String collisionPath = "images/collision.png";

    @Override
    public void visitCannon(AbstractCannon cannon) {
        drawCannon(cannon);
    }

    @Override
    public void visitGameInfo(AbstractGameInfo info) {
        drawGameInfo(info);
    }

    @Override
    public void visitEnemy(AbstractEnemy enemy) {
        drawEnemy(enemy);
    }

    @Override
    public void visitMissile(AbstractMissile missile) {
        drawMissile(missile);
    }

    @Override
    public void visitCollision(AbstractCollision collision) {
        drawCollision(collision);
    }

    @Override
    public void setGraphics(IGameGraphics gr) {
        this.graphics = gr;
    }

    private void drawCannon(AbstractCannon cannon) {
        this.graphics.drawImage(cannonPath, cannon.getPos());
    }

    private void drawGameInfo(AbstractGameInfo info) {
        this.graphics.drawText(info.getText(), info.getPos());
    }

    private void drawEnemy(AbstractEnemy enemy) {
        this.graphics.drawImage(enemy1Path, enemy.getPos());
    }

    private void drawMissile(AbstractMissile missile) {
        this.graphics.drawImage(missilePath, missile.getPos());
    }

    private void drawCollision(AbstractCollision collision) {
        this.graphics.drawImage(collisionPath, collision.getPos());
    }
}
