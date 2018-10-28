import greenfoot.*;

public class Human extends GameObject
{
    private ImageRenderer _renderer;
    private Boat _boat;

    public Human(Boat boat)
    {
        _renderer = new ImageRenderer("human.png");
        _boat = boat;
    }

    public void draw(GreenfootImage canvas, Camera camera)
    {
        _renderer.draw(canvas, camera, _boat.getPosition(), _boat.getRotation());
    }
}