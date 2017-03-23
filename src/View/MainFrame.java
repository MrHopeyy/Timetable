package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame {

	public static JFrame mainFrame;

	// Creates a jFrame to Contain all of the other views in the application.
	public MainFrame() throws IOException {

		// Creating a new jFrame to contain all of the panels that are created
		mainFrame = new JFrame("TimeTable Creator");
		// Setting the minimum size of the panel so the panel cannot get any smaller than this
		mainFrame.setMinimumSize(new Dimension(720, 480));
		// Setting the layout of this frame
		mainFrame.setLayout(new BorderLayout());
		// Setting the frame to be visible
		mainFrame.setVisible(true);
		// Setting the location to not be relative
		mainFrame.setLocationRelativeTo(null);
		// Stopping the frame from closing from when the x button is pressed
		mainFrame.setDefaultCloseOperation(0);
		// Creating an instance of the first panel to be displayed in the frame
		TimeTableCreatorMenuView g = new TimeTableCreatorMenuView();
		// Creating the panel
		JPanel start = g.buildTimeTableCreatorMenu();
		// Adding the panel to the frame
		mainFrame.add(start, BorderLayout.CENTER);
		// Setting the frame to be visible
		MainFrame.mainFrame.setVisible(true);
		// Stopping the frame from closing when the x button is pressed
		MainFrame.mainFrame.setDefaultCloseOperation(0);
	}

	public static void main(String[] args) throws IOException {

		// Running the frame
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new MainFrame();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

}
