import greenfoot.*;

public abstract class Renderer
{
    private Transformation _transformation;

    public abstract void Draw(GreenfootImage canvas, Camera camera);

    public Transformation getTransformation()
    {
        return _transformation;
    }

    public void setTransformation(Transformation transformation)
    {
        _transformation = transformation;
    }
}