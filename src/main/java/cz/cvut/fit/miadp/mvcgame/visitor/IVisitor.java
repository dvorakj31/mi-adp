package cz.cvut.fit.miadp.mvcgame.visitor;

import cz.cvut.fit.miadp.mvcgame.bridge.IGameGraphics;
import cz.cvut.fit.miadp.mvcgame.model.gameModels.*;

public interface IVisitor {
    void visitCannon(AbstractCannon cannon);
    void visitGameInfo(AbstractGameInfo info);
    void visitEnemy(AbstractEnemy enemy);
    void visitMissile(AbstractMissile missile);
    void visitCollision(AbstractCollision collision);

    void setGraphics(IGameGraphics gr);
}
