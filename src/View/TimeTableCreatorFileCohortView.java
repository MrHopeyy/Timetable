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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import Model.Main;

public class TimeTableCreatorFileCohortView {

	// Creating the panel
	private JPanel mainPanel;
	// Creating a global variable for the file path
	public static String CohortPath;
	// Initialising the buffered Reader
	public static BufferedReader br = null;

	/*
	 * constructor for the TimeTableCreatorFileCohortView
	 */
	public TimeTableCreatorFileCohortView() {
		

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
		// Setting the layout of the panel
		mainPanel.setLayout(new BorderLayout());

		// Creating all of the buttons for the menu
		JButton backButton = new JButton();
		JButton genButton = new JButton();
		JButton openButton = new JButton();

		// Creating a jTexArea for showing the file contents
		JTextArea textArea = new JTextArea();
		// Setting the text area to not be editable
		textArea.setEditable(false);

		// Creating a scroll pane to store the text area
		JScrollPane scrollPane = new JScrollPane(textArea);
		// Setting the scroll pane to always show the vertical scroll bar
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		// Setting the size of the scroll pane
		scrollPane.setPreferredSize(new Dimension(600, 250));

		// Creating a new label for the title and setting the text
		JLabel label1 = new JLabel("Select Cohort File");
		// Setting the font and the size of the text
		label1.setFont(new Font("Arial", Font.BOLD, 36));
		// Setting the vertical position of the text
		label1.setVerticalTextPosition(JLabel.CENTER);
		// Setting the horizontal position of the text
		label1.setHorizontalTextPosition(JLabel.CENTER);

		try {

			// Creating a new panel centre
			JPanel center = new JPanel();
			// Creating a new layout
			GridBagLayout thisLayout = new GridBagLayout();
			// Setting the layout of centre to grid bag layout
			center.setLayout(thisLayout);
			// Setting the size of the centre
			center.setSize(720, 480);
			// Setting centre to be transparent
			center.setOpaque(false);

			// Creating a new gridbag layout
			GridBagConstraints gbc = new GridBagConstraints();
			// Setting the insets of the layout
			gbc.insets = new Insets(8, 8, 8, 8);

			/**
			 * Creating the buttons for the panel
			 */

			{
				// Creating a new button
				backButton = new JButton();
				// Setting the foreground colour
				backButton.setForeground(Color.BLACK);
				// Setting the size of the button
				backButton.setPreferredSize(new Dimension(100, 35));
				// Setting the text of the button
				backButton.setText("Back");
				// Setting the font and text size of the button
				backButton.setFont(new Font("Arial", Font.PLAIN, 12));
				// Setting the Horizontal position of the text
				backButton.setHorizontalTextPosition(JButton.CENTER);
				// Setting the Vertical position of the text
				backButton.setVerticalTextPosition(JButton.CENTER);
			}
			{
				// Creating a new button
				genButton = new JButton();
				// Setting the foreground colour
				genButton.setForeground(Color.BLACK);
				// Setting the size of the button
				genButton.setPreferredSize(new Dimension(100, 35));
				// Setting the text of the button
				genButton.setText("Next");
				// Setting the font and text size of the button
				genButton.setFont(new Font("Arial", Font.PLAIN, 12));
				// Setting the Horizontal position of the text
				genButton.setHorizontalTextPosition(JButton.CENTER);
				// Setting the Vertical position of the text
				genButton.setVerticalTextPosition(JButton.CENTER);
			}

			{
				// Creating a new button
				openButton = new JButton();
				// Setting the foreground colour
				openButton.setForeground(Color.BLACK);
				// Setting the size of the button
				openButton.setPreferredSize(new Dimension(100, 35));
				// Setting the text of the button
				openButton.setText("Open File");
				// Setting the font and text size of the button
				openButton.setFont(new Font("Arial", Font.PLAIN, 12));
				// Setting the Horizontal position of the text
				openButton.setHorizontalTextPosition(JButton.CENTER);
				// Setting the Vertical position of the text
				openButton.setVerticalTextPosition(JButton.CENTER);
			}

			// Creating a new panel
			JPanel commandBox = new JPanel();
			// Setting the panel to be transparent
			commandBox.setOpaque(false);
			// Setting a the panels layout to be flow layout
			commandBox.setLayout(new FlowLayout());
			// Adding the back button
			commandBox.add(backButton, gbc);
			// Adding the generate button
			commandBox.add(genButton, gbc);

			// Setting the grid width of the grid bag layout
			gbc.gridwidth = gbc.REMAINDER;

			// Adding the scroll pane to centre
			center.add(scrollPane, gbc);
			// Adding the open button to centre
			center.add(openButton, gbc);

			// Creating a new panel
			JPanel titleBox = new JPanel();
			// Setting the panel to be transparent
			titleBox.setOpaque(false);
			// Adding the flow layout to the panel
			titleBox.setLayout(new FlowLayout());
			// Adding the label to the panel
			titleBox.add(label1);

			// Adding the panels to the main panel
			mainPanel.add(commandBox, BorderLayout.SOUTH);
			mainPanel.add(center, BorderLayout.CENTER);
			mainPanel.add(titleBox, BorderLayout.NORTH);

			// Setting the command box panel to be transparent
			commandBox.setOpaque(false);
			// Setting the centre panel to be transparent
			center.setOpaque(false);

		} catch (Exception e) {
			e.printStackTrace();
		}

		/**
		 * used to create an action listener to see if the user wants to quit.
		 */
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Removing all of the contents of the panel
				MainFrame.mainFrame.getContentPane().removeAll();
				// Creating a new instance of TimeTableCreatorFileModuleView
				TimeTableCreatorFileModuleView gov = new TimeTableCreatorFileModuleView();
				// Adding the instance of the new panel to the main frame
				
				try {
					MainFrame.mainFrame.add(gov.buildTimeTableCreatorMenu(), BorderLayout.CENTER);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// Repainting the frame
				MainFrame.mainFrame.repaint();
				// Revalidating the frame
				MainFrame.mainFrame.revalidate();
				// Setting cohort path to null
				CohortPath = null;
				// Setting the module path to null
				TimeTableCreatorFileModuleView.ModulePath = null;

			}
		});

		genButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Checking if the cohort path is not null
				if (CohortPath != null) {

					// Remove all of the contents of the frame
					MainFrame.mainFrame.getContentPane().removeAll();
					// Creating a new instance of TimeTableCreatorTableView
					TimeTableCreatorTableView gov = new TimeTableCreatorTableView();
					// Adding the instance of the new panel to the frame
					
					try {
						MainFrame.mainFrame.add(gov.buildTimeTableCreatorMenu(), BorderLayout.CENTER);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

					// Repainting the frame
					MainFrame.mainFrame.repaint();
					// Revalidating the frame
					MainFrame.mainFrame.revalidate();

				} else {

					// Telling the user to select a file
					JOptionPane.showMessageDialog(mainPanel, "Please select a file for cohort data!", "Attention!",
							JOptionPane.WARNING_MESSAGE);

				}

			}
		});

		openButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Creating a string of the users home path
				String userhome = System.getProperty("user.home");
				// Creating a new file chooser
				JFileChooser ModulefileChooser = new JFileChooser(userhome + "\\Documets");
				// Creating a filter for the file type of the file chooser
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt", "csv");
				// Adding the filter
				ModulefileChooser.setFileFilter(filter);
				// Removing the option to look for all file types
				ModulefileChooser.setAcceptAllFileFilterUsed(false);
				// Setting the current directory of the file chooser
				ModulefileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				// Setting the components parent to null
				Component parent = null;
				// Creating a int of the parent
				int returnVal = ModulefileChooser.showOpenDialog(parent);
				if (returnVal == JFileChooser.APPROVE_OPTION) {

					// Retrieving the file path to a string variable
					CohortPath = ModulefileChooser.getSelectedFile().getAbsolutePath().toString();

				}
				// Setting the file chooser to be transparent
				ModulefileChooser.setOpaque(false);
				// Setting the file reader to be null
				FileReader reader = null;
				// Reading the file that was selected by the file path
				try {
					reader = new FileReader(CohortPath);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					textArea.read(reader, CohortPath);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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

		// Setting the background image of the pane;
		final ImageIcon icon = new ImageIcon(this.getClass().getResource("/Files/background.jpg"));

		// Paining the background image
		@SuppressWarnings("serial")
		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(icon.getImage(), 0, 0, null);
			}
		};

		// Returning the panel
		return panel;
	}

}