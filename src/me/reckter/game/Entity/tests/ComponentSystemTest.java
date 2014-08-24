package me.reckter.game.Entity.tests;

import me.reckter.game.Entity.Component.Enemy;
import me.reckter.game.Entity.Component.Life;
import me.reckter.game.Entity.Component.Position;
import me.reckter.game.Entity.Entities.BaseEntity;
import me.reckter.game.Entity.Exception.ComponentNotFoundException;
import me.reckter.game.level.Level;
import me.reckter.network.ServerEngine;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by mediacenter on 08.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public class ComponentSystemTest {

	BaseEntity entity;

	@Before
	public void setUp() {
		Level level = new Level(new ServerEngine());
		entity = new BaseEntity(level);
		entity.init();
	}


	@Test
	public void testingGetComponent() {

		Assert.assertTrue("didn't get position back", entity.<Position>getComponent(Position.class) instanceof Position);
		Assert.assertTrue("didn't get Life back", entity.<Life>getComponent(Life.class) instanceof Life);


		Assert.assertTrue("didn't get position back", entity.getComponent(Position.class) instanceof Position);
		Assert.assertTrue("didn't get Life back", entity.getComponent(Life.class) instanceof Life);
	}

	@Test(expected = ComponentNotFoundException.class)
	public void testingComponentNotFoundException() {
		entity.getComponent(Enemy.class);
	}
}
