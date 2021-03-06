package View;
//java -jar /Users/AlexHope/Downloads/TimetableCreator.jar

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame {

	public static JFrame mainFrame;

	// Creates a jFrame to Contain all of the other views in the application.
	public MainFrame() throws IOException {

		mainFrame = new JFrame("TimeTable Creator");
		mainFrame.setMinimumSize(new Dimension(720, 480));
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setVisible(true);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(0);
		TimeTableCreatorMenuView g = new TimeTableCreatorMenuView();
		JPanel start = g.buildTimeTableCreatorMenu();
		mainFrame.add(start, BorderLayout.CENTER);
		MainFrame.mainFrame.setVisible(true);
		MainFrame.mainFrame.setDefaultCloseOperation(0);
		File fileKeepConstraints = new File("src/Files/keepConsraints.txt");
		File fileChangeConstraints = new File("src/Files/changeConsraints.txt");
		
		if (fileKeepConstraints.exists()) {
			fileKeepConstraints.delete();
			}
			
			if (!fileKeepConstraints.exists()) {
			fileChangeConstraints.delete();
			}

	}

	// Main method to create frame
	public static void main(String[] args) throws IOException {

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
