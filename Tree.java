import javax.swing.ImageIcon;

//incert picture and Call setLocation, passing it the given x,y pair
public class Tree extends Sprite{
    public Tree(int x, int y){
        super();
        setLocation(x,y);
        image = new ImageIcon("tree.png");
    } 
}