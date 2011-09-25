import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;


public class Tests {
	public static void main(String[] args) {
		
//		CHttpHeader [] headers = new CHttpHeader[8];
		
//		headers[0] = CHttp.getHeader("http://bashz.org.pl");
//		headers[1] = CHttp.getHeader("www.bash.org.pl");
//		headers[2] = CHttp.getHeader("http:/bash.org.pl");
//		headers[3] = CHttp.getHeader("http://bash.o");
//		headers[4] = CHttp.getHeader("www://bash.org.pl");
//		headers[5] = CHttp.getHeader("www.bash.org.pl.sssss");
//		headers[6] = CHttp.getHeader("httpL://bash.org.ppl");
//		headers[7] = CHttp.getHeader("http://wp.pl");
//				
//		System.out.println("-- Headers");
//		System.out.println(" - 1 {error: "+headers[0].getError()+" status: "+CHttpTools.extractResponseCodeNum(headers[0].getResponseCode())+" url: "+headers[0].getPath()+"}");
//		System.out.println(" - 2 {error: "+headers[1].getError()+" status: "+CHttpTools.extractResponseCodeNum(headers[1].getResponseCode())+" url: "+headers[1].getPath()+"}");
//		System.out.println(" - 3 {error: "+headers[2].getError()+" status: "+CHttpTools.extractResponseCodeNum(headers[2].getResponseCode())+" url: "+headers[2].getPath()+"}");
//		System.out.println(" - 4 {error: "+headers[3].getError()+" status: "+CHttpTools.extractResponseCodeNum(headers[3].getResponseCode())+" url: "+headers[3].getPath()+"}");
//		System.out.println(" - 5 {error: "+headers[4].getError()+" status: "+CHttpTools.extractResponseCodeNum(headers[4].getResponseCode())+" url: "+headers[4].getPath()+"}");
//		System.out.println(" - 6 {error: "+headers[5].getError()+" status: "+CHttpTools.extractResponseCodeNum(headers[5].getResponseCode())+" url: "+headers[5].getPath()+"}");
//		System.out.println(" - 7 {error: "+headers[6].getError()+" status: "+CHttpTools.extractResponseCodeNum(headers[6].getResponseCode())+" url: "+headers[6].getPath()+"}");
//		System.out.println(" - 8 {error: "+headers[7].getError()+" status: "+CHttpTools.extractResponseCodeNum(headers[7].getResponseCode())+" url: "+headers[7].getPath()+"}");
//		System.out.println("-- Headers");
		
//		try {
//			PLTUrlList list = PLTUrlListFile.tryToParse("test_list.txt", true);
//			System.out.println("-- Lines");
//			System.out.println(" - bad lines count "+list.getBadLinesCount());
//			{
//				List<PLTUrlInfo> temp = list.getList();
//				Iterator<PLTUrlInfo> it = temp.iterator();
//				while(it.hasNext())
//				{
//					PLTUrlInfo pui = it.next();
//					System.out.println("  - {path: '"+pui.PATH+"' quantity: "+pui.COUNT+" errors: "+pui.ERROR.getErrors()+"}" );
//				}
//			}			
//			System.out.println("-- Lines");
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		
		//----------------------------------------------------------------------------------------------------
		//----------------------------------------------------------------------------------------------------
		//----------------------------------------------------------------------------------------------------
		PLTUrlHeaderList urlHeaderList = new PLTUrlHeaderList();
		// #####
		try {
			PLTUrlList list = PLTUrlListFile.tryToParse("test_list.txt", true);
			System.out.println("-- Lines+Header");
			System.out.println(" - bad lines count "+list.getBadLinesCount());
			{
				List<PLTUrlInfo> temp = list.getList();
				Iterator<PLTUrlInfo> it = temp.iterator();
				
				while(it.hasNext())
				{
					PLTUrlInfo pui = it.next();
					pui.PATH = CHttpTools.tryTofixHttpUrl(pui.PATH);
					
					CHttpHeader header = CHttp.getHeader(pui.PATH);										
					System.out.println("  - {path: '"+pui.PATH+"' header: "+CHttpTools.extractResponseCodeNum(header.getResponseCode())+"}");
					
					urlHeaderList.add(header, pui);
				}
			}			
			System.out.println("-- Lines+Header");
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// #####
		try {
			urlHeaderList.saveToFile("test_list_output.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//----------------------------------------------------------------------------------------------------
		//----------------------------------------------------------------------------------------------------
		//----------------------------------------------------------------------------------------------------
	}

}
