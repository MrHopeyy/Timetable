package Model;

import com.google.ortools.constraintsolver.IntVar;
import com.google.ortools.constraintsolver.Solver;

public class Module {

	// Loads the library for the constrain tools
	static {
		System.loadLibrary("jniortools");
	}

	// Variable int for the length of a single day.
	public final static int N_HOURS = 10;
	// Variable int for how many days in a week.
	public final static int N_DAYS = 4;
	// String for the module code of the object.
	private String Module_Code;
	// Int for the amount of intro hours a module wants.
	private int introHours;
	// Int for the amount of total hours a module wants.
	private int totalHours;

	private IntVar[][] timetable = new IntVar[N_DAYS][N_HOURS];

	public Module(Solver solver, String Module_Code, int introHours, int totalHours) {

		// initialising the variables of the object
		this.Module_Code = Module_Code;
		this.introHours = introHours;
		this.totalHours = totalHours;

		// 2d array of timetable

		// Timetable Flatten is a 1d array of timetable.
		IntVar[] timetable_Flatten = solver.makeBoolVarArray(N_DAYS * N_HOURS);

		// For loop to create the length and width of the timetable array.
		for (int i = 0; i < N_DAYS; i++) {
			for (int j = 0; j < N_HOURS; j++) {

				// Creating the flattened version of the timetable array.
				timetable[i][j] = timetable_Flatten[i * N_HOURS + j];
			}
		}

		// Constraint to constrain the total hours of a module to the timetable
		solver.addConstraint(solver.makeSumEquality(timetable_Flatten, totalHours));

		// Constraint to constrain the introduction hours of a module to the
		solver.addConstraint(solver.makeSumEquality(timetable[0], introHours));

	}

	// Getter for the module code of a module
	public String getModuleCode() {

		return Module_Code;

	}

	// Getter for the intro hours of a module
	public int getIntroHours() {

		return introHours;

	}

	// Getter for the total hours of a module
	public int getTotalHours() {

		return totalHours;

	}

	// Getter for the module timetable
	public IntVar[][] getTimetable() {

		return timetable;

	}

}