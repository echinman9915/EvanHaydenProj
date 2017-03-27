import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Main extends JPanel {

    //instance fields for the general environment
    public static final int FRAMEWIDTH = 1400, FRAMEHEIGHT = 800;
    private Timer timer;
    private boolean[] keys;


    //instance fields for sprites which are located on screen.
    private Sprite batman = new Batman();
    private ArrayList<Sprite> obstacles;
    private ArrayList<Sprite> bullets;
    private ArrayList<Zombie> deadpeople;
    private boolean w,a,s,d;


    public Main() {

        keys = new boolean[512]; //should be enough to hold any key code.
        //initialize the instance fields.

        batman = new Batman();
        deadpeople = new ArrayList<Zombie>();
        bullets = new ArrayList<Sprite>();
        for (int i = 0; i < 10; i++) {
            deadpeople.add(new Zombie(100 * i, 100 * 1, batman));
        }


        //init arraylist ;)
        //add obstacles - cars and stuff


        timer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                if (keys[KeyEvent.VK_W]) {
                    w=true;
                    batman.setDir(Sprite.NORTH);

                }
                if (keys[KeyEvent.VK_A]) {
                    batman.setDir(Sprite.WEST);
                    a=true;

                     //probably.
                }
                if (keys[KeyEvent.VK_D]) {
                    batman.setDir(Sprite.EAST);
                    d=true;
                     //probably.
                }
                if (keys[KeyEvent.VK_S]) {
                    batman.setDir(Sprite.SOUTH);
                    s=true;
                     //probably.
                }
                if (keys[KeyEvent.VK_SPACE]) {

                    Bullet b = new Bullet(batman.getLoc().x, batman.getLoc().y, batman.getDir());
                    bullets.add(b);
                    keys[KeyEvent.VK_S] = false; //probably.
                }

                int lives = 3;
                for (Sprite b : bullets) {
                    for (int i = 0; i < deadpeople.size(); i++) {
                        Zombie zomb = deadpeople.get(i);
                        if (b.intersects(zomb) && lives != 0) {
                            lives--;
                        }
                        if (lives == 0) {
                            deadpeople.remove(i);
                        }

                    }

                }
                //for each bullet in bullets, update.
                //update each obstacle

                //check for collisions
//                if bullet hits zombie add 1 damamage out of 3
//                if damage equals 3 zomble dies
//                if bullet hits barrel, barrel blows up and gives 3x damage in a circle with a radius with 10.


                repaint();
                for (Zombie z : deadpeople) {
                    z.update();
                }
                if(w){
                    batman.setLoc(new Point(batman.getLoc().x,batman.getLoc().y-5));
                }
                if(s){
                    batman.setLoc(new Point(batman.getLoc().x,batman.getLoc().y+5));
                }
                if(a){
                    batman.setLoc(new Point(batman.getLoc().x-5,batman.getLoc().y));
                }
                if(d){
                    batman.setLoc(new Point(batman.getLoc().x+5,batman.getLoc().y));
                }
            }
        });
        timer.start();

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                keys[keyEvent.getKeyCode()] = true;
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                keys[keyEvent.getKeyCode()] = false;
                int code = keyEvent.getKeyChar();
                if(code=='w'){
                    w=false;
                }
                if(code=='a'){
                    a=false;
                }
                if(code=='s'){
                    s=false;
                }
                if(code=='d'){
                    d=false;
                }
            }
        });

    }

    //Our paint method.
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        batman.draw(g2);
        for (Zombie z : deadpeople) {
            z.draw(g2);
        }
        for (Sprite s:bullets) {
            s.draw(g2);
        }


        //draw all the things.
//        for (Sprite s : obstacles) {
//            s.draw(g2);
//        }

    }

    //sets ups the panel and frame.
    public static void main(String[] args) {
        JFrame window = new JFrame("BoxHead Zombie!");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(0, 0, FRAMEWIDTH, FRAMEHEIGHT + 22); //(x, y, w, h) 22 due to title bar.

        Main panel = new Main();
        panel.setSize(FRAMEWIDTH, FRAMEHEIGHT);

        panel.setFocusable(true);
        panel.grabFocus();

        window.add(panel);
        window.setVisible(true);
        window.setResizable(false);
    }
}