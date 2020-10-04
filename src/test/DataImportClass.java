package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataImportClass {

	public String location;
	public String url;
	public int numResults;
	public String result;
	public ArrayList<String> listOfResults;

	public DataImportClass() {
	}

	public DataImportClass(String location, String url, int numResults, String result) {
		super();
		this.location = location;
		this.url = url;
		this.numResults = numResults;
		this.result = result;
		this.listOfResults = new ArrayList<String>();
	}

	public List<DataImportClass> importSearches() throws IOException, InstantiationException, IllegalAccessException {

		List<DataImportClass> listOfSearches = new ArrayList<DataImportClass>();

		File file = new File("data/NewData.xlsx");
		FileInputStream fis = new FileInputStream(file);

		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);

		for (int i = 1; i < 7; i++) {
			XSSFRow row = sheet.getRow(i);

			if (row != null) {

				DataImportClass obj = DataImportClass.class.newInstance();

				listOfSearches.add(obj);

				String newLocation = row.getCell(0).getStringCellValue();
				String newUrl = row.getCell(1).getStringCellValue();

				int newNumResults = (int) row.getCell(2).getNumericCellValue();

				obj.location = newLocation;
				obj.url = newUrl;
				ArrayList<String> newList = new ArrayList<String>();
				obj.listOfResults = newList;

				for (int j = 3; j < 3 + newNumResults; j++) {
					String newResult = row.getCell(j).getStringCellValue();

					obj.listOfResults.add(newResult);
				}
			}
		}
		wb.close();
		fis.close();

		return listOfSearches;
	}

	public static ArrayList<String> importUrls() throws IOException {

		ArrayList<String> urlList = new ArrayList<>();

		File file = new File("data/Data.xlsx");
		FileInputStream fis = new FileInputStream(file);

		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Meals");

		for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
			XSSFRow row = sheet.getRow(i);

			if (row != null) {
				String url = row.getCell(0).getStringCellValue();
				urlList.add(url);
			}
		}
		wb.close();
		fis.close();

		return urlList;
	}
}
