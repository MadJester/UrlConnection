/**
 * @author beast
 * 
 */
public class CHttpTools {

	public static String extractResponseCodeNum(String responseCode) {
		String value = "#";

		String[] temp = responseCode.split(" ");

		if (temp.length == 3) {
			value = temp[1];
		}

		return value;
	}

	public static String tryTofixHttpUrl(String url) {
		String temp = url.toLowerCase();
		StringBuilder builder = new StringBuilder();

		if (temp.length() >= 6) {
			String t = temp.substring(0, 7); 
			if (t.equals("http://") != true) {
				builder.append("http://");
			}
		}
		else
		{
			builder.append("http://");
		}

		builder.append(temp);

		return builder.toString();
	}

}
