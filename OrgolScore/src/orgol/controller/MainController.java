package orgol.controller;

public class MainController {

	private FrameController frameController;
	private PanelController panelController;
	private PlayBarController playBarController;
	private MidiController midiController; 
	
	
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
		frameController = new FrameController();
		panelController = new PanelController();
		playBarController = new PlayBarController();
		midiController = new MidiController();
		

	}

	
	
	// 초기 실행 메소드 
	
	public void run() {
		
		System.out.println("[run] ...");
		

		
		
		
		frameController.createSplashFrame();
		
		
		
		
		// 메인 영역 생성
		//frame.createMainFrame();
		
		// 
		
		
		
		
		
		
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
