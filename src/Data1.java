import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
public class Data1 {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		JSONObject jsonObject = new JSONObject();
		JSONArray array=new JSONArray();
		array.add("name: Alexis Sanchez");
		array.add( "club: Manchester United");
		array.add("name: Robin van Persie");
		array.add( "club: Feyenoord");
		jsonObject.put("strikers",array);
	      try {
	         FileWriter file = new FileWriter("C:\\Users\\gt_ingole\\Desktop\\MCA Sem 5\\JSONMerger\\data1.json");
	         file.write(jsonObject.toJSONString());
	         file.close();
	      } catch (IOException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	      System.out.println("JSON file created: "+jsonObject);
	}
}
