
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

public class SimulationWorld extends World
{
    protected final double DEFAULT_CAMERA_WIDTH; // 1 meter per second
    
    protected Point2D cameraCenter;
    protected double cameraWidth;
    private Matrix2D toWindowMatrix;
    private Matrix2D toWorldMatrix;

    private long lastFrameTimeMS;
    private double timeStepDuration;

    public SimulationWorld(int windowWidth, int windowHeight, Point2D cameraCenter, double cameraWidth)
    {    
        super(windowWidth, windowHeight, 1, false); 
        DEFAULT_CAMERA_WIDTH = cameraWidth;
        this.cameraCenter = cameraCenter;
        this.cameraWidth = cameraWidth;
        this.toWindowMatrix = Matrix2D.worldToWindow(cameraCenter, cameraWidth, new Vector2D(getWidth(), getHeight()));
        this.toWorldMatrix = Matrix2D.windowToWorld(cameraCenter, cameraWidth, new Vector2D(getWidth(), getHeight()));
    }
    
    public void started()
    {
        lastFrameTimeMS = System.currentTimeMillis();
    }

    public void act()
    {
        // Update time step duration
        timeStepDuration = (System.currentTimeMillis() - lastFrameTimeMS) / 1000.0;
        lastFrameTimeMS = System.currentTimeMillis();

        // Update Viewport Matrix
        toWindowMatrix = Matrix2D.worldToWindow(cameraCenter, cameraWidth, new Vector2D(getWidth(), getHeight()));
        toWorldMatrix = Matrix2D.windowToWorld(cameraCenter, cameraWidth, new Vector2D(getWidth(), getHeight()));

    }
    
    public double getTimeStepDuration()
    {
        return timeStepDuration;
    }

    public Point2D worldToWindow(Point2D worldCoord)
    {
        return toWindowMatrix.transform(worldCoord);
    }

    public Vector2D worldToWindow(Vector2D worldCoord)
    {
        return toWindowMatrix.transform(worldCoord);
    }
    
    public Point2D windowToWorld(Point2D windowCoord)
    {        
        return toWorldMatrix.transform(windowCoord);
    }

    public Vector2D windowToWorld(Vector2D windowCoord)
    {        
        return toWorldMatrix.transform(windowCoord);
    }
    
    public void scaleActors()
    {
        List<SimulationActor> actors = getObjects(SimulationActor.class);
        
        double zoomRatio = DEFAULT_CAMERA_WIDTH / cameraWidth;
        
        for (int i=0; i<actors.size(); i++)
        {
            SimulationActor actor = actors.get(i);
            GreenfootImage original = actor.getOriginalImage();
            
            if (original != null)
            {
                int imageWidth = original.getWidth();
                int imageHeight = original.getHeight();
                actor.getImage().scale(imageWidth, imageHeight);
                actor.getImage().drawImage(original, imageWidth, imageHeight);
                actor.getImage().scale((int)(imageWidth*zoomRatio), (int)(imageHeight*zoomRatio));
            }
        }
    }
    

}
