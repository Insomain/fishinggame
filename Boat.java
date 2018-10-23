import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Boat here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Boat extends GameObject
{
    private final int Size = 48;
    private Vector2 _velocity;

    public Boat()
    {
        super(new ImageRenderer("boat02-f.png"));
        _velocity = new Vector2(0, 0);
    }

    public void Update(double deltaTime)
    {
        getTransformation().setRotation(getTransformation().getRotation() + 0.01);
    }

    public void act() 
    {
        //Vector2 gravityVector = new Vector2(0, 0);
        //Vector2 buoyancyVector = new Vector2(0, 0);
        //boolean isInAtmosphere = false;
        //List<OceanPlanet> planets = ((Space)getWorld()).getOceanPlanets();
        //for (OceanPlanet planet : planets)
        //{
        //    Vector2 toPlanet = planet.getWorldPosition().subtract(getWorldPosition());
        //    double distanceToPlanet = toPlanet.length();
        //    Vector2 direction = toPlanet.normalized();
        //    
        //    // Apply gravity to all planets.
        //    double gravityAmount = Space.G * planet.getArea() / Math.pow(distanceToPlanet, 2);
        //    gravityVector = gravityVector.add(direction.multiply(gravityAmount));
        //    
        //    if(distanceToPlanet - Size < planet.getRadius())
        //    {
        //        double submergeAmount = planet.getRadius() - distanceToPlanet + Size / 2;
        //        double buoyancyAmount = 0.2 * submergeAmount * gravityAmount;
        //        buoyancyVector = buoyancyVector.add(direction.multiply(-buoyancyAmount));
        //    }
        //
        //    isInAtmosphere |= distanceToPlanet < planet.getRadius() * 1.2;
        //}
        //Vector2 accelerationVector = gravityVector.add(buoyancyVector);
        //if(isInAtmosphere)
        //{
        //    Vector2 velocityDirection = _velocity.normalized();
        //    accelerationVector = accelerationVector.add(_velocity.multiply(-0.05));
        //}
        //
        //Vector2 movementVector = new Vector2(-gravityVector.getY(), gravityVector.getX());
        //Vector2 movementDirection = movementVector.normalized();
        //
        //if(Greenfoot.isKeyDown("right"))
        //{
        //    accelerationVector = accelerationVector.add(movementDirection.multiply(0.3));
        //}
        //if(Greenfoot.isKeyDown("left"))
        //{
        //    accelerationVector = accelerationVector.add(movementDirection.multiply(-0.3));
        //}
        //if(Greenfoot.isKeyDown("up"))
        //{
        //    Vector2 gravityDirection = gravityVector.normalized();
        //    accelerationVector = accelerationVector.add(gravityDirection.multiply(-1.1));
        //}
        //
        //_velocity = _velocity.add(accelerationVector);
        //setWorldPosition(getWorldPosition().add(_velocity));
        //double angle = Math.atan2(gravityVector.getY(), gravityVector.getX());
        //setRotation(-(int)(angle * 180 / Math.PI) - 90);
    }    
}
