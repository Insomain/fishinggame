import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class OceanPlanet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OceanPlanet extends CanvasActor
{
    private int _radius;
    private int _atmosphere;
    
    public OceanPlanet(Camera camera, int radius)
    {
        super(camera);
        _radius = radius;
    }

    public double getRadius()
    {
        return _radius;
    }

    public double getArea()
    {
        return Math.PI * _radius * _radius;
    }

    public void Draw(GreenfootImage canvas)
    {
        Vector2 position = getScreenPosition();
        canvas.setColor(Color.BLUE);
        canvas.fillOval((int)position.getX() - _radius, (int)position.getY() - _radius, 
            _radius * 2, _radius * 2);
    }
}
