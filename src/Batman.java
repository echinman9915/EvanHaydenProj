/**
 * Created by evan_chinman on 3/22/17.
 */
public class Batman extends Sprite{

    public Batman(){
        super(400,400,EAST);
        setPic("batman ak.png", EAST);
        setSpeed(this.getBoundingRectangle().height);


    }

    @Override
    public void update() {
        super.update();

//        try {
//            // open the sound file as a Java input stream
//            String hop = "res/hop.wav";
//            InputStream in = new FileInputStream(hop);
//
//            // create an audiostream from the inputstream
//            AudioStream audioStream = new AudioStream(in);
//
//            // play the audio clip with the audioplayer class
//            AudioPlayer.player.start(audioStream);
//        }catch(Exception e){
//            e.printStackTrace();
//            System.out.println("Error loading sound file.");
//        }

    }
}




