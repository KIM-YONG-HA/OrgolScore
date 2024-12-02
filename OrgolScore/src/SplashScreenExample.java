
import javax.swing.*;
import java.awt.*;

public class SplashScreenExample {
    // 스플래시 페이지 클래스
    static class SplashScreen extends JWindow {
        public SplashScreen() {
            // 스플래시 페이지의 크기 및 설정
            setSize(360, 230);
            setLocationRelativeTo(null); // 화면 중앙에 위치

            // 로고 또는 로딩 화면 구성 요소 설정
            JPanel contentPane = new JPanel(new BorderLayout());
            contentPane.setBackground(new Color(0X0560EB)); // 배경색 설정

            JLabel splashLabel = new JLabel("ORGOL SCORE", SwingConstants.CENTER);
            splashLabel.setFont(new Font("Arial", Font.BOLD, 24));
            splashLabel.setForeground(Color.WHITE);

            contentPane.add(splashLabel, BorderLayout.CENTER);
            setContentPane(contentPane);
        }
    }

    // 메인 애플리케이션 클래스
    static class MainApplication extends JFrame {
        public MainApplication() {
            setTitle("Main Application");
            setSize(800, 600);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JLabel mainLabel = new JLabel("Welcome to the Main Application!", SwingConstants.CENTER);
            mainLabel.setFont(new Font("Arial", Font.BOLD, 24));
            add(mainLabel);
        }
    }

    public static void main(String[] args) {
        // 스플래시 페이지 표시
        SplashScreen splashScreen = new SplashScreen();
        splashScreen.setVisible(true);

        // 비동기 작업을 이용해 메인 화면 로드
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                // 실제 초기화 작업을 이곳에 작성
                initializeResources();
                return null;
            }

            @Override
            protected void done() {
                // 스플래시 페이지 닫기
                splashScreen.dispose();

                // 메인 애플리케이션 표시
                MainApplication mainApp = new MainApplication();
                mainApp.setVisible(true);
            }
        };

        worker.execute();  // 비동기 작업 실행
    }

    // 실제 초기화 작업을 수행하는 메서드
    private static void initializeResources() {
        try {
            // 예: 데이터베이스 연결 설정
            System.out.println("Connecting to database...");
            Thread.sleep(1000);  // 데이터베이스 연결 시뮬레이션

            // 예: 파일 로드
            System.out.println("Loading configuration files...");
            Thread.sleep(2000);  // 파일 로드 시뮬레이션

            // 기타 초기화 작업
            System.out.println("Initializing application components...");
            Thread.sleep(1500);  // 컴포넌트 초기화 시뮬레이션

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
