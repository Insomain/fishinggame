import greenfoot.*;
import java.util.*;

public class Fish extends GameObject
{
    private final double targetThreshold = 20;
    private final double turnSpeed = 0.05;
    private final double moveSpeed = 2.5;

    private static ImageRenderer _renderer = new ImageRenderer("fish.png");
    private List<Planet> _planets;
    private Vector2 _targetLocation;
    private boolean _dead = false;

    public Fish(List<Planet> planets)
    {
        _planets = planets;
    }

    public void kill()
    {
        _dead = true;
    }

    public void update()
    {
        if(_dead)
        {
            return;
        }
        
        // If getting too close to target:
        if(_targetLocation == null ||
            getPosition().distanceTo(_targetLocation) < targetThreshold ||
            getTargetInSpace())
            _targetLocation = pickTargetLocation();

        // Rotate toward target location.
        Vector2 toTarget = (_targetLocation.subtract(getPosition())).normalized();
        Vector2 currentDirection = new Vector2(1, getRotation()).toCartesian();
        Vector2 sideDirection = currentDirection.perpendicular();
        double deltaAngle = Math.atan2(toTarget.dot(sideDirection), toTarget.dot(currentDirection));
        double direction = deltaAngle > 0 ? 1 : -1;
        double randomAmount = new Random().nextDouble();
        setRotation(getRotation() + direction * turnSpeed * randomAmount);

        // Move forward
        Vector2 velocity = currentDirection.multiply(
                moveSpeed * Math.max(Math.abs(currentDirection.dot(toTarget)), 0.5));

        setPosition(getPosition().add(velocity));
    }

    private Vector2 pickTargetLocation()
    {
        Planet targetPlanet = null;
        List<Planet> planets = getContainedPlanets();
        if(planets.size() > 0)
        {
            targetPlanet = planets.get(Greenfoot.getRandomNumber(planets.size()));
        }
        else
        {
            targetPlanet = getClosestPlanet();
        }
        if(targetPlanet == null)
        {
            return null;
        }
        return targetPlanet.getRandomPoint();
    }

    private List<Planet> getContainedPlanets()
    {        
        ArrayList<Planet> containedPlanets = new ArrayList<Planet>();
        for(Planet planet : _planets)
        {
            double distance = getPosition().distanceTo(planet.getPosition());
            if(distance < planet.getRadius())
            {
                containedPlanets.add(planet);
            }
        }
        return containedPlanets;
    }

    private Planet getClosestPlanet()
    {
        Planet closestPlanet = null;
        double closestDistnace = Double.MAX_VALUE;
        for(Planet planet : _planets)
        {
            double distance = getPosition()
                .distanceTo(planet.getPosition());
            if(distance < closestDistnace || closestPlanet == null)
            {
                closestPlanet = planet;
                closestDistnace = distance;
            }
        }
        return closestPlanet;
    }

    private boolean getTargetInSpace()
    {
        boolean targetInPlanet = false;
        for(Planet planet : _planets)
        {
            targetInPlanet |= planet.containsPoint(_targetLocation);
        }
        return !targetInPlanet;
    }

    public void draw(GreenfootImage canvas, Camera camera)
    {
        _renderer.draw(canvas, camera, getPosition(), getRotation());
        
        int radius = (int)targetThreshold;
        Vector2 drawPosition = camera.getScreenPosition(_targetLocation);
        canvas.setColor(Color.BLACK);
        //canvas.fillOval(
        //    (int) drawPosition.getX() - radius, 
        //    (int) drawPosition.getY() - radius, 
        //    radius * 2, radius * 2);
    }
}