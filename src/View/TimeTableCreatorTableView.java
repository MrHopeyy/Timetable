package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import com.google.ortools.constraintsolver.IntVar;
import java.util.*;
import com.google.ortools.constraintsolver.DecisionBuilder;
import com.google.ortools.constraintsolver.Solver;

import Model.Main;
import Model.Module;
import Model.Programme;

public class TimeTableCreatorTableView {

	private JPanel mainPanel;
	// Variable int for the length of a single day.
	public final static int N_HOURS = 10;
	// Variable int for how many days in a week.
	public final static int N_DAYS = 4;
	// Initialising the buffered Reader
	public static BufferedReader br = null;
	public static String line = "";
	public static String line2 = " ";
	public static String cvsSplitBy = ",";
	private ArrayList<String> module_codes = new ArrayList<String>();


	/*
	 * constructor for the menuView
	 */

	
	public TimeTableCreatorTableView() {

	}

	/**
	 * used to create a panel for the main menu
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("static-access")
	public JPanel buildTimeTableCreatorMenu() throws IOException {

		module_codes.add("BREAK");
		
		try {
			mainPanel = (JPanel) createContent();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		mainPanel.setVisible(true);
		mainPanel.setSize(720, 480);
		mainPanel.setLayout(new BorderLayout());

		// creating all of the buttons for the menu
		JButton backButton = new JButton();
		JButton printTXTButton = new JButton();
		JButton printCVSButton = new JButton();
		JButton exitButton = new JButton();

		JLabel label1 = new JLabel("Generated Table");
		label1.setFont(new Font("Arial", Font.BOLD, 36));
		label1.setVerticalTextPosition(JLabel.CENTER);
		label1.setHorizontalTextPosition(JLabel.CENTER);
		
		/////////////////////////////////////////////////////////////
		
		// Retrieving the file path from TimeTableCreatorFileModuleView
				String csvFile2 = TimeTableCreatorFileModuleView.ModulePath;
				// Creating an array list for storing the Modules
				ArrayList<String> modules = new ArrayList<String>();

				// Reading the file line by line, splitting by comma and storing the
				// segments into a new module object
				try {

					br = new BufferedReader(new FileReader(csvFile2));
					while ((line = br.readLine()) != null) {

						// Storing the line
						String[] moduleArray = line.split(cvsSplitBy);

						// Storing the elements into a new module object
						modules.add(moduleArray[0]);

					}

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (br != null) {
						try {
							br.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				
				// Reading the file path from TimeTableCreatorFileCohortView
				String csvFile = TimeTableCreatorFileCohortView.CohortPath;
				// Creating an array for storing the line of programmes
				String[] programme_Data = null;

				// Reading the file line by line
				try {

					br = new BufferedReader(new FileReader(csvFile));
					while ((line = br.readLine()) != null) {

						// Storing the line into the array
						programme_Data = line.split(cvsSplitBy);
					}

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (br != null) {
						try {
							br.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				
				// For the length of the programme data array
				for (int i = 0; i < programme_Data.length; i++) {

					// For the size of the module array list
					for (int a = 0; a < modules.size(); a++) {

						// if the programme string element matches the first element in module
						if (programme_Data[i].equals(modules.get(a))) {

							// Add that module to the add module method in programme class
							module_codes.add(modules.get(a));
							
						} else {

						}
					}

				}
		
		/////////////////////////////////////////////////////////////

		String[] columnNames = { "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00",
				"18:00" };
		
		IntVar[][] rowData = Main.solve();
		
		Object[][] intArray = new Object[rowData.length][];
		for(int p = 0; p < rowData.length; p++){
		    intArray[p] = new Object[rowData[p].length];
		    for(int j = 0; j < rowData[p].length; j++){
		    	
		       intArray[p][j] = module_codes.get((int) rowData[p][j].value());

		    }
		}
		
		Arrays.toString(module_codes.toArray());
		
		JTable mainTable = new JTable(intArray, columnNames);
		mainTable.setEnabled(false);
		JScrollPane scrollPane = new JScrollPane(mainTable);
		scrollPane.setPreferredSize(new Dimension(600, 100));
		JTable rowTable = new RowNumberTable(mainTable);
		rowTable.setOpaque(false);
		scrollPane.setRowHeaderView(rowTable);
		scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, rowTable.getTableHeader());
		scrollPane.setEnabled(false);

		// setting the layout of the main frame to a border layout

		try {

			// creating a new panel center and setting the properties
			// creating a new gridbag layout for all of the buttons and adding
			// them
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(8, 8, 8, 8);
			gbc.gridwidth = gbc.REMAINDER;
			JPanel center = new JPanel();
			GridBagLayout thisLayout = new GridBagLayout();
			center.setLayout(thisLayout);
			center.setOpaque(false);
			center.add(scrollPane, gbc);
			/**
			 * creating the buttons for the panel
			 */
			{
				backButton = new JButton();
				backButton.setForeground(Color.BLACK);
				backButton.setPreferredSize(new Dimension(100, 35));
				backButton.setText("Back to Menu");
				backButton.setFont(new Font("Arial", Font.PLAIN, 12));
				backButton.setHorizontalTextPosition(JButton.CENTER);
				backButton.setVerticalTextPosition(JButton.CENTER);
			}
			{
				printTXTButton = new JButton();
				printTXTButton.setForeground(Color.BLACK);
				printTXTButton.setPreferredSize(new Dimension(100, 35));
				printTXTButton.setText("Save to .TXT");
				printTXTButton.setFont(new Font("Arial", Font.PLAIN, 12));
				printTXTButton.setHorizontalTextPosition(JButton.CENTER);
				printTXTButton.setVerticalTextPosition(JButton.CENTER);
			}
			
			{
				printCVSButton = new JButton();
				printCVSButton.setForeground(Color.BLACK);
				printCVSButton.setPreferredSize(new Dimension(100, 35));
				printCVSButton.setText("Save to .CSV");
				printCVSButton.setFont(new Font("Arial", Font.PLAIN, 12));
				printCVSButton.setHorizontalTextPosition(JButton.CENTER);
				printCVSButton.setVerticalTextPosition(JButton.CENTER);
			}
			
			{
				exitButton = new JButton();
				exitButton.setForeground(Color.BLACK);
				exitButton.setPreferredSize(new Dimension(100, 35));
				exitButton.setText("Exit");
				exitButton.setFont(new Font("Arial", Font.PLAIN, 12));
				exitButton.setHorizontalTextPosition(JButton.CENTER);
				exitButton.setVerticalTextPosition(JButton.CENTER);
			}

			JPanel commandBox = new JPanel();
			commandBox.setOpaque(false);
			commandBox.setLayout(new FlowLayout());
			commandBox.add(backButton, gbc);
			commandBox.add(printTXTButton, gbc);
			commandBox.add(printCVSButton, gbc);
			commandBox.add(exitButton, gbc);

			JPanel titleBox = new JPanel();
			titleBox.setOpaque(false);
			titleBox.setLayout(new FlowLayout());
			titleBox.add(label1);

			mainPanel.add(commandBox, BorderLayout.SOUTH);
			mainPanel.add(center, BorderLayout.CENTER);
			mainPanel.add(titleBox, BorderLayout.NORTH);

			commandBox.setOpaque(false);
			center.setOpaque(false);

		} catch (Exception e) {
			e.printStackTrace();
		}

		/**
		 * used to create an action listener to see if the user wants to quit.
		 */
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MainFrame.mainFrame.getContentPane().removeAll();
				TimeTableCreatorMenuView x = new TimeTableCreatorMenuView();
				MainFrame.mainFrame.add(x.buildTimeTableCreatorMenu(), BorderLayout.CENTER);
				MainFrame.mainFrame.repaint();
				MainFrame.mainFrame.revalidate();
				TimeTableCreatorFileCohortView.CohortPath = null;
				TimeTableCreatorFileModuleView.ModulePath = null;

			}
		});
		
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (JOptionPane.showConfirmDialog(mainPanel, "Are you sure to exit the Program?", "Really Closing?",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					System.exit(0);
				}

			}
		});

		printTXTButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
				Date dateobj = new Date();

				File file = new File("/Users/AlexHope/TimeTable " + df.format(dateobj) + ".txt");

				if (!file.exists()) {
					try {
						file.createNewFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {

					StringBuilder builder = new StringBuilder();
					for (int i = 0; i < intArray.length; i++) {
						for (int j = 0; j < intArray[i].length; j++) {
							builder.append(intArray[i][j] + "");
							if (j < intArray[i].length - 1)
								builder.append(" ");
						}
						builder.append("\n");
					}
					BufferedWriter writer = null;
					try {

						writer = new BufferedWriter(new FileWriter(file));
						JOptionPane.showMessageDialog(mainPanel, "File Saved!", null, JOptionPane.PLAIN_MESSAGE);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						writer.write(builder.toString());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} // save the string representation of the board
					try {
						writer.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

				StringBuilder builder = new StringBuilder();
				for (int i = 0; i < intArray.length; i++) {
					for (int j = 0; j < intArray[i].length; j++) {
						builder.append(intArray[i][j] + "");
						if (j < intArray[i].length - 1)
							builder.append(" ");
					}
					builder.append("\n");
				}
				BufferedWriter writer = null;
				try {

					writer = new BufferedWriter(new FileWriter(file));
					JOptionPane.showMessageDialog(mainPanel, "File Saved!", null, JOptionPane.PLAIN_MESSAGE);

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					writer.write(builder.toString());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} // save the string representation of the board
				try {
					writer.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});
		
		printCVSButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				
				SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
				Date dateobj = new Date();

				File file = new File("/Users/AlexHope/TimeTable " + df.format(dateobj) + ".csv");

				if (!file.exists()) {
					try {
						file.createNewFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {

					StringBuilder builder = new StringBuilder();
					for (int i = 0; i < intArray.length; i++) {
						for (int j = 0; j < intArray[i].length; j++) {
							builder.append(intArray[i][j] + "");
							if (j < intArray[i].length - 1)
								builder.append(" ");
						}
						builder.append("\n");
					}
					BufferedWriter writer = null;
					try {

						writer = new BufferedWriter(new FileWriter(file));
						JOptionPane.showMessageDialog(mainPanel, "File Saved!", null, JOptionPane.PLAIN_MESSAGE);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						writer.write(builder.toString());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} // save the string representation of the board
					try {
						writer.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

				StringBuilder builder = new StringBuilder();
				for (int i = 0; i < intArray.length; i++) {
					for (int j = 0; j < intArray[i].length; j++) {
						builder.append(intArray[i][j] + "");
						if (j < intArray[i].length - 1)
							builder.append(" ");
					}
					builder.append("\n");
				}
				BufferedWriter writer = null;
				try {

					writer = new BufferedWriter(new FileWriter(file));
					JOptionPane.showMessageDialog(mainPanel, "File Saved!", null, JOptionPane.PLAIN_MESSAGE);

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					writer.write(builder.toString());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} // save the string representation of the board
				try {
					writer.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});

		return mainPanel;

	}

	/**
	 * used to create the background image for the panel
	 */
	private Component createContent() throws IOException {
		final ImageIcon icon = new ImageIcon(this.getClass().getResource("/Files/background.jpg"));

		@SuppressWarnings("serial")
		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(icon.getImage(), 0, 0, null);
			}
		};
		return panel;
	}

}