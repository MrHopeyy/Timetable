package Model;

import java.util.ArrayList;

import javax.swing.JOptionPane;

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
	public static int introHours;
	public static int totalHours;

	public Main() {

	}

	public static ArrayList<Module> importModules(Solver solver, String filePathModule) {

		// Creating an array list for storing the Modules
		ArrayList<Module> modules = new ArrayList<Module>();

		// Reading the file line by line, splitting by comma and storing the
		// segments into a new module object
		try {

			br = new BufferedReader(new FileReader(filePathModule));
			while ((line = br.readLine()) != null) {

				// Storing the line
				String[] moduleArray = line.split(cvsSplitBy);

				// Storing the elements into a new module object
				modules.add(new Module(solver, moduleArray[0], Integer.parseInt(moduleArray[1]),
						Integer.parseInt(moduleArray[2])));

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("No File Selected");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("");
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.println("file empty");
					e.printStackTrace();
				}
			}
		}

		// Returning the modules
		return modules;
	}

	public static String[] importCohort(String filePathCohort) throws IOException {

		// Creating an array for storing the line of programmes
		String[] programme_Data = null;

		// Reading the file line by line
		try {

			br = new BufferedReader(new FileReader(filePathCohort));
			while ((line = br.readLine()) != null) {

				// Storing the line into the array
				programme_Data = line.split(cvsSplitBy);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();

			// JOptionPane.showMessageDialog(null, "No file selected! Please try
			// again.", null, JOptionPane.PLAIN_MESSAGE);

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

		int introTemp = 0;
		int totalTem = 0;

		// Creating a new programme object
		Programme prog = new Programme(programme_Data.length, solver);

		// For the length of the programme data array
		for (int i = 0; i < programme_Data.length; i++) {

			// For the size of the module array list
			for (int a = 0; a < modules.size(); a++) {

				// if the programme string element matches the first element in
				// module
				if (programme_Data[i].equals(modules.get(a).getModuleCode())) {

					// Add that module to the add module method in programme
					// class
					prog.addModule(solver, modules.get(a));

					introTemp = introTemp + modules.get(a).getIntroHours();
					totalTem = totalTem + modules.get(a).getTotalHours();

				} else {

				}

			}

			introHours = introTemp;
			totalHours = totalTem;
			System.out.println("intro " + introHours);
			System.out.println("total " + totalHours);

		}

		// Return the prog object
		return prog;
	}

	public static IntVar[][] solve() throws IOException {

		Boolean isIntroHoursLegal = false;
		Boolean isTotalHoursLegal = false;

		// Reading the file path from TimeTableCreatorFileCohortView
		String csvFile = TimeTableCreatorFileCohortView.CohortPath;
		// Retrieving the file path from TimeTableCreatorFileModuleView
		String csvFile2 = TimeTableCreatorFileModuleView.ModulePath;

		// Creating a new solver object
		Solver solver = new Solver("Timetable");

		// Filling the modules array
		ArrayList<Module> modules = importModules(solver, csvFile2);

		// Filling the programme data array
		String[] programme_data = importCohort(csvFile);
		Programme programme = null;

		if (introHours <= 9) {
			// Creating a programme object
			isIntroHoursLegal = true;
		} else {

			JOptionPane.showMessageDialog(null, "Too many intro hours added! Please remove to fit criteria.", null,
					JOptionPane.PLAIN_MESSAGE);
		}

		if (totalHours <= 36) {
			// Creating a programme object
			isTotalHoursLegal = true;
		} else {

			JOptionPane.showMessageDialog(null, "Too many total hours added! Please remove to fit criteria.", null,
					JOptionPane.PLAIN_MESSAGE);
		}

		if (isIntroHoursLegal == true && introHours <= 9 && isTotalHoursLegal == true && totalHours <= 36) {
			// Creating a programme object
			programme = makeProgramme(solver, modules, programme_data);

			
		}

		return programme.generateTimetable(solver);
	}

}
