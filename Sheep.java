import javax.swing.ImageIcon;

//incert picture and Call setLocation, passing it the given x,y pair
public class Sheep extends Sprite{
    public Sheep(int x, int y){
        super();
        setLocation(x,y);
        image = new ImageIcon("sheep.png");
    } 
}