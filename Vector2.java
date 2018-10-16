/**
 * Write a description of class Vector2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Vector2  
{
    // instance variables - replace the example below with your own
    private double _x;
    private double _y;

    /**
     * Constructor for objects of class Vector2
     */
    public Vector2(double x, double y)
    {
        _x = x;
        _y = y;
    }

    public double getX()
    {
        return _x;
    }

    public double getY()
    {
        return _y;
    }

    public Vector2 subtract(Vector2 other)
    {
        return new Vector2(_x - other.getX(), _y - other.getY());
    }

    public Vector2 add(Vector2 other)
    {
        return new Vector2(_x + other.getX(), _y + other.getY());
    }

    public Vector2 divide(double scalar)
    {
        return new Vector2(_x / scalar, _y / scalar);
    }

    public Vector2 multiply(double scalar)
    {
        return new Vector2(_x * scalar, _y * scalar);
    }

    public double dot(Vector2 other)
    {
        return _x * other.getX() + _y * other.getY();
    }

    public double length()
    {
        return Math.sqrt(dot(this));
    }

    public Vector2 normalized()
    {
        return divide(length());
    }

    public Vector2 toPolar()
    {
        return new Vector2(length(), Math.atan2(_y, _x));
    }

    public Vector2 toCartesian()
    {
        // X = R
        // Y = Theta
        return new Vector2(_x * Math.cos(_y), _x * Math.sin(_y));
    }
}
