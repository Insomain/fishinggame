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

    public Space()
    {        
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1, false);  
        _camera = new Camera(getWidth(), getHeight());

        List<OceanPlanet> planets = new ArrayList<OceanPlanet>();
        planets.add(createPlanet(1000, new Vector2(0, -1200)));
        planets.add(createPlanet(1000, new Vector2(2500, -1200)));
        
        _boat = new Boat(planets);
        _gameObjects.add(_boat);

        for(int x = 0; x < 20; x++)
        {
            for(int y = 0; y < 20; y++)
            {
                Boat boat = new Boat(planets);
                boat.getTransformation().setPosition(new Vector2(x*10, y*10));
                //_gameObjects.add(boat);
            }
        }
        for(OceanPlanet planet : planets)
        {
            _gameObjects.add(planet);
        }
    }

    private OceanPlanet createPlanet(int radius, Vector2 position)
    {
        OceanPlanet planet = new OceanPlanet(radius);
        planet.getTransformation().setPosition(position);
        return planet;
    }

    public void act()
    {
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

        _camera.getTransformation().setPosition(_boat.getTransformation().getPosition());

        setBackground(image);
    }

    private void DrawGrid(GreenfootImage image, int squareCount)
    {        
        image.setColor(Color.WHITE);
        int width = getWidth();
        int height = getHeight();
        int cX = (int) _camera.getTransformation().getPosition().getX();
        int cY = (int) _camera.getTransformation().getPosition().getY();

        for(int i = 0; i < squareCount; i++)
        {
            double u = i / (double)squareCount;
            int pos = (int)(u * width - cX % width + width) % width;
            image.drawLine(pos, 0, pos, height);
        }
        for(int i = 0; i < squareCount; i++)
        {
            double v = i / (double)squareCount;
            int pos = (int) (v * height + cY % height + height) % height;
            image.drawLine(0, pos, width, pos);
        }
    }
}
