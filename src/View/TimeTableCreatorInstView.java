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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TimeTableCreatorInstView {

	// Creating a new j text area
	private JTextArea jTextArea1;

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
	public JPanel buildTimeTableCreatorMenu() {

		try {
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

		// Creating a new label
		JLabel label1 = new JLabel("Instructions");
		// Setting the font and font size of the label
		label1.setFont(new Font("Arial", Font.BOLD, 36));
		// Setting the vertical position of the label
		label1.setVerticalTextPosition(JLabel.CENTER);
		// Setting the horizontal position of the label
		label1.setHorizontalTextPosition(JLabel.CENTER);

		// Creating a new text area and setting the text
		jTextArea1 = new JTextArea("To make a new timetable, you require two files:"
				+ "\n\n"
				+ "- A file containing modules with there module code, intro hours and total hours."
				+ "\n\n"
				+ "- A file containing the module codes that you with to populate the timetable with");

		// Setting the foreground colour to black
		jTextArea1.setForeground(Color.BLACK);
		// Setting the font and font size of the text are
		jTextArea1.setFont(new Font("Arial", ~Font.BOLD, 22));
		// Setting the text area to not be editable
		jTextArea1.setEditable(false);
		// Setting the wrap line to be true
		jTextArea1.setLineWrap(true);
		// Setting the wrap style to true
		jTextArea1.setWrapStyleWord(true);

		// Creating a list scroller for the text area
		JScrollPane listScroller = new JScrollPane(jTextArea1);
		// Setting the preferred size
		listScroller.setPreferredSize(new Dimension(600, 200));
		// Setting the minimum size
		listScroller.setMinimumSize(new Dimension(600, 200));
		// Setting the scroller to not have a border around it
		listScroller.setBorder(null);
		// Setting the scroller to never have a horizontal bar
		listScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		try {

			// Creating a new panel centre and setting
			JPanel center = new JPanel();
			// Creating a new grid bag layout
			GridBagLayout thisLayout = new GridBagLayout();
			// Setting the layout of centre to grid bag layout
			center.setLayout(thisLayout);
			// Setting the size of the panel
			center.setSize(720, 480);
			// Setting the panel to be transparent
			center.setOpaque(false);

			// Creating a new grid bag constraint
			GridBagConstraints gbc = new GridBagConstraints();
			// Setting the insets of the gbc
			gbc.insets = new Insets(8, 8, 8, 8);
			// Setting the grid width of the gbc
			gbc.gridwidth = gbc.REMAINDER;

			/**
			 * creating the buttons for the panel
			 */
			{
				// Creating a button
				backButton = new JButton();
				// Setting the foreground colour of the button to black
				backButton.setForeground(Color.BLACK);
				// Setting the size of the button
				backButton.setPreferredSize(new Dimension(100, 35));
				// Setting the text of the button
				backButton.setText("Back to Menu");
				// Setting the font and size of the button text
				backButton.setFont(new Font("Arial", Font.PLAIN, 12));
				// Setting the text position of the button
				backButton.setHorizontalTextPosition(JButton.CENTER);
				// Setting the text position of the button
				backButton.setVerticalTextPosition(JButton.CENTER);
			}

			// Creating a new panel
			JPanel commandBox = new JPanel();
			// Setting the panel to be transparent
			commandBox.setOpaque(false);
			// Setting the layout of the panel to flow layout
			commandBox.setLayout(new FlowLayout());
			// Adding the button to the panel
			commandBox.add(backButton, gbc);

			// Creating a new panel
			JPanel titleBox = new JPanel();
			// Setting the panel to be transparent
			titleBox.setOpaque(false);
			// Setting th layout of the panel to flow layout
			titleBox.setLayout(new FlowLayout());
			// Adding the lable to this panel
			titleBox.add(label1);

			// Adding the list scroller to this panel
			center.add(listScroller, gbc);

			// Adding the panels to the main panel
			mainPanel.add(commandBox, BorderLayout.SOUTH);
			mainPanel.add(center, BorderLayout.CENTER);
			mainPanel.add(titleBox, BorderLayout.NORTH);

			// Setting this panel to be transparent
			commandBox.setOpaque(false);
			// Setting this panel to be transparent
			center.setOpaque(false);
			// Setting this text area to be transparent
			jTextArea1.setOpaque(false);
			// Setting this scoller to be transparent
			listScroller.setOpaque(false);
			// Setting this scoller to be transparent
			listScroller.getViewport().setOpaque(false);

		} catch (Exception e) {
			e.printStackTrace();
		}

		/**
		 * used to create an action listener to see if the user wants to quit.
		 */
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//Removing all the contents of the frame
				MainFrame.mainFrame.getContentPane().removeAll();
				//Creating an instance of TimeTableCreatorMenuView
				TimeTableCreatorMenuView gov = new TimeTableCreatorMenuView();
				//Adding the instance of the panel to the frame
				MainFrame.mainFrame.add(gov.buildTimeTableCreatorMenu(), BorderLayout.CENTER);
				//Repainting the frame
				MainFrame.mainFrame.repaint();
				//Revalidating the frame
				MainFrame.mainFrame.revalidate();

			}
		});

		//Returning the panel
		return mainPanel;

	}

	/**
	 * used to create the background image for the panel
	 */
	private Component createContent() throws IOException {
		//Importing the background image
		final ImageIcon icon = new ImageIcon(this.getClass().getResource("/Files/background.jpg"));

		@SuppressWarnings("serial")
		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				//Painting the background image to the panel
				super.paintComponent(g);
				g.drawImage(icon.getImage(), 0, 0, null);
			}
		};
		//Returning the panel
		return panel;
	}
}