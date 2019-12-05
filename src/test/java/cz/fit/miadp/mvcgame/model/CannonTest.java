package cz.fit.miadp.mvcgame.model;

import cz.cvut.fit.miadp.mvcgame.abstractFactory.IGameObjectsFactory;
import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.gameModels.AbstractCannon;
import cz.cvut.fit.miadp.mvcgame.model.gameModels.AbstractMissile;
import cz.cvut.fit.miadp.mvcgame.model.gameModels.objects.Cannon;
import cz.cvut.fit.miadp.mvcgame.model.gameModels.objects.Missile;
import cz.cvut.fit.miadp.mvcgame.state.DoubleShootingMode;
import cz.cvut.fit.miadp.mvcgame.state.SimpleShootingMode;
import cz.cvut.fit.miadp.mvcgame.strategy.RealisticMoveStrategy;
import cz.cvut.fit.miadp.mvcgame.strategy.SimpleMoveStrategy;
import cz.cvut.fit.miadp.mvcgame.visitor.IVisitor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class CannonTest {
    private static final double DELTA = 1E-6;
    private IGameObjectsFactory factory;

    @Before
    public void beforeTest() {
        factory = Mockito.mock(IGameObjectsFactory.class);
    }

    @Test
    public void testConstructorShootingMode() {
        Cannon cannon = new Cannon(factory);

        assertNotNull(cannon.getShootingMode());
        assertEquals(new SimpleShootingMode().getName(), cannon.getShootingMode().getName());
    }

    @Test
    public void testSingleShootingMode() {
        AbstractMissile missile = new Missile(1, 1, 1.6f, 1.5f, new SimpleMoveStrategy());
        Mockito.when(factory.createMissile()).thenReturn(missile);

        AbstractCannon cannon = new Cannon(factory);
        ArrayList<AbstractMissile> shootBatch = cannon.shoot();
        cannon.toggleMode();

        assertNotNull(shootBatch);
        assertEquals(1, shootBatch.size());
        assertEquals(missile, shootBatch.get(0));
        // verification of new shooting mode
        assertEquals(new DoubleShootingMode().getName(), cannon.getShootingMode().getName());
    }

    @Test
    public void testDoubleShootingMode() {
        Missile missile = new Missile(2, 2, 2.6f, 2.5f, new RealisticMoveStrategy());
        Mockito.when(factory.createMissile()).thenReturn(missile);

        // action
        AbstractCannon cannon = new Cannon(factory);
        cannon.toggleMode();
        ArrayList<AbstractMissile> shootBatch = cannon.shoot();
        cannon.toggleMode();

        // verification
        assertNotNull(shootBatch);
        assertEquals(2, shootBatch.size());
        assertEquals(missile, shootBatch.get(1));
        // verification the angle is not changed
        assertEquals(0, cannon.getAngle(), DELTA);
        // verification of new shooting mode
        assertEquals(new SimpleShootingMode().getName(), cannon.getShootingMode().getName());
    }

    @Test
    public void testAimUp() {
        AbstractCannon cannon = new Cannon(factory);
        cannon.aimUp();

        assertEquals(-MvcGameConfig.ANGLE_STEP, cannon.getAngle(), DELTA);
    }

    @Test
    public void testAimDown() {
        AbstractCannon cannon = new Cannon(factory);
        cannon.aimDown();

        assertEquals(MvcGameConfig.ANGLE_STEP, cannon.getAngle(), DELTA);
    }

    @Test
    public void testIncPower() {
        AbstractCannon cannon = new Cannon(factory);
        cannon.increasePower();
        assertEquals(1.0f + MvcGameConfig.POWER_STEP, cannon.getPower(), DELTA);
    }

    @Test
    public void testDecPower() {
        Cannon cannon = new Cannon(factory);
        cannon.decreasePower();

        assertEquals(1.0f, cannon.getPower(), DELTA);
    }

    @Test
    public void testVisitor() {
        IVisitor visitor = Mockito.mock(IVisitor.class);

        AbstractCannon cannon = new Cannon(factory);
        cannon.acceptVisitor(visitor);

        AbstractCannon cannon1 = new Cannon(factory);

        Mockito.verify(visitor).visitCannon(cannon);
        Mockito.verify(visitor, Mockito.never()).visitCannon(cannon1);
    }



}