package jsonFiles;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {
	
	/**
	 * @param responseString
	 * @return
	 */
	public static JsonPath convertRawResponseToJson(String responseString) {
		JsonPath jsn = new JsonPath(responseString);
		return jsn;
	}

}
