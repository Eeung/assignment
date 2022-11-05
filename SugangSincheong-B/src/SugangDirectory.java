import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SugangDirectory {
	File CamFile, ColFile, DepFile, ClaFile;
	String Colleges[], Departments[];

	public SugangDirectory() {
	}

	String[] FindCampus(String campus) {
		String[] CollegeArr = null;
		try {
			CamFile = new File("data/root");
			BufferedReader CamReader = new BufferedReader(new FileReader(CamFile));
			StringTokenizer CamSplited = new StringTokenizer(CamReader.readLine(), " ");
			CamSplited.nextToken();
			while (!campus.equals(CamSplited.nextToken())) {
				CamSplited = new StringTokenizer(CamReader.readLine(), " ");
				CamSplited.nextToken();
			}
			CamReader.close();

			// 대학 콤보박스 요소 추출
			ColFile = new File("data/" + CamSplited.nextToken());
			BufferedReader ColReader = new BufferedReader(new FileReader(ColFile));
			String temp = ColReader.readLine();
			ArrayList<String> ColList = new ArrayList<>();
			while (temp != null) {
				String ColSplited[] = temp.split(" ");
				ColList.add(ColSplited[1]);
				temp = ColReader.readLine();
			}
			CollegeArr = ColList.toArray(new String[0]);
			ColReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return CollegeArr;
	}

	String[] FindCollege(String college) {
		String[] DepartArr = null;
		try {
			BufferedReader ColReader = new BufferedReader(new FileReader(ColFile));
			StringTokenizer ColSplited = new StringTokenizer(ColReader.readLine(), " ");
			ColSplited.nextToken();
			while (!college.equals(ColSplited.nextToken())) {
				ColSplited = new StringTokenizer(ColReader.readLine(), " ");
				ColSplited.nextToken();
			}
			ColReader.close();

			// 학과 콤보박스 요소 추출
			DepFile = new File("data/" + ColSplited.nextToken());
			BufferedReader DepReader = new BufferedReader(new FileReader(DepFile));
			String temp = DepReader.readLine();
			ArrayList<String> DepList = new ArrayList<>();
			while (temp != null) {
				String DepSplited[] = temp.split(" ");
				DepList.add(DepSplited[1]);
				temp = DepReader.readLine();
			}
			DepartArr = DepList.toArray(new String[0]);
			DepReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return DepartArr;
	}

	String[][] FindDepartment(String department) {
		String[][] ClassArr = null;
		try {
			BufferedReader DepReader = new BufferedReader(new FileReader(DepFile));
			StringTokenizer DepSplited = new StringTokenizer(DepReader.readLine(), " ");
			DepSplited.nextToken();
			while (!department.equals(DepSplited.nextToken())) {
				DepSplited = new StringTokenizer(DepReader.readLine(), " ");
				DepSplited.nextToken();
			}
			DepReader.close();

			// 수업 콤보박스 요소 추출
			ClaFile = new File("data/" + DepSplited.nextToken());
			BufferedReader ClaReader = new BufferedReader(new FileReader(ClaFile));
			String temp = ClaReader.readLine();
			ArrayList<String[]> ClaList = new ArrayList<String[]>();
			while (temp != null) {
				String ClaSplited[] = temp.split(" ");
				ClaList.add(ClaSplited);
				temp = ClaReader.readLine();
			}
			ClassArr = ClaList.toArray(new String[0][0]);
			ClaReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ClassArr;
	}

}
