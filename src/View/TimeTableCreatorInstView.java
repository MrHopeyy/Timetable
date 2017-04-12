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
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TimeTableCreatorInstView {

	// Creating a new panel
	private JPanel mainPanel;

	/*
	 * constructor for the menuView
	 */
	public TimeTableCreatorInstView() {

	}

	/**
	 * used to create a panel for the main menu
	 */
	@SuppressWarnings("static-access")
	public JPanel buildTimeTableInstructionView() {

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
		JButton tableHelpButton = new JButton();
		JButton moduleHelpButton = new JButton();
		JButton cohortHelpButton = new JButton();

		// Creating a new label
		JLabel label1 = new JLabel("Instructions");
		label1.setFont(new Font("Arial", Font.BOLD, 36));
		label1.setVerticalTextPosition(JLabel.CENTER);
		label1.setHorizontalTextPosition(JLabel.CENTER);

		// Creating a new text area and setting the text
		JTextArea textArea;
		textArea = new JTextArea("To make a new timetable, you require two files:" + "\n\n"
				+ "- A file containing modules with there module code, intro hours and total hours." + "\n\n"
				+ "- A file containing the module codes that you with to populate the timetable with.");
		textArea.setForeground(Color.BLACK);
		textArea.setFont(new Font("Arial", ~Font.BOLD, 22));
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);

		// Creating a list scroller for the text area
		JScrollPane listScroller = new JScrollPane(textArea);
		listScroller.setPreferredSize(new Dimension(600, 250));
		listScroller.setMinimumSize(new Dimension(600, 250));
		listScroller.setBorder(null);
		listScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		textArea.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		listScroller.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		try {

			// Creating a new panel centre and setting
			JPanel center = new JPanel();
			GridBagLayout thisLayout = new GridBagLayout();
			center.setLayout(thisLayout);
			center.setSize(720, 480);
			center.setOpaque(false);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(8, 8, 8, 8);
			gbc.gridwidth = gbc.REMAINDER;

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
				tableHelpButton = new JButton();
				tableHelpButton.setForeground(Color.BLACK);
				tableHelpButton.setPreferredSize(new Dimension(100, 35));
				tableHelpButton.setText("Timetable Help");
				tableHelpButton.setFont(new Font("Arial", Font.PLAIN, 12));
				tableHelpButton.setHorizontalTextPosition(JButton.CENTER);
				tableHelpButton.setVerticalTextPosition(JButton.CENTER);
			}
			{
				moduleHelpButton = new JButton();
				moduleHelpButton.setForeground(Color.BLACK);
				moduleHelpButton.setPreferredSize(new Dimension(100, 35));
				moduleHelpButton.setText("Module Help");
				moduleHelpButton.setFont(new Font("Arial", Font.PLAIN, 12));
				moduleHelpButton.setHorizontalTextPosition(JButton.CENTER);
				moduleHelpButton.setVerticalTextPosition(JButton.CENTER);
			}
			{
				cohortHelpButton = new JButton();
				cohortHelpButton.setForeground(Color.BLACK);
				cohortHelpButton.setPreferredSize(new Dimension(100, 35));
				cohortHelpButton.setText("Cohort Help");
				cohortHelpButton.setFont(new Font("Arial", Font.PLAIN, 12));
				cohortHelpButton.setHorizontalTextPosition(JButton.CENTER);
				cohortHelpButton.setVerticalTextPosition(JButton.CENTER);
			}

			// Creating a new panel
			JPanel commandBox = new JPanel();
			commandBox.setOpaque(false);
			commandBox.setLayout(new FlowLayout());
			commandBox.add(backButton, gbc);

			// Creating a new panel
			JPanel titleBox = new JPanel();
			titleBox.setOpaque(false);
			titleBox.setLayout(new FlowLayout());
			titleBox.add(label1);
			center.add(listScroller, gbc);

			// Creating a new panel
			JPanel fileBox = new JPanel();
			fileBox.setOpaque(false);
			fileBox.setLayout(new FlowLayout());
			fileBox.add(tableHelpButton, gbc);
			fileBox.add(moduleHelpButton, gbc);
			fileBox.add(cohortHelpButton, gbc);
			center.add(fileBox, gbc);

			// Adding the panels to the main panel
			mainPanel.add(commandBox, BorderLayout.SOUTH);
			mainPanel.add(center, BorderLayout.CENTER);
			mainPanel.add(titleBox, BorderLayout.NORTH);

			commandBox.setOpaque(false);
			center.setOpaque(false);
			//textArea.setOpaque(false);
			//listScroller.setOpaque(false);
			//listScroller.getViewport().setOpaque(false);

		} catch (Exception e) {
			e.printStackTrace();
		}

		/**
		 * used to create an action listener to to previous panel.
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

		tableHelpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				textArea.setText("");
				textArea.setText("To make a new timetable, you require two files:" + "\n\n"
						+ "- A file containing modules with there module code, intro hours and total hours." + "\n\n"
						+ "- A file containing the module codes that you with to populate the timetable with.");

			}
		});

		moduleHelpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				textArea.setText("");
				textArea.setText("Example of module format:" + "\n\n" + "CS1111,1,5" + "\n" + "CS3214,4,6" + "\n"
						+ "CS2330,0,8" + "\n" + "CS1070,5,6" + "\n" + "CS2121,2,5");

			}
		});

		cohortHelpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				textArea.setText("");
				textArea.setText("Example of cohort format:" + "\n\n" + "CS1111,CS2020,CS3300,CS2100,CS1240");

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