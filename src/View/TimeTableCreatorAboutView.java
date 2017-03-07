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
import java.io.FileReader;
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
import javax.swing.text.JTextComponent;

public class TimeTableCreatorAboutView {

	private JTextArea jTextArea1;
	private JPanel mainPanel;

	/**
	 * Repaints the MainFrame to create an about view
	 */
	public TimeTableCreatorAboutView() {

	}

	/**
	 * used to create a panel for the main menu
	 */
	public JPanel buildTimeTableCreatorMenu() {

		final int blankSpace = 6;

		try {
			mainPanel = (JPanel) createContent();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		/**
		 * Creating the Panel
		 */
		mainPanel.setVisible(true);
		mainPanel.setSize(720, 480);
		mainPanel.setLayout(new BorderLayout());

		/**
		 * Creating the BackButton
		 */
		JButton backButton = new JButton();

		/**
		 * Creating the main heading label
		 */
		JLabel label1 = new JLabel("About");
		label1.setFont(new Font("Arial", Font.BOLD, 36));
		label1.setVerticalTextPosition(JLabel.CENTER);
		label1.setHorizontalTextPosition(JLabel.CENTER);

		/**
		 * Creating a jtext area to hold the text paragraph
		 */

		jTextArea1 = new JTextArea("This tool is an application that i have created for my final year project. "
				+ System.lineSeparator() + "This tool is for the perpose of creating timetabbles for universitys."
				+ System.lineSeparator() + System.lineSeparator()
				+ "It uses constraint based programming to sort the modlues into feasable and optimal time slots."
				+ System.lineSeparator() + System.lineSeparator() + "Created by Alex Hope");

		jTextArea1.setForeground(Color.BLACK);
		jTextArea1.setFont(new Font("Arial", Font.BOLD, 18));
		jTextArea1.setEditable(false);
		jTextArea1.setLineWrap(true);
		jTextArea1.setWrapStyleWord(true);
		jTextArea1.setOpaque(false);

		// Creating a JScroller to contain the textarea
		JScrollPane listScroller = new JScrollPane(jTextArea1);
		listScroller.setPreferredSize(new Dimension(600, 200));
		listScroller.setMinimumSize(new Dimension(600, 200));
		listScroller.setBorder(null);
		listScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		listScroller.setOpaque(false);
		// setting the layout of the main frame to a border layout

		try {

			/**
			 * Creating a panel called centre to be added centre in the box
			 * layout.
			 */
			JPanel center = new JPanel();
			GridBagLayout thisLayout = new GridBagLayout();
			center.setLayout(thisLayout);
			center.setSize(720, 480);
			center.setOpaque(false);

			/**
			 * Gridbag used to add the components vertically
			 */
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(8, 8, 8, 8);
			gbc.gridwidth = gbc.REMAINDER;
			Border thickBorder = new LineBorder(Color.BLACK, 4);

			/**
			 * creating all of the buttons for the panel
			 */
			{
				backButton = new JButton();
				backButton.setForeground(Color.BLACK);
				backButton.setPreferredSize(new Dimension(250, 50));
				backButton.setText("Back to Menu");
				backButton.setFont(new Font("Arial", Font.BOLD, 24));
				backButton.setHorizontalTextPosition(JButton.CENTER);
				backButton.setVerticalTextPosition(JButton.CENTER);
			}

			JPanel commandBox = new JPanel();
			commandBox.setOpaque(false);
			commandBox.setLayout(new FlowLayout());
			commandBox.add(backButton, gbc);

			JPanel titleBox = new JPanel();
			titleBox.setOpaque(false);
			titleBox.setLayout(new FlowLayout());
			titleBox.add(label1);

			mainPanel.add(commandBox, BorderLayout.SOUTH);
			mainPanel.add(center, BorderLayout.CENTER);
			mainPanel.add(titleBox, BorderLayout.NORTH);

			center.add(listScroller, gbc);
			commandBox.setOpaque(false);
			center.setOpaque(false);
			jTextArea1.setOpaque(false);
			listScroller.setOpaque(false);
			listScroller.getViewport().setOpaque(false);

		} catch (Exception e) {
			e.printStackTrace();
		}

		/**
		 * Creating action listeners to perform an action when button is
		 * pressed.
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

		return mainPanel;

	}

	/**
	 * Creating a component to add the background image for the panel
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