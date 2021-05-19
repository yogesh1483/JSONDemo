import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
public class Data2 {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		JSONObject jsonObject = new JSONObject();
		JSONArray array=new JSONArray();
		array.add("name: Nicolas Pepe");
		array.add( "club: Arsenal");
		jsonObject.put("strikers",array);
	      try {
	         FileWriter file = new FileWriter("C:\\Users\\gt_ingole\\Desktop\\MCA Sem 5\\JSONMerger\\data2.json");
	         file.write(jsonObject.toJSONString());
	         file.close();
	      } catch (IOException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	      System.out.println("JSON file created: "+jsonObject);
	}
}
