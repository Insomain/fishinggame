import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class OceanPlanet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OceanPlanet extends GameObject
{
    private int _radius;
    private int _atmosphere;
    
    public OceanPlanet(int radius)
    {
        super();
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

    public void update(double deltaTime)
    {
    }
    
    public void draw(GreenfootImage canvas, Camera camera)
    {
        Vector2 position = getTransformation().getPosition();
        Vector2 drawPosition = camera.getScreenPosition(position);
        canvas.setColor(Color.BLUE);
        canvas.fillOval(
            (int) drawPosition.getX() - _radius, 
            (int) drawPosition.getY() - _radius, 
            _radius * 2, _radius * 2);
    }
}
