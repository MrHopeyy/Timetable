package Model;

import com.google.ortools.constraintsolver.DecisionBuilder;
import com.google.ortools.constraintsolver.IntVar;
import com.google.ortools.constraintsolver.Solver;

import View.TimeTableCreatorFileCohortView;

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
		
		

		//
		// constraints
		//

		// Constraint to constrain the total hours of a module to the timetable
		solver.addConstraint(solver.makeSumEquality(timetable_Flatten, totalHours));

		// Constraint to constrain the introduction hours of a module to the
		// first row of the timetable
		solver.addConstraint(solver.makeSumEquality(timetable[0], introHours));
		
//		
//		DecisionBuilder db = solver.makePhase(timetable_Flatten, Solver.CHOOSE_FIRST_UNBOUND, Solver.ASSIGN_MAX_VALUE);
//		solver.newSearch(db);
//		solver.nextSolution();
//		for (int k = 0; k < N_DAYS; k++) {
//			for (int l = 0; l < N_HOURS; l++) {
//				System.out.print(timetable[k][l].value() + " ");
//
//			}
//
//			System.out.println();
//
//		}
	}

	// getter for the module code of a module
	public String getModuleCode() {

		return Module_Code;

	}

	// getter for the intro hours of a module
	public int getIntroHours() {

		return introHours;

	}

	// getter for the total hours of a module
	public int getTotalHours() {

		return totalHours;

	}

	public IntVar[][] getTimetable() {

		return timetable;
		

	}

}