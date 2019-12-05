package cz.fit.miadp.mvcgame.controller;

import cz.cvut.fit.miadp.mvcgame.command.*;
import cz.cvut.fit.miadp.mvcgame.controller.GameController;
import cz.cvut.fit.miadp.mvcgame.model.GameModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class GameControllerTest {

    @Mock
    private GameModel gameModel;

    private GameController gameController;

    @Before
    public void prepareGameController() {
        gameController = new GameController(gameModel);
    }

    @Test
    public void testUP() {
        testKeyPress("UP", MoveCannonUpCommand.class);
    }

    @Test
    public void testDOWN() {
        testKeyPress("DOWN", MoveCannonDownCommand.class);
    }

    @Test
    public void testA() {
        testKeyPress("A", CannonAimUpCommand.class);
    }

    @Test
    public void testD() {
        testKeyPress("D", CannonAimDownCommand.class);
    }

    @Test
    public void testM() {
        testKeyPress("M", ChangeShootMode.class);
    }

    @Test
    public void testL() {
        testKeyPress("L", ChangeMovingStrategy.class);
    }

    @Test
    public void testQ() {
        testKeyPress("Q", CannonPowerDecCommand.class);
    }

    @Test
    public void testE() {
        testKeyPress("E", CannonPowerIncCommand.class);
    }

    @Test
    public void testC() {
        testKeyPress("C", UndoCommand.class);
    }


    private void testKeyPress(String key, Class<? extends AbstractGameCommand> c) {
        gameController.onKeyPress(key);

        verify(gameModel).registerCommand(isA(c));
    }

}
