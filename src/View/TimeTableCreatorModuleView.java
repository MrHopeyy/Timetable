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
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class TimeTableCreatorModuleView {

	private JPanel mainPanel;

	/*
	 * constructor for the menuView
	 */
	public TimeTableCreatorModuleView() {

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

		// Module 1

		JLabel label1 = new JLabel("Input Modules Details");
		label1.setFont(new Font("Arial", Font.BOLD, 36));
		label1.setVerticalTextPosition(JLabel.CENTER);
		label1.setHorizontalTextPosition(JLabel.CENTER);

		JLabel comboLabel1 = new JLabel("Module 1 Code:");
		comboLabel1.setFont(new Font("Arial", Font.BOLD, 12));
		comboLabel1.setVerticalTextPosition(JLabel.CENTER);
		comboLabel1.setHorizontalTextPosition(JLabel.CENTER);

		JLabel comboLabel2 = new JLabel("Module 1 Room:");
		comboLabel2.setFont(new Font("Arial", Font.BOLD, 12));
		comboLabel2.setVerticalTextPosition(JLabel.CENTER);
		comboLabel2.setHorizontalTextPosition(JLabel.CENTER);

		JLabel comboLabel3 = new JLabel("Module 1 Tutor:");
		comboLabel3.setFont(new Font("Arial", Font.BOLD, 12));
		comboLabel3.setVerticalTextPosition(JLabel.CENTER);
		comboLabel3.setHorizontalTextPosition(JLabel.CENTER);

		JLabel comboLabel4 = new JLabel("Intro Hours:");
		comboLabel4.setFont(new Font("Arial", Font.BOLD, 12));
		comboLabel4.setVerticalTextPosition(JLabel.CENTER);
		comboLabel4.setHorizontalTextPosition(JLabel.CENTER);

		JLabel comboLabel5 = new JLabel("Total Hours:");
		comboLabel5.setFont(new Font("Arial", Font.BOLD, 12));
		comboLabel5.setVerticalTextPosition(JLabel.CENTER);
		comboLabel5.setHorizontalTextPosition(JLabel.CENTER);

		JTextField moduleName = new JTextField(10);
		JTextField moduleTutorName = new JTextField(10);
		JTextField moduleRoom = new JTextField(10);

		Integer[] comboIntro = new Integer[] { 0, 1, 2, 3, 4, 5 };
		JComboBox<Integer> howManyIntroHours = new JComboBox<Integer>(comboIntro);

		Integer[] comboTotal = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
				20 };
		JComboBox<Integer> totalHours = new JComboBox<Integer>(comboTotal);

		// End Module 1

		// Module 2

		JLabel comboLabel21 = new JLabel("Module 2 Code:");
		comboLabel21.setFont(new Font("Arial", Font.BOLD, 12));
		comboLabel21.setVerticalTextPosition(JLabel.CENTER);
		comboLabel21.setHorizontalTextPosition(JLabel.CENTER);

		JLabel comboLabel22 = new JLabel("Module 2 Room:");
		comboLabel22.setFont(new Font("Arial", Font.BOLD, 12));
		comboLabel22.setVerticalTextPosition(JLabel.CENTER);
		comboLabel22.setHorizontalTextPosition(JLabel.CENTER);

		JLabel comboLabel23 = new JLabel("Module 2 Tutor:");
		comboLabel23.setFont(new Font("Arial", Font.BOLD, 12));
		comboLabel23.setVerticalTextPosition(JLabel.CENTER);
		comboLabel23.setHorizontalTextPosition(JLabel.CENTER);

		JLabel comboLabel24 = new JLabel("Intro Hours:");
		comboLabel24.setFont(new Font("Arial", Font.BOLD, 12));
		comboLabel24.setVerticalTextPosition(JLabel.CENTER);
		comboLabel24.setHorizontalTextPosition(JLabel.CENTER);

		JLabel comboLabel25 = new JLabel("Total Hours:");
		comboLabel25.setFont(new Font("Arial", Font.BOLD, 12));
		comboLabel25.setVerticalTextPosition(JLabel.CENTER);
		comboLabel25.setHorizontalTextPosition(JLabel.CENTER);

		JTextField moduleName2 = new JTextField(10);
		JTextField moduleTutorName2 = new JTextField(10);
		JTextField moduleRoom2 = new JTextField(10);

		Integer[] comboIntro2 = new Integer[] { 0, 1, 2, 3, 4, 5 };
		JComboBox<Integer> howManyIntroHours2 = new JComboBox<Integer>(comboIntro2);

		Integer[] comboTotal2 = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
				20 };
		JComboBox<Integer> totalHours2 = new JComboBox<Integer>(comboTotal2);

		// End Module 2

		// Module 3

		JLabel comboLabel31 = new JLabel("Module 3 Code:");
		comboLabel31.setFont(new Font("Arial", Font.BOLD, 12));
		comboLabel31.setVerticalTextPosition(JLabel.CENTER);
		comboLabel31.setHorizontalTextPosition(JLabel.CENTER);

		JLabel comboLabel32 = new JLabel("Module 3 Room:");
		comboLabel32.setFont(new Font("Arial", Font.BOLD, 12));
		comboLabel32.setVerticalTextPosition(JLabel.CENTER);
		comboLabel32.setHorizontalTextPosition(JLabel.CENTER);

		JLabel comboLabel33 = new JLabel("Module 3 Tutor:");
		comboLabel33.setFont(new Font("Arial", Font.BOLD, 12));
		comboLabel33.setVerticalTextPosition(JLabel.CENTER);
		comboLabel33.setHorizontalTextPosition(JLabel.CENTER);

		JLabel comboLabel34 = new JLabel("Intro Hours:");
		comboLabel34.setFont(new Font("Arial", Font.BOLD, 12));
		comboLabel34.setVerticalTextPosition(JLabel.CENTER);
		comboLabel34.setHorizontalTextPosition(JLabel.CENTER);

		JLabel comboLabel35 = new JLabel("Total Hours:");
		comboLabel35.setFont(new Font("Arial", Font.BOLD, 12));
		comboLabel35.setVerticalTextPosition(JLabel.CENTER);
		comboLabel35.setHorizontalTextPosition(JLabel.CENTER);

		JTextField moduleName3 = new JTextField(10);
		JTextField moduleTutorName3 = new JTextField(10);
		JTextField moduleRoom3 = new JTextField(10);

		Integer[] comboIntro3 = new Integer[] { 0, 1, 2, 3, 4, 5 };
		JComboBox<Integer> howManyIntroHours3 = new JComboBox<Integer>(comboIntro3);

		Integer[] comboTotal3 = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
				20 };
		JComboBox<Integer> totalHours3 = new JComboBox<Integer>(comboTotal3);

		// End Module 3

		// Module 4

		JLabel comboLabel41 = new JLabel("Module 4 Code:");
		comboLabel41.setFont(new Font("Arial", Font.BOLD, 12));
		comboLabel41.setVerticalTextPosition(JLabel.CENTER);
		comboLabel41.setHorizontalTextPosition(JLabel.CENTER);

		JLabel comboLabel42 = new JLabel("Module 4 Room:");
		comboLabel42.setFont(new Font("Arial", Font.BOLD, 12));
		comboLabel42.setVerticalTextPosition(JLabel.CENTER);
		comboLabel42.setHorizontalTextPosition(JLabel.CENTER);

		JLabel comboLabel43 = new JLabel("Module 4 Tutor:");
		comboLabel43.setFont(new Font("Arial", Font.BOLD, 12));
		comboLabel43.setVerticalTextPosition(JLabel.CENTER);
		comboLabel43.setHorizontalTextPosition(JLabel.CENTER);

		JLabel comboLabel44 = new JLabel("Intro Hours:");
		comboLabel44.setFont(new Font("Arial", Font.BOLD, 12));
		comboLabel44.setVerticalTextPosition(JLabel.CENTER);
		comboLabel44.setHorizontalTextPosition(JLabel.CENTER);

		JLabel comboLabel45 = new JLabel("Total Hours:");
		comboLabel45.setFont(new Font("Arial", Font.BOLD, 12));
		comboLabel45.setVerticalTextPosition(JLabel.CENTER);
		comboLabel45.setHorizontalTextPosition(JLabel.CENTER);

		JTextField moduleName4 = new JTextField(10);
		JTextField moduleTutorName4 = new JTextField(10);
		JTextField moduleRoom4 = new JTextField(10);

		Integer[] comboIntro4 = new Integer[] { 0, 1, 2, 3, 4, 5 };
		JComboBox<Integer> howManyIntroHours4 = new JComboBox<Integer>(comboIntro4);

		Integer[] comboTotal4 = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
				20 };
		JComboBox<Integer> totalHours4 = new JComboBox<Integer>(comboTotal4);

		// End Module 4

		// Module 5

		JLabel comboLabel51 = new JLabel("Module 5 Code:");
		comboLabel51.setFont(new Font("Arial", Font.BOLD, 12));
		comboLabel51.setVerticalTextPosition(JLabel.CENTER);
		comboLabel51.setHorizontalTextPosition(JLabel.CENTER);

		JLabel comboLabel52 = new JLabel("Module 5 Room:");
		comboLabel52.setFont(new Font("Arial", Font.BOLD, 12));
		comboLabel52.setVerticalTextPosition(JLabel.CENTER);
		comboLabel52.setHorizontalTextPosition(JLabel.CENTER);

		JLabel comboLabel53 = new JLabel("Module 5 Tutor:");
		comboLabel53.setFont(new Font("Arial", Font.BOLD, 12));
		comboLabel53.setVerticalTextPosition(JLabel.CENTER);
		comboLabel53.setHorizontalTextPosition(JLabel.CENTER);

		JLabel comboLabel54 = new JLabel("Intro Hours:");
		comboLabel54.setFont(new Font("Arial", Font.BOLD, 12));
		comboLabel54.setVerticalTextPosition(JLabel.CENTER);
		comboLabel54.setHorizontalTextPosition(JLabel.CENTER);

		JLabel comboLabel55 = new JLabel("Total Hours:");
		comboLabel55.setFont(new Font("Arial", Font.BOLD, 12));
		comboLabel55.setVerticalTextPosition(JLabel.CENTER);
		comboLabel55.setHorizontalTextPosition(JLabel.CENTER);

		JTextField moduleName5 = new JTextField(10);
		JTextField moduleTutorName5 = new JTextField(10);
		JTextField moduleRoom5 = new JTextField(10);

		Integer[] comboIntro5 = new Integer[] { 0, 1, 2, 3, 4, 5 };
		JComboBox<Integer> howManyIntroHours5 = new JComboBox<Integer>(comboIntro5);

		Integer[] comboTotal5 = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
				20 };
		JComboBox<Integer> totalHours5 = new JComboBox<Integer>(comboTotal5);

		// End Module 5

		// setting the layout of the main frame to a border layout

		try {

			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(8, 8, 8, 8);
			gbc.gridwidth = gbc.REMAINDER;

			JPanel centerMain = new JPanel();
			GridBagLayout thisLayout = new GridBagLayout();
			centerMain.setLayout(thisLayout);
			centerMain.setSize(720, 480);
			centerMain.setOpaque(false);

			// Container for module 1

			JPanel center = new JPanel();
			center.setLayout(thisLayout);
			center.setSize(720, 480);
			center.setOpaque(false);

			JPanel center1 = new JPanel();
			GridBagLayout Layout1 = new GridBagLayout();
			center1.setLayout(Layout1);
			center1.setSize(70, 50);
			center1.setOpaque(false);

			JPanel center2 = new JPanel();
			GridBagLayout Layout2 = new GridBagLayout();
			center2.setLayout(Layout2);
			center2.setSize(70, 50);
			center2.setOpaque(false);

			JPanel center3 = new JPanel();
			GridBagLayout Layout3 = new GridBagLayout();
			center3.setLayout(Layout3);
			center3.setSize(70, 50);
			center3.setOpaque(false);

			JPanel center4 = new JPanel();
			GridBagLayout Layout4 = new GridBagLayout();
			center4.setLayout(Layout4);
			center4.setSize(70, 50);
			center4.setOpaque(false);

			JPanel center5 = new JPanel();
			GridBagLayout Layout5 = new GridBagLayout();
			center5.setLayout(Layout5);
			center5.setSize(70, 50);
			center5.setOpaque(false);

			// End of container for module 1

			// Container for module 2

			JPanel centerModule2 = new JPanel();
			centerModule2.setLayout(thisLayout);
			centerModule2.setSize(720, 480);
			centerModule2.setOpaque(false);

			JPanel center21 = new JPanel();
			center21.setLayout(Layout1);
			center21.setSize(70, 50);
			center21.setOpaque(false);

			JPanel center22 = new JPanel();
			center22.setLayout(Layout2);
			center22.setSize(70, 50);
			center22.setOpaque(false);

			JPanel center23 = new JPanel();
			center23.setLayout(Layout3);
			center23.setSize(70, 50);
			center23.setOpaque(false);

			JPanel center24 = new JPanel();
			center24.setLayout(Layout4);
			center24.setSize(70, 50);
			center24.setOpaque(false);

			JPanel center25 = new JPanel();
			center25.setLayout(Layout5);
			center25.setSize(70, 50);
			center25.setOpaque(false);

			// End of container for module 2

			// Container for module 3

			JPanel centerModule3 = new JPanel();
			centerModule3.setLayout(thisLayout);
			centerModule3.setSize(720, 480);
			centerModule3.setOpaque(false);

			JPanel center31 = new JPanel();
			center31.setLayout(Layout1);
			center31.setSize(70, 50);
			center31.setOpaque(false);

			JPanel center32 = new JPanel();
			center32.setLayout(Layout2);
			center32.setSize(70, 50);
			center32.setOpaque(false);

			JPanel center33 = new JPanel();
			center33.setLayout(Layout3);
			center33.setSize(70, 50);
			center33.setOpaque(false);

			JPanel center34 = new JPanel();
			center34.setLayout(Layout4);
			center34.setSize(70, 50);
			center34.setOpaque(false);

			JPanel center35 = new JPanel();
			center35.setLayout(Layout5);
			center35.setSize(720, 50);
			center35.setOpaque(false);

			// End of container for module 3

			// Container for module 4

			JPanel centerModule4 = new JPanel();
			centerModule4.setLayout(thisLayout);
			centerModule4.setSize(720, 480);
			centerModule4.setOpaque(false);

			JPanel center41 = new JPanel();
			center41.setLayout(Layout1);
			center41.setSize(70, 50);
			center41.setOpaque(false);

			JPanel center42 = new JPanel();
			center42.setLayout(Layout2);
			center42.setSize(70, 50);
			center42.setOpaque(false);

			JPanel center43 = new JPanel();
			center43.setLayout(Layout3);
			center43.setSize(70, 50);
			center43.setOpaque(false);

			JPanel center44 = new JPanel();
			center44.setLayout(Layout4);
			center44.setSize(70, 50);
			center44.setOpaque(false);

			JPanel center45 = new JPanel();
			center45.setLayout(Layout5);
			center45.setSize(720, 50);
			center45.setOpaque(false);

			// End of container for module 4

			// Container for module 5

			JPanel centerModule5 = new JPanel();
			centerModule5.setLayout(thisLayout);
			centerModule5.setSize(720, 480);
			centerModule5.setOpaque(false);

			JPanel center51 = new JPanel();
			center51.setLayout(Layout1);
			center51.setSize(70, 50);
			center51.setOpaque(false);

			JPanel center52 = new JPanel();
			center52.setLayout(Layout2);
			center52.setSize(70, 50);
			center52.setOpaque(false);

			JPanel center53 = new JPanel();
			center53.setLayout(Layout3);
			center53.setSize(70, 50);
			center53.setOpaque(false);

			JPanel center54 = new JPanel();
			center54.setLayout(Layout4);
			center54.setSize(70, 50);
			center54.setOpaque(false);

			JPanel center55 = new JPanel();
			center55.setLayout(Layout5);
			center55.setSize(720, 50);
			center55.setOpaque(false);

			// End of container for module 5

			// creating a new gridbag layout for all of the buttons and adding
			// them
			// GridBagConstraints gbc = new GridBagConstraints();
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
				nextButton.setText("Generate");
				nextButton.setFont(new Font("Arial", Font.BOLD, 24));
				nextButton.setHorizontalTextPosition(JButton.CENTER);
				nextButton.setVerticalTextPosition(JButton.CENTER);
			}

			JPanel commandBox = new JPanel();
			commandBox.setOpaque(false);
			commandBox.setLayout(new FlowLayout());
			commandBox.add(backButton, gbc);
			commandBox.add(nextButton, gbc);

			JPanel titleBox = new JPanel();
			titleBox.setOpaque(false);
			titleBox.setLayout(new FlowLayout());
			titleBox.add(label1);

			// Adding module 1 to module 1 pane

			center1.add(comboLabel1, gbc);
			center1.add(moduleName, gbc);

			//center2.add(comboLabel2, gbc);
			//center2.add(moduleTutorName, gbc);

			//center3.add(comboLabel3, gbc);
			//center3.add(moduleRoom, gbc);

			center4.add(comboLabel4, gbc);
			center4.add(howManyIntroHours, gbc);

			center5.add(comboLabel5, gbc);
			center5.add(totalHours, gbc);

			// End adding module 1 to module 1 pane

			// Adding module 2 to module 2 pane

			center21.add(comboLabel21, gbc);
			center21.add(moduleName2, gbc);

			//center22.add(comboLabel22, gbc);
			//center22.add(moduleTutorName2, gbc);

			//center23.add(comboLabel23, gbc);
			//center23.add(moduleRoom2, gbc);

			center24.add(comboLabel24, gbc);
			center24.add(howManyIntroHours2, gbc);

			center25.add(comboLabel25, gbc);
			center25.add(totalHours2, gbc);

			// End adding module 2 to module 2 pane

			// Adding module 3 to module 3 pane

			center31.add(comboLabel31, gbc);
			center31.add(moduleName3, gbc);

			//center32.add(comboLabel32, gbc);
			//center32.add(moduleTutorName3, gbc);

			//center33.add(comboLabel33, gbc);
			//center33.add(moduleRoom3, gbc);

			center34.add(comboLabel34, gbc);
			center34.add(howManyIntroHours3, gbc);

			center35.add(comboLabel35, gbc);
			center35.add(totalHours3, gbc);

			// End adding module 3 to module 3 pane

			// Adding module 4 to module 4 pane

			center41.add(comboLabel41, gbc);
			center41.add(moduleName4, gbc);

			//center42.add(comboLabel42, gbc);
			//center42.add(moduleTutorName4, gbc);

			//center43.add(comboLabel43, gbc);
			///center43.add(moduleRoom4, gbc);

			center44.add(comboLabel44, gbc);
			center44.add(howManyIntroHours4, gbc);

			center45.add(comboLabel45, gbc);
			center45.add(totalHours4, gbc);

			// End adding module 4 to module 4 pane

			// Adding module 5 to module 5 pane

			center51.add(comboLabel51, gbc);
			center51.add(moduleName5, gbc);

			//center52.add(comboLabel52, gbc);
			//center52.add(moduleTutorName5, gbc);

			//center53.add(comboLabel53, gbc);
			//center53.add(moduleRoom5, gbc);

			center54.add(comboLabel54, gbc);
			center54.add(howManyIntroHours5, gbc);

			center55.add(comboLabel55, gbc);
			center55.add(totalHours5, gbc);

			// End adding module 5 to module 5 pane

			center.add(center1);
			center.add(center2);
			center.add(center3);
			center.add(center4);
			center.add(center5);

			centerModule2.add(center21);
			centerModule2.add(center22);
			centerModule2.add(center23);
			centerModule2.add(center24);
			centerModule2.add(center25);

			centerModule3.add(center31);
			centerModule3.add(center32);
			centerModule3.add(center33);
			centerModule3.add(center34);
			centerModule3.add(center35);

			centerModule4.add(center41);
			centerModule4.add(center42);
			centerModule4.add(center43);
			centerModule4.add(center44);
			centerModule4.add(center45);

			centerModule5.add(center51);
			centerModule5.add(center52);
			centerModule5.add(center53);
			centerModule5.add(center54);
			centerModule5.add(center55);
			
			TimeTableCreatorInputView ttciv = new TimeTableCreatorInputView();
			int howManyModules = ttciv.intValueHMM;
			
			if (howManyModules == 2) {

				centerMain.add(center, gbc);
				centerMain.add(centerModule2, gbc);

			} else if (howManyModules == 3) {

				centerMain.add(center, gbc);
				centerMain.add(centerModule2, gbc);
				centerMain.add(centerModule3, gbc);

			}else if (howManyModules == 4) {

				centerMain.add(center, gbc);
				centerMain.add(centerModule2, gbc);
				centerMain.add(centerModule3, gbc);
				centerMain.add(centerModule4, gbc);

			}else if (howManyModules == 5) {

				centerMain.add(center, gbc);
				centerMain.add(centerModule2, gbc);
				centerMain.add(centerModule3, gbc);
				centerMain.add(centerModule4, gbc);
				centerMain.add(centerModule5, gbc);

			}else {

				centerMain.add(center, gbc);

			}

			centerMain.setOpaque(false);
			JScrollPane scrollPane = new JScrollPane(centerMain);

			mainPanel.add(commandBox, BorderLayout.SOUTH);
			mainPanel.add(scrollPane, BorderLayout.CENTER);
			mainPanel.add(titleBox, BorderLayout.NORTH);

			commandBox.setOpaque(false);
			center.setOpaque(false);
			centerMain.setOpaque(false);
			centerModule2.setOpaque(false);
			centerModule3.setOpaque(false);
			centerModule4.setOpaque(false);
			centerModule5.setOpaque(false);
			scrollPane.setOpaque(false);
			scrollPane.getViewport().setOpaque(false);

		} catch (Exception e) {
			e.printStackTrace();
		}

		/**
		 * used to create an action listener to see if the user wants to quit.
		 */
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MainFrame.mainFrame.getContentPane().removeAll();
				TimeTableCreatorInputView x = new TimeTableCreatorInputView();
				MainFrame.mainFrame.add(x.buildTimeTableCreatorMenu(), BorderLayout.CENTER);
				MainFrame.mainFrame.repaint();
				MainFrame.mainFrame.revalidate();

			}
		});

		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MainFrame.mainFrame.getContentPane().removeAll();
				TimeTableCreatorTableView x = new TimeTableCreatorTableView();
				MainFrame.mainFrame.add(x.buildTimeTableCreatorMenu(), BorderLayout.CENTER);
				MainFrame.mainFrame.repaint();
				MainFrame.mainFrame.revalidate();

				// String textFieldValue = testField.getText();

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