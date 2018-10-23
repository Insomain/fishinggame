import greenfoot.*;

public class ImageRenderer extends Renderer
{
    private GreenfootImage _image;

    public ImageRenderer(String filePath)
    {
        _image = new GreenfootImage(filePath);
    }

    public void Draw(GreenfootImage canvas, Camera camera)
    {
        Transformation transformation = getTransformation();
        GreenfootImage workingCopy = new GreenfootImage(_image);
        workingCopy.rotate((int)(transformation.getRotation() * 180 / Math.PI));
        Vector2 worldPosition = transformation.getPosition();
        Vector2 screenPosition = camera.getScreenPosition(worldPosition);
        canvas.drawImage(workingCopy, 
            (int)screenPosition.getX() - workingCopy.getWidth() / 2, 
            (int)screenPosition.getY() - workingCopy.getHeight() / 2);
    }
}