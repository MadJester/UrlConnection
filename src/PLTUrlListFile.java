import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class PLTUrlListFile {

	public static PLTUrlList tryToParse(String string, Boolean tryToFix)
			throws FileNotFoundException, IOException {

		List<String> lines = loadFileLineByLine(string);
		List<PLTUrlListError> errors = checkForErrors(lines);

		if(tryToFix == true)
		{
			lines = fixCode(lines);
		}
		
		PLTUrlList list = parse(lines, errors);

		return list;
	}

	private static List<String> fixCode(List<String> lines) {
		
		
		
		return null;
	}

	public static PLTUrlList parse(List<String> lines,
			List<PLTUrlListError> errors) throws IllegalArgumentException {

		if (lines.size() != errors.size()) {
			throw new IllegalArgumentException();
		}

		PLTUrlList urlList = new PLTUrlList(errors);

		Iterator<String> it = lines.iterator();
		Iterator<PLTUrlListError> er = errors.iterator();

		while (it.hasNext() && er.hasNext()) {
			
			String current_string = it.next();
			PLTUrlListError current_error = er.next();

			if (current_string.length() > 0) {
				if (current_string.trim().charAt(0) != '#') {

					String[] list = StringUtils.split(current_string, " ");

					if (list.length > 0) {

						Integer urlRepeatTimes = 1;
						if (list.length > 1) {
							try {
								urlRepeatTimes = Integer.parseInt(list[1].trim());

								if (urlRepeatTimes <= 0) {
									throw new NumberFormatException();
								}

							} catch (NumberFormatException e) {
								urlRepeatTimes = 1;
							}
						}
						urlList.add(current_string, current_error, list[0].trim(), urlRepeatTimes);
					}
				}
			}
		}

		return urlList;
	}

	public static List<PLTUrlListError> checkForErrors(List<String> lines) {
		List<PLTUrlListError> errorsList = new ArrayList<PLTUrlListError>();

		Iterator<String> it = lines.iterator();
		while (it.hasNext()) {
			String current = it.next();

			PLTUrlListError se = new PLTUrlListError(current);

			if (current.length() > 0) {
				if (current.trim().charAt(0) != '#') {

					int spaceCount = StringUtils.countMatches(current, " ");
					if (spaceCount > 1) {
						se.addError(PLTUrlListError.WHITESPACE_COUNT_ERROR);
					}

					int tabCount = StringUtils.countMatches(current, "\t");
					if (tabCount != 0) {
						se.addError(PLTUrlListError.TABULATORS_COUNT_ERROR);
					}

					String[] list = StringUtils.split(current, " ");
					if (list.length > 2 && list.length <= 0) {
						se.addError(PLTUrlListError.ELEMENT_SIZE_ERROR);
					}

					Integer urlRepeatTimes = 1;
					if (list.length > 1) {
						try {
							urlRepeatTimes = Integer.parseInt(list[1]);

							if (urlRepeatTimes <= 0) {
								throw new NumberFormatException();
							}

						} catch (NumberFormatException e) {
							se.addError(PLTUrlListError.ELEMENT_REPEAT_ERROR);
						}
					}

				}
			} else {
				se.addError(PLTUrlListError.ELEMENT_SIZE_ERROR);
			}

			errorsList.add(se);
		}

		return errorsList;
	}

	public static List<String> loadFileLineByLine(String string)
			throws FileNotFoundException, IOException {
		List<String> list = new ArrayList<String>();

		File file = new File(string);
		FileInputStream fStream = new FileInputStream(file);
		BufferedReader bReader = new BufferedReader(new InputStreamReader(
				fStream));

		String temp;
		while ((temp = bReader.readLine()) != null) {
			list.add(temp);
		}

		fStream.close();
		bReader.close();

		return list;
	}

}
