package Account;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class EAccount {
	private File file;
	private Workbook workbook;
	private int rowIndex = 1;

	public EAccount() {
		try {
			file = new File("account/account.xlsx");
			workbook = WorkbookFactory.create(new FileInputStream(file));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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

	public VLogin getLoginInfo() {
		Sheet sheet = workbook.getSheet("ac");
		VLogin vLogin = null;

		int rows = sheet.getPhysicalNumberOfRows();
		if (rowIndex < rows) {
			Row row = sheet.getRow(rowIndex);
			if (row != null) {
				int cells = row.getPhysicalNumberOfCells(); // 해당 행의 열의 개수
				String[] userInfo = new String[cells];
				for (int columnIndex = 0; columnIndex < cells; columnIndex++) {
					Cell cell = row.getCell(columnIndex); // 셀에 담겨있는 값을 읽는다.
					userInfo[columnIndex] = cell.getStringCellValue();
				}
				vLogin = new VLogin(userInfo);
			}
		}
		rowIndex++;
		return vLogin;
	}

	public void writeAccount(String[] accountInfo) {
		try {
			FileOutputStream fos = new FileOutputStream(file);

			Sheet sheet = workbook.getSheet("ac");
			int lowestRow = sheet.getLastRowNum();
			Row row = sheet.createRow(lowestRow + 1);
			for (int i = 0; i < accountInfo.length; i++) {
				row.createCell(i).setCellValue(accountInfo[i]);
			}

			workbook.write(fos);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
