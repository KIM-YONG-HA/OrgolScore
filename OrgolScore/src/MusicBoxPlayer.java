
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicBoxPlayer {
    public static void main(String[] args) {
        try {
            // 오디오 파일 로드 (오르골 소리)
            File audioFile = new File("path/to/your/musicbox_sound.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            // 오디오 재생
            clip.start();
            System.out.println("오르골 사운드를 재생합니다.");

            // 재생이 끝날 때까지 대기
            Thread.sleep(clip.getMicrosecondLength() / 1000);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("오디오 파일을 재생하는 중 문제가 발생했습니다.");
        }
    }
}
