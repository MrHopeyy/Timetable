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

		// Setting the panel to be visible
		mainPanel.setVisible(true);
		// Setting the size of the panel
		mainPanel.setSize(720, 480);
		// Setting the layout of the panel to be border layout
		mainPanel.setLayout(new BorderLayout());

		// creating all of the buttons for the menu
		JButton newTableButton = new JButton();
		JButton instButton = new JButton();
		JButton ExitButton = new JButton();

		// Creating a new label
		JLabel label1 = new JLabel("University Timetable Creator");
		// Setting the font and the size of the label text
		label1.setFont(new Font("Arial", Font.BOLD, 36));
		// Setting the position of the text
		label1.setVerticalTextPosition(JLabel.CENTER);
		// Setting the position of the text
		label1.setHorizontalTextPosition(JLabel.CENTER);

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
				newTableButton = new JButton();
				// Setting the foreground colour of the button to black
				newTableButton.setForeground(Color.BLACK);
				// Setting the size of the button
				newTableButton.setPreferredSize(new Dimension(250, 50));
				// Setting the text of the button
				newTableButton.setText("Create New Table");
				// Setting the font and size of the button text
				newTableButton.setFont(new Font("Arial", Font.BOLD, 24));
				// Setting the text position of the button
				newTableButton.setHorizontalTextPosition(JButton.CENTER);
				// Setting the text position of the button
				newTableButton.setVerticalTextPosition(JButton.CENTER);
				// Adding the button to centre
				center.add(newTableButton, gbc);
			}
			{
				// Creating a button
				instButton = new JButton();
				// Setting the foreground colour of the button to black
				instButton.setForeground(Color.BLACK);
				// Setting the size of the button
				instButton.setPreferredSize(new Dimension(250, 50));
				// Setting the text of the button
				instButton.setText("Instructions");
				// Setting the font and size of the button text
				instButton.setFont(new Font("Arial", Font.BOLD, 24));
				// Setting the text position of the button
				instButton.setHorizontalTextPosition(JButton.CENTER);
				// Setting the text position of the button
				instButton.setVerticalTextPosition(JButton.CENTER);
				// Adding the button to centre
				center.add(instButton, gbc);
			}
			{
				// Creating a button
				ExitButton = new JButton();
				// Setting the foreground colour of the button to black
				ExitButton.setForeground(Color.BLACK);
				// Setting the size of the button
				ExitButton.setPreferredSize(new Dimension(250, 50));
				// Setting the text of the button
				ExitButton.setText("Exit Program");
				// Setting the font and size of the button text
				ExitButton.setFont(new Font("Arial", Font.BOLD, 24));
				// Setting the text position of the button
				ExitButton.setHorizontalTextPosition(JButton.CENTER);
				// Setting the text position of the button
				ExitButton.setVerticalTextPosition(JButton.CENTER);
				// Adding the button to centre
				center.add(ExitButton, gbc);
			}

			// Creating a new panel
			JPanel commandBox = new JPanel();
			// Setting the panel to be transparent
			commandBox.setOpaque(false);
			// Setting the layout of the panel to flow layout
			commandBox.setLayout(new FlowLayout());
			// Adding the button to the panel

			// Creating a new panel
			JPanel titleBox = new JPanel();
			// Setting the panel to be transparent
			titleBox.setOpaque(false);
			// Setting the layout of the panel to flow layout
			titleBox.setLayout(new FlowLayout());
			// Adding the label to this panel
			titleBox.add(label1);

			// Adding the panels to the main panel
			mainPanel.add(commandBox, BorderLayout.SOUTH);
			mainPanel.add(center, BorderLayout.CENTER);
			mainPanel.add(titleBox, BorderLayout.NORTH);

			// Setting this panel to be transparent
			commandBox.setOpaque(false);
			// Setting this panel to be transparent
			center.setOpaque(false);

		} catch (Exception e) {
			e.printStackTrace();
		}

		/**
		 * used to create an action listener to see if the user wants to quit.
		 */
		newTableButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Removing all the contents of the frame
				MainFrame.mainFrame.getContentPane().removeAll();
				// Creating an instance of TimeTableCreatorMenuView
				TimeTableCreatorFileModuleView gov = new TimeTableCreatorFileModuleView();
				try {
					// Adding the instance of the panel to the frame
					MainFrame.mainFrame.add(gov.buildTimeTableCreatorMenu(), BorderLayout.CENTER);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// Repainting the frame
				MainFrame.mainFrame.repaint();
				// Revalidating the frame
				MainFrame.mainFrame.revalidate();
			}
		});

		instButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Removing all the contents of the frame
				MainFrame.mainFrame.getContentPane().removeAll();
				// Creating an instance of TimeTableCreatorMenuView
				TimeTableCreatorInstView gov = new TimeTableCreatorInstView();
				// Adding the instance of the panel to the frame
				MainFrame.mainFrame.add(gov.buildTimeTableCreatorMenu(), BorderLayout.CENTER);
				// Repainting the frame
				MainFrame.mainFrame.repaint();
				// Revalidating the frame
				MainFrame.mainFrame.revalidate();

			}
		});

		ExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Show dialog box and ask if user really wants to quit the
				// program
				if (JOptionPane.showConfirmDialog(mainPanel, "Are you sure to exit the Program?", "Really Closing?",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					//On close delete temp file
					try
					{
					    File f=new File("src/Files/output.txt");
					    @SuppressWarnings("unused")
						boolean deleted = f.delete();
					} catch(Exception e1) {
					    JOptionPane.showMessageDialog(null, "Something Went Wrong!",
					        " ", JOptionPane.ERROR_MESSAGE);
					}
					System.exit(0);
				}
			}
		});

		// Returning the panel
		return mainPanel;

	}

	/**
	 * used to create the background image for the panel
	 */
	private Component createContent() throws IOException {
		// Importing the background image
		final ImageIcon icon = new ImageIcon(this.getClass().getResource("/Files/background.jpg"));

		@SuppressWarnings("serial")
		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				// Painting the background image to the panel
				super.paintComponent(g);
				g.drawImage(icon.getImage(), 0, 0, null);
			}
		};
		// Returning the panel
		return panel;
	}
}