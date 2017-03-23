package Model;

//import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import com.google.ortools.constraintsolver.DecisionBuilder;
import com.google.ortools.constraintsolver.IntVar;
import com.google.ortools.constraintsolver.Solver;

public class Programme {

	static {
		System.loadLibrary("jniortools");
	}

	// Variable int for the length of a single day.
	public static int N_HOURS = 10;
	// Variable int for how many days in a week.
	public static int N_DAYS = 4;
	// Creating the 2d array for the timetable.
	public IntVar[][] timetable = new IntVar[N_DAYS][N_HOURS];
	// Creating the an array list for the module objects soured form the main
	// class.
	ArrayList<String> module_codes = new ArrayList<String>();
	// Creating a IntVar array
	private IntVar[] timetable_Flatten;

	public Programme(int nModulesInProgramme, Solver solver) {

		// Adding a blank break into the module codes arraylist
		module_codes.add("BREAK");

		// Initialising the timetable flatten array
		timetable_Flatten = solver.makeIntVarArray(N_DAYS * N_HOURS, 0, nModulesInProgramme);

		// Flattening the timetable 2d array into the timetable flattend array
		for (int i = 0; i < N_DAYS; i++) {
			for (int j = 0; j < N_HOURS; j++) {
				// Creating the flattened version of the timetable array.
				timetable[i][j] = timetable_Flatten[i * N_HOURS + j];
			}
		}

	}

	public void addModule(Solver solver, Module module) {

		// Adding the module objects to the array.
		module_codes.add(module.getModuleCode());

		// Creating an index for the array.
		int module_index = module_codes.size() - 1;

		// Storing the module 2d array from the module object
		IntVar[][] modTimetable = module.getTimetable();

		// For the length of the module timetable array
		for (int day = 0; day < modTimetable.length; day++) {
			// For the length of the module timetable array inner array
			for (int hour = 0; hour < modTimetable[day].length; hour++) {

				// Add a constraint to timetable 2d array constraining them to
				// not clash with each other
				solver.addConstraint(
						solver.makeIsEqualCstCt(timetable[day][hour], module_index, modTimetable[day][hour]));

			}

		}

	}

	public IntVar[][] generateTimetable(Solver solver) throws IOException {

		// Creating a decision builder to solve all the constrain that have been
		// made
		DecisionBuilder db = solver.makePhase(timetable_Flatten, Solver.CHOOSE_FIRST_UNBOUND, Solver.ASSIGN_MAX_VALUE);
		// Making a new search
		solver.newSearch(db);
		// Looking for next solution
		solver.nextSolution();

		// FileWriter textOutput = new FileWriter("output.txt");

		// For the length of the timetable
		for (int k = 0; k < N_DAYS; k++) {
			for (int l = 0; l < N_HOURS; l++) {

				// Print the elements of the array to there string counterparts
				// System.out.print(timetable[k][l].value() + " ");
				System.out.print(module_codes.get((int) timetable[k][l].value()) + " ");

				// textOutput.write(module_codes.get((int)
				// timetable[k][l].value()) + " ");

			}

			System.out.println();
			// textOutput.write("\r\n");

		}

		// textOutput.close();
		
		// Returning the timetable array
		return timetable;

	}

}
