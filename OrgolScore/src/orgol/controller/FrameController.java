package orgol.controller;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FrameController {
	
	private JFrame frame;

	private int frameWidth;
	private int frameHeight;
	
	
	
	public FrameController() {
		
		frame = new JFrame();
		
		frame.setTitle("오르골 악보 제작 프로그램");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(400,300);
		
		//frame.setLocationRelativeTo(null); // 중앙 위치
		
		

        //frame.setUndecorated(true); // 타이틀 바 제거
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // 최대화

	/*

		JMenuBar menuBar = new JMenuBar(); // 메뉴바 생성
		
		JMenu fileMenu = new JMenu("파일");
		
		
		fileMenu.add(new JMenuItem("파일 열기"));
		fileMenu.addSeparator();
		fileMenu.add(new JMenuItem("최근 작업 파일"));
		fileMenu.add(new JMenuItem("midi 가져오기"));
		fileMenu.add(new JMenuItem("midi 내보내기"));
		fileMenu.add(new JMenuItem("저장"));
		fileMenu.add(new JMenuItem("다른 이름으로 저장"));
		fileMenu.add(new JMenuItem("악보 변환"));
		fileMenu.add(new JMenuItem("종료"));
		
		menuBar.add(fileMenu);
		
		frame.setJMenuBar(menuBar);
		
		
		
		*/
		
		
	/*
	 * PanelController panelController = new PanelController();
	 * panelController.menuBarPanel();
	 * 
	 * frame.setJMenuBar(panelController);
	 */
		
		
		JPanel jp = new JPanel();
		jp.add(new JButton("btn"));
		
		frame.add(jp);
		
		
		frame.setVisible(true);
		
		
	}
	
	
	
	// 프레임 생성 
	
	
	// 
	
	
	

	
	
	
	
	

}
