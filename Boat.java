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
    private ImageRenderer _renderer;
    private List<OceanPlanet> _planets;

    public Boat(List<OceanPlanet> planets)
    {
        super();
        _renderer = new ImageRenderer("boat02-f.png");
        _velocity = new Vector2(0, 0);
        _planets = planets;
    }

    public void update(double deltaTime)
    {
        Transformation transform = getTransformation();
        Vector2 gravityVector = new Vector2(0, 0);
        Vector2 buoyancyVector = new Vector2(0, 0);
        boolean isInAtmosphere = false;
        for (OceanPlanet planet : _planets)
        {
            Vector2 toPlanet = planet.getTransformation().getPosition().subtract(transform.getPosition());
            double distanceToPlanet = toPlanet.length();
            Vector2 direction = toPlanet.normalized();
            
            // Apply gravity to all planets.
            double gravityAmount = Space.G * planet.getArea() / Math.pow(distanceToPlanet, 2);
            gravityVector = gravityVector.add(direction.multiply(gravityAmount));
            
            if(distanceToPlanet - Size < planet.getRadius())
            {
                double submergeAmount = planet.getRadius() - distanceToPlanet + Size / 2;
                double buoyancyAmount = 0.2 * submergeAmount * gravityAmount;
                buoyancyVector = buoyancyVector.add(direction.multiply(-buoyancyAmount));
            }
        
            isInAtmosphere |= distanceToPlanet < planet.getRadius() * 1.2;
        }
        Vector2 accelerationVector = gravityVector.add(buoyancyVector);
        if(isInAtmosphere)
        {
            Vector2 velocityDirection = _velocity.normalized();
            accelerationVector = accelerationVector.add(_velocity.multiply(-0.05));
        }
        
        Vector2 movementVector = new Vector2(-gravityVector.getY(), gravityVector.getX());
        Vector2 movementDirection = movementVector.normalized();
        
        if(Greenfoot.isKeyDown("right"))
        {
            accelerationVector = accelerationVector.add(movementDirection.multiply(0.3));
        }
        if(Greenfoot.isKeyDown("left"))
        {
            accelerationVector = accelerationVector.add(movementDirection.multiply(-0.3));
        }
        if(Greenfoot.isKeyDown("up"))
        {
            Vector2 gravityDirection = gravityVector.normalized();
            accelerationVector = accelerationVector.add(gravityDirection.multiply(-1.1));
        }
        
        _velocity = _velocity.add(accelerationVector);
        transform.setPosition(transform.getPosition().add(_velocity));
        double angle = Math.atan2(gravityVector.getY(), gravityVector.getX());
        transform.setRotation(-angle - Math.PI / 2);
    }   
    public void draw(GreenfootImage canvas, Camera camera)
    {
        _renderer.draw(canvas, camera, getTransformation());
    }
}
