import greenfoot.*;
import java.util.*;

public class Hook extends GameObject
{
    private static final Vector2 ImageOffset = new Vector2(0, -10);
    private static final double CatchRadius = 30;

    private List<Fish> _fish;
    private ImageRenderer _renderer;

    private HashSet<Fish> _caughtFish = new HashSet<Fish>();

    public Hook(List<Fish> fish)
    {
        _fish = fish; 
        _renderer = new ImageRenderer("hook.png");
    }

    public List<Fish> takeFish()
    {
        List<Fish> caughtFish = new ArrayList<>(_caughtFish);
        _caughtFish.clear();
        return caughtFish;
    }

    public void update()
    {
        for(Fish fish : _fish)
        {
            double distance = getPosition().distanceTo(fish.getPosition());
            if(distance < CatchRadius)
            {
                _caughtFish.add(fish);
            }
        }
        for(Fish fish : _caughtFish)
        {
            fish.setPosition(getPosition());
        }
    }

    public void draw(GreenfootImage canvas, Camera camera)
    {
        _renderer.draw(canvas, camera, getPosition().add(getOffset(ImageOffset)), getRotation());
    }
}