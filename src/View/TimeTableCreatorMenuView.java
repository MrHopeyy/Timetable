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
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TimeTableCreatorMenuView {

	// Creating a new panel
	private JPanel mainPanel;

	/*
	 * constructor for the menuView
	 */
	public TimeTableCreatorMenuView() {

	}

	/**
	 * used to create a panel for the main menu
	 */
	@SuppressWarnings("static-access")
	public JPanel buildTimeTableCreatorMenu() {

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
		JButton newModuleFieButton = new JButton();
		JButton newCohortFieButton = new JButton();
		JButton instButton = new JButton();
		JButton ExitButton = new JButton();

		// Creating a new label
		JLabel label1 = new JLabel("University Timetable Creator");
		label1.setFont(new Font("Arial", Font.BOLD, 36));
		label1.setVerticalTextPosition(JLabel.CENTER);
		label1.setHorizontalTextPosition(JLabel.CENTER);

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
				newTableButton = new JButton();
				newTableButton.setForeground(Color.BLACK);
				newTableButton.setPreferredSize(new Dimension(250, 50));
				newTableButton.setText("Create Timetable");
				newTableButton.setFont(new Font("Arial", Font.BOLD, 24));
				newTableButton.setHorizontalTextPosition(JButton.CENTER);
				newTableButton.setVerticalTextPosition(JButton.CENTER);
				center.add(newTableButton, gbc);
			}
			{
				newModuleFieButton = new JButton();
				newModuleFieButton.setForeground(Color.BLACK);
				newModuleFieButton.setPreferredSize(new Dimension(250, 50));
				newModuleFieButton.setText("Create Module File");
				newModuleFieButton.setFont(new Font("Arial", Font.BOLD, 24));
				newModuleFieButton.setHorizontalTextPosition(JButton.CENTER);
				newModuleFieButton.setVerticalTextPosition(JButton.CENTER);
				center.add(newModuleFieButton, gbc);
			}
			{
				newCohortFieButton = new JButton();
				newCohortFieButton.setForeground(Color.BLACK);
				newCohortFieButton.setPreferredSize(new Dimension(250, 50));
				newCohortFieButton.setText("Create Cohort File");
				newCohortFieButton.setFont(new Font("Arial", Font.BOLD, 24));
				newCohortFieButton.setHorizontalTextPosition(JButton.CENTER);
				newCohortFieButton.setVerticalTextPosition(JButton.CENTER);
				center.add(newCohortFieButton, gbc);
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
				ExitButton = new JButton();
				ExitButton.setForeground(Color.BLACK);
				ExitButton.setPreferredSize(new Dimension(250, 50));
				ExitButton.setText("Exit Program");
				ExitButton.setFont(new Font("Arial", Font.BOLD, 24));
				ExitButton.setHorizontalTextPosition(JButton.CENTER);
				ExitButton.setVerticalTextPosition(JButton.CENTER);
				center.add(ExitButton, gbc);
			}

			// Creating a new panel
			JPanel commandBox = new JPanel();
			commandBox.setOpaque(false);
			commandBox.setLayout(new FlowLayout());

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
		 * used to create an action listener to open next panel to create a
		 * timetable.
		 */
		newTableButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MainFrame.mainFrame.getContentPane().removeAll();
				TimeTableCreatorFileModuleView gov = new TimeTableCreatorFileModuleView();
				try {
					MainFrame.mainFrame.add(gov.buildTimeTableModuleView(), BorderLayout.CENTER);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				MainFrame.mainFrame.repaint();
				MainFrame.mainFrame.revalidate();
			}
		});

		/**
		 * used to create an action listener to open next panel to create a
		 * module file.
		 */
		newModuleFieButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MainFrame.mainFrame.getContentPane().removeAll();
				TimeTableCreatorFileModuleCreationView gov = new TimeTableCreatorFileModuleCreationView();
				try {
					MainFrame.mainFrame.add(gov.buildTimeTableCohortView(), BorderLayout.CENTER);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				MainFrame.mainFrame.repaint();
				MainFrame.mainFrame.revalidate();
			}
		});

		/**
		 * used to create an action listener to open next panel to create a new
		 * cohort file.
		 */
		newCohortFieButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MainFrame.mainFrame.getContentPane().removeAll();
				TimeTableCreatorFileCohortCreationView gov = new TimeTableCreatorFileCohortCreationView();
				try {
					MainFrame.mainFrame.add(gov.buildTimeTableCohortCreator(), BorderLayout.CENTER);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				MainFrame.mainFrame.repaint();
				MainFrame.mainFrame.revalidate();
			}
		});

		/**
		 * used to create an action listener to open next panel to see
		 * instructions.
		 */
		instButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MainFrame.mainFrame.getContentPane().removeAll();
				TimeTableCreatorInstView gov = new TimeTableCreatorInstView();
				MainFrame.mainFrame.add(gov.buildTimeTableInstructionView(), BorderLayout.CENTER);
				MainFrame.mainFrame.repaint();
				MainFrame.mainFrame.revalidate();

			}
		});

		/**
		 * used to create an action listener to exit programme.
		 */
		ExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (JOptionPane.showConfirmDialog(mainPanel, "Are you sure to exit the Program?", "Really Closing?",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					try {
						File f = new File("src/Files/output.txt");
						@SuppressWarnings("unused")
						boolean deleted = f.delete();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Something Went Wrong!", " ", JOptionPane.ERROR_MESSAGE);
					}
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