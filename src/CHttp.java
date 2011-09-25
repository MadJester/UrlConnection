import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author beast
 * 
 */
public class CHttp {

	public static CHttpHeader getHeader(String path) {

		CHttpHeader header = new CHttpHeader(path);
		HttpURLConnection urlc = null;
		
		try {

			URL url = new URL(path);

			if (!(url.openConnection() instanceof HttpURLConnection)) {
				throw new RuntimeException();
			}

			urlc = (HttpURLConnection) url.openConnection();
			urlc.setAllowUserInteraction(false);
			urlc.setDoInput(true);
			urlc.setDoOutput(false);
			urlc.setUseCaches(false);
			urlc.setRequestProperty("Connection", "Close");
			urlc.setRequestMethod("HEAD");
			urlc.connect();

			header.setError(CHttpHeader.NO_ERROR);
			header.setResponseCode(urlc.getHeaderField(0));

		} catch (MalformedURLException e) {
			header.setError(CHttpHeader.MALFORMED_URL_ERROR);
		} catch (IOException e) {
			header.setError(CHttpHeader.CONNECTION_ERROR);			
		} catch (RuntimeException e) {
			header.setError(CHttpHeader.URL_TYPE_ERROR);
		}

		return header;
	}

}
