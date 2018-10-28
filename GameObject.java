import greenfoot.*;

public abstract class GameObject implements Drawable
{
    private Vector2 _position = new Vector2(0, 0);
    private double _rotation = 0.0;
    
    public Vector2 getPosition()
    {
        return _position;
    }

    public void setPosition(Vector2 position)
    {
        _position = position;
    }

    public double getRotation()
    {
        return _rotation;
    }

    public void setRotation(double rotation)
    {
        _rotation = rotation;
    }

    public abstract void draw(GreenfootImage canvas, Camera camera);

    public void update(double deltaTime)
    {
        // Overriden by derived classes.
    }
}