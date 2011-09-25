import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class PLTUrlHeader {
	PLTUrlInfo  ELEMENTINFO;
	CHttpHeader HEADERINFO;
}

public class PLTUrlHeaderList {
	private List<PLTUrlHeader> urlsList;

	public PLTUrlHeaderList() {
		urlsList = new ArrayList<PLTUrlHeader>();
	}

	public void add(CHttpHeader header, PLTUrlInfo element) {
		PLTUrlHeader info = new PLTUrlHeader();
		
		info.ELEMENTINFO = element;
		info.HEADERINFO = header;
		
		urlsList.add(info);
	}

	public List<PLTUrlHeader> getList() {
		return urlsList;
	}

	public void saveToFile(String string) throws IOException {
		File file = new File(string);
		
		if(file.exists())
			file.delete();

		file.createNewFile();
		
		FileOutputStream fStream = new FileOutputStream(file);
		BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(fStream));
		
		Iterator<PLTUrlHeader> it = urlsList.iterator();
		
		while(it.hasNext())
		{
			PLTUrlHeader temp = it.next();
			bWriter.write(temp.ELEMENTINFO.PATH+";"+temp.ELEMENTINFO.COUNT+";"+temp.HEADERINFO.getResponseCodeNum()+";"+temp.ELEMENTINFO.ERROR.getErrors());
			bWriter.write("\n");
		}
		
		bWriter.close();
		fStream.close();		
	}
}
