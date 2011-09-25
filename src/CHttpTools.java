/**
 * @author beast
 * 
 */
public class CHttpTools {

	public static String extractResponseCodeNum(String responseCode) {
		String value = "#";
		
		String [] temp = responseCode.split(" ");
		
		if(temp.length == 3)
		{
			value = temp[1];
		}
		
		return value;
	}

}
