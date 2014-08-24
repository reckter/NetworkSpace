package me.reckter.game.Entity.tests;

import me.reckter.game.Entity.Category.Matching.Matching;
import me.reckter.game.Entity.Component.Enemy;
import me.reckter.game.Entity.Component.Gravity;
import me.reckter.game.Entity.Component.Life;
import me.reckter.game.Entity.Component.Movement;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Hannes on 2/24/14.
 *
 * @author Hannes Güdelhöfer
 */
public class MatchingTest {
	@Test
	public void testParse() throws Exception {
		Assert.assertEquals("'movement & gravity' not parsed", Matching.AND(Matching.MATCH(Movement.class), Matching.MATCH(Gravity.class)), Matching.parse("movement & gravity"));
		Assert.assertEquals("'( movement & gravity )' not parsed", Matching.AND(Matching.MATCH(Movement.class), Matching.MATCH(Gravity.class)), Matching.parse("( movement & gravity )"));
		Assert.assertEquals("'( ( movement & gravity ) | ( gravity ^ !enemy ) )' not parsed", Matching.OR(Matching.AND(Matching.MATCH(Movement.class), Matching.MATCH(Gravity.class)), Matching.XOR(Matching.MATCH(Gravity.class), Matching.NOT(Matching.MATCH(Enemy.class)))), Matching.parse(" ( ( movement & gravity ) | ( gravity ^ !enemy ) )"));
		Assert.assertEquals(Matching.AND(Matching.MATCH(Movement.class), Matching.OR(Matching.MATCH(Gravity.class), Matching.NOT(Matching.OR(Matching.MATCH(Enemy.class), Matching.MATCH(Life.class))))), Matching.parse("movement & (gravity | !( enemy | maxLife))"));
	}
}
