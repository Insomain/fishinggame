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
    private ImageRenderer _renderer;
    private List<Planet> _planets;
    private Vector2 _velocity = new Vector2(0, 0);
    private Vector2 _driveVector = new Vector2(0, 0);

    public Boat(List<Planet> planets)
    {
        super();
        _renderer = new ImageRenderer("boat02-f.png");
        _planets = planets;
    }

    public void update()
    {
        Vector2 gravityVector = new Vector2(0, 0);
        Vector2 buoyancyVector = new Vector2(0, 0);
        boolean isInAtmosphere = false;
        double closestSurface = Double.MAX_VALUE;
        for (Planet planet : _planets)
        {
            Vector2 toPlanet = planet.getPosition().subtract(getPosition());
            double distanceToPlanet = toPlanet.length();
            double surfaceDistance = distanceToPlanet - planet.getRadius();
            if(surfaceDistance < closestSurface)
            {
                closestSurface = surfaceDistance;
            }
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
        closestSurface = Math.max(Size / 2, closestSurface);
        Vector2 accelerationVector = gravityVector.add(buoyancyVector);
        if(isInAtmosphere)
        {
            Vector2 velocityDirection = _velocity.normalized();
            accelerationVector = accelerationVector.add(_velocity.multiply(-0.05));
        }
        
        Vector2 movementVector = new Vector2(-gravityVector.getY(), gravityVector.getX());
        Vector2 movementDirection = movementVector.normalized();
        accelerationVector = accelerationVector.add(
            movementDirection.multiply(_driveVector.getX() / closestSurface * 10));

        Vector2 gravityDirection = gravityVector.normalized();
        accelerationVector = accelerationVector.add(
            gravityDirection.multiply(-_driveVector.getY() / closestSurface * 50));
        
        _velocity = _velocity.add(accelerationVector);
        setPosition(getPosition().add(_velocity));
        double angle = Math.atan2(gravityVector.getY(), gravityVector.getX());
        setRotation(angle + Math.PI / 2);
    }  

    public void drive(Vector2 vector)
    {
        _driveVector = vector;
    }

    public void draw(GreenfootImage canvas, Camera camera)
    {
        _renderer.draw(canvas, camera, getPosition(), getRotation());
    }
}
