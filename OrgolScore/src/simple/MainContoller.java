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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class MainContoller {

	private JFrame frame;
	private Container c;
	private Font customFont;

	private int screenWidth;
	private int screenHeight;

	// private static final Border DEFAULT_BORDER =
	// BorderFactory.createLineBorder(Color.BLACK, 1);

	private JPanel mainPanel;
	private JPanel workSpacePanel;

	private List<JLabel> midiLabels = new ArrayList<>();

	private Map<String, Clip> soundCache = new HashMap<>();

	
	// 스케일
	private String[] scale = {
			"C3", "D3", "G3", "A3", "B3", "C4", "D4", "E4", "F4", "F#4", "G4", "G#4", "A4", "A#4", "B4", "C5", "C#5",
			"D5", "D#5", "E5", "F5", "G5", "G#5", "A5", "A#5", "B5", "C6", "D6", "E6"
	};
	
	// 스케일 역순 
	private String[] reversedScale = new String[scale.length];
	
	
	// 메인 컨트롤러 
	
	public MainContoller() {

		frame = new JFrame();
		c = frame.getContentPane();
		createMainFrame();

		//preloadSounds();

		customFont = setCustomFont("PretendardVariable", 12);

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		screenWidth = screenSize.width;
		screenHeight = screenSize.height;

		for (int i = 0; i < scale.length; i++) {
			
			reversedScale[i] = scale[scale.length - 1 - i];
			
		}

	}
	

	// 메인 프레임 생성 
	
	public void createMainFrame() {

		
		screenWidth = 700;
		screenHeight = 800;
		frame.setLocation(0,0);
		
		frame.setTitle("Orgol Score");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(screenWidth, screenHeight);
		// frame.setUndecorated(true); // 타이틀 바 제거
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // 최대화
		//frame.setLocationRelativeTo(null);

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

	
	// 워크스페이스 프레임 생성 
	
	public void createWorkSpaceFrame() {

		c.removeAll();
		c.setBackground(new Color(0xffffff));

		// wrapper 스크롤
		JPanel wrap = new JPanel();
		wrap.setLayout(null);
		wrap.setBounds(0, 0, screenWidth, screenHeight);
		wrap.setBackground(Color.gray);

		// 상단
		JPanel midiArea = new JPanel();
		midiArea.setLayout(null);
		midiArea.setBackground(new Color(0xFFFFcc));
		midiArea.setBounds(0, 0, screenWidth, 650);

		JPanel midiLeftArea = new JPanel();
		midiLeftArea.setLayout(null);
		midiLeftArea.setBounds(0, 50, 50, 650);

		
		
		
		// 스케일 출력 
		
		int x = 0;
		int y = 0;

		for (int i = scale.length - 1; i >= 0; i--) {

			JLabel scaleLabel = new JLabel(scale[i], SwingConstants.CENTER);
			scaleLabel.setFont(customFont);
			scaleLabel.setOpaque(true);
			scaleLabel.setBounds(x, y, 50, 20);

			if (i % 2 == 0) {
				scaleLabel.setBackground(new Color(0xCCCCCC));
			}

			midiLeftArea.add(scaleLabel);

			y += 20;

		}

		midiArea.add(midiLeftArea);

		
		
		JPanel midiRightArea = new JPanel();
		midiRightArea.setBackground(new Color(0xabcabc));
		midiRightArea.setLayout(null);
		
		// 마디 및 박자 출력 
		
		int defX = 0;
		
		// 마디
		for (int i = 0; i < 32; i++) {

			JLabel measure = new JLabel("" + (i + 1), SwingConstants.CENTER);
			measure.setBounds(400 * i, 0, 400, 25);
			measure.setOpaque(true);
			measure.setBackground(new Color(0xffffff));
			
			measure.setBorder(BorderFactory.createLineBorder(new Color(0xcccccc), 1));
			midiRightArea.add(measure);
			
			
			// 마디 내 박자

			for (int j = 0; j <= 3; j++) {
				
				JLabel bit = new JLabel("" + (j+1) , SwingConstants.CENTER);
				bit.setBounds(defX, 25, 100, 25);
				bit.setOpaque(true);
				bit.setBackground(new Color(0xfafafa));
				bit.setBorder(BorderFactory.createLineBorder(new Color(0xcccccc), 1));
				midiRightArea.add(bit);

				defX += 100;
				
			}
			
		}
		
		
		//createMidiLabels(midiRightArea);
		
		
		
		midiRightArea.setPreferredSize(new Dimension(256 * 50, 29 * 20));

		// 버튼 256개 추가 (32마디 x 8개)

		int midiPosX = 0;
		int midiPosY = 50;
		
		int oddBgColor = 0xECF0F5;
		int evenBgColor = 0xB6C3CF;
		int borderColor = 0xB0B4B8;
		
		
		for (int i = 1; i <= 29; i++) {

			midiPosX = 0;

			for (int j = 1; j <= 256; j++) {

				JLabel button = new JLabel(reversedScale[i - 1]);
				button.setFont(customFont);
				button.setOpaque(true);
				button.setBorder(BorderFactory.createLineBorder(new Color(borderColor), 1));

				button.setBackground(new Color(oddBgColor));
				button.setForeground(new Color(oddBgColor));
				button.putClientProperty("pos", "odd");
			
				button.putClientProperty("scale_pos", j);
				button.putClientProperty("scale_name", reversedScale[i - 1]);
				button.putClientProperty("checked", false);


				if (i % 2 == 0) {
					
					button.putClientProperty("pos", "even");
					button.setBackground(new Color(evenBgColor));
					button.setForeground(new Color(evenBgColor));
					
				}
				
				button.setBounds(midiPosX, midiPosY, 50, 20);

				button.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {


						String pos = (String) button.getClientProperty("pos");

						if (e.getButton() == MouseEvent.BUTTON1) {

							button.setBackground(new Color(0xC90033));
							button.setForeground(Color.white);
							button.putClientProperty("checked", true);
							new Thread(() -> playVstSound(button.getText())).start();

						} else if (e.getButton() == MouseEvent.BUTTON3) {

							if (pos.equals("odd")) {

								button.setBackground(new Color(0xECF0F5));
								button.setForeground(new Color(0xECF0F5));

							} else {

								button.setBackground(new Color(0xB6C3CF));
								button.setForeground(new Color(0xB6C3CF));

							}
							
							button.putClientProperty("checked", false);
							 
						}
				

					}
				});
				
				midiRightArea.add(button);
				midiLabels.add(button);
				midiPosX += 50;

			}

			midiPosY += 20;

		}

		JScrollPane midiScrollPane = setJScrollPane(midiRightArea, 1);
		midiScrollPane.setBounds(50, 0, screenWidth - 66, 650);
		midiArea.add(midiScrollPane);

		// 미디 영역 마우스 휠 이벤트 
		midiScrollPane.addMouseWheelListener(new MouseWheelListener() {

			@Override
            public void mouseWheelMoved(MouseWheelEvent e) {

                if (e.getWheelRotation() != 0) {
                	
                    int scrollAmount = e.getWheelRotation() * 20;  // 휠 한칸 이동 픽셀 
                    JScrollBar horizontalBar = midiScrollPane.getHorizontalScrollBar();
                    horizontalBar.setValue(horizontalBar.getValue() + scrollAmount);  // 가로 스크롤 이동
                    
                }
            }
        });
		
		
		
		
		
		
		
		
		
		// 중단 악보영역
		JPanel scoreArea = new JPanel();
		scoreArea.setBackground(new Color(0x123456)); 
		scoreArea.add(new JLabel("악보"));

		
		
		
		// 하단 플레이바
		JPanel playBarArea = new JPanel();
		playBarArea.setBackground(new Color(0xfafafa));
		playBarArea.setPreferredSize(new Dimension(1920, 50));
		playBarArea.setLayout(new GridLayout());
		playBarArea.setBounds(0, 650, screenWidth, 50);
		
		
		// 하단 플레이바 버튼
		
		String[] btns = {"prev","play","pause","stop","next"};
		JButton[] buttonArray = new JButton[btns.length];
		
		for (int i = 0; i < btns.length; i++) {

			buttonArray[i] = new JButton(btns[i]);
			buttonArray[i].setBackground(new Color(0x333333));
			buttonArray[i].setForeground(new Color(0xffffff));
			//buttonArray[i].setBorderPainted(false);
			buttonArray[i].setFocusPainted(false);
			buttonArray[i].setBorder(new LineBorder(Color.white));
			playBarArea.add(buttonArray[i]);

		}
		
		buttonArray[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				playSelectedNotes();
				
			}
		});

	
		

		wrap.add(midiArea);
		//wrap.add(scoreArea);
		wrap.add(playBarArea);

		c.add(setJScrollPane(wrap, 1));

		// long startTime = System.currentTimeMillis();
		// long endTime = System.currentTimeMillis();
		// System.out.println( (endTime - startTime) + " ms");

		// 화면 갱신
		frame.revalidate();
		//frame.repaint();

	}

//	public JLabel createMidiLabels(JPanel p) {
//
//		JLabel button = null;
//		int midiPosX = 0;
//		int midiPosY = 50;
//
//		for (int i = 1; i <= 29; i++) {
//
//			midiPosX = 0;
//
//			for (int j = 1; j <= 256; j++) {
//				button = new JLabel(reversedScale[i - 1]);
//				button.setFont(customFont);
//				button.setOpaque(true);
//				button.setBorder(BorderFactory.createLineBorder(new Color(0xB0B4B8), 1));
//				button.setBounds(midiPosX, midiPosY, 50, 20);
//
//				// 라벨을 리스트에 저장
//				midiLabels.add(button);
//				p.add(button);
//
//				midiPosX += 50;
//			}
//
//			midiPosY += 20;
//		}
//
//		return button;
//
//	}
	
	public void playSelectedNotes() {

		List<ScaleInfo> selectedNotes = new ArrayList<>();

		for (JLabel button : midiLabels) {

			Boolean isChecked = (Boolean) button.getClientProperty("checked");

			if (isChecked != null && isChecked) {
			int pos = (int) button.getClientProperty("scale_pos");
			String scale = (String) button.getClientProperty("scale_name");

			// ScaleInfo 객체 추가
			selectedNotes.add(new ScaleInfo(pos, scale, isChecked));

			}
		}

		selectedNotes.sort(Comparator.comparingInt(ScaleInfo::getScalePos));

		// 리스트 출력
		for (ScaleInfo scale : selectedNotes) {

			System.out.println(scale);

		}
	   

	  
	    
     // 선택된 음들을 차례대로 재생
        new Thread(() -> {
            final int bpm = 60; 
            int beatDuration = 60000 / bpm / 2; // 

            for (ScaleInfo n : selectedNotes) {
                try {
                    String note = n.getScaleName();
                    int pos = n.getScalePos();
                    boolean isChecked = n.isChecked();

                	playVstSound(note);
                    Thread.sleep(beatDuration);
                    
                   
                    
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
		
		
		
	}
	
	
	
	
	
	
	// 체크된 음원 json화
//    public void playSelectedNotes() {
//    	
//        JSONArray selectedNotesArray = new JSONArray();
//
//        for (JLabel button : midiLabels) {
//        	
//        	int scalePos = (int) button.getClientProperty("scale_pos");
//        	String scaleName = (String) button.getClientProperty("scale_name");
//            boolean isChecked = (boolean) button.getClientProperty("checked");
//
//            if (isChecked) {
//            	
//                JSONObject noteObject = new JSONObject();
//                noteObject.put("scale_name", scaleName);
//                noteObject.put("scale_pos", scalePos);
//                selectedNotesArray.put(noteObject);
//                
//            }
//        }
//        
//        
//
//        
//        JSONObject json = new JSONObject();
//        json.put("selected_notes", selectedNotesArray);
//
//        // JSON 데이터를 출력 (디버깅용)
//        System.out.println(json.toString(4));
//
//  
//        
//        // 이제 이 JSON 데이터를 기반으로 음원 재생
//        // 새로운 쓰레드에서 음원 재생을 시작
//        //new Thread(() -> playNotesFromJson(json)).start();
//        
//        
//    }

    // 음원 재생
//    private void playNotesFromJson(JSONObject json) {
//        // JSON에서 선택된 음 정보를 가져옴
//        JSONArray selectedNotesArray = json.getJSONArray("selected_notes");
//
//        List<ScaleInfo> selectedNotes = new ArrayList<>();
//
//        // 선택된 음들 (scale_name, scale_pos)을 리스트에 저장
//        for (int i = 0; i < selectedNotesArray.length(); i++) {
//            JSONObject noteObject = selectedNotesArray.getJSONObject(i);
//            String scaleName = noteObject.getString("scale_name");
//            int scalePos = noteObject.getInt("scale_pos");
//
//            selectedNotes.add(new ScaleInfo(scalePos, scaleName, true)); // checked 값은 true
//        }
//
//        // 선택된 음들을 차례대로 재생
//        final int bpm = 70;  // BPM (분당 박자 수)
//        int beatDuration = 60000 / bpm; // 한 박자의 길이 (밀리초 단위)
//
//        for (ScaleInfo scaleInfo : selectedNotes) {
//            try {
//                // 음원 재생
//                playVstSound(scaleInfo.getScaleName());
//
//                // scalePos에 따른 박자 길이 조정 (예: 짝수는 더 짧은 재생)
//                int noteDuration = beatDuration;
//                if (scaleInfo.getScalePos() % 2 != 0) { // 예를 들어 홀수 박자는 절반 길이
//                    noteDuration /= 2;
//                }
//
//                // 해당 음의 재생 후 일시 정지
//                Thread.sleep(noteDuration);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }



	// 메뉴바 생성
	public void createMenuBar() {
	}

	// 타이틀 바 생성
	public void createTitleBar() {
	}

	// 폰트 커스텀
	private Font setCustomFont(String fontName, int fontSize) {

		try {

			InputStream fontStream = getClass().getClassLoader()
					.getResourceAsStream("resource/font/" + fontName + ".ttf");
			if (fontStream == null)
				throw new IOException("폰트 파일 없음");

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

				System.out.println("파일 없음 : " + filePath);

			} else {

				System.out.println(scale + "파일 로드");
			}

			AudioInputStream audioStream = AudioSystem.getAudioInputStream(inputStream);
			Clip audioClip = AudioSystem.getClip();
			audioClip.open(audioStream);
			audioClip.start();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	/*
	 * // 음원 로드 private void preloadSounds() { for (String note : scale) { try {
	 * String filePath = "/resource/vst/orgol/" + note + ".wav"; InputStream
	 * inputStream = MainContoller.class.getResourceAsStream(filePath);
	 * 
	 * AudioInputStream audioStream = AudioSystem.getAudioInputStream(inputStream);
	 * Clip clip = AudioSystem.getClip(); clip.open(audioStream);
	 * 
	 * soundCache.put(note, clip); } catch (Exception e) { e.printStackTrace(); } }
	 * }
	 * 
	 * // 재생 private void playVstSound(String scale) { Clip clip =
	 * soundCache.get(scale); if (clip != null) { clip.setFramePosition(0); // 시작
	 * 위치로 재설정 clip.start(); } }
	 * 
	 */

}
