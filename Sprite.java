import java.awt.*;
import javax.swing.ImageIcon;


public abstract class Sprite {

    //make fields
    protected Point absolutePosition;
    protected Point relativePosition;
    protected ImageIcon image;

    //instantiate
    public Sprite(){
        absolutePosition = new Point();
        relativePosition= new Point();
        image = null;
    }

    //the method to converts between absolute and relative coordinates
    public void setLocation(int x, int y){
        relativePosition.x = x;
        relativePosition.y = y;
        absolutePosition.x = x*80+10;
        absolutePosition.y = y*80+10;
    }

    //the method to set the location
    public void setLocation(Point p){
        if(p!= null){
            setLocation(p.x,p.y);
        } else {
            absolutePosition = null;
            relativePosition = null;
        }
    }

    //the method to draw the picture
    public void draw(Graphics g){
        if(absolutePosition != null){
            image.paintIcon(null, g, absolutePosition.x,absolutePosition.y);
        }
    }

    //getter method
    public Point getLocation() {
        return relativePosition;
    }

    //the method to check if Ammon is touching something
    public boolean isTouching(Sprite other){
        if (this.relativePosition.equals(other.relativePosition)){
            return true;
        }else{
            return false;
        }
    }

    //the method to check if Ammon is near something
    public boolean isNear(Sprite other){
        if (this.relativePosition != null && other.relativePosition != null){
            int dx = Math.abs(this.relativePosition.x - other.relativePosition.x);
            int dy = Math.abs(this.relativePosition.y - other.relativePosition.y);

            return(dx<=1 && dy <=1);
        }else{
            return false;
        }
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Sprite other){
            return this.isTouching(other);
        }
        return false;
    }
    
}