package Model;

import java.util.ArrayList;

import com.google.ortools.constraintsolver.IntVar;
import com.google.ortools.constraintsolver.Solver;

public class Programme {

	public Programme(String Module_Code, int introHours, int totalHours) {

		// var module_codes = ['BREAK'];
		// var timetable = IntVar[][];

		int N_HOURS = 8;
		int N_DAYS = 5;
		IntVar[][] timetable = new IntVar[N_DAYS][N_HOURS];
	}

	public void addModule(Module Modules) {

		// function add_module(module){
		// module_codes.push(module.code);
		// module_index = module_codes.length-1;
		//
		// //module.get_timetable();
		// //relate a module's timetable to a progamme's timetable
		// }
		// }

	}

}
