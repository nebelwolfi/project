package de.tu_darmstadt.gdi1.gorillas.util;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import de.tu_darmstadt.gdi1.gorillas.main.Gorillas;

public class MusicPlayer {
	public static Clip clip;
	public static void playBg() {
		if (!Gorillas.data.musicIsPlaying && Gorillas.options.isMusicEnabled()) {
		  try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("sounds/bg.wav").getAbsoluteFile());
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			Gorillas.data.musicIsPlaying = true;
    	  } catch(Exception ex) {
    		System.out.println("Error with playing sound.");
    		ex.printStackTrace();
    	  }
		}
	}

	public static void stopBg() {
		clip.stop();
	}
	
	public static void playButton() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("sounds/button.wav").getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
    	} catch(Exception ex) {
    		System.out.println("Error with playing sound.");
    		ex.printStackTrace();
    	}
	}
	
	public static void playApplause() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("sounds/applause.wav").getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
    	} catch(Exception ex) {
    		System.out.println("Error with playing sound.");
    		ex.printStackTrace();
    	}
	}
}
