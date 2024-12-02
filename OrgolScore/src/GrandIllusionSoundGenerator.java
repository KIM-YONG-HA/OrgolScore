
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class GrandIllusionSoundGenerator {
    public static void main(String[] args) {
        // 음계 리스트 정의 (Grand Illusion 30 기준 및 C키 추가)
        String[] notes = {
            "C2", "D2", "E2", "F2", "G2", "A2", "B2", "C3", "C4", "F4", "F#4", "G4", "G#4", "A4", "A#4", "B4", "C5", "C#5", "D5", "D#5", "E5", "F5", "F#5", "G5", "G#5", "A5", "A#5", "B5", "C6", "C#6", "D6", "D#6", "E6", "F6", "F#6", "G6", "G#6", "A6", "A#6"
        };

        // 각 음계별 WAV 파일 생성
        for (String note : notes) {
            generateWavFile(note);
        }
    }

    public static void generateWavFile(String note) {
        // 샘플링 레이트와 기타 오디오 속성 설정
        float sampleRate = 44100;
        int sampleSizeInBits = 16;
        int channels = 1;
        boolean signed = true;
        boolean bigEndian = false;

        AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
        int durationInSeconds = 2;  // 각 음계의 길이 (2초)
        int bufferSize = (int) sampleRate * durationInSeconds;
        byte[] buffer = new byte[bufferSize];

        // 파형 데이터 생성 (간단한 사인파 예제)
        double frequency = getFrequency(note);
        for (int i = 0; i < buffer.length; i++) {
            double angle = 2.0 * Math.PI * i / (sampleRate / frequency);
            buffer[i] = (byte) (Math.sin(angle) * 127);
        }

        // WAV 파일로 저장
        File file = new File(note + ".wav");
        try (AudioInputStream stream = new AudioInputStream(
                new ByteArrayInputStream(buffer), format, buffer.length)) {
            AudioSystem.write(stream, AudioFileFormat.Type.WAVE, file);
            System.out.println("파일 생성 완료: " + file.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 음계에 따른 주파수 반환 메소드
    public static double getFrequency(String note) {
        switch (note) {
            case "C2": return 65.41;
            case "D2": return 73.42;
            case "E2": return 82.41;
            case "F2": return 87.31;
            case "G2": return 98.00;
            case "A2": return 110.00;
            case "B2": return 123.47;
            case "C3": return 130.81;
            case "C4": return 261.63;
            case "F4": return 349.23;
            case "F#4": return 369.99;
            case "G4": return 392.00;
            case "G#4": return 415.30;
            case "A4": return 440.00;
            case "A#4": return 466.16;
            case "B4": return 493.88;
            case "C5": return 523.25;
            case "C#5": return 554.37;
            case "D5": return 587.33;
            case "D#5": return 622.25;
            case "E5": return 659.25;
            case "F5": return 698.46;
            case "F#5": return 739.99;
            case "G5": return 783.99;
            case "G#5": return 830.61;
            case "A5": return 880.00;
            case "A#5": return 932.33;
            case "B5": return 987.77;
            case "C6": return 1046.50;
            case "C#6": return 1108.73;
            case "D6": return 1174.66;
            case "D#6": return 1244.51;
            case "E6": return 1318.51;
            case "F6": return 1396.91;
            case "F#6": return 1479.98;
            case "G6": return 1567.98;
            case "G#6": return 1661.22;
            case "A6": return 1760.00;
            case "A#6": return 1864.66;
            default: throw new IllegalArgumentException("지원하지 않는 음계입니다: " + note);
        }
    }
}
