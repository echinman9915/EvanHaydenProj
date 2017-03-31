/**
 * Created by haydn_davies on 3/24/17.
 */
public class Bullet extends Sprite{

    private Sprite bullet;

    public Bullet(int x, int y, int dir){
        super(x,y,dir);
        setSpeed(10);
        setPic("red2.png", WEST);

    }


    public void update(){

        super.update();

     }


}
