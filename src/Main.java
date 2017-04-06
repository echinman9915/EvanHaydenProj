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
    private Batman batman = new Batman();
    private int gamelevel = 0;
    private ArrayList<Sprite> obstacles;
    private ArrayList<Sprite> bullets;
    private ArrayList<Zombie> deadpeople;
    private boolean w, a, s, d;
    private int count = 0;
    private int zombieSize;
    private int nextlevel;
    private int score;
    private int countHit;
    private boolean alive = true;


    public Main() {
        nextlevel = 2;
        score = 0;

        System.out.println("it");
        keys = new boolean[512]; //should be enough to hold any key code.
        //initialize the instance fields.

        batman = new Batman();
        deadpeople = new ArrayList<Zombie>();
        bullets = new ArrayList<Sprite>();
        for (int i = 0; i < 4; i++) {
            deadpeople.add(new Zombie(50, 100 * (2 * i) + 20, batman));
        }
        for (int i = 0; i < 4; i++) {
            deadpeople.add(new Zombie(1200, 100 * (2 * i) + 20, batman));
        }


        //init arraylist ;)
        //add obstacles - cars and stuff


        timer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (batman.getLifes() == 0) {
                    alive = false;
                    gamelevel = 1;
                }
                if (keys[KeyEvent.VK_W]) {
                    w = true;
                    batman.setDir(Sprite.NORTH);

                }
                if (keys[KeyEvent.VK_A]) {
                    batman.setDir(Sprite.WEST);
                    a = true;

                    //probably.
                }
                if (keys[KeyEvent.VK_D]) {
                    batman.setDir(Sprite.EAST);
                    d = true;
                    //probably.
                }
                if (keys[KeyEvent.VK_S]) {
                    batman.setDir(Sprite.SOUTH);
                    s = true;
                    //probably.
                }
                if (keys[KeyEvent.VK_SPACE]) {
//                    if(count%2==0)
                    {
                        Bullet b = new Bullet(batman.getCenterPoint().x, batman.getCenterPoint().y, batman.getDir());
                        bullets.add(b);
                        b.setSpeed(15);
                    }


                    keys[KeyEvent.VK_SPACE] = false; //probably.
                }


                for (Sprite b : bullets) {
                    b.update();
                }
                zombieSize = deadpeople.size();
                for (int i = 0; i < deadpeople.size(); i++) {
                    // && lives != 0) {

                    for (int j = 0; j < bullets.size(); j++) {
                        if (i < deadpeople.size()) {


                            if (deadpeople.get(i).intersects(bullets.get(j))) {
                                deadpeople.get(i).getHit();
                                bullets.remove(j);
                            }
                            if (deadpeople.get(i).getLives() == 0) {
                                deadpeople.remove(i);
                                score++;
                            }
                        }

                    }


//                                zomb.getHit();
//
//
//
//                            if (zomb.getLives() == 0) {
//                                deadpeople.remove(i);
//                            }

                }
                batman.update();

                if (deadpeople.size() == 0) {
//                        deadpeople.add(new Zombie(100,100,batman));
//                        deadpeople.add(new Zombie(300,100,batman));
//                        deadpeople.add(new Zombie(100,300,batman));
//                        deadpeople.add(new Zombie(100,100,batman));
//                        deadpeople.add(new Zombie(500,100,batman));
//                        deadpeople.add(new Zombie(100,500,batman));
//                        deadpeople.add(new Zombie(0,0,batman));
                    for (int i = 0; i < nextlevel; i++) {
                        int y = 800 / nextlevel;

                        if (i % 2 == 0)
                            deadpeople.add(new Zombie(1250, y * i, batman));
                        else
                            deadpeople.add(new Zombie(50, y * i, batman));
                    }
                    nextlevel++;


                }


                //for each bullet in bullets, update.
                //update each obstacle

                //check for collisions
//                if bullet hits zombie add 1 damamage out of 3
//                if damage equals 3 zomble dies
//                if bullet hits barrel, barrel blows up and gives 3x damage in a circle with a radius with 10.

                count = count + 1;
                repaint();
                for (Zombie z : deadpeople) {
                    z.update();
                    if (z.intersects(batman) && !batman.getVince()) {
                        batman.getHit();
                        batman.setVince(true);
                    }
                }
                if (w) {
                    batman.setLoc(new Point(batman.getLoc().x, batman.getLoc().y - 8));
                }
                if (s) {
                    batman.setLoc(new Point(batman.getLoc().x, batman.getLoc().y + 8));
                }
                if (a) {
                    batman.setLoc(new Point(batman.getLoc().x - 8, batman.getLoc().y));
                }
                if (d) {
                    batman.setLoc(new Point(batman.getLoc().x + 8, batman.getLoc().y));
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
                if (code == 'w') {
                    w = false;
                }
                if (code == 'a') {
                    a = false;
                }
                if (code == 's') {
                    s = false;
                }
                if (code == 'd') {
                    d = false;
                }
            }
        });

    }

    //Our paint method.
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (gamelevel == 0) {
            if (alive) {
                batman.draw(g2);
            }

            for (Zombie z : deadpeople) {
                z.draw(g2);
            }
            for (Sprite s : bullets) {
                s.draw(g2);
            }

            Font myFont = new Font("Courier New", 1, 50);
            g2.setFont(myFont);
            g2.drawString("score: " + score, getWidth() / 2 + 400, 50);
            g2.drawString("Lives: " + batman.getLifes(), 100, 100);
        }
        if (gamelevel == 1) {
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.setColor(Color.WHITE);
            Font myFont = new Font("Courier New", 1, 50);
            g2.drawString("Final Score: " + score, getWidth()/2, getHeight()/2);

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