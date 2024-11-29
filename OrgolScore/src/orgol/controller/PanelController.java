package orgol.controller;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class PanelController {

	
	private JPanel pn;
	private JMenu mn;
	
	public PanelController() {
		
		//System.out.println("PanelController 생성자...");
		
		
	}
	
	
	// 타이틀바
	public void titleBarPanel() {
		
		System.out.println("타이틀바");
		
		
		
	}
	
	
	// 메뉴바 
	public JPanel menuBarPanel() {
		
		System.out.println("메뉴바");
		
		pn = new JPanel();
		
		JMenuBar menuBar = new JMenuBar(); // 메뉴바 생성
		
		JMenu fileMenu = new JMenu();
		
		fileMenu.add(new JMenu("새파일"));
		fileMenu.addSeparator();
		fileMenu.add(new JMenu("파일 열기"));
		fileMenu.add(new JMenu("최근 작업 파일"));
		fileMenu.add(new JMenu("midi 가져오기"));
		fileMenu.add(new JMenu("midi 내보내기"));
		fileMenu.add(new JMenu("저장"));
		fileMenu.add(new JMenu("다른 이름으로 저장"));
		fileMenu.add(new JMenu("악보 변환"));
		fileMenu.add(new JMenu("종료"));
		
		
		menuBar.add(fileMenu);
		
		pn.add(menuBar);
		
		
		return pn;
		
		
	}
	
	
	// 레이아웃 영역
	public void layoutPanel() {
		
		
		
	}
	
	
	
	
	// 첫번째 화면
	
	public void introScreenPanel() {
		
		
	}
	
	
	// 두번째 화면
	public void workSpacePanel() {
		
		
		
	}
	
	
	
	
	
	// 플레이바
	
	public void playBarPanel() {
		
		
		
	}
	
	
	
	
	
	
	

}
