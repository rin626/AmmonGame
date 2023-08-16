import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import java.awt.Point;
import javax.swing.JOptionPane;


public class Main extends JPanel implements KeyListener{

    //declare fields and ArrayLists
    public static final int NUMROWS = 9;
    public static final int NUMCOLS = 9;
    private ArrayList<Sprite> sprites;
    private ArrayList<Robbers> thugs;
    private ArrayList<Tree> forest;
    private ArrayList<Water> lake;
    private ArrayList<Sheep> flock;
    private Ammon hero;
    ImageIcon img1;
    private int sheepCounter;
    private int robberCounter;
    boolean killed = false;


    public Main(){       
        //instantiate
        thugs = new ArrayList<>();
        forest = new ArrayList<>();
        lake = new ArrayList<>();
        flock = new ArrayList<>();
        sprites = new ArrayList<>();
        sheepCounter = 6;
        robberCounter = 4;

        //register the key listener
        addKeyListener(this);

        //add picture where you want to set
        setPosition();

    }
    
    //the method to set the position for objects
    public void setPosition(){
        hero = new Ammon(1,1);
        thugs.add(new Robbers(5,0));
        thugs.add(new Robbers(8,4));
        thugs.add(new Robbers(0,5));
        thugs.add(new Robbers(6,7));

        forest.add(new Tree(0,0));
        forest.add(new Tree(0,1));
        forest.add(new Tree(1,0));
        forest.add(new Tree(2,3));
        forest.add(new Tree(3,3));
        forest.add(new Tree(1,7));
        forest.add(new Tree(1,6));
        forest.add(new Tree(8,7));
        forest.add(new Tree(8,8));
        forest.add(new Tree(7,1));

        lake.add(new Water(5,2));
        lake.add(new Water(5,3));
        lake.add(new Water(5,4));
        lake.add(new Water(5,5));
        lake.add(new Water(5,6));
        lake.add(new Water(6,2));
        lake.add(new Water(6,3));
        lake.add(new Water(6,4));
        lake.add(new Water(7,2));
        lake.add(new Water(7,3));
        lake.add(new Water(7,4));
        lake.add(new Water(2,5));
        lake.add(new Water(2,6));
        lake.add(new Water(3,5));
        lake.add(new Water(3,6));
        lake.add(new Water(4,5));
        lake.add(new Water(4,6));
    
        flock.add(new Sheep(4,4));
        flock.add(new Sheep(6,5));
        flock.add(new Sheep(8,2));
        flock.add(new Sheep(3,7));
        flock.add(new Sheep(4,7));
        flock.add(new Sheep(5,7));


        //adding all objects to sprites arrayList
        for(var tree:forest){
            sprites.add(tree);
        }
        for(var water:lake){
            sprites.add(water);
        }
        for(var robber:thugs){
            sprites.add(robber);
        }
        for(var sheep:flock){
            sprites.add(sheep);
        }
        sprites.add(hero);


    }

    public static void main(String[] args) {
        
        var window = new JFrame();
        //make the window
        window.setSize(740,800);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setContentPane(new Main());
        window.setVisible(true);
        }

    public void paintComponent(Graphics g){
        requestFocusInWindow();
        //set background
        // g.setColor(new Color(200, 250, 150, 100));
        // g.fillRect(10,10,numCols*50,numRows*50);
        for (int row = 0; row < NUMROWS; row++) {
            for (int col = 0; col < NUMCOLS; col++) {
                img1 = new ImageIcon("ground.png");
                img1.paintIcon(null, g,row*80+10,col*80+10);
            }
            
        }
        // img1 = new ImageIcon("grass_15.jpg");
        // img1.paintIcon(null, g,10,10);



        //draw the line around the map border
        g.setColor(Color.black);
        g.drawLine(10,10,10,730);
        g.drawLine(10,10,730,10);
        g.drawLine(10,730,730,730);
        g.drawLine(730,10,730,730);


        //call draw method

        // for(var r: flock){
        //     r.draw(g);
        // }

        // for(var r: thugs){
        //     r.draw(g);
        // }
        // for(var r: forest){
        //     r.draw(g);
        // }
        // for(var r: lake){
        //     r.draw(g);
        // }

        // hero.draw(g);

        //draw all objects one by one from sprites arrayList
        for(var s:sprites){
            s.draw(g);
        }

        g.drawString("Sheep Remaining"+sheepCounter,30,755);
        g.drawString("Robbers Remaining"+robberCounter,500,755);
    }

        // for(int i=0;i<flock.size();i++){
        //     flock.get(i).draw(g);
        // }
    private boolean hitATree(Point p){
        boolean result = false;
        for(var tree :forest){
            Point t = tree.getLocation();
            if(t.x == p.x && t.y == p.y){
                result = true;
                break;
            }

        }
        return result;

    }


        public void keyTyped(KeyEvent e){

        }

        public void keyPressed(KeyEvent e){
            var nextPos = new Point(hero.getLocation());
            int k = e.getKeyCode();
            //Prevent Ammon from going over the boundary line.
            if(nextPos.y > 0){
                if (k == KeyEvent.VK_UP) {
                    nextPos.y--;
                    //stop moving if there is tree on the top
                    if(hitATree(nextPos)){
                        nextPos.y++;
                    }
                }
            }

            //Prevent Ammon from going down the boundary line.
            if(nextPos.y < 8){
                if (k == KeyEvent.VK_DOWN) {
                    nextPos.y++;
                    //stop moving if there is tree under
                    if(hitATree(nextPos)){
                        nextPos.y--;
                    }
                }
            }

            //Prevent Ammon from going to the left of the boundary line.
            if(nextPos.x > 0){
                if (k == KeyEvent.VK_LEFT){
                    nextPos.x--;
                    //stop moving if there is tree on the left
                    if(hitATree(nextPos)){
                        nextPos.x++;
                    }
                }
            }

            //Prevent Ammon from going to the right of the boundary line.
            if(nextPos.x < 8){
                if (k == KeyEvent.VK_RIGHT) {
                    nextPos.x++;
                    //stop moving if there is tree on the right
                    if(hitATree(nextPos)){
                        nextPos.x--;
                    }
                }
            }

             // if(hitATree(nextPos)==F){
            //     hero.setLocation(nextPos);
            // }
            hero.setLocation(nextPos);

            if (k == KeyEvent.VK_W || k == KeyEvent.VK_S || k == KeyEvent.VK_A || k == KeyEvent.VK_D){
                shoot(k);
                if(killed){
                    reset();
                }
                
            }

            //sheep will disappear when Ammon touch the sheep
            for(var sheep:flock){
                if (hero.equals(sheep)){
                    sheep.setLocation(5000,5000);
                    sheepCounter--;
                }
            }

            //Ammon dies when he touches the water and reset the game or exit
            for(var water:lake){
                if (hero.equals(water)) {
                    repaint();
                    int question = JOptionPane.showConfirmDialog(null, "Ammon drowned in the water.\nTry again.","Game Over",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE);
                    if(question == 0){
                        reset();
                    }else{
                        System.exit(0);
                    }
                }
            }
            //Ammon dies when he stands next to the robber and reset the game or exit
            for(var robber: thugs){
                if (hero.isNear(robber)) {
                    repaint();
                    int question = JOptionPane.showConfirmDialog(null, "The robber killed Ammon. \nTry again.","Game Over",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE);
                    if(question == 0){
                        reset();
                    }else{
                        System.exit(0);
                    }
                }
            }
            //end the game when Ammon saves all sheep and reset the game or exit
            if (sheepCounter == 0) {
                repaint();
                int question = JOptionPane.showConfirmDialog(null, "Congratulation! You saved all of the sheep.","Play Again",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE);
                if(question == 0){
                    reset();
                }else{
                    System.exit(0);
                }
            }

            repaint();
        
        }

        public void keyReleased(KeyEvent e){

        }

        //the method to reset the position of all objects
        public void reset(){
            thugs.clear();
            flock.clear();
            lake.clear();
            forest.clear();
            sprites.clear();
            sheepCounter = 6;
            robberCounter = 4;
            setPosition();
            killed = false;
            repaint();
            
        }

        //the method to shoot the robber
        public void shoot(int k){
            switch (k){
                //north
                case KeyEvent.VK_W:
                    for(int i = hero.relativePosition.y; i >= 0 ;i--){   
                        //remove the robber when you shoot it         
                        for(var robber:thugs){
                            if(robber.relativePosition.x == hero.relativePosition.x && robber.relativePosition.y == i){
                                robber.setLocation(5000,5000);
                                robberCounter--;
                                repaint();
                                return;
                            }                  
                        }
                        //display the message and reset the game when you shoot the sheep
                        for(var sheep:flock){
                            if(sheep.relativePosition.x == hero.relativePosition.x && sheep.relativePosition.y == i){
                                int question = JOptionPane.showConfirmDialog(null, "You killed the sheep \nTry again.","Game Over",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE);
                                if(question == 0){
                                    killed = true;
                                    return;
                                }else{
                                    System.exit(0);
                                }
                            }                  
                        }
                        //nothing occurs when you shoot the tree
                        for(var tree:forest){
                            if(tree.relativePosition.x == hero.relativePosition.x && tree.relativePosition.y == i){
                                return;
                            }                  
                        }
                    }
                    break;
                    //south
                case KeyEvent.VK_S:
                    for(int i = hero.relativePosition.y; i <= 8 ;i++){              
                            for(var robber:thugs){
                                if(robber.relativePosition.x == hero.relativePosition.x && robber.relativePosition.y == i){
                                    robber.setLocation(5000,5000);
                                    robberCounter--;
                                    repaint();
                                    return;
                                }                  
                            }
    
                            for(var sheep:flock){
                                if(sheep.relativePosition.x == hero.relativePosition.x && sheep.relativePosition.y == i){
                                    int question = JOptionPane.showConfirmDialog(null, "You killed the sheep \nTry again.","Game Over",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE);
                                    if(question == 0){
                                        killed = true;
                                        return;
                                    }else{
                                        System.exit(0);
                                    }
                                }                  
                            }
    
                            for(var tree:forest){
                                if(tree.relativePosition.x == hero.relativePosition.x && tree.relativePosition.y == i){
                                    return;
                                }                  
                            }
                        }
                        break;
                        //east
                    case KeyEvent.VK_D:
                        for(int i = hero.relativePosition.x; i <= 8 ;i++){  
                            // System.out.println(i);              
                                for(var robber:thugs){
                                    if(robber.relativePosition.y == hero.relativePosition.y && robber.relativePosition.x == i){
                                        robber.setLocation(5000,5000);
                                        robberCounter--;
                                        repaint();
                                        return;
                                    }                  
                                }
        
                                for(var sheep:flock){
                                    if(sheep.relativePosition.y == hero.relativePosition.y && sheep.relativePosition.x == i){
                                        int question = JOptionPane.showConfirmDialog(null, "You killed the sheep \nTry again.","Game Over",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE);
                                        if(question == 0){                                       
                                            killed = true;
                                            return;
                                        }else{
                                            System.exit(0);
                                        }
                                    }                  
                                }
        
                                for(var tree:forest){
                                    if(tree.relativePosition.y == hero.relativePosition.y && tree.relativePosition.x == i){
                                        return;
                                    }                  
                                }
                            }
                            break;
                            //west
                    case KeyEvent.VK_A:
                        for(int i = hero.relativePosition.x; i >= 0 ;i--){  
                            // System.out.println(i);              
                                for(var robber:thugs){
                                    if(robber.relativePosition.y == hero.relativePosition.y && robber.relativePosition.x == i){
                                        robber.setLocation(5000,5000);
                                        robberCounter--;
                                        repaint();
                                        return;
                                    }                  
                                }
        
                                for(var sheep:flock){
                                    if(sheep.relativePosition.y == hero.relativePosition.y && sheep.relativePosition.x == i){
                                        int question = JOptionPane.showConfirmDialog(null, "You killed the sheep \nTry again.","Game Over",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE);
                                        if(question == 0){
                                            killed = true;
                                            return;
                                        }else{
                                            System.exit(0);
                                        }
                                    }                  
                                }
        
                                for(var tree:forest){
                                    if(tree.relativePosition.y == hero.relativePosition.y && tree.relativePosition.x == i){
                                        return;
                                    }                  
                                }
                            }

            }

            
        }

    }     

