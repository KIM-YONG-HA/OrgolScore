import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.io.IOException;

public class CustomFontExample extends JFrame {
    public CustomFontExample() {
        // 기본 JFrame 설정
        setTitle("Custom Font Example");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 사용자 정의 폰트 로드
        try (InputStream fontStream = getClass().getClassLoader().getResourceAsStream("resource/font/강원교육모두 Bold.ttf")) {
            if (fontStream == null) {
                throw new IOException("폰트 파일을 찾을 수 없습니다.");
            }

            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(24f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont); // 폰트를 시스템에 등록

            // JLabel에 사용자 정의 폰트 적용
            JLabel label = new JLabel("Hello with Custom Font! 안녕하세요", SwingConstants.CENTER);
            
            label.setFont(customFont);
            add(label);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            System.out.println("폰트를 로드하는 중 문제가 발생했습니다.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CustomFontExample frame = new CustomFontExample();
            frame.setVisible(true);
        });
    }
}
