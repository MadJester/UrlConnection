import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class PLTUrlInfo
{
	public String PATH;
	public Integer COUNT;
	public PLTUrlListError ERROR;
	public String OLDENTRY;
}

public class PLTUrlList {
	
	private List<PLTUrlInfo> urlsList;
	private List<PLTUrlListError> errors;
	private Integer errorsCount = 0;
	
	public PLTUrlList(List<PLTUrlListError> errors) {

		this.errors = errors;
		urlsList = new ArrayList<PLTUrlInfo>();	
		
		countErrors();
	}

	private void countErrors() {
		Iterator<PLTUrlListError> it = errors.iterator();
		while(it.hasNext())
		{
			PLTUrlListError tmp = it.next();
			if(tmp.getErrors()!=0)
				errorsCount++;
		}
	}

	public void add(String full, PLTUrlListError current_error, String path, Integer urlRepeatTimes) {
		PLTUrlInfo info = new PLTUrlInfo();
		
		info.PATH = path;
		info.COUNT = urlRepeatTimes;
		info.ERROR = current_error;
		info.OLDENTRY = full;
		
		urlsList.add(info);
	}	
	
	public Integer getBadLinesCount()
	{
		return errorsCount;
	}
	
	public List<PLTUrlInfo> getList()
	{
		return urlsList;
	}
}
