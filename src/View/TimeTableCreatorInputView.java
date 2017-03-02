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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class TimeTableCreatorInputView {

	public static int intValueHMD = 0;
	public static int intValueHMH = 0;
	// public static int intValueST = 0;
	// public static int intValueFT = 0;
	public static int intValueHMM = 0;

	private JPanel mainPanel;

	/*
	 * constructor for the menuView
	 */
	public TimeTableCreatorInputView() {

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
		JButton nextButton = new JButton();

		JLabel label1 = new JLabel("Input Values");
		label1.setFont(new Font("Arial", Font.BOLD, 36));
		label1.setVerticalTextPosition(JLabel.CENTER);
		label1.setHorizontalTextPosition(JLabel.CENTER);

		JLabel comboLabel1 = new JLabel("How Many Days:");
		comboLabel1.setFont(new Font("Arial", Font.BOLD, 12));
		comboLabel1.setVerticalTextPosition(JLabel.CENTER);
		comboLabel1.setHorizontalTextPosition(JLabel.CENTER);
		
		JLabel comboLabel22 = new JLabel("Total Hours in Day:");
		comboLabel22.setFont(new Font("Arial", Font.BOLD, 12));
		comboLabel22.setVerticalTextPosition(JLabel.CENTER);
		comboLabel22.setHorizontalTextPosition(JLabel.CENTER);

//		JLabel comboLabel2 = new JLabel("Day Start Time:");
//		comboLabel2.setFont(new Font("Arial", Font.BOLD, 12));
//		comboLabel2.setVerticalTextPosition(JLabel.CENTER);
//		comboLabel2.setHorizontalTextPosition(JLabel.CENTER);
//
//		JLabel comboLabel3 = new JLabel("Day Finish Time:");
//		comboLabel3.setFont(new Font("Arial", Font.BOLD, 12));
//		comboLabel3.setVerticalTextPosition(JLabel.CENTER);
//		comboLabel3.setHorizontalTextPosition(JLabel.CENTER);

		JLabel comboLabel4 = new JLabel("How Many Modules:");
		comboLabel4.setFont(new Font("Arial", Font.BOLD, 12));
		comboLabel4.setVerticalTextPosition(JLabel.CENTER);
		comboLabel4.setHorizontalTextPosition(JLabel.CENTER);

		Integer[] comboDays = new Integer[] { 0, 1, 2, 3, 4, 5 };
		JComboBox<Integer> howManyDays = new JComboBox<Integer>(comboDays);

		Integer[] comboHours = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23 };
		JComboBox<Integer> howManyHours = new JComboBox<Integer>(comboHours);
		
//		Integer[] comboSH = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23 };
//		JComboBox<Integer> startHour = new JComboBox<Integer>(comboSH);
//
//		Integer[] comboFH = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24 };
//		JComboBox<Integer> finishHour = new JComboBox<Integer>(comboFH);

		Integer[] comboModules = new Integer[] { 0, 1, 2, 3, 4, 5};
		JComboBox<Integer> howManyModules = new JComboBox<Integer>(comboModules);

		// setting the layout of the main frame to a border layout

		try {

			// creating a new panel center and setting the properties
			JPanel center = new JPanel();
			GridBagLayout thisLayout = new GridBagLayout();
			center.setLayout(thisLayout);
			center.setSize(720, 480);
			center.setOpaque(false);

			JPanel center1 = new JPanel();
			GridBagLayout thisLayout1 = new GridBagLayout();
			center1.setLayout(thisLayout1);
			center1.setSize(180, 360);
			center1.setOpaque(false);

			JPanel center2 = new JPanel();
			GridBagLayout thisLayout2 = new GridBagLayout();
			center2.setLayout(thisLayout2);
			center2.setSize(180, 360);
			center2.setOpaque(false);

			JPanel center3 = new JPanel();
			GridBagLayout thisLayout3 = new GridBagLayout();
			center3.setLayout(thisLayout3);
			center3.setSize(180, 360);
			center3.setOpaque(false);

			JPanel center4 = new JPanel();
			GridBagLayout thisLayout4 = new GridBagLayout();
			center4.setLayout(thisLayout1);
			center4.setSize(180, 360);
			center4.setOpaque(false);

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
				// ExitButton.setIcon(new
				// ImageIcon(this.getClass().getResource("/Files/button5.jpg")));
				backButton.setText("Back");
				backButton.setFont(new Font("Arial", Font.BOLD, 24));
				backButton.setHorizontalTextPosition(JButton.CENTER);
				backButton.setVerticalTextPosition(JButton.CENTER);
			}
			{
				nextButton = new JButton();
				nextButton.setForeground(Color.BLACK);
				nextButton.setPreferredSize(new Dimension(125, 50));
				// ExitButton.setIcon(new
				// ImageIcon(this.getClass().getResource("/Files/button5.jpg")));
				nextButton.setText("Next");
				nextButton.setFont(new Font("Arial", Font.BOLD, 24));
				nextButton.setHorizontalTextPosition(JButton.CENTER);
				nextButton.setVerticalTextPosition(JButton.CENTER);
			}

			JPanel commandBox = new JPanel();
			commandBox.setOpaque(false);
			commandBox.setLayout(new FlowLayout());
			commandBox.add(backButton, gbc);
			commandBox.add(nextButton, gbc);

			center.add(center1, gbc);
			center.add(center2, gbc);
			center.add(center3, gbc);
			center.add(center4, gbc);

			gbc.gridwidth = gbc.REMAINDER;
			center1.add(comboLabel1, gbc);
			center1.add(howManyDays, gbc);
			
			gbc.gridwidth = gbc.REMAINDER;
			center2.add(comboLabel22, gbc);
			center2.add(howManyHours, gbc);

//			center2.add(comboLabel2, gbc);
//			center2.add(startHour, gbc);
//
//			center3.add(comboLabel3, gbc);
//			center3.add(finishHour, gbc);

			center3.add(comboLabel4, gbc);
			center3.add(howManyModules, gbc);

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
				TimeTableCreatorMenuView gov = new TimeTableCreatorMenuView();
				MainFrame.mainFrame.add(gov.buildTimeTableCreatorMenu(), BorderLayout.CENTER);
				MainFrame.mainFrame.repaint();
				MainFrame.mainFrame.revalidate();

			}
		});

		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Int Values From The Combo Boxes
				intValueHMD = (Integer) howManyDays.getSelectedItem();
				intValueHMH = (Integer) howManyHours.getSelectedItem();
//				intValueST = (Integer) startHour.getSelectedItem();
//				intValueFT = (Integer) finishHour.getSelectedItem();
				intValueHMM = (Integer) howManyModules.getSelectedItem();

				if (intValueHMD == 0) {

					JOptionPane.showMessageDialog(mainPanel, "Please Select How Many Days You Want!", "Attention!",
							JOptionPane.WARNING_MESSAGE);
					
				}
					
					else if (intValueHMD == 0) {

						JOptionPane.showMessageDialog(mainPanel,
								"Please Select How Many Hours You Want!", "Attention!",
								JOptionPane.WARNING_MESSAGE);

					}

//				} 
					//else if (intValueST == 0) {
//
//					JOptionPane.showMessageDialog(mainPanel, "Please Select a Start Time!", "Attention!",
//							JOptionPane.WARNING_MESSAGE);
//
//				} else if (intValueFT == 0) {
//
//					JOptionPane.showMessageDialog(mainPanel, "Please Select a Finish Time!", "Attention!",
//							JOptionPane.WARNING_MESSAGE);
//
//				} else if (intValueHMM == 0) {

//					JOptionPane.showMessageDialog(mainPanel, "Please Select How Many Modules You Want!", "Attention!",
//							JOptionPane.WARNING_MESSAGE);

//				} else if (intValueST >= intValueFT) {
//
//					JOptionPane.showMessageDialog(mainPanel,
//							"Please Select a Finish Time Atleast an Hour Later Than The Start Time!", "Attention!",
//							JOptionPane.WARNING_MESSAGE);
//
//				} 
					
					else {

					MainFrame.mainFrame.getContentPane().removeAll();
					TimeTableCreatorModuleView gov = new TimeTableCreatorModuleView();
					MainFrame.mainFrame.add(gov.buildTimeTableCreatorMenu(), BorderLayout.CENTER);
					MainFrame.mainFrame.repaint();
					MainFrame.mainFrame.revalidate();

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