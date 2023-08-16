import javax.swing.ImageIcon;

////incert picture and Call setLocation, passing it the given x,y pair
public class Robbers extends Sprite{
    public Robbers(int x, int y){
        super();
        setLocation(x,y);
        image = new ImageIcon("robber.png");
    } 
}