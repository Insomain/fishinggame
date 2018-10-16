import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Boat here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Boat extends Vehicle
{
    private final int Size = 48;
    private Vector2 _velocity;

    public Boat(Camera camera)
    {
        super(camera);
        _velocity = new Vector2(-7, 7);
    }

    public void act() 
    {
        Vector2 gravityVector = new Vector2(0, 0);
        Vector2 buoyancyVector = new Vector2(0, 0);
        boolean isInAtmosphere = false;
        List<OceanPlanet> planets = ((Space)getWorld()).getOceanPlanets();
        for (OceanPlanet planet : planets)
        {
            Vector2 toPlanet = planet.getWorldPosition().subtract(getWorldPosition());
            double distanceToPlanet = toPlanet.length();
            Vector2 direction = toPlanet.normalized();
            
            // Apply gravity to all planets.
            double gravityAmount = Space.G * planet.getArea() * (1 / Math.pow(distanceToPlanet, 2));
            gravityVector = direction.multiply(gravityAmount);

            if(distanceToPlanet < planet.getRadius())
            {
                double submergeAmount = planet.getRadius() - distanceToPlanet;
                double buoyancyAmount = 0.1 * submergeAmount * gravityAmount;
                buoyancyVector = direction.multiply(-buoyancyAmount);
            }

            isInAtmosphere = distanceToPlanet < planet.getRadius() * 1.3;
        }
        Vector2 accelerationVector = gravityVector.add(buoyancyVector);
        if(isInAtmosphere)
        {
            Vector2 velocityDirection = _velocity.normalized();
            accelerationVector = accelerationVector.add(_velocity.multiply(-0.1));
        }
       
        _velocity = _velocity.add(accelerationVector);
        if(_velocity.length() > 1)
        {
            setWorldPosition(getWorldPosition().add(_velocity));
        }
    }    
}
