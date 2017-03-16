package Model;

import java.util.*;

import com.google.ortools.constraintsolver.DecisionBuilder;
import com.google.ortools.constraintsolver.IntVar;
import com.google.ortools.constraintsolver.Solver;

import View.TimeTableCreatorFileCohortView;

public class Programme {

	static {
		System.loadLibrary("jniortools");
	}

	// Variable int for the length of a single day.
	public static int N_HOURS = 10;
	// Variable int for how many days in a week.
	public static int N_DAYS = 4;
	// Creating the 2d array for the timetable.
	IntVar[][] timetable = new IntVar[N_DAYS][N_HOURS];
	// Creating the an array list for the module objects sourced form the main class.
	ArrayList<String> module_codes = new ArrayList<String>();
	
	private IntVar[] timetable_Flatten;

	public Programme(int nModulesInProgramme, Solver solver) {

		module_codes.add("BREAK");
		
		timetable_Flatten = solver.makeIntVarArray(N_DAYS * N_HOURS, 0, nModulesInProgramme);
		
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

		IntVar[][] modTimetable = module.getTimetable();

		for (int day = 0; day < modTimetable.length; day++) {
			for (int hour = 0; hour < modTimetable[day].length; hour++) {

				solver.addConstraint(
				          solver.makeIsEqualCstCt(timetable[day][hour], module_index, modTimetable[day][hour]));

			}

		}

	}

	public IntVar[][] generateTimetable(Solver solver) {

		// Timetable Flatten is a 1d array of timetable.

		DecisionBuilder db = solver.makePhase(timetable_Flatten, Solver.CHOOSE_FIRST_UNBOUND, Solver.ASSIGN_MAX_VALUE);
		solver.newSearch(db);
		solver.nextSolution();
		for (int k = 0; k < N_DAYS; k++) {
			for (int l = 0; l < N_HOURS; l++) {
				//System.out.print(timetable[k][l].value() + " ");
				
				IntVar value = timetable[k][l];
				module_codes.get((int) value.value());
				System.out.print(module_codes.get((int) value.value()) + " ");
				

			}

			System.out.println();

		}

		return timetable;

	}

}
