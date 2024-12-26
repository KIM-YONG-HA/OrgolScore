import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
//
//private Map<String, Clip> soundCache = new HashMap<>();
//
//// 사전 로드
//private void preloadSounds() {
//    for (String note : scale) {
//        try {
//            String filePath = "/resource/vst/orgol/" + note + ".wav";
//            InputStream inputStream = getClass().getResourceAsStream(filePath);
//            AudioInputStream audioStream = AudioSystem.getAudioInputStream(inputStream);
//            Clip clip = AudioSystem.getClip();
//            clip.open(audioStream);
//
//            soundCache.put(note, clip);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
//
//// 재생
//private void playVstSound(String scale) {
//    Clip clip = soundCache.get(scale);
//    if (clip != null) {
//        clip.setFramePosition(0); // 시작 위치로 재설정
//        clip.start();
//    }
//}
//
//
//private void createMidiAreaWithGridLayout(JPanel panel) {
//    panel.setLayout(new GridLayout(29, 256, 1, 1)); // 29행 x 256열
//    for (int i = 1; i <= 29; i++) {
//        for (int j = 1; j <= 256; j++) {
//            JLabel button = new JLabel(reversedScale[i - 1]);
//            button.setFont(customFont);
//            button.setOpaque(true);
//            button.setBorder(BorderFactory.createLineBorder(new Color(0xB0B4B8), 1));
//            button.setBackground(i % 2 == 0 ? new Color(0xB6C3CF) : new Color(0xECF0F5));
//
//            button.addMouseListener(new MouseAdapter() {
//                @Override
//                public void mouseClicked(MouseEvent e) {
//                    button.setBackground(new Color(0xC90033));
//                    new Thread(() -> playVstSound(button.getText())).start();
//                }
//            });
//
//            panel.add(button);
//        }
//    }
//}






//
//private void playAllSelectedNotes(List<String> selectedNotes, int bpm) {
//    int beatDuration = 60000 / bpm; // 한 박자의 길이(ms)
//    for (String note : selectedNotes) {
//        new Thread(() -> playVstSound(note)).start();
//        try {
//            Thread.sleep(beatDuration); // BPM 간격
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//}
//
//
//
//
//
//private void createMidiLabels(JPanel panel) {
//    panel.setLayout(null); // 필요 시 GridLayout으로 변경
//    int midiPosX = 0;
//    int midiPosY = 50;
//
//    for (int i = 1; i <= 29; i++) {
//        midiPosX = 0;
//        for (int j = 1; j <= 256; j++) {
//            JLabel button = new JLabel(reversedScale[i - 1]);
//            button.setFont(customFont);
//            button.setOpaque(true);
//            button.setBorder(BorderFactory.createLineBorder(new Color(0xB0B4B8), 1));
//            button.setBounds(midiPosX, midiPosY, 50, 20);
//
//            // 배경색, 전경색 초기화
//            if (i % 2 == 0) {
//                button.setBackground(new Color(0xB6C3CF));
//                button.setForeground(new Color(0xB6C3CF));
//            } else {
//                button.setBackground(new Color(0xECF0F5));
//                button.setForeground(new Color(0xECF0F5));
//            }
//
//            // 클릭 이벤트 추가
//            button.addMouseListener(new MouseAdapter() {
//                @Override
//                public void mouseClicked(MouseEvent e) {
//                    button.setBackground(new Color(0xC90033));
//                    button.setForeground(Color.white);
//
//                    // 오디오 재생을 별도 스레드로 실행
//                    new Thread(() -> playVstSound(button.getText())).start();
//                }
//            });
//
//            // 라벨 추가
//            midiLabels.add(button);
//            panel.add(button);
//            midiPosX += 50;
//        }
//        midiPosY += 20;
//    }
//}

