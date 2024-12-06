package simple;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

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
    
    
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();
    
    int screenWidth = screenSize.width;
    int screenHeight = screenSize.height;
    
    
    
    private JPanel mainPanel;
    private JPanel workSpacePanel;
    
    
    
	String[] scale = {
			
		    "C3", "D3", "G3", "A3", "B3", "C4", "D4", "E4", "F4", "F#4", 
		    "G4", "G#4", "A4", "A#4", "B4", "C5", "C#5", "D5", "D#5", "E5", 
		    "F5", "G5", "G#5", "A5", "A#5", "B5", "C6", "D6", "E6"
		    
		};

	public MainContoller() {

		frame = new JFrame();
		c = frame.getContentPane();
		createMainFrame();
		
		customFont = setCustomFont("PretendardVariable", 12);

		

	}

	public void createMainFrame() {

		frame.setTitle("Orgol Score");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(screenWidth, screenHeight);
		frame.setUndecorated(true); // 타이틀 바 제거
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
        midiArea.setBounds(0,0,screenWidth,600);
        
        
        JPanel midiLeftArea = new JPanel();
        midiLeftArea.setLayout(null);
        midiLeftArea.setBounds(0,0,50,600);
        
        
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
        //midiRightArea.setBounds(50,0,screenWidth-85,500);
        
        
		
		  // 버튼 256개 추가 (32마디 x 8개)
		  
        	int midiPosX = 0;
        	int midiPosY = 0;
        	        	
			for (int i = 1; i <= 29; i++) {

				midiPosX = 0;
				
				for (int j = 1; j <= 256; j++) {
					
					
					JLabel button = new JLabel("C" + i + " " + j + " ", SwingConstants.CENTER);
					button.setFont(customFont);
					button.setOpaque(true);
					button.setBackground(Color.white);
					button.setBounds(midiPosX, midiPosY, 100, 20);
					midiRightArea.add(button);

				
					midiPosX += 100;
					
				}
				
				midiPosY += 20;

			}
		  
	        
        JScrollPane midiScrollPane = setJScrollPane(midiRightArea, 1);
        midiScrollPane.setBounds(50, 0, screenWidth-85, 600);
		  
		 
		 
        
   
        midiArea.add(midiScrollPane);
        
        
        
        
        
        
        // 중단
        JPanel scoreArea = new JPanel();
	    scoreArea.setBackground(new Color(0x123456)); // 회색 배경
	    scoreArea.add(new JLabel("악보"));
        
        
        
	    
	    
	    JPanel playBarArea = new JPanel();
	    playBarArea.setBackground(new Color(0x999999)); // 짙은 회색 배경
	    playBarArea.add(new JLabel("플레이바"));
	    playBarArea.setPreferredSize(new Dimension(1920, 50)); 

        
        
        
        
        
        wrap.add(midiArea);
       // wrap.add(scoreArea);
       // wrap.add(playBarArea);
        
        

        
        
        
        
        
        
        
        
        
        c.add(setJScrollPane(wrap, 1));
	    
	    /*  
	    

        

        
        
        //long startTime = System.currentTimeMillis();
       


        
        
        //long endTime = System.currentTimeMillis();
        //System.out.println( (endTime - startTime) + " ms");
        
     
        
        
        
        
        
       
        
        
        
        //midiArea.setPreferredSize(new Dimension(1920, 500));
        
        

	    
	    


	    // 2. 중단: 악보 영역
	    

	    // 3. 하단: 플레이바 영역
	   
	    // 컨테이너에 패널 추가
	    c.add(midiArea, BorderLayout.NORTH); // 스크롤 가능한 미디 영역
	    c.add(scoreArea, BorderLayout.CENTER);     // 악보 영역
	    c.add(playBarArea, BorderLayout.SOUTH);   // 플레이바 영역


	     */



	    // 화면 갱신
	    frame.revalidate();
	    frame.repaint();
	}

	
	
	
	
	// 메뉴바 생성
	public void createMenuBar() {
		
		
		
	}
	
	
	// 타이틀 바 생성
	public void createTitleBar() {
		
		
		
	}
	
	
	
	
	
	
	
	
	

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
	
	
	
	
	public JScrollPane setJScrollPane(JPanel panel, int status) {
		

		
		JScrollPane wrap = new JScrollPane(panel);
		
		if(status == 1) {
			
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
	
	
	
	
	
	

}

