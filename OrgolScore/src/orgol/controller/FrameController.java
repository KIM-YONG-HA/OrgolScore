package orgol.controller;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class FrameController {
	
	private JFrame frame;
	
	public FrameController() {
		
		
	}
	
	
	// 프레임 생성
	
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
