package simple;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

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
		


		

	}

	public void createMainFrame() {

		frame.setTitle("오르골 악보 제작 프로그램");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1024, 600);
		frame.setUndecorated(true); // 타이틀 바 제거
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // 최대화

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
	    c.setLayout(new BorderLayout()); 
	    

	    
	    

	    // 1. 상단: 미디 찍는 영역
	    JPanel midiArea = new JPanel();
	    midiArea.setLayout(new BorderLayout());
        midiArea.setBackground(new Color(0xFFFFFF));
        
        
        
        JPanel midiLeftArea = new JPanel();
        midiLeftArea.setLayout(new GridLayout(scale.length,1));
        
        for(int i=0;i<scale.length;i++) {
        	
        	JLabel scaleLabel = new JLabel(scale[i]);
        	scaleLabel.setFont(setCustomFont("PretendardVariable", 12));      	
        	midiLeftArea.add(scaleLabel);
        	
        }
      
        
        midiArea.add(midiLeftArea, BorderLayout.WEST);
        
        
        JPanel midiRightArea = new JPanel();
        midiRightArea.setBackground(new Color(0x2F2F2F));
        midiRightArea.add(new JLabel("right1"));
        midiArea.add(midiRightArea, BorderLayout.EAST);
        
        
        
        
        
        
        //midiArea.setPreferredSize(new Dimension(1920, 500));
        
        
//        // 버튼 256개 추가 (32마디 x 8개)
//        for (int i = 1; i <= 256; i++) {
//        	    JButton button = new JButton("c " + i);
//        		button.setMargin(new Insets(10, 20, 10, 20)); 
//                midiArea.add(button);
//        	}
//	    
	    
	    

	    // JScrollPane에 midArea를 추가
	    JScrollPane midiScrollPane = new JScrollPane(midiArea);
	    midiScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    midiScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

	    // 2. 중단: 악보 영역
	    JPanel scoreArea = new JPanel();
	    scoreArea.setBackground(new Color(0x123456)); // 회색 배경
	    scoreArea.add(new JLabel("악보"));

	    // 3. 하단: 플레이바 영역
	    JPanel playBarArea = new JPanel();
	    playBarArea.setBackground(new Color(0x999999)); // 짙은 회색 배경
	    playBarArea.add(new JLabel("플레이바"));
	    playBarArea.setPreferredSize(new Dimension(1920, 100)); 

	    // 컨테이너에 패널 추가
	    c.add(midiScrollPane, BorderLayout.NORTH); // 스크롤 가능한 미디 영역
	    c.add(scoreArea, BorderLayout.CENTER);     // 악보 영역
	    c.add(playBarArea, BorderLayout.SOUTH);   // 플레이바 영역

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
	
	
	
	
	
	
	
	

}

