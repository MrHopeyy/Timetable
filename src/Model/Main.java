package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.ortools.constraintsolver.Solver;

import View.TimeTableCreatorFileCohortView;
import View.TimeTableCreatorFileModuleView;

public class Main {

	static {
		System.loadLibrary("jniortools");
	}

	public static BufferedReader br = null;
	public static String line = "";
	public static String line2 = " ";
	public static String cvsSplitBy = ",";
	// Variable int for the length of a single day.
	public final static int N_HOURS = 10;
	// Variable int for how many days in a week.
	public final static int N_DAYS = 4;

	public Main() {

	}

	public static ArrayList<Module> importModules(Solver solver) {

		String csvFile2 = TimeTableCreatorFileModuleView.ModulePath;
		ArrayList<Module> modules = new ArrayList<Module>();
		//int i = 0;
		try {

			br = new BufferedReader(new FileReader(csvFile2));
			while ((line = br.readLine()) != null) {

				String[] moduleArray = line.split(cvsSplitBy);

				modules.add(new Module(solver, moduleArray[0], Integer.parseInt(moduleArray[1]),
						Integer.parseInt(moduleArray[2])));
				System.out.println(moduleArray[0].toString());

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("");
		return modules;
	}

	public static String[] importCohort() throws IOException {

		 String csvFile = TimeTableCreatorFileCohortView.CohortPath;
		String[] programme_Data = null;
		// String[] cohortLine;
		// String[] arraySplit = new String[100];

		// int cohortLength = 0;
		// int moduleLength = 0;

		// ArrayList<Module> modules = new ArrayList<Module>();
		//int i = 0;
		String cvsSplitBy = ",";
		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				programme_Data = line.split(cvsSplitBy);
				System.out.println(Arrays.toString(programme_Data));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			System.out.println("");
		}

		return programme_Data;
		
	}

	// try {
	//
	// br = new BufferedReader(new FileReader(csvFile));
	//
	// while ((line = br.readLine()) != null) {
	//
	// cohortLength++;
	//
	// }
	//
	// } catch (FileNotFoundException e) {
	// e.printStackTrace();
	// }
	//
	// programme_Data = new String[cohortLength][];
	// int count = 0;
	//
	// try {
	//
	// br = new BufferedReader(new FileReader(csvFile));
	//
	// while ((line = br.readLine()) != null) {
	//
	// cohortLine = line.split(line2);
	// arraySplit = cohortLine[0].split(cvsSplitBy);
	// moduleLength = cohortLine[0].split(cvsSplitBy).length;
	//
	// programme_Data[count] = new String[moduleLength];
	// int cohortCount = 1;
	//
	// while (cohortCount <= moduleLength) {
	//
	// programme_Data[count][cohortCount - 1] = arraySplit[cohortCount - 1];
	//
	// cohortCount++;
	//
	// }
	//
	// count++;
	//
	// }
	//
	// } catch (FileNotFoundException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// } finally {
	// if (br != null) {
	// try {
	// br.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	// }

	// System.out.println(Arrays.deepToString(programme_Data));
	

	public static Programme makeProgramme(Solver solver, ArrayList<Module> modules, String[] programme_Data)
			throws IOException {

		Programme prog = new Programme(programme_Data.length, solver);

		for (int i = 0; i < programme_Data.length; i++) {
			for (int a = 0; a < modules.size(); a++) {
				if (programme_Data[i].equals(modules.get(a).getModuleCode())) {

					prog.addModule(solver, modules.get(a));

					System.out.println("Match found");
				} else {
					System.out.println("Not found");
				}
			}
			
			System.out.println("");

		}

		return prog;
	}

	public static void solve() throws IOException {

		Solver solver = new Solver("Timetable");

		ArrayList<Module> modules = importModules(solver);
		String[] programme_data = importCohort();
		Programme programme = makeProgramme(solver, modules, programme_data);

		programme.generateTimetable(solver);
		
	}


}
