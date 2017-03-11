package Model;

import java.util.ArrayList;
import java.util.Collection;

import com.google.ortools.constraintsolver.DecisionBuilder;
import com.google.ortools.constraintsolver.IntVar;
import com.google.ortools.constraintsolver.Solver;

import View.TimeTableCreatorInputView;

public class Module {

	// Loads the library for the constrain tools
	static {
		System.loadLibrary("jniortools");
	}

	// Variable int for the length of a single day.
	public static int N_HOURS = 10;
	// Variable int for how many days in a week.
	public static int N_DAYS = 4;
	// String for the module code of the object.
	public String Module_Code;
	// Int for the amount of intro hours a module wants.
	public static int introHours;
	// Int for the amount of total hours a module wants.
	public static int totalHours;

	// public static IntVar[][] timetable_Flatten;

	public Module(Solver solver, String Module_Code, int introHours, int totalHours) {

		// initialising the variables of the object
		this.Module_Code = Module_Code;
		this.introHours = introHours;
		this.totalHours = totalHours;

		// 2d array of timetable
		IntVar[][] timetable = new IntVar[N_DAYS][N_HOURS];

		// Timetable Flatten is a 1d array of timetable.
		IntVar[] timetable_Flatten = solver.makeBoolVarArray(N_DAYS * N_HOURS);

		// For loop to create the length and width of the timetable array.
		for (int i = 0; i < N_DAYS; i++) {
			for (int j = 0; j < N_HOURS; j++) {

				// Creating the flattened version of the timetable array.
				timetable[i][j] = timetable_Flatten[i * N_HOURS + j];
			}
		}

		//
		// constraints
		//

		// Constraint to constrain the total hours of a module to the timetable
		solver.addConstraint(solver.makeSumEquality(timetable_Flatten, Module.getTotalHours(totalHours)));
		// Constraint to constrain the introduction hours of a module to the
		// first row of the timetable
		solver.addConstraint(solver.makeSumEquality(timetable[0], Module.getIntroHours(introHours)));
	}

	// getter for the module code of a module
	public String getModuleCode() {

		return Module_Code;

	}

	// getter for the intro hours of a module
	private static int getIntroHours(int introHours) {

		return introHours;

	}

	// getter for the total hours of a module
	private static int getTotalHours(int totalHours) {

		return totalHours;

	}

}
// public static void solve() {

//
// Solver to make a single module timetable.
//

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

// }
