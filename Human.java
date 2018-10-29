import greenfoot.*;
import java.util.*;

public class Human extends GameObject
{
    private static final Vector2 SeatOffset = new Vector2(10, 0);
    private static final Vector2 PoleOffset = new Vector2(50, 20);
    private static final Vector2 FishOffset = new Vector2(-25, 0);
    private static final double StackHeight = 10;

    private ImageRenderer _renderer;
    private Boat _boat;
    private Pole _pole;
    private ArrayList<Fish> _caughtFish = new ArrayList<Fish>();

    public Human(Boat boat, Pole pole)
    {
        _renderer = new ImageRenderer("human.png");
        _boat = boat;
        _pole = pole;
    }

    public int getNumberOfCaughtFish()
    {
        return _caughtFish.size();
    }

    public void update()
    {
        Vector2 driveVector = new Vector2(0, 0);
        if(Greenfoot.isKeyDown("right"))
        {
            driveVector = driveVector.add(new Vector2(1, 0));
        }
        if(Greenfoot.isKeyDown("left"))
        {
            driveVector = driveVector.add(new Vector2(-1, 0));
        }
        if(Greenfoot.isKeyDown("space"))
        {
            driveVector = driveVector.add(new Vector2(0, 1));
        }
        driveVector = driveVector.normalized();
        _boat.drive(driveVector);
        
        // Hold on to the boat.
        setPosition(_boat.getPosition().add(getOffset(SeatOffset)));
        setRotation(_boat.getRotation());

        if(Greenfoot.isKeyDown("down"))
        {
            _pole.extend(1);
        }
        if(Greenfoot.isKeyDown("up"))
        {
            _pole.extend(-1);
        }
        if(_pole.getLength() < 10)
        {
            _caughtFish.addAll(_pole.takeFish());
        }
        for(int i = 0; i < _caughtFish.size(); i++)
        {
            Fish fish = _caughtFish.get(i);
            fish.setPosition(getPosition().add(getOffset(FishOffset.add(new Vector2(0, i * StackHeight)))));
            fish.setRotation(getRotation());
        }

        // Hold the fishing pole
        _pole.setPosition(getPosition().add(getOffset(PoleOffset)));
        _pole.setRotation(getRotation());
    }

    public void draw(GreenfootImage canvas, Camera camera)
    {
        _renderer.draw(canvas, camera, getPosition(), getRotation());
    }
}