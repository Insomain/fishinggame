import greenfoot.*;

public class ImageRenderer
{
    private GreenfootImage _image;

    public ImageRenderer(String filePath)
    {
        _image = new GreenfootImage(filePath);
    }

    public void draw(GreenfootImage canvas, Camera camera, Transformation transformation)
    {
        GreenfootImage workingCopy = new GreenfootImage(_image);
        workingCopy.rotate(-(int)(
            (transformation.getRotation() - camera.getTransformation().getRotation()) * 180 / Math.PI)
            );
        Vector2 worldPosition = transformation.getPosition();
        Vector2 screenPosition = camera.getScreenPosition(worldPosition);
        canvas.drawImage(workingCopy, 
            (int)screenPosition.getX() - workingCopy.getWidth() / 2, 
            (int)screenPosition.getY() - workingCopy.getHeight() / 2);
    }
}