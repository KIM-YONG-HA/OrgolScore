import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject; 

public class JsonTest{
	

	public static void main(String[] args) {
		
		


JSONArray jsonArray = new JSONArray();

for (int i = 1; i < 10; i++) {
	JSONObject jsonObj = new JSONObject();
	jsonObj.put("number", i);
	jsonObj.put("name", "amor_dev");
	jsonArray.add(jsonObj);
}

JSONArray sortedJsonArray = new JSONArray();

List<JSONObject> jsonValues = new ArrayList<JSONObject>();
for (int i = 0; i < jsonArray.size(); i++) {                   //동적 배열 size(), 정적 배열 length()
	jsonValues.add(jsonArray.getJSONObject(i));
	System.out.println(i+""+jsonArray.getJSONObject(i));
}
Collections.sort( jsonValues, new Comparator<JSONObject>() {
	private static final String KEY_NUM = "Number";             //JSON key 변수 선언 생성
	@Override
	public int compare(JSONObject a, JSONObject b) {
		int valA = 0;
		int valB = 0;
            //값이 문자열 일 때
            //String valA = "";
            //String valA = "";
		try {
			valA = Integer.parseInt(a.getString(KEY_NUM));
			valB = Integer.parseInt(b.getString(KEY_NUM));
            
                //값이 문자열 일 때
                //valA = (String) a.get(KEY_NUM);
                //valB = (String) b.get(KEY_NUM);
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		return Integer.compare(valA,valB);
            //값이 문자열 일 때
            //return valA.compareTo(valB);
	}
});
for (int i = 0; i < jsonArray.size(); i++) {
	sortedJsonArray.add(jsonValues.get(i));
}





	}



}

