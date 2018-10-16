import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CanvasActor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CanvasActor extends WorldActor
{
    GreenfootImage _canvas;

    public CanvasActor(Camera camera)
    {
        super(camera);

        _canvas = new GreenfootImage(camera.getWidth(), camera.getHeight());
        setImage(_canvas);
    }
    
    public void addedToWorld(World world)
    {
        super.setLocation(world.getWidth() / 2, world.getHeight() / 2);
        super.addedToWorld(world);
    }

    public void act()
    {
        _canvas.clear();
        Draw(_canvas);
    }

    public void Draw(GreenfootImage canvas)
    {
    }

    public void setLocation(int x, int y)
    {
    }
}
