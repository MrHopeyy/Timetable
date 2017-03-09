package Model;

import java.util.ArrayList;
import java.util.Collection;

import com.google.ortools.constraintsolver.DecisionBuilder;
import com.google.ortools.constraintsolver.IntVar;
import com.google.ortools.constraintsolver.Solver;

import View.TimeTableCreatorInputView;

public class Module {

	static {
		System.loadLibrary("jniortools");
	}

	public static int N_HOURS = 10;
	public static int N_DAYS = 5;
	public String Module_Code;
	public static int introHours;
	public static int totalHours;
	public static IntVar[][] timetable = new IntVar[N_DAYS][N_HOURS];
	public static IntVar[][] timetable_Flatten;

	public static Solver solver = new Solver("Module");

	public Module(Solver solver,String Module_Code, int introHours, int totalHours) {
		this.Module_Code = Module_Code;
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

		solver.addConstraint(solver.makeSumEquality(timetable_Flatten, Module.getTotalHours(totalHours)));
		solver.addConstraint(solver.makeSumEquality(timetable[0], Module.getIntroHours(introHours)));
	}

	// setter for the module code of a module
	void setModuleCode(String Module_Code) {

		Module.Module_Code = Module_Code;

	}

	// setter for the intro hours of a module
	void setIntroHours(int introHours) {

		Module.introHours = introHours;

	}

	// setter for the total hours of a module
	void setTotalHours(int totalHours) {

		Module.totalHours = totalHours;

	}

	// setter for the module code of a module
	public String getModuleCode() {

		return Module_Code;

	}

	// setter for the intro hours of a module
	private static int getIntroHours(int introHours) {

		return introHours;

	}

	// setter for the total hours of a module
	private static int getTotalHours(int totalHours) {

		return totalHours;

	}

	// solver
	public static void solve() {

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

	}

}
