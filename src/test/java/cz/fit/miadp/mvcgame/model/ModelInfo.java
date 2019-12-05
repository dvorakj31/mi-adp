package cz.fit.miadp.mvcgame.model;

import cz.cvut.fit.miadp.mvcgame.model.GameModel;
import cz.cvut.fit.miadp.mvcgame.model.gameModels.AbstractCannon;
import cz.cvut.fit.miadp.mvcgame.model.gameModels.AbstractGameInfo;
import cz.cvut.fit.miadp.mvcgame.model.gameModels.objects.GameInfo;
import cz.cvut.fit.miadp.mvcgame.state.DoubleShootingMode;
import cz.cvut.fit.miadp.mvcgame.state.SimpleShootingMode;
import cz.cvut.fit.miadp.mvcgame.strategy.SimpleMoveStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ModelInfo {

    @Test
    public void testInfoText() {
        GameModel gameModel = mock(GameModel.class);

        AbstractCannon cannon = mock(AbstractCannon.class);
        when(cannon.getAngle()).thenReturn(10.0f);
        when(gameModel.getPower()).thenReturn(3.0f);
        when(gameModel.getMovingStrategy()).thenReturn(new SimpleMoveStrategy());
        when(gameModel.getShootingMode()).thenReturn(new DoubleShootingMode());
        when(gameModel.getCannon()).thenReturn(cannon);

        AbstractGameInfo info = new GameInfo(gameModel);

        assertEquals(info.getText(), "Score: 0 Angle: 10.0 Power: 3.0 Move strategy: Simple Shoot mode: Double");
    }
}
