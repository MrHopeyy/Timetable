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
import java.util.logging.Level;
import java.util.logging.Logger;
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

import Model.Module;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class TimeTableCreatorTableView {

	private JPanel mainPanel;
	

	/*
	 * constructor for the menuView
	 */
	public TimeTableCreatorTableView() {
		
		
		
	}

	/**
	 * used to create a panel for the main menu
	 */
	public JPanel buildTimeTableCreatorMenu() {

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
		JButton printButton = new JButton();

		JLabel label1 = new JLabel("Generated Table");
		label1.setFont(new Font("Arial", Font.BOLD, 36));
		label1.setVerticalTextPosition(JLabel.CENTER);
		label1.setHorizontalTextPosition(JLabel.CENTER);

		String[] columnNames = { "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00",
				"18:00" };
		Object[][] rowData = { { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" },
				{ "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" },
				{ "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" },
				{ "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" },
				{ "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" } };

		JTable mainTable = new JTable(rowData, columnNames);
		mainTable.setEnabled(false);
		JScrollPane scrollPane = new JScrollPane(mainTable);
		JTable rowTable = new RowNumberTable(mainTable);
		rowTable.setOpaque(false);
		scrollPane.setRowHeaderView(rowTable);
		scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, rowTable.getTableHeader());
		scrollPane.setEnabled(false);
		// scrollPane.setOpaque(false);
		// scrollPane.getViewport().setOpaque(false);

		// setting the layout of the main frame to a border layout

		try {

			// creating a new panel center and setting the properties
			JPanel center = new JPanel();
			center.setLayout(new BorderLayout());
			// center.setSize(720, 480);
			center.setOpaque(false);
			center.add(scrollPane, BorderLayout.CENTER);

			// creating a new gridbag layout for all of the buttons and adding
			// them
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(8, 8, 8, 8);
			// gbc.gridwidth = gbc.REMAINDER;

			Border thickBorder = new LineBorder(Color.BLACK, 4);
			/**
			 * creating the buttons for the panel
			 */
			{
				backButton = new JButton();
				backButton.setForeground(Color.BLACK);
				backButton.setPreferredSize(new Dimension(125, 50));
				backButton.setText("Back to Menu");
				backButton.setFont(new Font("Arial", Font.BOLD, 12));
				backButton.setHorizontalTextPosition(JButton.CENTER);
				backButton.setVerticalTextPosition(JButton.CENTER);
			}
			{
				printButton = new JButton();
				printButton.setForeground(Color.BLACK);
				printButton.setPreferredSize(new Dimension(125, 50));
				printButton.setText("Print Table");
				printButton.setFont(new Font("Arial", Font.BOLD, 12));
				printButton.setHorizontalTextPosition(JButton.CENTER);
				printButton.setVerticalTextPosition(JButton.CENTER);
			}

			JPanel commandBox = new JPanel();
			commandBox.setOpaque(false);
			commandBox.setLayout(new FlowLayout());
			commandBox.add(backButton, gbc);
			commandBox.add(printButton, gbc);

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

		printButton.addActionListener(new ActionListener() {
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
			    }

				StringBuilder builder = new StringBuilder();
				for(int i = 0; i < rowData.length; i++)
				{
				   for(int j = 0; j < rowData[i].length; j++)
				   {
				      builder.append(rowData[i][j]+"");
				      if(j < rowData[i].length - 1)
				         builder.append(" ");
				   }
				   builder.append("\n");
				}
				BufferedWriter writer = null;
				try {
					
					writer = new BufferedWriter(new FileWriter("/Users/AlexHope/TimeTable " + df.format(dateobj) + ".txt"));
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					writer.write(builder.toString());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}//save the string representation of the board
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