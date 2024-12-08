package simple;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class MainContoller {

    private JFrame frame;
    private Container c;
    private Font customFont;
    
    private int screenWidth;
    private int screenHeight;
    
    //private static final Border DEFAULT_BORDER = BorderFactory.createLineBorder(Color.BLACK, 1);
    
    private JPanel mainPanel;
    private JPanel workSpacePanel;
    
    
    
	private String[] scale = {
			
		    "C3", "D3", "G3", "A3", "B3", "C4", "D4", "E4", "F4", "F#4", 
		    "G4", "G#4", "A4", "A#4", "B4", "C5", "C#5", "D5", "D#5", "E5", 
		    "F5", "G5", "G#5", "A5", "A#5", "B5", "C6", "D6", "E6"
		    
		};
	
	private String[] reversedScale = new String[scale.length];

	public MainContoller() {

		frame = new JFrame();
		c = frame.getContentPane();
		createMainFrame();
		
		customFont = setCustomFont("PretendardVariable", 12);
	    
	    Toolkit toolkit = Toolkit.getDefaultToolkit();
	    Dimension screenSize = toolkit.getScreenSize();
	    screenWidth = screenSize.width;
	    screenHeight = screenSize.height;
		

	    
	    for (int i = 0; i < scale.length; i++) {
	        reversedScale[i] = scale[scale.length - 1 - i];
	    }
	    
	    
	    
	    
	}

	public void createMainFrame() {

		frame.setTitle("Orgol Score");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(screenWidth, screenHeight);
		//frame.setUndecorated(true); // 타이틀 바 제거
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // 최대화
		frame.setLocationRelativeTo(null); 
		 
        c.setBackground(new Color(0x0560EB));
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));

       
        JLabel label = new JLabel("오스 :: ORGOL SCORE", SwingConstants.CENTER);
        label.setFont(setCustomFont("CookieRunBold", 50));
        label.setForeground(new Color(0xffffff));
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        JButton startButton = new JButton("시작하기");
        startButton.setFont(setCustomFont("CookieRunBold", 30));
        startButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        startButton.setBorderPainted(false);
        startButton.setFocusPainted(false);
        startButton.setBackground(new Color(0xFFD700));
        startButton.setMargin(new Insets(5, 15, 5, 15)); 
        startButton.setForeground(new Color(0x333));
        
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            		createWorkSpaceFrame();
            	
            }
        });

       
        c.add(Box.createVerticalGlue());
        c.add(label);
        c.add(Box.createVerticalStrut(20));
        c.add(startButton);
        c.add(Box.createVerticalGlue());
        
        
        frame.setVisible(true);

	}
	
	
	public void createWorkSpaceFrame() {
	    
		
	    c.removeAll();
	    c.setBackground(new Color(0x123456));
	    
	    
	    // wrapper 스크롤
	    JPanel wrap = new JPanel();
	    wrap.setLayout(null);
	    	wrap.setBounds(0, 0, screenWidth, screenHeight);
	    	wrap.setBackground(Color.gray);
	    	
	    	
    	// 상단
	    JPanel midiArea = new JPanel();
	    midiArea.setLayout(null);
        midiArea.setBackground(new Color(0xFFFFcc));
        midiArea.setBounds(0,0,screenWidth,650);
        
        
        JPanel midiLeftArea = new JPanel();
        midiLeftArea.setLayout(null);
        midiLeftArea.setBounds(0,50,50,650);
        
        
        int x = 0;
        int y = 0;
        
        for (int i = scale.length - 1; i >= 0; i--) {

            JLabel scaleLabel = new JLabel(scale[i], SwingConstants.CENTER);
            scaleLabel.setFont(customFont);  
            scaleLabel.setOpaque(true); 

            
            scaleLabel.setBounds(x,y,50,20); 

            if (i % 2 == 0) {
                scaleLabel.setBackground(new Color(0xCCCCCC));
            }

            midiLeftArea.add(scaleLabel);
            
            y+=20;
            
        }
        
        midiArea.add(midiLeftArea);
        
        
        
        
        JPanel midiRightArea = new JPanel();
        midiRightArea.setBackground(new Color(0xabcabc));
        midiRightArea.setLayout(null);
        
        

        // 마디 
        for(int i=0;i<32;i++) {
        	
            JLabel tmp = new JLabel("bar"+(i+1));
            tmp.setBounds(400*i,0,400,25);
            tmp.setOpaque(true);
            tmp.setBackground(Color.gray);
            midiRightArea.add(tmp);
        	

            
        	
        }
        

        
        // 마디 내 박자
        
        for(int j=0;j<=3;j++) {
        	
            JLabel tmp2 = new JLabel("t"+(j+1));
            tmp2.setBounds(50*j,25,50,25);
            tmp2.setOpaque(true);
            tmp2.setBackground(Color.yellow);
            midiRightArea.add(tmp2);
        	
        }
        
        
        
        midiRightArea.setPreferredSize(new Dimension(256 * 50, 29 * 20)); 

        
        
		
		  // 버튼 256개 추가 (32마디 x 8개)
		  
        	int midiPosX = 0;
        	int midiPosY = 50;
        	        	
			for (int i = 1; i <= 29; i++) {

				midiPosX = 0;
				
				for (int j = 1; j <= 256; j++) {
					
					
					JLabel button = new JLabel(reversedScale[i-1]);
					button.setFont(customFont);
					button.setOpaque(true);
					button.setBorder(BorderFactory.createLineBorder(new Color(0xB0B4B8), 1));
					
					button.setBackground(new Color(0xECF0F5));
				 	button.setForeground(new Color(0xECF0F5));
					
					if(i%2==0) {
						button.setBackground(new Color(0xB6C3CF));
						button.setForeground(new Color(0xB6C3CF));
					}
					button.setBounds(midiPosX, midiPosY, 50, 20);
					 // 마우스 클릭 이벤트 추가
					button.addMouseListener(new MouseAdapter() {
			            @Override
			            public void mouseClicked(MouseEvent e) {
			               
			            	
			            	
			            	
			            	button.setBackground(new Color(0xC90033));
			            	button.setForeground(Color.white);
			            	playVstSound(button.getText());
			            	
			            }
			        });
					midiRightArea.add(button);

				
					midiPosX += 50;
					
				}
				
				midiPosY += 20;

			}
		  
	        
        JScrollPane midiScrollPane = setJScrollPane(midiRightArea, 1);
        midiScrollPane.setBounds(50, 0, screenWidth-66, 650);
		  
   
        midiArea.add(midiScrollPane);
        
        
        
        
        // 중단 악보영역 
        JPanel scoreArea = new JPanel();
	    scoreArea.setBackground(new Color(0x123456)); // 회색 배경
	    scoreArea.add(new JLabel("악보"));
        
        
	    
	    // 하단 플레이바 
	    JPanel playBarArea = new JPanel();
	    playBarArea.setBackground(new Color(0x999999)); // 짙은 회색 배경
	    playBarArea.add(new JLabel("플레이바"));
	    playBarArea.setPreferredSize(new Dimension(1920, 50)); 

        
        
        
        wrap.add(midiArea);
       // wrap.add(scoreArea);
       // wrap.add(playBarArea);
        
        
        
        c.add(setJScrollPane(wrap, 1));
	
	    
        
        //long startTime = System.currentTimeMillis();
        //long endTime = System.currentTimeMillis();
        //System.out.println( (endTime - startTime) + " ms");
        
     
        


	    // 화면 갱신
	    frame.revalidate();
	    frame.repaint();
	    
	}

	
	// 메뉴바 생성
	public void createMenuBar() {}
	
	// 타이틀 바 생성
	public void createTitleBar() {}
	
	
	// 폰트 커스텀 
	private Font setCustomFont(String fontName, int fontSize) {

		try {

			InputStream fontStream = getClass().getClassLoader().getResourceAsStream("resource/font/" + fontName + ".ttf");
			if (fontStream == null)	throw new IOException("폰트 파일 없음");

			customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(Font.BOLD, (float) fontSize);

		} catch (FontFormatException | IOException e) {

			e.printStackTrace();
			System.out.println("폰트 로드 오류");
			return new Font("Arial", Font.BOLD, fontSize);

		}

		return customFont;

	}
	
	
	// 스크롤 
	public JScrollPane setJScrollPane(JPanel panel, int status) {

		JScrollPane wrap = new JScrollPane(panel);

		if (status == 1) {

			wrap.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			wrap.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		} else {

			wrap.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		}

		wrap.getVerticalScrollBar().setUnitIncrement(10);

//    	wrap.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
//            @Override
//            protected JButton createDecreaseButton(int orientation) {
//                JButton btn = new JButton();
//                btn.setPreferredSize(new Dimension(0, 0));
//                btn.setMinimumSize(new Dimension(0, 0));
//                btn.setMaximumSize(new Dimension(0, 0));
//
//                return btn;
//            }
//
//            @Override
//            protected JButton createIncreaseButton(int orientation) {
//                JButton btn = new JButton();
//                btn.setPreferredSize(new Dimension(0, 0));
//                btn.setMinimumSize(new Dimension(0, 0));
//                btn.setMaximumSize(new Dimension(0, 0));
//                return btn;
//            }
//
//            @Override
//            protected void configureScrollBarColors() {
//                this.thumbColor = new Color(0x4d4d4d);
//                this.trackColor = new Color(0x171717);
//            }
//        });

		return wrap;

	}
	
	
	
	public static void playVstSound(String scale) {

		String filePath = "/resource/vst/orgol/" + scale + ".wav";

		try {

			// 클래스 로더를 통해 리소스를 가져옴
			InputStream inputStream = MainContoller.class.getResourceAsStream(filePath);

			if (inputStream == null) {
				System.out.println("파일을 찾을 수 없습니다: " + filePath);
			} else {
				System.out.println("리소스 파일 로드 성공!");
			}

			AudioInputStream audioStream = AudioSystem.getAudioInputStream(inputStream);
			Clip audioClip = AudioSystem.getClip();
			audioClip.open(audioStream);
			audioClip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	 
	 
	 
	
	
	
	

}

