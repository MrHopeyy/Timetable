package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import com.google.ortools.constraintsolver.Solver;

public class Main {
	
	static {
		System.loadLibrary("jniortools");
	}

	public static BufferedReader br = null;
	public static String line = "";
	public static String line2 = " ";
	public static String cvsSplitBy = ",";
	
	public Main() {
		
	}

	public static ArrayList<Module> importModules(Solver solver) {

		String csvFile2 = "/Users/AlexHope/Documents/workspace/Timetable/src/Files/Modules.cvs";
		// public static String csvFile =
		// TimeTableCreatorFileModuleView.ModulePath;
		ArrayList<Module> modules = new ArrayList<Module>();
		int i = 0;
		try {

			br = new BufferedReader(new FileReader(csvFile2));
			while ((line = br.readLine()) != null) {

				String[] moduleArray = line.split(cvsSplitBy);

				modules.add(new Module(solver, moduleArray[0], Integer.parseInt(moduleArray[1]),
						Integer.parseInt(moduleArray[2])));
				//System.out.println(modules.get(i));

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

	public static String[][] importCohort() throws IOException {

		String csvFile = "/Users/AlexHope/Documents/workspace/Timetable/src/Files/Cohort.cvs";
		// public static String csvFile2 =
		// TimeTableCreatorFileCohortView.CohortPath;
		String[][] programme_Data;
		String[] CohortLine;
		String[] arraySplit = new String[100];

		int cohortLength = 0;
		int moduleLength = 0;

		try {

			br = new BufferedReader(new FileReader(csvFile));

			while ((line = br.readLine()) != null) {

				cohortLength++;

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		programme_Data = new String[cohortLength][];
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

		//System.out.println(Arrays.deepToString(programme_Data));
		//System.out.println("");
		return programme_Data;

	}

	public static Programme makeProgramme(Solver solver, ArrayList<Module> modules, String[][] programme_Data)
			throws IOException {

		//System.out.println("The number of elements is : " + programme_Data == null ? 0 : programme_Data.length);
		//System.out.println("The number of elements is : " + modules == null ? 0 : modules.size());
		//System.out.println("");

		for (int i = 0; i < programme_Data.length; i++) {
			for (int j = 0; j < programme_Data[0].length; j++) {
				for (int x = 0; x < modules.size(); x++) {
					if (programme_Data[i][j].equals(modules.get(x).getModuleCode())) {

						Programme.addModule(modules.get(x));

						System.out.println("Match found");
					} else {
						System.out.println("Not found");
					}
				}
			}
		}
		return null;
	}

	public static void solve() throws IOException {
		
		Solver solver = new Solver("Timetable");

		ArrayList<Module> modules = importModules(solver);
		String[][] programme_data = importCohort();
		Programme programme = makeProgramme(solver, modules, programme_data);

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

		Main.solve();

		// importModules(null);
		// importCohort();
		// makeProgramme();

	}

}
