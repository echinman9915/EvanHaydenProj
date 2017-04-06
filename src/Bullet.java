/**
 * Created by haydn_davies on 3/24/17.
 */
public class Bullet extends Sprite{

    private Sprite bullet;
    private int life;

    public Bullet(int x, int y, int dir){
        super(x,y,dir);
        setSpeed(10);
        setPic("red2.png", WEST);
        life=1;


    }


    public void update(){

        super.update();

     }

     public void getHit(){
         life--;
     }

     public int getLife(){
         return life;
     }


}
