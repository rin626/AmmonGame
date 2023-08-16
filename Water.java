// import java.awt.Graphics;
// import java.awt.Color;
import javax.swing.ImageIcon;

//Call setLocation, passing it the given x,y pair
public class Water extends Sprite{
    public Water(int x, int y){
        super();
        setLocation(x,y);
        image = new ImageIcon("water.jpg");
    }

}
//         image = null;

//     }

//     @Override
//     public void draw(Graphics g){
//         if(absolutePosition != null){
//             g.setColor(Color.blue);
//             g.fillRect(absolutePosition.x, absolutePosition.y, 50, 50);
//         }
//     }
// }
