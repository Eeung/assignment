package Account;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class EAccount {
	private File file;
	private XSSFWorkbook workbook;
	private int rowIndex = 1;

	public EAccount() {
		try {
			file = new File("account/account.xlsx");
			workbook = new XSSFWorkbook(new FileInputStream(file));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 계정 정보를 가져오기
	public VAccount getLoginInfo() {
		XSSFSheet sheet = workbook.getSheet("ac");
		VAccount vAccount = null;

		int rows = sheet.getPhysicalNumberOfRows();
		if (rowIndex < rows) {
			XSSFRow row = sheet.getRow(rowIndex);
			if (row != null) {
				int cells = row.getPhysicalNumberOfCells(); // 해당 행의 열의 개수
				String[] userInfo = new String[cells];
				for (int columnIndex = 0; columnIndex < cells; columnIndex++) {
					XSSFCell cell = row.getCell(columnIndex); // 셀에 담겨있는 값을 읽는다.
					userInfo[columnIndex] = cell.getStringCellValue();
				}
				vAccount = new VAccount(userInfo);
			}
		}
		rowIndex++;
		return vAccount;
	}

	// 새로운 계정을 쓰기
	public void writeAccount(String[] accountInfo) {
		try {
			FileOutputStream writeExcelFile = new FileOutputStream(file);

			XSSFSheet sheet = workbook.getSheet("ac");
			int lowestRow = sheet.getLastRowNum();
			XSSFRow row = sheet.createRow(lowestRow + 1);
			for (int i = 0; i < accountInfo.length; i++) {
				row.createCell(i).setCellValue(accountInfo[i]);
			}

			workbook.write(writeExcelFile);
			createUserFile(accountInfo[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 사용자의 파일을 생성
	public void createUserFile(String id) {
		try {
			File user = new File("account/basket/" + id + ".txt");
			user.createNewFile();
			user = new File("account/sugang/" + id + ".txt");
			user.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
