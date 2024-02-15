//import java.awt.*;
//import java.io.File;
//import javax.sound.sampled.*;
import javax.swing.*;
public class p2index {
	public static void main(String[] args) {
		JFrame frame = new JFrame("test");
		car bluecar = new car("blue arrow.png",0);
		car redcar = new car("car1.png",0);
		/*try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("vineboom.mp3").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }*/
		frame.setTitle("Racetrack");
		frame.setSize(850,600);
		frame.setVisible(true);
		frame.add(bluecar);
	}
}