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
    private Vector2 _velocity;

    public Fish(List<Planet> planets)
    {
        _planets = planets;
    }

    public void update(double deltaTime)
    {
        Transformation transform = getTransformation();

        // If getting too close to target:
        if(_targetLocation == null ||
            transform.getPosition().distanceTo(_targetLocation) < targetThreshold ||
            getTargetInSpace())
            _targetLocation = pickTargetLocation();

        // Rotate toward target location.
        Vector2 toTarget = (_targetLocation.subtract(transform.getPosition())).normalized();
        Vector2 currentDirection = new Vector2(1, transform.getRotation()).toCartesian();
        Vector2 sideDirection = currentDirection.perpendicular();
        double deltaAngle = Math.atan2(toTarget.dot(sideDirection), toTarget.dot(currentDirection));
        double direction = deltaAngle > 0 ? 1 : -1;
        double randomAmount = new Random().nextDouble();
        transform.setRotation(transform.getRotation() + direction * turnSpeed * randomAmount);

        // Move forward
        _velocity = currentDirection.multiply(
                moveSpeed * Math.max(Math.abs(currentDirection.dot(toTarget)), 0.5));

        transform.setPosition(transform.getPosition().add(_velocity));
    }

    private Vector2 pickTargetLocation()
    {
        Transformation transform = getTransformation();
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
        Transformation transform = getTransformation();
        ArrayList<Planet> containedPlanets = new ArrayList<Planet>();
        for(Planet planet : _planets)
        {
            Transformation planetTransform = planet.getTransformation();
            double distance = transform.getPosition()
                .distanceTo(planetTransform.getPosition());
            if(distance < planet.getRadius())
            {
                containedPlanets.add(planet);
            }
        }
        return containedPlanets;
    }

    private Planet getClosestPlanet()
    {
        Transformation transform = getTransformation();
        Planet closestPlanet = null;
        double closestDistnace = Double.MAX_VALUE;
        for(Planet planet : _planets)
        {
            Transformation planetTransform = planet.getTransformation();
            double distance = transform.getPosition()
                .distanceTo(planetTransform.getPosition());
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
        _renderer.draw(canvas, camera, getTransformation());
        
        int radius = (int)targetThreshold;
        Vector2 drawPosition = camera.getScreenPosition(_targetLocation);
        canvas.setColor(Color.BLACK);
        //canvas.fillOval(
        //    (int) drawPosition.getX() - radius, 
        //    (int) drawPosition.getY() - radius, 
        //    radius * 2, radius * 2);
    }
}