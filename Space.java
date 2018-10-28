import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Space here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Space extends World
{
    public static final double G = 0.1;
    private Camera _camera;
    private Boat _boat;
    private ArrayList<GameObject> _gameObjects = new ArrayList<GameObject>();
    private ArrayList<Planet> _planets = new ArrayList<Planet>();
    private ArrayList<Fish> _fish = new ArrayList<Fish>();

    private Planet _stillPlanet;
    private Planet _rightPlanet;
    private Planet _leftPlanet;

    public Space()
    {        
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 1200, 1, false);  
        _camera = new Camera(getWidth(), getHeight());

        _planets = new ArrayList<Planet>();
        _boat = new Boat(_planets);
        _gameObjects.add(_boat);
        _stillPlanet = addPlanet(500, new Vector2(0, -600), Color.BLUE);
        _rightPlanet = addPlanet(500, new Vector2(1250, -600), Color.BLUE);
        _leftPlanet = addPlanet(500, new Vector2(-1250, -600), Color.BLUE);
        
        for(int x = 0; x < 20; x++)
        {
            for(int y = 0; y < 20; y++)
            {
                Boat boat = new Boat(_planets);
                boat.setPosition(new Vector2(x*10, y*10));
                //_gameObjects.add(boat);
            }
        }

        for(Planet planet : _planets)
        {
            _gameObjects.add(planet);
        }
        for(Fish fish : _fish)
        {
            _gameObjects.add(fish);
        }
    }

    private Planet addPlanet(int radius, Vector2 position, Color color)
    {
        Planet planet = new Planet(radius, color);
        planet.setPosition(position);
        _planets.add(planet);
        for(int i = 0; i < 1; i++)
        {
            Fish fish = new Fish(_planets);
            fish.setPosition(planet.getRandomPoint());
            fish.setRotation(new Random().nextDouble() * Math.PI * 2);
            _fish.add(fish);
        }
        return planet;
    }

    private double _time = 0;
    public void act()
    {
        _time += 0.002;
        _rightPlanet.setPosition(new Vector2(1250, -600).add(new Vector2(500 * Math.sin(_time), 0)));
        _leftPlanet.setPosition(new Vector2(-1250, -600).add(new Vector2(-500 * Math.sin(_time * 0.9), 0)));

        GreenfootImage image = new GreenfootImage(getWidth(), getHeight());
        image.setColor(Color.BLACK);
        image.fill();
        DrawGrid(image, 5);

        // Need to sort by z depth between updating and rendering.
        for(GameObject gameObject : _gameObjects)
        {
            gameObject.update(0.01);
            gameObject.draw(image, _camera);
        }

        _camera.setPosition(_boat.getPosition());
        _camera.setRotation(_boat.getRotation());

        setBackground(image);
    }

    private void DrawGrid(GreenfootImage image, int squareCount)
    {        
        image.setColor(Color.WHITE);
        int width = getWidth();
        int height = getHeight();
        
        double radius = Math.sqrt(Math.pow(width / 2, 2) + Math.pow(height / 2, 2));
        double minX = _camera.getPosition().getX() - radius;
        double maxX = _camera.getPosition().getX() + radius;
        double minY = _camera.getPosition().getY() - radius;
        double maxY = _camera.getPosition().getY() + radius;

        int starRadius = 3;
        int count = 10;
        double step = 2 * radius / count;
        for(int xi = 0; xi < count; xi++)
        {
            for(int yi = 0; yi < count; yi++)
            {
                double x = (xi / (double)count) * (maxX - minX) + minX - minX / 3 % step;
                double y = (yi / (double)count) * (maxY - minY) + minY - minY / 3 % step;
                Vector2 screenPosition = _camera.getScreenPosition(new Vector2(x, y));
                image.fillOval(
                    (int) screenPosition.getX() - starRadius, 
                    (int) screenPosition.getY() - starRadius, 
                    starRadius * 2, starRadius * 2);
            }
        }
    }
}
