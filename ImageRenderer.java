import greenfoot.*;

public class ImageRenderer
{
    private GreenfootImage _image;

    public ImageRenderer(String filePath)
    {
        _image = new GreenfootImage(filePath);
    }

    public void draw(GreenfootImage canvas, Camera camera, Vector2 position, double rotation)
    {
        GreenfootImage workingCopy = new GreenfootImage(_image);
        workingCopy.rotate(-(int)((rotation - camera.getRotation()) * 180 / Math.PI));
        Vector2 screenPosition = camera.getScreenPosition(position);
        canvas.drawImage(workingCopy, 
            (int)screenPosition.getX() - workingCopy.getWidth() / 2, 
            (int)screenPosition.getY() - workingCopy.getHeight() / 2);
    }
}