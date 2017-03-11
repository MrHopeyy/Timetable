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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class TimeTableCreatorMenuView {

	private JPanel mainPanel;

	/*
	 * constructor for the menuView
	 */
	public TimeTableCreatorMenuView() {

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
		JButton newTableButton = new JButton();
		JButton instButton = new JButton();
		JButton aboutButton = new JButton();
		JButton ExitButton = new JButton();

		JLabel label1 = new JLabel("University Timetable Creator");
		label1.setFont(new Font("Arial", Font.BOLD, 36));
		label1.setVerticalTextPosition(JLabel.CENTER);
		label1.setHorizontalTextPosition(JLabel.CENTER);

		// setting the layout of the main frame to a border layout

		try {

			// creating a new panel center and setting the properties
			JPanel center = new JPanel();
			GridBagLayout thisLayout = new GridBagLayout();
			center.setLayout(thisLayout);
			center.setSize(720, 480);
			center.setOpaque(false);

			// creating a new gridbag layout for all of the buttons and adding
			// them
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(8, 8, 8, 8);
			gbc.gridwidth = gbc.REMAINDER;

			Border thickBorder = new LineBorder(Color.BLACK, 4);
			/**
			 * creating the buttons for the panel
			 */
			{
				newTableButton = new JButton();
				newTableButton.setForeground(Color.BLACK);
				newTableButton.setPreferredSize(new Dimension(250, 50));
				// exitGame.setIcon(new
				// ImageIcon(this.getClass().getResource("/Files/button5.jpg")));
				newTableButton.setText("Create New Table");
				newTableButton.setFont(new Font("Arial", Font.BOLD, 24));
				newTableButton.setHorizontalTextPosition(JButton.CENTER);
				newTableButton.setVerticalTextPosition(JButton.CENTER);

				center.add(newTableButton, gbc);
			}
			{
				instButton = new JButton();
				instButton.setForeground(Color.BLACK);
				instButton.setPreferredSize(new Dimension(250, 50));
				instButton.setText("Instructions");
				instButton.setFont(new Font("Arial", Font.BOLD, 24));
				instButton.setHorizontalTextPosition(JButton.CENTER);
				instButton.setVerticalTextPosition(JButton.CENTER);

				center.add(instButton, gbc);
			}
			{
				aboutButton = new JButton();
				aboutButton.setForeground(Color.BLACK);
				aboutButton.setPreferredSize(new Dimension(250, 50));
				aboutButton.setText("About");
				aboutButton.setFont(new Font("Arial", Font.BOLD, 24));
				aboutButton.setHorizontalTextPosition(JButton.CENTER);
				aboutButton.setVerticalTextPosition(JButton.CENTER);

				//center.add(aboutButton, gbc);
			}
			{
				ExitButton = new JButton();
				ExitButton.setForeground(Color.BLACK);
				ExitButton.setPreferredSize(new Dimension(250, 50));
				// ExitButton.setIcon(new
				// ImageIcon(this.getClass().getResource("/Files/button5.jpg")));
				ExitButton.setText("Exit Program");
				ExitButton.setFont(new Font("Arial", Font.BOLD, 24));
				ExitButton.setHorizontalTextPosition(JButton.CENTER);
				ExitButton.setVerticalTextPosition(JButton.CENTER);

				center.add(ExitButton, gbc);
			}
			JPanel commandBox = new JPanel();
			commandBox.setOpaque(false);
			commandBox.setLayout(new FlowLayout());

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
		newTableButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MainFrame.mainFrame.getContentPane().removeAll();
				TimeTableCreatorFileModuleView gov = new TimeTableCreatorFileModuleView();
				MainFrame.mainFrame.add(gov.buildTimeTableCreatorMenu(), BorderLayout.CENTER);
				MainFrame.mainFrame.repaint();
				MainFrame.mainFrame.revalidate();

			}
		});

		instButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MainFrame.mainFrame.getContentPane().removeAll();
				TimeTableCreatorInstView gov = new TimeTableCreatorInstView();
				MainFrame.mainFrame.add(gov.buildTimeTableCreatorMenu(), BorderLayout.CENTER);
				MainFrame.mainFrame.repaint();
				MainFrame.mainFrame.revalidate();

			}
		});

		aboutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MainFrame.mainFrame.getContentPane().removeAll();
				TimeTableCreatorAboutView gov = new TimeTableCreatorAboutView();
				MainFrame.mainFrame.add(gov.buildTimeTableCreatorMenu(), BorderLayout.CENTER);
				MainFrame.mainFrame.repaint();
				MainFrame.mainFrame.revalidate();

			}
		});

		ExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (JOptionPane.showConfirmDialog(mainPanel, "Are you sure to exit the Program?", "Really Closing?",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					System.exit(0);
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