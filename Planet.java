import greenfoot.*;  
import java.util.Random;

public class Planet extends GameObject
{
    private int _radius;
    private int _atmosphere;
    private Color _color;

    public Planet(int radius, Color color)
    {
        super();
        _radius = radius;
        _color = color;
    }

    public double getRadius()
    {
        return _radius;
    }

    public double getArea()
    {
        return Math.PI * _radius * _radius;
    }

    public boolean containsPoint(Vector2 point)
    {
        return point.distanceTo(getTransformation().getPosition()) < _radius;
    }
    
    public Vector2 getRandomPoint()
    {
        Random r = new Random();
        Vector2 position = getTransformation().getPosition();
        double radius = r.nextDouble() * _radius;
        double theta = r.nextDouble() * Math.PI * 2;
        return new Vector2(radius, theta).toCartesian().add(getTransformation().getPosition());
    }

    public void draw(GreenfootImage canvas, Camera camera)
    {
        Vector2 position = getTransformation().getPosition();
        Vector2 drawPosition = camera.getScreenPosition(position);
        canvas.setColor(_color);
        canvas.fillOval(
            (int) drawPosition.getX() - _radius, 
            (int) drawPosition.getY() - _radius, 
            _radius * 2, _radius * 2);
    }
}
