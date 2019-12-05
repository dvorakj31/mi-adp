package cz.fit.miadp.mvcgame.model;

import cz.cvut.fit.miadp.mvcgame.model.GameObject;
import cz.cvut.fit.miadp.mvcgame.model.gameModels.AbstractEnemy;
import cz.cvut.fit.miadp.mvcgame.model.gameModels.AbstractMissile;
import cz.cvut.fit.miadp.mvcgame.model.gameModels.objects.Enemy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CollisionTest {

    @Test
    public void testCollidesWith() {
        testCollision(100, 100, true);
    }

    @Test
    public void testCollidesWithNegativeCase() {
        testCollision(150, 130, false);
    }

    private void testCollision(int x, int y, boolean expectedResult) {
        AbstractMissile missile = Mockito.mock(AbstractMissile.class);
        when(missile.getPosX()).thenReturn(100);
        when(missile.getPosY()).thenReturn(100);
        Mockito.doCallRealMethod().when(missile).collide(Matchers.anyObject());

        AbstractEnemy enemy = new Enemy(x, y);

        boolean result = missile.collide(enemy);
        assertEquals(expectedResult, result);
    }

}