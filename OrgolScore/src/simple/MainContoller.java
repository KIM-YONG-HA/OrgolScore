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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import org.json.JSONArray;
import org.json.JSONObject;
public class MainContoller {

	private JFrame frame;
	private Container c;
	private Font customFont;

	private int screenWidth;
	private int screenHeight;

	private JPanel mainPanel;
	private JPanel workSpacePanel;

	private String[] scale = {
			"C3", "D3", "G3", "A3", "B3", "C4", "D4", "E4", "F4", "F#4", "G4", "G#4", "A4", "A#4", "B4", "C5", "C#5",
			"D5", "D#5", "E5", "F5", "G5", "G#5", "A5", "A#5", "B5", "C6", "D6", "E6"
	};
	private String[] reversedScale = new String[scale.length];
	private List<JLabel> midiLabels = new ArrayList<>();
	
	private Timer timer; 
	private boolean isPaused = false;
	private int currentColumn = 0; 
	private final int totalColumns = 256; // 총 열의 개수
	private final int totalRows = 29; // 총 행의 개수
	private final int columnDuration = 500; // 열 재생 시간 (밀리초)

	
	// 메인 컨트롤러 
	public MainContoller() {

		frame = new JFrame();
		c = frame.getContentPane();
		createMainFrame();

		//preloadSounds();

		 // reversedScale 초기화
	    reversedScale = new String[scale.length];
	    for (int i = 0; i < scale.length; i++) {
	        reversedScale[i] = scale[scale.length - 1 - i];
	    }
	    
		customFont = setCustomFont("PretendardVariable", 14);

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
		
		frame.setTitle("Orgol Score");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(screenWidth, screenHeight);
		frame.setLocation(0,0);
		
		//frame.setUndecorated(true); // 타이틀 바 제거
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // 최대화
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

		// 워크스페이스 전환 이벤트 
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
		
		// 메뉴바 설정
		JMenuBar menuBar = createMenuBar();
		frame.setJMenuBar(menuBar);
		
		// wrapper 스크롤
		JPanel wrap = new JPanel();
		wrap.setLayout(null);
		wrap.setBounds(0, 0, screenWidth, screenHeight);
		wrap.setBackground(new Color(0xffffff));

		
		// 플레이바 생성 
		createPlayBarArea(wrap);
		
		// 미디 영역 생성 
		createMidiArea(wrap);
		//setPachelbelCanon();
		c.add(setJScrollPane(wrap, 1));
		
		frame.revalidate();
		frame.repaint();
		
	}
	
	
	
	
	// 플레이바 생성 
	public void createPlayBarArea(JPanel wrap) {
		
		JPanel playBarArea = new JPanel();
		//playBarArea.setPreferredSize(new Dimension(screenWidth, 50));
		playBarArea.setBackground(Color.red);
		playBarArea.setLayout(new GridLayout());
		playBarArea.setBounds(0, 0, screenWidth, 50);
		
		String[] btns = {"< prev","next > ","▶ play","|| pause","■ stop"};
		JButton[] buttonArray = new JButton[btns.length];
		
		for (int i = 0; i < btns.length; i++) {

			buttonArray[i] = new JButton(btns[i]);
			buttonArray[i].setBackground(new Color(0x333333));
			buttonArray[i].setForeground(new Color(0xffffff));
			buttonArray[i].setFocusPainted(false);
			buttonArray[i].setBorder(new LineBorder(Color.white));
			buttonArray[i].setFont(setCustomFont("PretendardVariable", 15));
			playBarArea.add(buttonArray[i]);

		}

		buttonArray[0].addActionListener(new ActionListener() { // Prev
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        System.out.println("prev");
		        if (timer != null) {
		            timer.stop(); // 타이머 정지
		        }
		        if (currentColumn > 0) {
		            currentColumn--; // 이전 열로 이동
		            highlightColumn(); // 현재 열 강조
		        }
		    }
		});

		buttonArray[1].addActionListener(new ActionListener() { // Next
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        System.out.println("next");
		        if (timer != null) {
		            timer.stop();
		        }
		        if (currentColumn < totalColumns - 1) {
		            currentColumn++;
		            highlightColumn();
		        }
		    }
		});

		buttonArray[2].addActionListener(new ActionListener() { // Play
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        System.out.println("play");
		        if (timer == null || !timer.isRunning()) {
		            isPaused = false;
		            startPlayback();
		        }
		    }
		});


		buttonArray[3].addActionListener(new ActionListener() { // Pause
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        System.out.println("pause");
		        if (timer != null && timer.isRunning()) {
		            timer.stop();
		            isPaused = true;
		        }
		    }
		});

		buttonArray[4].addActionListener(new ActionListener() { // Stop
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        System.out.println("stop");
		        if (timer != null) {
		            timer.stop();
		        }
		        currentColumn = 0;
		        resetAllLabels();
		    }
		});
		
		wrap.add(playBarArea);
		
	}
	

	// 미디 영역 생성 
	public void createMidiArea(JPanel wrap) {
		
		JPanel midiArea = new JPanel();
		midiArea.setLayout(null);
		midiArea.setBackground(new Color(0xEEEEEE));
		midiArea.setBounds(0, 0, screenWidth, 700);

		// 왼쪽 스케일 영역
		JPanel midiLeftArea = new JPanel();
		midiLeftArea.setLayout(null);
		midiLeftArea.setBounds(0, 100, 50, 580 + 20);

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

		// 오른쪽 마디 및 박자 영역
		JPanel midiRightArea = new JPanel();
		midiRightArea.setLayout(null);
		midiRightArea.setBounds(0, 0, screenWidth, 580);
		midiRightArea.setPreferredSize(new Dimension(256 * 50, 29 * 20));

		// 마디 및 박자 출력
		int defX = 0;
		for (int i = 0; i < 32; i++) {

			JLabel measure = new JLabel("" + (i + 1), SwingConstants.CENTER);
			measure.setBounds(400 * i, 0, 400, 25);
			measure.setOpaque(true);
			measure.setBackground(new Color(0xfafafa));
			measure.setBorder(BorderFactory.createLineBorder(new Color(0xcccccc), 1));
			measure.setFont(customFont);
			midiRightArea.add(measure);

			for (int j = 0; j <= 3; j++) {
				JLabel bit = new JLabel("" + (j + 1), SwingConstants.CENTER);
				bit.setBounds(defX, 25, 100, 25);
				bit.setOpaque(true);
				bit.setBackground(new Color(0xfafafa));
				bit.setBorder(BorderFactory.createLineBorder(new Color(0xcccccc), 1));
				bit.setFont(customFont);
				midiRightArea.add(bit);
				defX += 100;
			}
		}



		// 미디 라벨 생성
		int midiPosX = 0;
		int midiPosY = 50;

		int selectedColor = 0xC90033;
		int oddColor = 0xECF0F5;
		int evenColor = 0xB6C3CF;
		int borderColor = 0xB0B4B8;

		for (int i = 1; i <= 29; i++) {

			midiPosX = 0;

			for (int j = 1; j <= 256; j++) {

				JLabel button = new JLabel(reversedScale[i - 1]);
				button.setFont(customFont);
				button.setOpaque(true);
				button.setBorder(BorderFactory.createLineBorder(new Color(borderColor), 1));

				button.setBackground(new Color(oddColor));
				button.setForeground(new Color(oddColor));
				button.putClientProperty("pos", "odd");

				button.putClientProperty("scale_pos", j);
				button.putClientProperty("scale_name", reversedScale[i - 1]);
				button.putClientProperty("checked", false);

				if (i % 2 == 0) {

					button.putClientProperty("pos", "even");
					button.setBackground(new Color(evenColor));
					button.setForeground(new Color(evenColor));

				}

				button.setBounds(midiPosX, midiPosY, 50, 20);

				button.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						String pos = (String) button.getClientProperty("pos");

						if (e.getButton() == MouseEvent.BUTTON1) {

							button.setBackground(new Color(selectedColor));
							button.setForeground(Color.white);
							button.putClientProperty("checked", true);
							new Thread(() -> playVstSound(button.getText())).start();

						} else if (e.getButton() == MouseEvent.BUTTON3) {

							if (pos.equals("odd")) {

								button.setBackground(new Color(oddColor));
								button.setForeground(new Color(oddColor));

							} else {

								button.setBackground(new Color(evenColor));
								button.setForeground(new Color(evenColor));

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


		
		// 미디 영역 스크롤
		JScrollPane midiScrollPane = new JScrollPane(midiRightArea);
		midiScrollPane.setBounds(50, 50, screenWidth - 66, 650);
		midiScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		midiScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

		// 미디 영역 마우스 휠 이벤트
		midiScrollPane.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {

				if (e.getWheelRotation() != 0) {

					int scrollAmount = e.getWheelRotation() * 47; // 휠 한칸 이동 픽셀
					JScrollBar horizontalBar = midiScrollPane.getHorizontalScrollBar();
					horizontalBar.setValue(horizontalBar.getValue() + scrollAmount); // 가로 스크롤 이동

				}
			}
		});

		midiArea.add(midiScrollPane);
		
		wrap.add(midiArea);

	}
	
	
	// 현재 재생 중 위치 표시
	public void currentMeasureDisplay() {
	    final int totalColumns = 256; // 총 열의 개수
	    final int totalRows = 29; // 총 행의 개수
	    final int columnDuration = 500; // 각 열 재생 시간 (밀리초)

	    Timer timer = new Timer(columnDuration, null);
	    final int[] currentColumn = {0}; 

	    timer.addActionListener(e -> {
	        // 모든 열이 끝나면 타이머 중지
	        if (currentColumn[0] >= totalColumns) {
	            timer.stop();
	            return;
	        }

	        // 이전 열 복구
	        if (currentColumn[0] > 0) {
	            for (int row = 0; row < totalRows; row++) {
	                int prevLabelIndex = row * totalColumns + (currentColumn[0] - 1);
	                JLabel prevLabel = midiLabels.get(prevLabelIndex);
	                String pos = (String) prevLabel.getClientProperty("pos");

	                if ("odd".equals(pos)) {
	                    prevLabel.setBackground(new Color(0xECF0F5)); // odd color
	                    prevLabel.setForeground(new Color(0xECF0F5));
	                } else {
	                    prevLabel.setBackground(new Color(0xB6C3CF)); // even color
	                    prevLabel.setForeground(new Color(0xB6C3CF));
	                }

	                // 체크 상태 복구
	                Boolean isChecked = (Boolean) prevLabel.getClientProperty("checked");
	                if (Boolean.TRUE.equals(isChecked)) {
	                    prevLabel.setBackground(new Color(0xC90033)); // checked 색상
	                    prevLabel.setForeground(Color.WHITE);
	                }
	            }
	        }

	        // 현재 열 색상 변경 및 음 실행
	        for (int row = 0; row < totalRows; row++) {
	            int labelIndex = row * totalColumns + currentColumn[0];
	            JLabel currentLabel = midiLabels.get(labelIndex);

	            Boolean isChecked = (Boolean) currentLabel.getClientProperty("checked");
	            if (Boolean.TRUE.equals(isChecked)) {
	                currentLabel.setBackground(new Color(0xC90033)); // checked 색상
	                currentLabel.setForeground(Color.WHITE);

	                String scaleName = (String) currentLabel.getClientProperty("scale_name");
	                new Thread(() -> playVstSound(scaleName)).start();
	                
	            } else {
	                currentLabel.setBackground(new Color(0x333333)); // 재생 중 색상
	                currentLabel.setForeground(new Color(0x333333)); // 글자색
	            }
	        }

	        // 다음 열로 이동
	        currentColumn[0]++;
	    });

	    timer.start(); // 타이머 시작
	}

	// 재생
	private void startPlayback() {
	    if (timer != null && timer.isRunning()) {
	        timer.stop(); // 기존 타이머 중지
	    }

	    timer = new javax.swing.Timer(columnDuration, e -> {
	        if (currentColumn >= totalColumns) {
	            timer.stop(); // 재생 종료
	            return;
	        }

	        highlightColumn(); // 현재 열 강조 및 체크된 음 실행
	        currentColumn++; // 다음 열로 이동
	    });

	    timer.start(); // 타이머 시작
	}

	// 재생영역 하이라이트 
	private void highlightColumn() {
	    resetAllLabels(); // 모든 라벨 초기화

	    for (int row = 0; row < totalRows; row++) {
	        int labelIndex = row * totalColumns + currentColumn;
	        JLabel currentLabel = midiLabels.get(labelIndex);

	        Boolean isChecked = (Boolean) currentLabel.getClientProperty("checked");
	        if (Boolean.TRUE.equals(isChecked)) {
	            currentLabel.setBackground(new Color(0xC90033)); // checked 색상
	            currentLabel.setForeground(Color.WHITE);

	            // 음 실행 (비동기로 동시에 실행)
	            String scaleName = (String) currentLabel.getClientProperty("scale_name");
	            new Thread(() -> playVstSound(scaleName)).start();
	        } else {
	            currentLabel.setBackground(new Color(0x333333)); // 재생 중 색상
	            currentLabel.setForeground(new Color(0x333333)); // 글자색
	        }
	    }
	}

	// 미디 라벨 리셋
	private void resetAllLabels() {
		
	    for (int i = 0; i < totalRows; i++) {
	        for (int j = 0; j < totalColumns; j++) {
	            int index = i * totalColumns + j;
	            JLabel label = midiLabels.get(index);

	            String pos = (String) label.getClientProperty("pos");
	            if ("odd".equals(pos)) {
	                label.setBackground(new Color(0xECF0F5)); // odd color
	                label.setForeground(new Color(0xECF0F5));
	            } else {
	                label.setBackground(new Color(0xB6C3CF)); // even color
	                label.setForeground(new Color(0xB6C3CF));
	            }

	            Boolean isChecked = (Boolean) label.getClientProperty("checked");
	            if (Boolean.TRUE.equals(isChecked)) {
	                label.setBackground(new Color(0xC90033)); // checked 색상
	                label.setForeground(Color.WHITE);
	            }
	        }
	    }
	    
	}

	
	

	// 메뉴바 생성
	 public JMenuBar createMenuBar() {
		 
	        JMenuBar menuBar = new JMenuBar();
	        menuBar.setFont(customFont);
	        
	        JMenu fileMenu = new JMenu("File");
	        fileMenu.setFont(customFont);
	 
	        
	        JMenuItem newFileItem = new JMenuItem("New");
	        JMenuItem importMidiItem = new JMenuItem("Import json");
	        JMenuItem exportMidiItem = new JMenuItem("Export json");
	        JMenuItem exitItem = new JMenuItem("Exit");

	        // 메뉴에 아이템 추가
	        fileMenu.add(newFileItem);
	        fileMenu.add(importMidiItem);
	        fileMenu.add(exportMidiItem);
	        fileMenu.addSeparator();
	        fileMenu.add(exitItem);
	        menuBar.add(fileMenu);
	        
	        
	        
	        newFileItem.addActionListener(e -> handleNewFile());
	        importMidiItem.addActionListener(e -> importScore());
	        exportMidiItem.addActionListener(e -> exportScore());

	        exitItem.addActionListener(e -> System.exit(0)); 

	        return menuBar;
	    }
	
	 
	 private void handleNewFile() {
		 
		    boolean hasCheckedLabels = midiLabels.stream().anyMatch(label -> Boolean.TRUE.equals(label.getClientProperty("checked")));

		    if (hasCheckedLabels) {
		        int result = JOptionPane.showConfirmDialog(
		            frame,
		            "작업 중인 내역이 있습니다. 저장하시겠습니까?",
		            "확인",
		            JOptionPane.YES_NO_CANCEL_OPTION, // 예/아니오/취소
		            JOptionPane.WARNING_MESSAGE
		        );

		        if (result == JOptionPane.YES_OPTION) {
		        	
		            exportScore(); 
		            resetAllLabels(); 
		            System.out.println("새 작업 시작");
		            
		        } else if (result == JOptionPane.NO_OPTION) {
		        	
		            resetAllLabels();
		            System.out.println("라벨 초기화 , 새 작업 시작");
		            
		        } else if (result == JOptionPane.CANCEL_OPTION) {
		        	
		            System.out.println("작업 취소");
		            
		        }
		        
		    } else {
		    	
		        resetAllLabels(); // 선택된 라벨이 없으면 바로 초기화
		        System.out.println("새 작업 시작");
		        
		    }
		}

	 
	 
	// 타이틀 바 생성
	public void createTitleBar() {}

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
	


	
	public void importScore() {
	    try {
	        // JSON 파일 읽기
	        JFileChooser fileChooser = new JFileChooser();
	        fileChooser.setDialogTitle("Select JSON File to Import");
	        int result = fileChooser.showOpenDialog(frame);
	        
	        if (result == JFileChooser.APPROVE_OPTION) {
	            File selectedFile = fileChooser.getSelectedFile();
	            BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
	            StringBuilder jsonContent = new StringBuilder();
	            String line;
	            while ((line = reader.readLine()) != null) {
	                jsonContent.append(line);
	            }
	            reader.close();

	            // JSON 파싱
	            JSONObject jsonScore = new JSONObject(jsonContent.toString());
	            JSONArray notesArray = jsonScore.getJSONArray("notes");

	            // MIDI 라벨 초기화
	            resetAllLabels();

	            // JSON 데이터를 MIDI 라벨에 반영
	            for (int i = 0; i < notesArray.length(); i++) {
	                JSONObject noteObject = notesArray.getJSONObject(i);
	                String scaleName = noteObject.getString("pitch");
	                int columnIndex = noteObject.getInt("index");

	                for (JLabel label : midiLabels) {
	                    String labelScale = (String) label.getClientProperty("scale_name");
	                    int labelColumn = (int) label.getClientProperty("scale_pos");

	                    if (labelScale.equals(scaleName) && labelColumn == columnIndex) {
	                        label.setBackground(new Color(0xC90033));
	                        label.setForeground(Color.WHITE);
	                        label.putClientProperty("checked", true);
	                    }
	                }
	            }

	            System.out.println("JSON 악보 load");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("JSON 악보 load fail");
	    }
	}

	public void exportScore() {
	    try {
	        // JSON 객체 생성
	        JSONObject jsonScore = new JSONObject();
	        jsonScore.put("title", "Custom Score");
	        jsonScore.put("tempo", 120);

	        JSONArray notesArray = new JSONArray();
	        for (JLabel label : midiLabels) {
	            Boolean isChecked = (Boolean) label.getClientProperty("checked");
	            if (Boolean.TRUE.equals(isChecked)) {
	                JSONObject noteObject = new JSONObject();
	                noteObject.put("pitch", (String) label.getClientProperty("scale_name"));
	                noteObject.put("index", (int) label.getClientProperty("scale_pos"));
	                notesArray.put(noteObject);
	            }
	        }

	        jsonScore.put("notes", notesArray);

	        // JSON 파일 저장
	        JFileChooser fileChooser = new JFileChooser();
	        fileChooser.setDialogTitle("Save JSON File");
	        int result = fileChooser.showSaveDialog(frame);

	        if (result == JFileChooser.APPROVE_OPTION) {
	            File selectedFile = fileChooser.getSelectedFile();
	            if (!selectedFile.getName().endsWith(".json")) {
	                selectedFile = new File(selectedFile.getAbsolutePath() + ".json");
	            }

	            FileWriter fileWriter = new FileWriter(selectedFile);
	            fileWriter.write(jsonScore.toString(4)); // JSON 들여쓰기
	            fileWriter.close();

	            System.out.println("악보가 JSON 파일로 저장: " + selectedFile.getAbsolutePath());
	        }

	    } catch (Exception e) {
	    	
	        e.printStackTrace();
	        System.out.println("JSON 파일 저장 실패.");
	        
	    }
	}
	
	
	
	
	
	
}
