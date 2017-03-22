//import java.util.ArrayList;
//
///**
// * Created by haydn_davies on 3/22/17.
// */
//public class Zombie extends Sprite{
//
//    private Sprite target;
//    public Zombie(){
//        super(x,y, EAST);
//        ArrayList<Sprite> sprites = world.getAllSprites();
//        target = sprites.get((int)(Math.random() * sprites.size()));
//
//        while(target.equals(this) && sprites.size() > 1){
//            target = sprites.get((int)(Math.random() * sprites.size()));
//        }
//        setPic("frog-prince.png", NORTH);
//        setSpeed(11);
//    }
//
//    @Override
//    public void update(){
//        int d = getWorld().getDirection(this.getLoc(), target.getLoc());
//        setDir(d);
//
//        super.update();
//
//    }
//
//    public Sprite getTarget() {
//        return target;
//    }
//
//    public void setTarget(Sprite target) {
//        this.target = target;
//    }
//
//}
