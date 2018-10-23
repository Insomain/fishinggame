import greenfoot.*;

public abstract class GameObject implements Drawable, Transformable
{
    private Transformation _transformation;
    private Renderer _renderer;
    
    protected GameObject(Renderer renderer)
    {
        _transformation = new Transformation();
        _renderer = renderer;
        _renderer.setTransformation(_transformation);
    }

    public Transformation getTransformation()
    {
        return _transformation;
    }

    public Renderer getRenderer()
    {
        return _renderer;
    }

    public abstract void Update(double deltaTime);
}