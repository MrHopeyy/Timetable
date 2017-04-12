package Model;

import java.io.IOException;
import java.util.*;
import com.google.ortools.constraintsolver.DecisionBuilder;
import com.google.ortools.constraintsolver.IntVar;
import com.google.ortools.constraintsolver.Solver;

public class Programme {

	// Creating variables
	public IntVar[][] timetable = new IntVar[Module.N_DAYS][Module.N_HOURS];
	public ArrayList<String> module_codes = new ArrayList<String>();
	private IntVar[] timetable_Flatten;

	public Programme(int nModulesInProgramme, Solver solver) {

		module_codes.add("BREAK");
		timetable_Flatten = solver.makeIntVarArray(Module.N_DAYS * Module.N_HOURS, 0, nModulesInProgramme);

		// Adding constraints to add breaks to certain parts of the timetable
		solver.addConstraint(solver.makeEquality(timetable_Flatten[0], 0));
		solver.addConstraint(solver.makeEquality(timetable_Flatten[9], 0));
		solver.addConstraint(solver.makeEquality(timetable_Flatten[26], 0));
		solver.addConstraint(solver.makeEquality(timetable_Flatten[35], 0));

		for (int h = 4; h < timetable_Flatten.length; h = h + 9) {

			solver.addConstraint(solver.makeEquality(timetable_Flatten[h], 0));
		}

		for (int i = 0; i < Module.N_DAYS; i++) {
			for (int j = 0; j < Module.N_HOURS; j++) {
				timetable[i][j] = timetable_Flatten[i * Module.N_HOURS + j];
			}
		}

	}

	// Method to add modules to the programme
	public void addModule(Solver solver, Module module) {

		module_codes.add(module.getModuleCode());
		int module_index = module_codes.size() - 1;
		IntVar[][] modTimetable = module.getTimetable();

		for (int day = 0; day < modTimetable.length; day++) {
			for (int hour = 0; hour < modTimetable[day].length; hour++) {

				// Add a constraint to every module timetable
				solver.addConstraint(
						solver.makeIsEqualCstCt(timetable[day][hour], module_index, modTimetable[day][hour]));

			}

		}

	}

	// Method to generate a timetable from all of the constraints
	public IntVar[][] generateTimetable(Solver solver) throws IOException {

		DecisionBuilder db = solver.makePhase(timetable_Flatten, Solver.CHOOSE_FIRST_UNBOUND, Solver.ASSIGN_MAX_VALUE);
		solver.newSearch(db);
		solver.nextSolution();

		return timetable;

	}
}
