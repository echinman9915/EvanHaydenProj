import java.awt.*;
import java.util.ArrayList;
import java.awt.Point;

/**
 * Created by haydn_davies on 3/22/17.
 */
public class Zombie extends Sprite {

    private Sprite target;
    private int lives;


    public Zombie(int x, int y, Sprite sprite) {
        super(x, y, EAST);
        setPic(" BRAINS.png", EAST);
        lives = 3;

//        ArrayList<Sprite> sprites = sprite.getAllSprites();
//        target = sprites.get((int) (Math.random() * sprites.size()));
//        while ((target.equals(this) && sprites.size() > 1)) {
//            target = sprites.get((int) (Math.random() * sprites.size()));
//        }

        target = sprite;
        setSpeed(4);

        setLoc(new Point(x, y));
    }

    @Override
    public void update() {

        int d = target.getDirection(this.getLoc(), target.getLoc());
        this.setDir(d);


        setDir(d);

        super.update();

    }

    public Sprite getTarget() {
        return target;
    }

    public void setTarget(Sprite target) {
        this.target = target;
    }

    public void getHit(){
        lives--;
    }
    public int getLives(){
        return lives;
    }

}









