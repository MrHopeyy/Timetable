package Model;

import com.google.ortools.constraintsolver.IntVar;
import com.google.ortools.constraintsolver.Solver;

public class Module {

	// Creating variables
	public final static int N_HOURS = 9;
	public final static int N_DAYS = 4;
	private String module_Code;
	private int introHours;
	private int totalHours;
	private IntVar[][] timetable = new IntVar[N_DAYS][N_HOURS];

	public Module(Solver solver, String module_Code, int introHours, int totalHours) {

		// initialising the variables of the object
		this.module_Code = module_Code;
		this.introHours = introHours;
		this.totalHours = totalHours;

		IntVar[] timetable_Flatten = solver.makeBoolVarArray(N_DAYS * N_HOURS);

		for (int i = 0; i < N_DAYS; i++) {
			for (int j = 0; j < N_HOURS; j++) {

				timetable[i][j] = timetable_Flatten[i * N_HOURS + j];
			}
		}

		// Constraint to constrain the total hours of a module to the timetable
		solver.addConstraint(solver.makeSumEquality(timetable_Flatten, totalHours));

		// Constraint to constrain the introduction hours of a module to the timetable
		solver.addConstraint(solver.makeSumEquality(timetable[0], introHours));

	}

	// Getters for the module
	public String getModuleCode() {

		return module_Code;

	}

	public int getIntroHours() {

		return introHours;

	}

	public int getTotalHours() {

		return totalHours;

	}

	// Method to return the module timetable
	public IntVar[][] getTimetable() {

		return timetable;

	}

}