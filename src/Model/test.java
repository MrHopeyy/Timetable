package Model;

import com.google.ortools.constraintsolver.IntVar;

public class test {

	public static String TestModule_Code;
	public static int TestintroHours;
	public static int TesttotalHours;
	public static int TestN_HOURS = 10;
	public static int TestN_DAYS = 5;
	public static IntVar[][] Testtimetable = new IntVar[TestN_DAYS][TestN_HOURS];

	
	
public static void main(String[] args) throws Exception {

	TestModule_Code = "cs1111";
	TestintroHours = 3;
	TesttotalHours = 7;
	ModuleOld.solve();
	System.out.println("");
	TestModule_Code = "cs2222";
	TestintroHours = 2;
	TesttotalHours = 4;
	ModuleOld.solve();


}
}
