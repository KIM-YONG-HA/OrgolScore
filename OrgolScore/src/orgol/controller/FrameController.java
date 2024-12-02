package orgol.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class FrameController {
	
	private JFrame frame;
	
	public FrameController() {
		
		frame = new JFrame();
		
	}
	
	
	
	
	public void createSplashFrame() {
		
		
		
        // 스플래시 페이지의 크기 및 설정
        frame.setSize(360, 230);
        frame.setLocationRelativeTo(null); // 화면 중앙에 위치
        
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setUndecorated(true); // 타이틀 바 제거
        

        Container c = frame.getContentPane();
        
        
        // 로고 또는 로딩 화면 구성 요소 설정
        JPanel splashPanel = new JPanel(new BorderLayout());
        splashPanel.setBackground(new Color(0X0560EB)); // 배경색 설정
      
        
        
        // 사용자 정의 폰트 로드
        try  {
        	
        	InputStream fontStream = getClass().getClassLoader().getResourceAsStream("resource/font/CookieRunBold.ttf");
        	
        	if (fontStream == null) throw new IOException("폰트 파일을 찾을 수 없습니다.");

            // 기본 폰트 생성
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(20f);

            // 굵은 스타일로 변경
            Font boldCustomFont = customFont.deriveFont(Font.BOLD);

            // JLabel에 스타일의 폰트 적용
            JLabel splashLabel = new JLabel("오스 :: ORGOL SCORE", SwingConstants.CENTER);
            splashLabel.setFont(boldCustomFont);
            splashLabel.setForeground(new Color(0xffffff));
            
            //FFD700
            //F0F0F0
            
            splashPanel.add(splashLabel);
            
        } catch (FontFormatException | IOException e) {
        	
            e.printStackTrace();
            System.out.println("폰트를 로드하는 중 문제가 발생했습니다.");
            
        }
        
      
        
        c.add(splashPanel);
        
              
        


        
        frame.setVisible(true);
		
	}
	
	
	
	public void createMainFrame() {
		
		frame = new JFrame();
		
		frame.setTitle("오르골 악보 제작 프로그램");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(400,300);
		//frame.setLocationRelativeTo(null); // 중앙 위치
		
		
		frame.setUndecorated(true); // 타이틀 바 제거
		

        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // 최대화

		
		// 메뉴 타이틀
		
		
		
		// 메뉴바 
		frame.setJMenuBar(menuBar());
		
		frame.setVisible(true);
		
		
		
		
	}
	
	
	
	

	// 타이틀바
	public void titleBar() {
		
		System.out.println("타이틀바");
		
		
		
	}
	
	
	// 메뉴바 
	public JMenuBar menuBar() {
		
		System.out.println("메뉴바");
		JMenuBar menuBar = new JMenuBar(); // 메뉴바 생성
		JMenu fileMenu = new JMenu("파일");
		
		fileMenu.add(new JMenuItem("새파일"));
		fileMenu.addSeparator();
		fileMenu.add(new JMenuItem("파일 열기"));
		fileMenu.add(new JMenuItem("최근 작업 파일"));
		fileMenu.add(new JMenuItem("midi 가져오기"));
		fileMenu.add(new JMenuItem("midi 내보내기"));
		fileMenu.add(new JMenuItem("저장"));
		fileMenu.add(new JMenuItem("다른 이름으로 저장"));
		fileMenu.add(new JMenuItem("악보 변환"));
		fileMenu.add(new JMenuItem("종료"));
		
		menuBar.add(fileMenu);
		
		return menuBar;
		
	}
	
	
	// 프레임 생성 
	
	
	// 
	
	
	

	
	// 레이아웃 영역
	public void layoutPanel() {
		
		
		
	}
	
	
	
	
	// 첫번째 화면
	
	public void introScreenPanel() {
		
		
	}
	
	
	// 두번째 화면
	public void workSpacePanel() {
		
		
		
	}
	
	
	
	
	
	
	
	

}
