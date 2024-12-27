import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONObject;

public class HashMapTest {

	public static void main(String[] args) {
		
//		
//		
//		String[] tmp = new String[256];
//		
//	
//		
//		
//		HashMap<String, String> map = new HashMap<String, String>(); // HashMap 선언 
//
//		String arr1 = "{1, E6, true}";
//		String arr2 = "{1, C6, true}";
//		String arr3 = "{1, C6, true}";
//		
//		map.put("1", arr1); 
//		map.put("1", arr1); 
//		map.put("2", "Oracle"); 
//		map.put("3", "SQL"); 
//		map.put("4", "Spring"); 
//		
//		
//		
//		System.out.println(map);
//		
//		
//		
//		Map<Integer, String> treeMap = new TreeMap<>();
//        
//        // 값 삽입 (중복된 키는 덮어씌워짐)
//        treeMap.put(3, "Three");
//        treeMap.put(1, "One");
//        treeMap.put(2, "Two");
//        treeMap.put(2, "Two Updated"); // 기존 값 덮어씌움
//
//        // 소팅된 상태로 출력
//        for (Map.Entry<Integer, String> entry : treeMap.entrySet()) {
//            System.out.println(entry.getKey() + ": " + entry.getValue());
//        }
//        
        
        
		
		
		
		// JSON 데이터 초기화
        JSONObject measure = new JSONObject();

        JSONArray measure1 = new JSONArray();
        measure1.put(new JSONObject().put("note", "e6").put("checked", true));
        measure1.put(new JSONObject().put("note", "d6").put("checked", false));
        measure1.put(new JSONObject().put("note", "c6").put("checked", true));

        JSONArray measure2 = new JSONArray();
        measure2.put(new JSONObject().put("note", "e6").put("checked", true));
        measure2.put(new JSONObject().put("note", "d6").put("checked", false));
        measure2.put(new JSONObject().put("note", "c6").put("checked", true));

        measure.put("1", measure1);
        measure.put("2", measure2);

        // 데이터 출력
        System.out.println("Measure 데이터: " + measure.toString(4));

        // 특정 키 검색
        String targetKey = "1";
        if (measure.has(targetKey)) {
            System.out.println("Key " + targetKey + "의 데이터: " + measure.getJSONArray(targetKey));
        }

        // 키를 정렬하려면 Map으로 변환
        measure.keySet().stream().sorted().forEach(key -> System.out.println("Key: " + key + ", Value: " + measure.getJSONArray(key)));
        
	
	
	
	
	
		
		
		

	}

}
