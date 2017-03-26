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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import com.google.ortools.constraintsolver.IntVar;
import Model.Main;

public class TimeTableCreatorTableView {

	private JPanel mainPanel;
	// Initialising the buffered Reader// Initialising the buffered Reader
	public static BufferedReader br = null;
	// Creating a variable to split by space
	public static String line = "";
	// Creating a variable to split by white space
	public static String line2 = " ";
	// Creating a variable to split by line space
	public static String cvsSplitBy = ",";
	// Creating an array list to store module code strings
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

		// Adding a break inside the module codes array
		module_codes.add("BREAK");

		try {
			// Creating the contents for the panel
			mainPanel = (JPanel) createContent();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Setting the panel to be visible
		mainPanel.setVisible(true);
		// Setting the size of the panel
		mainPanel.setSize(720, 480);
		// Setting the layout of the panel to be border layout
		mainPanel.setLayout(new BorderLayout());

		// creating all of the buttons for the menu
		JButton backButton = new JButton();
		JButton printTXTButton = new JButton();
		JButton exitButton = new JButton();

		// Creating a new label for the panel
		JLabel label1 = new JLabel("Generated Table");
		// Setting the font and the size of the label
		label1.setFont(new Font("Arial", Font.BOLD, 36));
		// Setting the position of the label
		label1.setVerticalTextPosition(JLabel.CENTER);
		// Setting the position of the label
		label1.setHorizontalTextPosition(JLabel.CENTER);

		/////////////////////////////////////////////////////////////
		// To be removed as this is duplicated code from another class

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

				// if the programme string element matches the first element in
				// module
				if (programme_Data[i].equals(modules.get(a))) {

					// Add that module to the add module method in programme
					// class
					module_codes.add(modules.get(a));

				} else {

				}
			}

		}

		/////////////////////////////////////////////////////////////

		// Setting the column names of the table
		String[] columnNames = { "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00",
				"18:00" };

		// Running the model and storing result into a 2d array
		IntVar[][] rowData = Main.solve();

		// Creating a new array for converting the array type
		Object[][] intArray = new Object[rowData.length][];
		for (int p = 0; p < rowData.length; p++) {
			intArray[p] = new Object[rowData[p].length];
			for (int j = 0; j < rowData[p].length; j++) {

				// Getting the string version of the course code
				intArray[p][j] = module_codes.get((int) rowData[p][j].value());

			}
		}

		// Creating the table for storing the results of the model
		JTable mainTable = new JTable(intArray, columnNames);
		// Setting the table to not be editable
		mainTable.setEnabled(false);
		// Creating a scroll pane to store the table
		JScrollPane scrollPane = new JScrollPane(mainTable);
		// Setting the size of the scroll pane
		scrollPane.setPreferredSize(new Dimension(600, 250));
		// Creating a row table using supplied class to add row names to the
		// table
		JTable rowTable = new RowNumberTable(mainTable);
		// Setting the row table to be transparent
		rowTable.setOpaque(false);
		// Adding the row table to the scroll panel
		scrollPane.setRowHeaderView(rowTable);
		// Setting the position of the row table
		scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, rowTable.getTableHeader());
		// Setting the row table to not be editable
		scrollPane.setEnabled(false);
		
		String[] fileExtensions = {
		         ".txt",
		         ".csv",
		         ".rtf",
		         ".docx",
		};
		
		JComboBox<String> extensionChoice = new JComboBox<String>(fileExtensions);
		//String choice = (String) extensionChoice.getSelectedItem().toString();

		try {

			// Creating a new grid bag layout
			GridBagConstraints gbc = new GridBagConstraints();
			// Creating the insets for the layout
			gbc.insets = new Insets(8, 8, 8, 8);
			// Setting the grid width for the layout
			gbc.gridwidth = gbc.REMAINDER;

			// Creating a new panel
			JPanel center = new JPanel();
			// Creating a new grid bag layout
			GridBagLayout thisLayout = new GridBagLayout();
			// Setting the layout of the panel to gbc
			center.setLayout(thisLayout);
			// Setting the panel to be transparent
			center.setOpaque(false);
			// Adding the scroll pane to the table
			center.add(scrollPane, gbc);

			/**
			 * creating the buttons for the panel
			 */

			{
				// Creating a new button
				backButton = new JButton();
				// Setting the foreground colour of the button
				backButton.setForeground(Color.BLACK);
				// Setting the size of the button
				backButton.setPreferredSize(new Dimension(100, 35));
				// Setting the text of the button
				backButton.setText("Back to Menu");
				// Setting the font and the size of the text
				backButton.setFont(new Font("Arial", Font.PLAIN, 12));
				// Setting the position of the button
				backButton.setHorizontalTextPosition(JButton.CENTER);
				// Setting the position of the button
				backButton.setVerticalTextPosition(JButton.CENTER);
			}
			{
				// Creating a new button
				printTXTButton = new JButton();
				// Setting the foreground colour of the button
				printTXTButton.setForeground(Color.BLACK);
				// Setting the size of the button
				printTXTButton.setPreferredSize(new Dimension(100, 35));
				// Setting the text of the button
				printTXTButton.setText("Save to file");
				// Setting the font and the size of the text
				printTXTButton.setFont(new Font("Arial", Font.PLAIN, 12));
				// Setting the position of the button
				printTXTButton.setHorizontalTextPosition(JButton.CENTER);
				// Setting the position of the button
				printTXTButton.setVerticalTextPosition(JButton.CENTER);
			}

			{
				// Creating a new button
				exitButton = new JButton();
				// Setting the foreground colour of the button
				exitButton.setForeground(Color.BLACK);
				// Setting the size of the button
				exitButton.setPreferredSize(new Dimension(100, 35));
				// Setting the text of the button
				exitButton.setText("Exit");
				// Setting the font and the size of the text
				exitButton.setFont(new Font("Arial", Font.PLAIN, 12));
				// Setting the position of the button
				exitButton.setHorizontalTextPosition(JButton.CENTER);
				// Setting the position of the button
				exitButton.setVerticalTextPosition(JButton.CENTER);
			}

			// Creating a new panel
			JPanel fileBox = new JPanel();
			// Setting the panel to be transparent
			fileBox.setOpaque(false);
			// Adding a button to the panel
			fileBox.setLayout(new FlowLayout());
			// Adding a button to the panel
			fileBox.add(extensionChoice, gbc);
			// Adding a button to the panel
			fileBox.add(printTXTButton, gbc);
			//Adding the panel into the panel
			center.add(fileBox, gbc);

			// Creating a new panel
			JPanel commandBox = new JPanel();
			// Setting the panel to be transparent
			commandBox.setOpaque(false);
			// Adding a button to the panel
			commandBox.setLayout(new FlowLayout());
			// Adding a button to the panel
			commandBox.add(backButton, gbc);
			// Adding a button to the panel
			commandBox.add(exitButton, gbc);

			// Creating a new panel
			JPanel titleBox = new JPanel();
			// Setting the panel to be transparent
			titleBox.setOpaque(false);
			// Setting the layout of the panel to be flow layout
			titleBox.setLayout(new FlowLayout());
			// Adding a label to the panel
			titleBox.add(label1);

			// Adding the panels to the main panel
			mainPanel.add(commandBox, BorderLayout.SOUTH);
			mainPanel.add(center, BorderLayout.CENTER);
			mainPanel.add(titleBox, BorderLayout.NORTH);

			// Setting the panel to be transparent
			commandBox.setOpaque(false);
			// Setting the panel to be transparent
			center.setOpaque(false);

		} catch (Exception e) {
			e.printStackTrace();
		}

		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Removing all of the contents from the frame
				MainFrame.mainFrame.getContentPane().removeAll();
				// Creating anew instance of TimeTableCreatorMenuView
				TimeTableCreatorMenuView x = new TimeTableCreatorMenuView();
				// Adding the instance of the panel to the frame
				MainFrame.mainFrame.add(x.buildTimeTableCreatorMenu(), BorderLayout.CENTER);
				// Re-painting the frame
				MainFrame.mainFrame.repaint();
				// Re-validating the frame
				MainFrame.mainFrame.revalidate();
				// Setting the file path to null
				TimeTableCreatorFileCohortView.CohortPath = null;
				// Setting the file path to null
				TimeTableCreatorFileModuleView.ModulePath = null;

			}
		});

		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Creating a dialogue to see if the user wants to quit the
				// application
				if (JOptionPane.showConfirmDialog(mainPanel, "Are you sure to exit the Program?", "Really Closing?",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					// Exit the application
					System.exit(0);
				}

			}
		});

		printTXTButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser chooser = new JFileChooser();
			    chooser.setCurrentDirectory(new File("/home/me/Documents"));
			    @SuppressWarnings("unused")
				int retrival = chooser.showSaveDialog(null);
				File file = new File(chooser.getSelectedFile() + (String) extensionChoice.getSelectedItem().toString());

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
							builder.append(intArray[i][j] + ",");
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
						builder.append(intArray[i][j] + ",");
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

		// Return the panel
		return mainPanel;

	}

	/**
	 * used to create the background image for the panel
	 */
	private Component createContent() throws IOException {
		// Create a background for the panel
		final ImageIcon icon = new ImageIcon(this.getClass().getResource("/Files/background.jpg"));

		@SuppressWarnings("serial")
		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				// Adding the image to the background
				g.drawImage(icon.getImage(), 0, 0, null);
			}
		};
		// Return the panel
		return panel;
	}

}