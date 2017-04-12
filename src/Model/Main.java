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

	// Loading the google OR-Tools Library
	static {
		System.loadLibrary("jniortools");
	}

	// Setting up variable.
	public static BufferedReader br = null;
	public static String line = "";
	public static String line2 = " ";
	public static String cvsSplitBy = ",";
	public static int introHours;
	public static int totalHours;

	public Main() {

	}

	// Method to import all of the modules from at file and store them in an
	// array
	public static ArrayList<Module> importModules(Solver solver, String filePathModule) {

		ArrayList<Module> modules = new ArrayList<Module>();

		try {

			br = new BufferedReader(new FileReader(filePathModule));
			while ((line = br.readLine()) != null) {

				String[] moduleArray = line.split(cvsSplitBy);
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

		return modules;
	}

	// Loading all of the cohorts from a file and storing them into an array
	public static String[] importCohort(String filePathCohort) throws IOException {

		String[] programme_Data = null;

		try {

			br = new BufferedReader(new FileReader(filePathCohort));
			while ((line = br.readLine()) != null) {

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

		return programme_Data;

	}

	// Creating a programme from the imported files
	public static Programme makeProgramme(Solver solver, ArrayList<Module> modules, String[] programme_Data)
			throws IOException {

		int introTemp = 0;
		int totalTemp = 0;
		Programme prog = new Programme(programme_Data.length, solver);

		for (int i = 0; i < programme_Data.length; i++) {

			for (int a = 0; a < modules.size(); a++) {

				if (programme_Data[i].equals(modules.get(a).getModuleCode())) {

					prog.addModule(solver, modules.get(a));
					introTemp = introTemp + modules.get(a).getIntroHours();
					totalTemp = totalTemp + modules.get(a).getTotalHours();

				} else {

				}

			}

			introHours = introTemp;
			totalHours = totalTemp;

		}

		return prog;
	}

	public static Boolean validTimetableCheck() throws IOException {

		Boolean isIntroHoursLegal = false;
		Boolean isTotalHoursLegal = false;
		Boolean isLegal = false;
		Boolean createTimetableBool = false;

		String csvFile = TimeTableCreatorFileCohortView.CohortPath;
		String csvFile2 = TimeTableCreatorFileModuleView.ModulePath;
		Solver solver = new Solver("Timetable");
		ArrayList<Module> modules = importModules(solver, csvFile2);
		String[] programme_data = importCohort(csvFile);

		Programme programme = makeProgramme(solver, modules, programme_data);
		@SuppressWarnings("unused")
		IntVar[][] timeTable = programme.generateTimetable(solver);

		if (introHours == 0 || totalHours == 0) {

		} else {
			
			isLegal = true;

		}

		if (introHours <= 9) {
			isIntroHoursLegal = true;
		} else {

			JOptionPane.showMessageDialog(null, "Too many intro hours added! Please remove to fit criteria.", null,
					JOptionPane.PLAIN_MESSAGE);
		}

		if (totalHours <= 36) {
			isTotalHoursLegal = true;
		} else {

			JOptionPane.showMessageDialog(null, "Too many total hours added! Please remove to fit criteria.", null,
					JOptionPane.PLAIN_MESSAGE);
		}

		if (isIntroHoursLegal == true && isTotalHoursLegal == true && isLegal == true) {

			createTimetableBool = true;

		}
		return createTimetableBool;
	}

	// Solving the timetable using the constraints and outputting the timetable
	// in numeric format
	public static IntVar[][] solve() throws IOException {

		String csvFile = TimeTableCreatorFileCohortView.CohortPath;
		String csvFile2 = TimeTableCreatorFileModuleView.ModulePath;
		Solver solver = new Solver("Timetable");
		ArrayList<Module> modules = importModules(solver, csvFile2);
		String[] programme_data = importCohort(csvFile);

		Programme programme = makeProgramme(solver, modules, programme_data);
		IntVar[][] timeTable = programme.generateTimetable(solver);

		return timeTable;

	}

}
