
import javax.sound.midi.*;

public class MidiScaleCreator {
    public static void main(String[] args) {
        try {
            // 새로운 시퀀스 생성 (Division Type: PPQ, Resolution: 480)
            Sequence sequence = new Sequence(Sequence.PPQ, 480);
            Track track = sequence.createTrack();

            // C 메이저 음계 (중간 C부터 1옥타브)
            int[] notes = {60, 62, 64, 65, 67, 69, 71, 72};

            // 각 노트에 대해 note_on 및 note_off 메시지 추가
            int tick = 0;
            for (int note : notes) {
                // note_on 메시지 추가 (Velocity: 64)
                track.add(createNoteEvent(ShortMessage.NOTE_ON, note, 64, tick));
                tick += 480; // 음을 유지할 시간 설정

                // note_off 메시지 추가
                track.add(createNoteEvent(ShortMessage.NOTE_OFF, note, 64, tick));
            }

            // MIDI 파일로 저장
            MidiSystem.write(sequence, 1, new java.io.File("scale.mid"));
            System.out.println("MIDI 파일이 성공적으로 생성되었습니다: scale.mid");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 노트 이벤트 생성 메서드
    private static MidiEvent createNoteEvent(int command, int note, int velocity, int tick) {
        MidiEvent event = null;
        try {
            ShortMessage message = new ShortMessage();
            message.setMessage(command, 0, note, velocity);
            event = new MidiEvent(message, tick);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return event;
    }
}
