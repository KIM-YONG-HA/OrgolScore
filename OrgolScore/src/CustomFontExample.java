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

        // 배경색 설정
        getContentPane().setBackground(new Color(0X0560EB));

        // 사용자 정의 폰트 로드
        try (InputStream fontStream = getClass().getClassLoader().getResourceAsStream("resource/font/CookieRunBold.ttf")) {
            if (fontStream == null) {
                throw new IOException("폰트 파일을 찾을 수 없습니다.");
            }

            // 기본 폰트 생성
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(50f);

            // 굵은 스타일로 변경
            Font boldCustomFont = customFont.deriveFont(Font.BOLD);

            // JLabel에 굵은 스타일의 폰트 적용
            JLabel label = new JLabel("오스 :: ORGOL SCORE", SwingConstants.CENTER);
            label.setFont(boldCustomFont);
            label.setForeground(new Color(0xffffff)); // 폰트 색상 설정 (하얀색)
            
            //FFD700
            
            //F0F0F0
            
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
