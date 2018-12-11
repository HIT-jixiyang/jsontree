package jsontree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonTree {
	public static int ID = 0;

	public static void readFileByLines(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				System.out.println("line " + line + ": " + tempString);
				line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	public String getJsonStr2() {
		String fileName = String.valueOf("C:\\Users\\83723\\Desktop\\IMGS\\data.json");
		File file = new File(fileName);
		BufferedReader reader = null;
		String jsonStr = "";
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				jsonStr = jsonStr + tempString + "\n";
				System.out.println("line " + line + ": " + tempString);
				line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return jsonStr;
	}

	@SuppressWarnings("rawtypes")
	public void analysisJson(Object objJson, String parent_type) {
		JSONObject jsonObject = (JSONObject) objJson;
		JSONObject objData = jsonObject.getJSONObject("data");
		JSONArray arresult = objData.getJSONArray("result");
		int counter = 0;
		while (true) {
			try {
				JSONObject inn = arresult.getJSONObject(counter);
				String score = inn.getString("score");
				String name = inn.getString("name");
				System.out.println(score + " " + name);
				counter++;
			} catch (Exception e) {
				break;
			}
			
		}
		/*
		 * // 如果obj为json数组 if (objJson instanceof JSONArray) { JSONArray objArray =
		 * (JSONArray) objJson;
		 * 
		 * analysisJson(objArray.get(0), "Array");
		 * 
		 * } // 如果为json对象 else if (objJson instanceof JSONObject) { JSONObject
		 * jsonObject = (JSONObject) objJson; Iterator it =
		 * jsonObject.keySet().iterator(); while (it.hasNext()) { String key =
		 * it.next().toString(); Object object = jsonObject.get(key); // 如果得到的是数组 if
		 * (object instanceof JSONArray) { JSONArray objArray = (JSONArray) object;
		 * analysisJson(objArray, "Array"); } // 如果key中是一个json对象 else if (object
		 * instanceof JSONObject) { analysisJson((JSONObject) object, "Object"); } //
		 * 如果key中是其他 else { ID++; System.out.println(ID + "------" + "[" + key + "]:" +
		 * object.toString() + " " + parent_type); } } }
		 */
	}

	public static void main(String[] args) {
		JsonTree hw = new JsonTree();
		JSONObject jsonObject = JSONObject.parseObject(hw.getJsonStr2());
		hw.analysisJson(jsonObject, "Object");

	}
}
