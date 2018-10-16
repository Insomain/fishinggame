/**
 * Write a description of class Camera here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Camera  
{
    private int _width;
    private int _height;

    private Vector2 _worldPosition;
    private double _rotation;

    public Camera(int width, int height)
    {
        _width = width;
        _height = height;
        _worldPosition = new Vector2(0, 0);
        _rotation = 0;
    }

    public int getWidth()
    {
        return _width;
    }

    public int getHeight()
    {
        return _height;
    }

    public Vector2 getWorldPosition()
    {
        return _worldPosition;
    }

    public void setWorldPosition(Vector2 position)
    {
        _worldPosition = position;
    }

    public double getRotation()
    {
        return _rotation;
    }

    public void setRotation(double rotation)
    {
        _rotation = rotation;
    }

    public Vector2 getScreenPosition(Vector2 worldPosition)
    {        
        // Offset camera position.
        Vector2 worldOffset = new Vector2(
            _worldPosition.getX(),
            _worldPosition.getY()
        );
        Vector2 screenPosition = worldPosition.subtract(worldOffset);

        // Mirror vertically to allow up to be positive.
        screenPosition = new Vector2(screenPosition.getX(), -screenPosition.getY());

        // Offset camera rotation.
        Vector2 polarPosition = screenPosition.toPolar();
        Vector2 newPolarPosition = new Vector2(polarPosition.getX(),
            polarPosition.getY() + _rotation);
        screenPosition = newPolarPosition.toCartesian();

        // Offset screen center.
        Vector2 screenOffset = new Vector2(_width / 2, _height / 2);
        screenPosition = screenPosition.add(screenOffset);

        return screenPosition;
    }

    public Vector2 getWorldPosition(Vector2 screenPosition)
    {
        Vector2 worldPosition = screenPosition;

        // Offset screen center.
        Vector2 screenOffset = new Vector2(_width / 2, _height / 2);
        worldPosition = worldPosition.subtract(screenOffset);
        screenPosition = screenPosition.subtract(_worldPosition);

        return worldPosition;
    }
}
