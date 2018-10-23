public class Transformation
{
    private Vector2 _position;
    private double _rotation; // All roations are tracked in radians.
    private double _scale;

    public Transformation()
    {
        _position = new Vector2(0, 0);
        _rotation = 0.0;
        _scale = 1.0;
    }

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

    public double getScale()
    {
        return _scale;
    }

    public void setScale(double scale)
    {
        _scale = scale;
    }
}