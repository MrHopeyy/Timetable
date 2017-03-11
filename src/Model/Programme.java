package Model;

import java.util.ArrayList;

import com.google.ortools.constraintsolver.IntVar;
import com.google.ortools.constraintsolver.Solver;

public class Programme {

	// var module_codes = ['BREAK'];
	// var timetable = IntVar[][];
	
	static {
		System.loadLibrary("jniortools");
	}

	// Variable int for the length of a single day.
	public static int N_HOURS = 10;
	// Variable int for how many days in a week.
	public static int N_DAYS = 4;

	public Programme(String Module_Code, int introHours, int totalHours) {

	}

	public static void addModule(Module module) {

		// module_codes.push(module.code);
		// module_index = module_codes.length-1;
		// module.get_timetable();
		// relate a module's timetable to a progamme's timetable

		//Creating the 2d array for the timetable.
		IntVar[][] timetable = new IntVar[N_DAYS][N_HOURS];
		
		//Creating the an arraylist for the module objects sourced form the main class.
		ArrayList<Module> module_codes = new ArrayList<Module>();
		//module_codes.add(new Module(null, null, 0,0));

		//Adding the module objects to the array.
		module_codes.add(module);
		System.out.println("The number of elements is : " + module_codes == null ? 0 : module_codes.size());
		
		for (int x = 0; x < module_codes.size(); x++) {
			
			System.out.println(module_codes);
			
		}
		
		//Creating an index for the array.
		int module_index = module_codes.size() - 1;
		System.out.println(module_index);

		// module.get_timetable();

	}
	
	public static void main(String[] args) throws Exception {

		//Main.solve();
		//addModule(null);

	}

}
