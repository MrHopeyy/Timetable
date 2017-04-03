package Model;

import java.io.IOException;
import java.util.*;
import com.google.ortools.constraintsolver.DecisionBuilder;
import com.google.ortools.constraintsolver.IntVar;
import com.google.ortools.constraintsolver.Solver;

public class Programme {

	static {
		System.loadLibrary("jniortools");
	}

	// Creating the 2d array for the timetable.
	public IntVar[][] timetable = new IntVar[Module.N_DAYS][Module.N_HOURS];
	// Creating the an array list for the module objects soured form the main
	// class.
	public ArrayList<String> module_codes = new ArrayList<String>();
	// Creating a IntVar array
	private IntVar[] timetable_Flatten;

	public Programme(int nModulesInProgramme, Solver solver) {

		// Adding a blank break into the module codes array list
		module_codes.add("BREAK");

		// Initialising the timetable flatten array
		timetable_Flatten = solver.makeIntVarArray(Module.N_DAYS * Module.N_HOURS, 0, nModulesInProgramme);

		solver.addConstraint(solver.makeEquality(timetable_Flatten[0], 0));
		solver.addConstraint(solver.makeEquality(timetable_Flatten[9], 0));
		solver.addConstraint(solver.makeEquality(timetable_Flatten[26], 0));
		solver.addConstraint(solver.makeEquality(timetable_Flatten[35], 0));

		for (int h = 4; h < timetable_Flatten.length; h = h + 9) {

			solver.addConstraint(solver.makeEquality(timetable_Flatten[h], 0));
		}

		// Flattening the timetable 2d array into the timetable flattened array
		for (int i = 0; i < Module.N_DAYS; i++) {
			for (int j = 0; j < Module.N_HOURS; j++) {
				// Creating the flattened version of the timetable array.
				timetable[i][j] = timetable_Flatten[i * Module.N_HOURS + j];
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

		return timetable;

	}
}
