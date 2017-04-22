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

import javax.swing.BorderFactory;
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
	private JTable mainTable;

	/*
	 * constructor for the menuView
	 */

	public TimeTableCreatorTableView() {

	}

	// Method to import module names
	public ArrayList<String> importModulesNames() {

		BufferedReader br = null;
		String line = "";
		@SuppressWarnings("unused")
		String line2 = " ";
		String cvsSplitBy = ",";
		String csvFile2 = TimeTableCreatorFileModuleView.ModulePath;
		ArrayList<String> modules = new ArrayList<String>();

		try {

			br = new BufferedReader(new FileReader(csvFile2));
			while ((line = br.readLine()) != null) {

				String[] moduleArray = line.split(cvsSplitBy);
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

		return modules;
	}

	// Method to read cohort names
	public static String[] importCohortNames() {

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		//ArrayList<String> module_codes = new ArrayList<String>();
		//module_codes.add("BREAK");
		String csvFile = TimeTableCreatorFileCohortView.CohortPath;
		String[] programme_Data = null;

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

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

		return programme_Data;

	}

	// Method to compare saved names and store matches
	public Object[][] moduleCodeNameGenerator(String[] importCohortNames, ArrayList<String> importModulesNames)
			throws IOException {
		ArrayList<String> module_codes = new ArrayList<String>();
		module_codes.add("BREAK");

		for (int i = 0; i < importCohortNames.length; i++) {

			for (int a = 0; a < importModulesNames.size(); a++) {

				if (importCohortNames[i].equals(importModulesNames.get(a))) {

					module_codes.add(importModulesNames.get(a));

				} else {

				}
			}

		}

		IntVar[][] rowData = null;
		Boolean checkTable = false;
		try {
			checkTable = Main.validTimetableCheck();

		} catch (Exception e) {

		}
		try {
			if (checkTable == true) {
				long startTime = System.nanoTime();
				rowData = Main.solve();
				long endTime = System.nanoTime();
				long duration = (endTime - startTime);
				System.out.println("Generation Time: " + duration / 1000000 + " milliseconds");

			}
		} catch (Exception e) {
		}

		try {
			Object[][] moduleNameArrayGenerated = new Object[rowData.length][];
			for (int p = 0; p < rowData.length; p++) {
				moduleNameArrayGenerated[p] = new Object[rowData[p].length];
				for (int j = 0; j < rowData[p].length; j++) {

					// moduleNameArrayGenerated[p][j] = rowData[p][j].value();
					moduleNameArrayGenerated[p][j] = module_codes.get((int) rowData[p][j].value());

				}

			}
			return moduleNameArrayGenerated;

		} catch (Exception e) {

			return null;

		}

	}

	// Method to compare saved names and store matches
	public Object[][] moduleCodeNameGeneratorInt(String[] importCohortNames, ArrayList<String> importModulesNames)
			throws IOException {
		ArrayList<String> module_codes2 = new ArrayList<String>();
		module_codes2.add("BREAK");

		for (int i = 0; i < importCohortNames.length; i++) {

			for (int a = 0; a < importModulesNames.size(); a++) {

				if (importCohortNames[i].equals(importModulesNames.get(a))) {

					module_codes2.add(importModulesNames.get(a));

				} else {

				}
			}

		}

		IntVar[][] rowData2 = null;
		Boolean checkTable = false;
		try {
			checkTable = Main.validTimetableCheck();

		} catch (Exception e) {

		}
		try {
			if (checkTable == true) {
				rowData2 = Main.solve();

			}
		} catch (Exception e) {
		}

		try {
			Object[][] moduleNameArrayGeneratedInt = new Object[rowData2.length][];
			for (int p = 0; p < rowData2.length; p++) {
				moduleNameArrayGeneratedInt[p] = new Object[rowData2[p].length];
				for (int j = 0; j < rowData2[p].length; j++) {

					moduleNameArrayGeneratedInt[p][j] = rowData2[p][j].value();

				}

			}
			return moduleNameArrayGeneratedInt;

		} catch (Exception e) {

			return null;

		}

	}

	/**
	 * used to create a panel for the main menu
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("static-access")
	public JPanel buildTimeTableTimetableView() throws IOException {

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
		JButton exitButton = new JButton();
		JButton changeButton = new JButton();
		JButton keepButton = new JButton();
		JButton reRunButton = new JButton();

		// Creating a new label for the panel
		JLabel label1 = new JLabel("Generated Table");
		label1.setFont(new Font("Arial", Font.BOLD, 36));
		label1.setVerticalTextPosition(JLabel.CENTER);
		label1.setHorizontalTextPosition(JLabel.CENTER);

		// Creating a string array of the column names
		String[] columnNames = { "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00" };
		Object[][] rowData = { { "0", "0", "0", "0", "0", "0", "0", "0", "0" },
				{ "0", "0", "0", "0", "0", "0", "0", "0", "0" }, { "0", "0", "0", "0", "0", "0", "0", "0", "0" },
				{ "0", "0", "0", "0", "0", "0", "0", "0", "0" } };

		Object[][] moduleCodeNameGenerator = moduleCodeNameGenerator(importCohortNames(), importModulesNames());
		

		if (moduleCodeNameGenerator == null) {

			JOptionPane.showMessageDialog(mainPanel, "Problem found! Timetable not generated.", null,
					JOptionPane.PLAIN_MESSAGE);
			mainTable = new JTable(rowData, columnNames);

		} else {

			mainTable = new JTable(moduleCodeNameGenerator(importCohortNames(), importModulesNames()), columnNames);

		}

		mainTable.setEnabled(false);

		// Creating a scroll pane to store the table
		JScrollPane scrollPane = new JScrollPane(mainTable);
		scrollPane.setPreferredSize(new Dimension(600, 90));
		scrollPane.setEnabled(false);
		mainTable.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		String[] fileExtensions = { ".txt", ".csv", ".rtf", ".docx", };
		JComboBox<String> extensionChoice = new JComboBox<String>(fileExtensions);

		try {

			// Creating a new grid bag layout
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(8, 8, 8, 8);
			gbc.gridwidth = gbc.REMAINDER;

			// Creating a new panel
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
				printTXTButton.setText("Save to file");
				printTXTButton.setFont(new Font("Arial", Font.PLAIN, 12));
				printTXTButton.setHorizontalTextPosition(JButton.CENTER);
				printTXTButton.setVerticalTextPosition(JButton.CENTER);
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
			{
				changeButton = new JButton();
				changeButton.setForeground(Color.BLACK);
				changeButton.setPreferredSize(new Dimension(100, 35));
				changeButton.setText("Change");
				changeButton.setFont(new Font("Arial", Font.PLAIN, 12));
				changeButton.setHorizontalTextPosition(JButton.CENTER);
				changeButton.setVerticalTextPosition(JButton.CENTER);
			}
			{
				keepButton = new JButton();
				keepButton.setForeground(Color.BLACK);
				keepButton.setPreferredSize(new Dimension(100, 35));
				keepButton.setText("Keep");
				keepButton.setFont(new Font("Arial", Font.PLAIN, 12));
				keepButton.setHorizontalTextPosition(JButton.CENTER);
				keepButton.setVerticalTextPosition(JButton.CENTER);
			}
			{
				reRunButton = new JButton();
				reRunButton.setForeground(Color.BLACK);
				reRunButton.setPreferredSize(new Dimension(100, 35));
				reRunButton.setText("Re-Run");
				reRunButton.setFont(new Font("Arial", Font.PLAIN, 12));
				reRunButton.setHorizontalTextPosition(JButton.CENTER);
				reRunButton.setVerticalTextPosition(JButton.CENTER);
			}

			// Creating a new panel
			JPanel testBox = new JPanel();
			testBox.setOpaque(false);
			testBox.setLayout(new FlowLayout());
			testBox.add(changeButton, gbc);
			testBox.add(keepButton, gbc);
			testBox.add(reRunButton, gbc);
			center.add(testBox, gbc);

			// Creating a new panel
			JPanel fileBox = new JPanel();
			fileBox.setOpaque(false);
			fileBox.setLayout(new FlowLayout());
			fileBox.add(extensionChoice, gbc);
			fileBox.add(printTXTButton, gbc);
			center.add(fileBox, gbc);

			// Creating a new panel
			JPanel commandBox = new JPanel();
			commandBox.setOpaque(false);
			commandBox.setLayout(new FlowLayout());
			commandBox.add(backButton, gbc);
			commandBox.add(exitButton, gbc);

			// Creating a new panel
			JPanel titleBox = new JPanel();
			titleBox.setOpaque(false);
			titleBox.setLayout(new FlowLayout());
			titleBox.add(label1);

			// Adding the panels to the main panel
			mainPanel.add(commandBox, BorderLayout.SOUTH);
			mainPanel.add(center, BorderLayout.CENTER);
			mainPanel.add(titleBox, BorderLayout.NORTH);

			commandBox.setOpaque(false);
			center.setOpaque(false);

		} catch (Exception e) {
			e.printStackTrace();
		}

		/**
		 * used to create an action listener to open previous panel.
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

		/**
		 * used to create an action listener to exit application.
		 */
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (JOptionPane.showConfirmDialog(mainPanel, "Are you sure to exit the Program?", "Really Closing?",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					System.exit(0);
				}

			}
		});

		/**
		 * used to create an action listener to print timetable to a file.
		 */
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
					try {
						for (int i = 0; i < moduleCodeNameGenerator(importCohortNames(),
								importModulesNames()).length; i++) {
							for (int j = 0; j < moduleCodeNameGenerator(importCohortNames(),
									importModulesNames())[i].length; j++) {
								builder.append(
										moduleCodeNameGenerator(importCohortNames(), importModulesNames())[i][j] + ",");
								if (j < moduleCodeNameGenerator(importCohortNames(), importModulesNames())[i].length
										- 1)
									builder.append(" ");
							}
							builder.append("\n");
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
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
				try {
					for (int i = 0; i < moduleCodeNameGenerator(importCohortNames(),
							importModulesNames()).length; i++) {
						for (int j = 0; j < moduleCodeNameGenerator(importCohortNames(),
								importModulesNames())[i].length; j++) {
							builder.append(
									moduleCodeNameGenerator(importCohortNames(), importModulesNames())[i][j] + ",");
							if (j < moduleCodeNameGenerator(importCohortNames(), importModulesNames())[i].length - 1)
								builder.append(" ");
						}
						builder.append("\n");
					}

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				BufferedWriter writer = null;
				try {

					writer = new BufferedWriter(new FileWriter(file));
					// JOptionPane.showMessageDialog(mainPanel, "File Saved!",
					// null, JOptionPane.PLAIN_MESSAGE);

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

		Object[][] timetableReference = moduleCodeNameGeneratorInt(importCohortNames(), importModulesNames());
		
		Object selectedModuleObj = 0;
		int position = 0;
		
		mainTable.addMouseListener(new java.awt.event.MouseAdapter() {
			
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = mainTable.rowAtPoint(evt.getPoint());
				int col = mainTable.columnAtPoint(evt.getPoint());
				if (row >= 0 && col >= 0) {

					Object selectedModuleObj = timetableReference[row][col];
					int position = row * 9 + col;
					System.out.println("Position: " + position);
					System.out.println("Module: " + selectedModuleObj);

				}

			}
		});

		File fileKeepConstraints = new File("src/Files/keepConsraints.txt");
		File fileChangeConstraints = new File("src/Files/changeConsraints.txt");

		changeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!fileChangeConstraints.exists()) {
					try {
						fileChangeConstraints.createNewFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

				BufferedWriter bw = null;

				try {
					bw = new BufferedWriter(new FileWriter(fileChangeConstraints, true));
					bw.write(position + "," + selectedModuleObj);
					bw.newLine();
					bw.flush();
					 JOptionPane.showMessageDialog(mainPanel, "Module position will be changed!", null, JOptionPane.PLAIN_MESSAGE);
				} catch (IOException ioe) {
					ioe.printStackTrace();
				} finally {
					if (bw != null)
						try {
							bw.close();
						} catch (IOException ioe2) {
						}
				}

			}
		});

		keepButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!fileKeepConstraints.exists()) {
					try {
						fileKeepConstraints.createNewFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

				BufferedWriter bw = null;

				try {
					bw = new BufferedWriter(new FileWriter(fileKeepConstraints, true));
					bw.write(position + "," + selectedModuleObj);
					bw.newLine();
					bw.flush();
					 JOptionPane.showMessageDialog(mainPanel, "Module position will be saved!", null, JOptionPane.PLAIN_MESSAGE);
				} catch (IOException ioe) {
					ioe.printStackTrace();
				} finally {
					if (bw != null)
						try {
							bw.close();
						} catch (IOException ioe2) {
						}
				}

			}
		});

		reRunButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainTable = null;
				
				MainFrame.mainFrame.getContentPane().removeAll();
				TimeTableCreatorTableView gov = new TimeTableCreatorTableView();

				try {
					MainFrame.mainFrame.add(gov.buildTimeTableTimetableView(), BorderLayout.CENTER);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				MainFrame.mainFrame.repaint();
				MainFrame.mainFrame.revalidate();
				
				if (fileKeepConstraints.exists()) {
					fileKeepConstraints.delete();
					}
					
					if (!fileKeepConstraints.exists()) {
					fileChangeConstraints.delete();
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