import greenfoot.*;
import java.util.*;

public class Pole extends GameObject
{
    private static final Vector2 LineOffset = new Vector2(50, 20);
    
    private Vector2 _start;
    private Vector2 _end;
    private FishingLine _line;

    private ImageRenderer _renderer;

    public Pole(FishingLine line)
    {
        _line = line;
        _renderer = new ImageRenderer("pole.png");
    }

    public double getLength()
    {
        return _line.getLength();
    }    
    
    public List<Fish> takeFish()
    {
        return _line.takeFish();
    }

    public void update()
    {
        _line.setPosition(getPosition().add(getOffset(LineOffset)));
        _line.setRotation(getRotation());
    }

    public void extend(double amount)
    {
        _line.extend(amount);
    }

    public void draw(GreenfootImage canvas, Camera camera)
    {
        _renderer.draw(canvas, camera, getPosition(), getRotation());
    }
}