package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;

import com.google.ortools.constraintsolver.Solver;

public class Main {

	public static ArrayList<Module> Modules = new ArrayList<Module>();
	public static ArrayList<Programme> cohort_Data = new ArrayList<Programme>();
	public static String[][] programme_Data;

	public Main() {

	}

	public static void importModules() {

		Modules.add(new Module(null, 0, 0));

		String csvFile = "/Users/AlexHope/Documents/workspace/Timetable/src/Files/Modules.cvs";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		int i = 0;
		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] moduleArray = line.split(cvsSplitBy);

				Modules.add(
						new Module(moduleArray[0], Integer.parseInt(moduleArray[1]), Integer.parseInt(moduleArray[2])));
				System.out.println(Modules.get(i));
				// System.out.println("Module [code= " + moduleArray[0] + " ,
				// Intro Hours= " + moduleArray[1] + " , Total Hours= " +
				// moduleArray[2] + "]");

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

	}

	public static void importCohort() throws IOException {

		int cohortLength = 0;
		int moduleLength = 0;

		String csvFile = "/Users/AlexHope/Documents/workspace/Timetable/src/Files/Cohort.cvs";
		BufferedReader br = null;
		String line = "";
		String line2 = " ";
		String cvsSplitBy = ",";
		String[] CohortLine;
		String[] arraySplit = new String[100];

		try {

			br = new BufferedReader(new FileReader(csvFile));

			while ((line = br.readLine()) != null) {

				cohortLength++;

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String[][] programme_Data = new String[cohortLength][];
		int count = 0;

		try {

			br = new BufferedReader(new FileReader(csvFile));

			while ((line = br.readLine()) != null) {

				CohortLine = line.split(line2);
				arraySplit = CohortLine[0].split(cvsSplitBy);
				moduleLength = CohortLine[0].split(cvsSplitBy).length;

				programme_Data[count] = new String[moduleLength];
				int cohortCount = 1;

				while (cohortCount <= moduleLength) {

					programme_Data[count][cohortCount - 1] = arraySplit[cohortCount - 1];

					cohortCount++;

				}

				count++;

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

		System.out.println(Arrays.deepToString(programme_Data));
		System.out.println(programme_Data.length);
		System.out.println("");

	}

	public static void makeProgramme() throws IOException {
		

		importModules();
		importCohort();
		
		System.out.println("The number of elements is : " + programme_Data == null ? 0 : programme_Data.length);
		System.out.println("The number of elements is : " + Modules == null ? 0 : Modules.size());

		Programme programme = new Programme(null, 0, 0);

		for (int i = 0; i < programme_Data.length; i++) {
			for (int j = 0; j < programme_Data.length; j++) {
				for (int x = 0; x < Modules.size(); x++) {
					if (programme_Data[i][j].equals(Modules.get(x))) {
						
						programme.addModule(Modules.get(i));

						System.out.println("Match found");
					} else {
						System.out.println("Not found");
					}
				}
			}
		}
	}

	public static void solve() {

		Solver solver = new Solver("Main");

		// solver.add(Constraint* MakeIsEqualCstCt(IntExpr* const v, int64 c,
		// IntVar* const b));

		// DecisionBuilder db = solver.makePhase(timetable_Flatten,
		// Solver.CHOOSE_FIRST_UNBOUND, Solver.ASSIGN_MAX_VALUE);
		// solver.newSearch(db);
		// solver.nextSolution();
		// for (int i = 0; i < N_DAYS; i++) {
		// for (int j = 0; j < N_HOURS; j++) {
		// System.out.print(timetable[i][j].value() + " ");
		//
		// }
		//
		// System.out.println();
		//
		// }
		//
		// }

		//
		// programme.generate_timetable();
		// }

	}

	public static void main(String[] args) throws Exception {
		
		//importModules();
		//importCohort();
		makeProgramme();
		
	}

}
