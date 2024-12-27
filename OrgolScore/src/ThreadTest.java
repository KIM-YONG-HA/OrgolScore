import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import simple.ScaleInfo;

public class ThreadTest {

	public ThreadTest() {
		// ExecutorService를 사용하여 멀티스레딩
        ExecutorService executorService = Executors.newFixedThreadPool(selectedNotes.size());

        // BPM 설정 (70)
        final int bpm = 70;
        int beatDuration = 60000 / bpm;  // 한 박자의 길이(ms)

        // 선택된 음들을 동시에 재생
        for (ScaleInfo n : selectedNotes) {
            executorService.submit(() -> {
                try {
                    boolean isChecked = n.isChecked();
                    String note = n.getScaleName();
                    int pos = n.getScalePos();

                    // 음이 체크되었으면 재생
                    if (isChecked) {
                        playVstSound(note);
                    }

                    // 8비트 구현을 위한 조정 (8비트는 박자의 길이를 절반으로 설정)
                    int noteDuration = beatDuration;
                    if (pos % 2 != 0) {
                        noteDuration /= 2; // 8비트 (빠른 재생)
                    }

                    // 해당 음의 재생 후 일시 정지
                    Thread.sleep(noteDuration);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // 모든 스레드 종료 대기
        executorService.shutdown();
	}
	
	
	
	

}
