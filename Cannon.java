import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Cannon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cannon extends Actor
{
    /**
     * Act - do whatever the Cannon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        
        if (mouse != null)
        {
            Vector2D cannonToMouse = new Vector2D(mouse.getX() - getX(), 
                                                  mouse.getY() - getY());
                                                  
            alignWithVector(cannonToMouse);
        }
    }    
    
    public void alignWithVector(Vector2D v)
    {
        double angleRad = Math.atan2(v.getY(), v.getX());
        double angleDeg = Math.toDegrees(angleRad);
            
        setRotation((int) angleDeg);
    }
}











