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
		
		MainSlashScreen();
		
		init();
		load();
		
	}

	
	// initialize 
	public void init() {

		
		System.out.println("[init] ...");
		
		frame = new FrameController();
		System.out.println("[init] frame class...");
		
		panel = new PanelController();
		System.out.println("[init] panel class...");
		
		play = new PlayBarController();
		System.out.println("[init] play class...");
		
		midi = new MidiController();
		System.out.println("[init] midi class...");
		 

	}
	
	
	
	public void MainSlashScreen() {
		
		System.out.println("[MainSlashScreen] 스플래시 시작");
		System.out.println();
		
	}
	
	
	
	
	
	
	public void load() {
		
		System.out.println("[load] 초기화 후 로드");
		
		attachPanelToFrame();
		
	}
	
	
	public void attachPanelToFrame() {
		
		
		System.out.println("[attachPanelToFrame] 패널을 프레임에 부착...");
		
		
		
	}
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	// 뷰
	public void view() {
		
		
		
	}
	
	
	
	
	

}
