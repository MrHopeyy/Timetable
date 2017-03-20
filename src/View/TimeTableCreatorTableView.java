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
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import com.google.ortools.constraintsolver.IntVar;
import com.google.ortools.constraintsolver.Solver;

import Model.Main;
import Model.Module;
import Model.Programme;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class TimeTableCreatorTableView {

	private JPanel mainPanel;
	// Variable int for the length of a single day.
	public final static int N_HOURS = 10;
	// Variable int for how many days in a week.
	public final static int N_DAYS = 4;
	public static BufferedReader br = null;
	public static String line = "";
	public static String line2 = " ";
	public static String cvsSplitBy = ",";

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
	public JPanel buildTimeTableCreatorMenu() throws IOException {

		final int blankSpace = 6; // blank at edge of panels

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

		String[] columnNames = { "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00",
				"18:00" };
		Object[][] rowData = { { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" },
				{ "0", "1", "1", "0", "0", "0", "0", "0", "0", "0" },
				{ "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" },
				{ "0", "0", "0", "2", "2", "0", "0", "0", "0", "0" } };

		JTable mainTable = new JTable(rowData, columnNames);
		mainTable.setEnabled(false);
		JScrollPane scrollPane = new JScrollPane(mainTable);
		scrollPane.setPreferredSize(new Dimension(500, 100));
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

			Border thickBorder = new LineBorder(Color.BLACK, 4);
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
				printCVSButton.setText("Save to .CVS");
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
					for (int i = 0; i < rowData.length; i++) {
						for (int j = 0; j < rowData[i].length; j++) {
							builder.append(rowData[i][j] + "");
							if (j < rowData[i].length - 1)
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
				for (int i = 0; i < rowData.length; i++) {
					for (int j = 0; j < rowData[i].length; j++) {
						builder.append(rowData[i][j] + "");
						if (j < rowData[i].length - 1)
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

				File file = new File("/Users/AlexHope/TimeTable " + df.format(dateobj) + ".cvs");

				if (!file.exists()) {
					try {
						file.createNewFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {

					StringBuilder builder = new StringBuilder();
					for (int i = 0; i < rowData.length; i++) {
						for (int j = 0; j < rowData[i].length; j++) {
							builder.append(rowData[i][j] + "");
							if (j < rowData[i].length - 1)
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
				for (int i = 0; i < rowData.length; i++) {
					for (int j = 0; j < rowData[i].length; j++) {
						builder.append(rowData[i][j] + "");
						if (j < rowData[i].length - 1)
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