import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Space here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Space extends World
{
    public static final double G = 1;
    private Camera _camera;
    private WorldActor _focusTarget;

    public Space()
    {        
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1, false);  
        _camera = new Camera(getWidth(), getHeight());
        _camera.setWorldPosition(new Vector2(0, 0));
        
        OceanPlanet planet1 = new OceanPlanet(_camera, 1000);
        addObject(planet1, 0, 0);
        planet1.setWorldPosition(new Vector2(-1500, 0));

        OceanPlanet planet2 = new OceanPlanet(_camera, 200);
        addObject(planet2, 0, 0);
        planet2.setWorldPosition(new Vector2(0, 0));

        Boat boat1 = new Boat(_camera);
        addObject(boat1, 0, 0);
        boat1.setWorldPosition(new Vector2(-200, 1000));
        _focusTarget = boat1;
    }

    public List<OceanPlanet> getOceanPlanets()
    {
        return getObjects(OceanPlanet.class);
    }

    double t = 0;
    public void act()
    {
        t += 0.001;
        _camera.setWorldPosition(_focusTarget.getWorldPosition());
        //_camera.setRotation(t);

        GreenfootImage grid = new GreenfootImage(getWidth(), getHeight());
        grid.setColor(Color.BLACK);
        grid.fill();
        grid.setColor(Color.WHITE);

        int squareCount = 10;
        for(int i = 0; i < squareCount; i++)
        {
            double u = i / (double)squareCount;
            int pos = (int)((u * getWidth() - _camera.getWorldPosition().getX() + getWidth()) % getWidth());
            //System.out.println(pos);
            grid.drawLine(pos, 0, pos, getHeight());
        }
        for(int i = 0; i < squareCount; i++)
        {
            double v = i / (double)squareCount;
            int pos = (int)((v * getHeight() + _camera.getWorldPosition().getY() + getHeight()) % getHeight());
            //System.out.println(pos);
            grid.drawLine(0, pos, getWidth(), pos);
        }
        setBackground(grid);
    }
}
