import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONMerger {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException, ParseException {
		String directory_path;
		String[] input_base_name = new String[1];
		input_base_name[0] = null;
		String output_base_name;
		String max_file_size;
		System.out.println("folder path, input file base name, output file base name, max file size");

		Scanner inputscanner = new Scanner(System.in);
		String userinput = inputscanner.nextLine();
		if (userinput.isEmpty()) {
			System.out.println("NO INPUT \n PROGRAMING IS EXITING");
			System.exit(0);
		}
		args = userinput.split(",");
		directory_path = args[0];
		input_base_name[0] = args[1];
		output_base_name = args[2];
		max_file_size = args[3];
		inputscanner.close();

		FileFilter filefilter = new FileFilter() {
			
			public boolean accept(File file) {
				
				if (file.getName().endsWith(".json") && file.getName().startsWith(input_base_name[0])) {
					return true;
				}
				return false;
			}
		};
		File fileinput = new File(directory_path);
		int merge_counter = 0;
		JSONParser parser = new JSONParser();
		if (fileinput.isDirectory()) {
			boolean write = true;
			JSONObject[] prev_json = new JSONObject[1];
			for (File files : fileinput.listFiles(filefilter)) {
				
				System.out.println("Input file name: " + files.getName());
				FileReader finput = new FileReader(files);				
				JSONObject jsoninput = (JSONObject) parser.parse(finput);
				if (prev_json[0] != null) {					
					JSONObject[] final_json = new JSONObject[1];
					final_json[0] = new JSONObject();
					
					prev_json[0].keySet().forEach(key -> {
						if (jsoninput.containsKey(key)) {
							JSONArray json_array = (JSONArray) prev_json[0].get(key);
							JSONArray json_array_second = (JSONArray) jsoninput.get(key);
							for (int i = 0; i < json_array_second.size(); i++) {
								json_array.add(json_array_second.get(i));
							}
							final_json[0].put(key, json_array);
						}
					});
					prev_json[0] = final_json[0];
				} else {
					prev_json[0] = jsoninput;
					merge_counter++;
					continue;

				}

				File fileoutput = new File(directory_path + "/" + output_base_name + merge_counter + ".json");
				FileWriter foutput = new FileWriter(fileoutput);

				if (merge_counter > 0 && write) {
					try {
						org.json.JSONObject tmp = new org.json.JSONObject(prev_json[0].toString());
						foutput.write(tmp.toString(4));
					} catch (Exception e) {
						e.printStackTrace();
						foutput.write(prev_json[0].toJSONString());
					}
					foutput.flush();
					foutput.close();
					
					if (fileoutput.length() > Long.parseLong(max_file_size)) {
						System.out.println("Merged File Size exceeded limit :  "    + fileoutput.length() + " kb");
						write = false;
						fileoutput.delete();
						break;
					}

					System.out.println("Output file name: " + fileoutput.getName());
				}

				merge_counter++;
			}
		} else {
			System.out.println("Input is not a directory");
		}
	}
}