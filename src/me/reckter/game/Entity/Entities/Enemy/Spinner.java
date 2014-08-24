package me.reckter.game.Entity.Entities.Enemy;


import me.reckter.Util;
import me.reckter.game.Abilities.AbilityHandler;
import me.reckter.game.Abilities.SpinnerShoot;
import me.reckter.game.Entity.Component.Abilities;
import me.reckter.game.Entity.Component.Position;
import me.reckter.game.level.Level;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by mediacenter on 05.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public class Spinner extends BaseEnemy {


	public Spinner(Level level) {
		super(level);
	}

    @Override
    protected void fillComponent() {
        super.fillComponent();


        double turnSpeed;
        if(Util.random.nextFloat() < 0.5){
            turnSpeed = Util.random.nextDouble() * 30 + 15;
        } else {
            turnSpeed = -(Util.random.nextDouble() * 30 + 15);
        }

        addComponent(new me.reckter.game.Entity.Component.Spinner(turnSpeed));
        getComponent(Position.class).size = new Vector2f(15,15);

        AbilityHandler abilities = new AbilityHandler();
        abilities.add("shoot", new SpinnerShoot(this));
        abilities.get("shoot").setMAX_COOLDOWN(200);

        addComponent(new Abilities(abilities, "shoot"));
    }

    public Shape getHitBox() {
		Position position = getComponent(Position.class);
		return new Circle(position.coords.x, position.coords.y, position.size.x * 0.75f, 4).transform(Transform.createRotateTransform((float) Math.toRadians(position.angle), position.coords.x, position.coords.y));
	}

}
