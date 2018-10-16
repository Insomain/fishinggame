import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TransformableActor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WorldActor extends Actor
{
    private Camera _camera;
    private Vector2 _worldPosition;
    private Vector2 _screenPosition;

    public WorldActor(Camera camera)
    {
        _camera = camera;
        _worldPosition = new Vector2(0, 0);
        _screenPosition = new Vector2(0, 0);
    }

    public void addedToWorld(World world)
    {
        _screenPosition = new Vector2(getX(), getY());
        _worldPosition = _camera.getWorldPosition(_screenPosition);
    }

    protected Camera getCamera()
    {
        return _camera;
    }

    protected Vector2 getScreenPosition()
    {
        return _camera.getScreenPosition(_worldPosition);
    }

    protected Vector2 getWorldPosition()
    {
        return _worldPosition;
    }

    protected void setWorldPosition(Vector2 position)
    {
        _worldPosition = position;
        setLocation((int)getScreenPosition().getX(), (int)getScreenPosition().getY());
    }
}
