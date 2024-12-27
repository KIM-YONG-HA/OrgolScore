
public class SelectedNotes_back {
	
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
            final int bpm = 70; 
            int beatDuration = 60000 / bpm; // 한 박자의 길이(ms)

            for (ScaleInfo n : selectedNotes) {
                try {
                    String note = (String) n.getScaleName();
                    int pos = (int) n.getScalePos();

                    // 음을 재생하는 메서드
                    playVstSound(note);

                    // scalePos 값에 따라 박자 길이 결정
                    int noteDuration = beatDuration;
                    if (pos % 2 != 0) {  // 홀수 scalePos 값은 8분음표 (eighth note)
                        noteDuration /= 2; // 절반 길이로 설정
                    }
                    
                    // 해당 음의 재생 후 일시 정지
                    Thread.sleep(noteDuration);
                    
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

	    
	}

}
