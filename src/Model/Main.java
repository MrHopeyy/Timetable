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
	// public static String[][] programme_Data = new String[100][1];

	public Main() {

	}

	public static void addModules() {

		Modules.add(new Module(null, 0, 0));

		String csvFile = "/Users/AlexHope/Documents/workspace/Timetable/src/Files/Modules.cvs";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] Module = line.split(cvsSplitBy);

				Modules.add(new Module(Module[0], Integer.parseInt(Module[1]), Integer.parseInt(Module[2])));

				System.out.println("Module [code= " + Module[0] + " , Intro Hours= " + Module[1] + " , Total Hours= "
						+ Module[2] + "]");

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

	public static void addCohort() throws IOException {

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

	}

	public void Programs() {

		Programme programme = new Programme(null, 0, 0);

		for (int p = 1; p < Modules.size(); p++) {

			// if (Modules.[0] == programme_Data[0]){
			//
			// programme_Data.add(new Module(Modules.[0],
			// Integer.parseInt(Module[1]), Integer.parseInt(Module[2]));
			//
			// }

		}

		// var programme = new Programme(programme_data);
		// for(module in modules)
		// if(module.code is in programme_data.modules)
		// programme.add_module(module);

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

		addModules();
		addCohort();
	}

}
