package Model;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.ortools.constraintsolver.IntVar;
import com.google.ortools.constraintsolver.Solver;
import View.TimeTableCreatorFileCohortView;
import View.TimeTableCreatorFileModuleView;

public class Main {

	static {
		System.loadLibrary("jniortools");
	}

	// Initialising the buffered Reader
	public static BufferedReader br = null;
	// Creating a variable to split by space
	public static String line = "";
	// Creating a variable to split by white space
	public static String line2 = " ";
	// Creating a variable to split by line space
	public static String cvsSplitBy = ",";

	public Main() {

	}

	public static ArrayList<Module> importModules(Solver solver) {

		// Retrieving the file path from TimeTableCreatorFileModuleView
		String csvFile2 = TimeTableCreatorFileModuleView.ModulePath;
		// Creating an array list for storing the Modules
		ArrayList<Module> modules = new ArrayList<Module>();

		// Reading the file line by line, splitting by comma and storing the
		// segments into a new module object
		try {

			br = new BufferedReader(new FileReader(csvFile2));
			while ((line = br.readLine()) != null) {

				// Storing the line
				String[] moduleArray = line.split(cvsSplitBy);

				// Storing the elements into a new module object
				modules.add(new Module(solver, moduleArray[0], Integer.parseInt(moduleArray[1]),
						Integer.parseInt(moduleArray[2])));

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

		// Returning the modules
		return modules;
	}

	public static String[] importCohort() throws IOException {

		// Reading the file path from TimeTableCreatorFileCohortView
		String csvFile = TimeTableCreatorFileCohortView.CohortPath;
		// Creating an array for storing the line of programmes
		String[] programme_Data = null;

		// Reading the file line by line
		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				// Storing the line into the array
				programme_Data = line.split(cvsSplitBy);
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

		// Returning the programme data array
		return programme_Data;

	}

	public static Programme makeProgramme(Solver solver, ArrayList<Module> modules, String[] programme_Data)
			throws IOException {

		// Creating a new programme object
		Programme prog = new Programme(programme_Data.length, solver);

		// For the length of the programme data array
		for (int i = 0; i < programme_Data.length; i++) {

			// For the size of the module array list
			for (int a = 0; a < modules.size(); a++) {

				// if the programme string element matches the first element in module
				if (programme_Data[i].equals(modules.get(a).getModuleCode())) {

					// Add that module to the add module method in programme class
					prog.addModule(solver, modules.get(a));

				} else {

				}
			}

		}

		// Return the prog object
		return prog;
	}

	public static IntVar[][] solve() throws IOException {

		// Creating a new solver object
		Solver solver = new Solver("Timetable");
		// Filling the modules array
		ArrayList<Module> modules = importModules(solver);
		// Filling the programme data array
		String[] programme_data = importCohort();
		// Creating a programme object
		Programme programme = makeProgramme(solver, modules, programme_data);
		return programme.generateTimetable(solver);

	}

}
