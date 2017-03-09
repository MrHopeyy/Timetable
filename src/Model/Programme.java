package Model;

import java.util.ArrayList;

import com.google.ortools.constraintsolver.IntVar;
import com.google.ortools.constraintsolver.Solver;

public class Programme {

	// var module_codes = ['BREAK'];
	// var timetable = IntVar[][];

	public static int N_HOURS = 8;
	public static int N_DAYS = 5;
	public static IntVar[][] timetable = new IntVar[N_DAYS][N_HOURS];
	public static Module module_codes = new Module(null, 0, 0);

	public Programme(String Module_Code, int introHours, int totalHours) {

	}

	public void addModule(Module module) {
		
		// module_codes.push(module.code);
		// module_index = module_codes.length-1;
		// module.get_timetable();
		// relate a module's timetable to a progamme's timetable
		
		// module_codes.add();
	}

}
