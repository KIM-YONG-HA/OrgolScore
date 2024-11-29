package orgol.controller;

public class MainController {

	private FrameController frame;
	private PanelController panel;
	private PlayBarController play;
	private MidiController midi; 
	
	
	// Contructor
	public MainController() {
		
		System.out.println("[MainController] ... ");
		System.out.println();
		
		init();
		run();
		
		
	}

	
	// initialize 
	public void init() {
		
		System.out.println("[init] ...");
		frame = new FrameController();
		panel = new PanelController();
		play = new PlayBarController();
		midi = new MidiController();
		

	}

	
	
	// 초기 실행 메소드 
	
	public void run() {
		
		System.out.println("[run] ...");
		
		
		// 스플래시 
		mainSlashScreen();
		
		
		
		
		// 메인 영역 생성
		frame.createMainFrame();
		
		// 
		
		
		
		
		
		
	}
	
	
	public void mainSlashScreen() {
		
		System.out.println("[mainSlashScreen] 스플래시 시작");
		System.out.println();
		
	}
	
	
	
	public void fontCustom() {
		
		
		
		
	}
	

	
	
	public void attachPanelToFrame() {
		
		
		System.out.println("[attachPanelToFrame] 패널을 프레임에 부착...");
		
		
		
	}
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	// 뷰
	public void view() {
		
		
		
	}
	
	
	
	
	

}
