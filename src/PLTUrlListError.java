public class PLTUrlListError {

	public static final Integer WHITESPACE_COUNT_ERROR = 0x01;
	public static final Integer TABULATORS_COUNT_ERROR = 0x02;
	public static final Integer ELEMENT_SIZE_ERROR = 0x4;
	public static final Integer ELEMENT_REPEAT_ERROR = 0x8;

	private Integer errors = 0x0;
	private String string = null;
	
	public PLTUrlListError(String current) {
		string = current;
	}

	public void addError(Integer error) {
		errors = errors | error;
	}
	
	public Integer getErrors()
	{
		return errors;
	}
	
	public Integer checkIfErrorOccurred(Integer error)
	{
		return errors & error;
	}

	@Override
	public String toString() {
		return "{string: '"+string+"' errors: "+errors+"}";
	}
}
