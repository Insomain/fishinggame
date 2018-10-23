/**
 * Write a description of class Camera here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Camera implements Transformable
{
    private int _width;
    private int _height;
    private Transformation _transformation;

    public Camera(int width, int height)
    {
        _width = width;
        _height = height;
        _transformation = new Transformation();
    }

    public int getWidth()
    {
        return _width;
    }

    public int getHeight()
    {
        return _height;
    }

    public Transformation getTransformation()
    {
        return _transformation;
    }

    public Vector2 getScreenPosition(Vector2 worldPosition)
    {        
        // Offset camera position.
        Vector2 worldOffset = _transformation.getPosition();
        Vector2 screenPosition = worldPosition.subtract(worldOffset);

        // Mirror vertically to allow up to be positive.
        screenPosition = new Vector2(
            screenPosition.getX(),
            -screenPosition.getY());

        // Offset camera rotation.
        Vector2 polarPosition = screenPosition.toPolar();
        Vector2 newPolarPosition = new Vector2(
            polarPosition.getX(),
            polarPosition.getY() + _transformation.getRotation());
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
        screenPosition = screenPosition.subtract(_transformation.getPosition());

        return worldPosition;
    }
}
