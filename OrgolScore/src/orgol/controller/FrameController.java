package orgol.controller;

import java.awt.Color;

import javax.swing.JFrame;

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
		
		
		frame.setBackground(new Color(0X2F2F2F));
        frame.setUndecorated(true); // 타이틀 바 제거
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // 최대화
		
        
		frame.setVisible(true);
		
		
	}
	
	
	
	// 프레임 생성 
	
	
	// 
	
	
	

	
	
	
	
	

}
