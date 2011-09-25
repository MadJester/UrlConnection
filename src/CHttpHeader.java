/**
 * @author beast
 * 
 */
public class CHttpHeader {

	public static final Integer NO_ERROR = 0;
	public static final Integer NOT_INITIALIZED_ERROR = 1;
	public static final Integer MALFORMED_URL_ERROR = 2;
	public static final Integer CONNECTION_ERROR = 3;
	public static final Integer URL_TYPE_ERROR = 4;
	private Integer mError = null;
	private String mResponseCode = null;
	private String mPath = null;

	public CHttpHeader(String path) {
		mPath = path;
		mError = NOT_INITIALIZED_ERROR;
		mResponseCode = "#";
	}

	public Integer getError() {
		return this.mError;
	}

	public String getResponseCode() {
		return this.mResponseCode;
	}

	public String getPath() {
		return this.mPath;
	}

	public void setResponseCode(String field) {
		this.mResponseCode = field;
	}

	public void setError(Integer error) {
		this.mError = error;
	}

	@Override
	public String toString() { 
		return "{error: "+this.getError()+" status: "+this.getResponseCode()+" url: "+this.getPath()+"}";
	}

	public String getResponseCodeNum() {
		return CHttpTools.extractResponseCodeNum(this.mResponseCode);
	}
}
