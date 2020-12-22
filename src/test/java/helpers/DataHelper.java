package helpers;

import cucumber.api.Scenario;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;

public class DataHelper {

	private static HashMap<String, HashMap<String, String>> data;

	private static HashMap<String, String> globalData;

	private static String currentScenarioKey;

	private static String sheetName;

	private static String testCaseId;

	public static HashMap<String, HashMap<String, String>> getData() {
		return data;
	}

	/**
	 * Get data for current feature file being run
	 * 
	 * @return
	 */
	public static HashMap<String, String> getCurrentData() {
		if (data == null) {
			data = new HashMap<>();
			try {
				DataFormatter formatter = new DataFormatter();
				FileInputStream fs = new FileInputStream(ConfigurationHelper.getFeatureFile());
				XSSFWorkbook workbook = new XSSFWorkbook(fs);
				// workbook.createSheet().getRow(1).getCell(8).setCellValue("");
//                int numSheets = workbook.getNumberOfSheets();
				sheetName = System.getProperty("lang");
				if (sheetName == null) {
					sheetName = "default";
				}
				XSSFSheet sheet = workbook.getSheet(sheetName.toLowerCase());
				Row headerRow = sheet.getRow(0);
				int columns = headerRow.getPhysicalNumberOfCells();

				for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
					Row currentRow = sheet.getRow(i);
					if (currentRow == null) {
						break;
					}
					HashMap<String, String> currentHash = new HashMap<>();
					for (int j = 0; j < columns; j++) {
						String value = formatter.formatCellValue(currentRow.getCell(j));
						currentHash.put(headerRow.getCell(j).getStringCellValue(), value);
					}
					data.put(getScenarioKey(currentHash.get("featureFile"), currentHash.get("scenario")), currentHash);
				}
				fs.close();
				System.out.println("Pulled data for " + sheetName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (data.get(currentScenarioKey) == null) {
			System.out.println("Test data for the the give scenario is not available : " + currentScenarioKey);
		}
		return data.get(currentScenarioKey);

	}

	public static String getOrUpdateBugID(String testCaseID, int testCaseColumnIndex, String bugId,
			boolean updateCellValue, String projectKey) {
		FileInputStream fs;
		try {
			fs = new FileInputStream(ConfigurationHelper.getFeatureFile());

			XSSFWorkbook workbook = new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			Row headerRow = sheet.getRow(0);
			int columns = headerRow.getPhysicalNumberOfCells();

			for (int rowNumber = 1; rowNumber < sheet.getPhysicalNumberOfRows(); rowNumber++) {
				Row currentRow = sheet.getRow(rowNumber);
				if (currentRow == null) {
					break;
				}
				if (currentRow.getCell(0) == null) {
					break;
				}
				String value = currentRow.getCell(0).toString();
				if (value.equals(testCaseID)) {
					if (updateCellValue) {
						setCellValue(currentRow, testCaseColumnIndex, bugId);
						FileOutputStream outFile = new FileOutputStream(new File(ConfigurationHelper.getFeatureFile()));
						workbook.write(outFile);
						outFile.close();
						return "";
					}
					String previousBugId = currentRow.getCell(testCaseColumnIndex).getStringCellValue();
					if (previousBugId.contains(projectKey)) {
						return previousBugId;
					}
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	public static void setCellValue(Row currentRow, int cellNumber, String valueToBeSet) {
		try {
			currentRow.getCell(cellNumber).setCellValue(valueToBeSet);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void setCurrentScenario(Scenario scenario) {
		String[] parts = scenario.getId().split(";");
		currentScenarioKey = getScenarioKey(parts[0], parts[1]);
	}

	public static HashMap<String, String> getGlobalData() {
		if (globalData == null) {
			globalData = new HashMap<>();
		}

		return globalData;
	}

	private static String getScenarioKey(String featureFile, String scenarioName) {
		return featureFile.toLowerCase().replace(' ', '-') + scenarioName.toLowerCase().replace(' ', '-');
	}

	public static String getTestCaseId(){
		return data.get(currentScenarioKey).get("testCaseId");
	}
}
