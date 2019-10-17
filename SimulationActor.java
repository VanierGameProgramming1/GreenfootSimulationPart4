import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*; 
import java.awt.image.*; 

/**
 * Write a description of class SimulationActor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SimulationActor extends Actor
{
    protected Point2D position;
    protected Vector2D velocity;
    protected Vector2D acceleration;

    protected BufferedImage originalImage;
    
    public SimulationActor()
    {
        this.position = null;
        this.velocity = new Vector2D(0.0,0.0);
        this.acceleration = new Vector2D(0.0,0.0);
        originalImage = null;
    }
    
    public SimulationActor(Point2D position, Vector2D velocity, Vector2D acceleration)
    {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
        originalImage = null;
    }
    
    public void act() 
    {
        eulerIntegration();
    }    
    
    protected void eulerIntegration()
    {
        // Initial position
        if (position == null)
        {
            position = windowToWorld(new Point2D(getX(), getY()));
        }
        
        if (originalImage == null && getImage() != null)
        {
            BufferedImage img = getImage().getAwtImage();
            originalImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());

            Graphics g = originalImage.getGraphics();
            g.drawImage(img, 0, 0, null);
            g.dispose();
        }

        
        // Get time step duration
        SimulationWorld world = (SimulationWorld) getWorld();
        double dt = world.getTimeStepDuration();
        
        // Update velocity
        Vector2D velocityVariation = Vector2D.multiply(acceleration, dt);
        velocity = Vector2D.add(velocity, velocityVariation);

        // Update position
        Vector2D positionVariation = Vector2D.multiply(velocity, dt);
        position.add(positionVariation);
        
        // Set new actor position
        setLocation(position);
    }

    public void setVelocity(Vector2D newValue)
    {
        velocity = newValue;
    }
    
    public void setLocation(Point2D worldLocation)
    {
        SimulationWorld world = (SimulationWorld) getWorld();
        Point2D windowLocation = world.worldToWindow(worldLocation);
        setLocation((int) windowLocation.getX(), (int) windowLocation.getY());
    }
    
    public BufferedImage getOriginalImage()
    {
        return originalImage;
    }
    
    protected Point2D worldToWindow(Point2D windowCoordinates)
    {
        SimulationWorld world = (SimulationWorld) getWorld();
        return world.worldToWindow(windowCoordinates);
    }

    protected Vector2D worldToWindow(Vector2D windowCoordinates)
    {
        SimulationWorld world = (SimulationWorld) getWorld();
        return world.worldToWindow(windowCoordinates);
    }
    
    protected Point2D windowToWorld(Point2D windowCoordinates)
    {
        SimulationWorld world = (SimulationWorld) getWorld();
        return world.windowToWorld(windowCoordinates);
    }

    protected Vector2D windowToWorld(Vector2D windowCoordinates)
    {
        SimulationWorld world = (SimulationWorld) getWorld();
        return world.windowToWorld(windowCoordinates);
    }
}
