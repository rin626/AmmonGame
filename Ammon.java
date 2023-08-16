import javax.swing.ImageIcon;

//incert picture and Call setLocation, passing it the given x,y pair
public class Ammon extends Sprite{
    public Ammon(int x, int y){
        super();
        setLocation(x,y);
        image = new ImageIcon("ammon.png");
    } 
}