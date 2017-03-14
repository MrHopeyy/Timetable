package Model;

import java.util.ArrayList;
import java.util.Collection;

import com.google.ortools.constraintsolver.DecisionBuilder;
import com.google.ortools.constraintsolver.IntVar;
import com.google.ortools.constraintsolver.Solver;

import View.TimeTableCreatorInputView;

public class ModuleOld {

	static {
		System.loadLibrary("jniortools");
	}

	public static int N_HOURS = 10;
	public static int N_DAYS = 4;
	public static String Module_Code;
	public static int introHours;
	public static int totalHours;
	public static IntVar[][] timetable = new IntVar[N_DAYS][N_HOURS];

	public ModuleOld(String string, int introHours, int totalHours) {

	}

	// setter for the module code of a module
	void setModuleCode(String Module_Code) {

		ModuleOld.Module_Code = Module_Code;

	}

	// setter for the intro hours of a module
	void setIntroHours(int introHours) {

		ModuleOld.introHours = introHours;

	}

	// setter for the total hours of a module
	void setTotalHours(int totalHours) {

		ModuleOld.totalHours = totalHours;

	}

	// setter for the module code of a module
	private String getModuleCode(String Module_Code) {

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

		Solver solver = new Solver("Module");

		//
		// data
		//

		ModuleOld module = new ModuleOld(Module_Code, introHours, totalHours);
		module.setModuleCode("CS1234");
		module.setIntroHours(3);
		module.setTotalHours(7);

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

		
		solver.addConstraint(solver.makeSumEquality(timetable_Flatten, ModuleOld.getTotalHours(totalHours)));
		solver.addConstraint(solver.makeSumEquality(timetable[0], ModuleOld.getIntroHours(introHours)));

		//
		// Solver to make a single module timetable.
		//

		DecisionBuilder db = solver.makePhase(timetable_Flatten, Solver.CHOOSE_FIRST_UNBOUND, Solver.ASSIGN_MAX_VALUE);
		solver.newSearch(db);
		solver.nextSolution();
		for (int i = 0; i < N_DAYS; i++) {
			for (int j = 0; j < N_HOURS; j++) {
				System.out.print(timetable[i][j].value() + " ");

			}

			System.out.println();

		}

	}

	public static void main(String[] args) throws Exception {

		ModuleOld.solve();

	}

}
