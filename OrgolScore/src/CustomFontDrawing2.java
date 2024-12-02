
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class CustomFontDrawing2 extends JPanel {
    private Font customFont;

    public CustomFontDrawing2() {
        try (InputStream is = getClass().getResourceAsStream("/resource/font/PretendardVariable.ttf")) {
            if (is == null) {
                throw new IOException("폰트 파일을 찾을 수 없습니다.");
            }
            customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(24f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            customFont = new Font("Serif", Font.PLAIN, 24); // 폰트 로드 실패 시 기본 폰트 사용
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // 안티앨리어싱 설정
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // 폰트 설정
        g2d.setFont(customFont);

        // 텍스트 그리기 좌표 계산
        String text = "안녕하세요 Hello with Custom Font!";
        FontRenderContext frc = g2d.getFontRenderContext();
        Rectangle2D bounds = customFont.getStringBounds(text, frc);
        double baselineOffset = -bounds.getY();
        int x = 50;  // 원하는 x 좌표
        int y = (int) (100 + baselineOffset); // 원하는 y 좌표에 베이스라인 오프셋 추가

        // 텍스트 그리기
        g2d.drawString(text, x, y);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Custom Font Drawing Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);
            frame.setLocationRelativeTo(null);
            frame.add(new CustomFontDrawing2());
            frame.setVisible(true);
        });
    }
}
