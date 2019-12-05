package cz.fit.miadp.mvcgame.model;

import cz.cvut.fit.miadp.mvcgame.command.AbstractGameCommand;
import cz.cvut.fit.miadp.mvcgame.model.GameModel;
import cz.cvut.fit.miadp.mvcgame.model.gameModels.AbstractEnemy;
import cz.cvut.fit.miadp.mvcgame.model.gameModels.AbstractGameInfo;
import cz.cvut.fit.miadp.mvcgame.strategy.IMovingStrategy;
import cz.cvut.fit.miadp.mvcgame.strategy.RealisticMoveStrategy;
import cz.cvut.fit.miadp.mvcgame.strategy.SimpleMoveStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class GameModelTest {

    private static final float DELTA = 1e-6f;

    @Test
    public void testConstructor() {
        GameModel gameModel = new GameModel();

        IMovingStrategy movementStrategy = gameModel.getMovingStrategy();
        assertNotNull(movementStrategy);
        assertEquals(0, gameModel.getScore());
        assertNotNull(gameModel.getCannon());
        assertEquals(1.0f, gameModel.getPower(), DELTA);
        assertNotNull(gameModel.getShootingMode());
    }


    //Tests usual test case of undo last command
    @Test
    public void testUndoLastCommand() {
        GameModel gameModel = new GameModel();

        AbstractGameCommand command = Mockito.mock(AbstractGameCommand.class);
        AbstractGameCommand command1 = Mockito.mock(AbstractGameCommand.class);
        Stack<AbstractGameCommand> commands = new Stack<>();
        commands.push(command);
        commands.push(command1);
        Whitebox.setInternalState(gameModel, "executedCmds", commands);

        gameModel.undoLastCmd();
        Mockito.verify(command1, Mockito.atLeast(1)).unexecute();
        Mockito.verify(command, Mockito.never()).unexecute();
    }


    @Test
    public void testSwitchMovementStrategy() {
        GameModel gameModel = new GameModel();

        assertEquals(new SimpleMoveStrategy().getName(), gameModel.getMovingStrategy().getName());
        gameModel.changeMovingStrategy();
        assertEquals(new RealisticMoveStrategy().getName(), gameModel.getMovingStrategy().getName());
    }

}
