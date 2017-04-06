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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TimeTableCreatorFileModuleCreationView {

	// Creating the panel
	private JPanel mainPanel;
	// Creating a global variable for the file path
	public static String ModulePath;

	/*
	 * constructor for the TimeTableCreatorFileModuleView
	 */
	public TimeTableCreatorFileModuleCreationView() {

	}

	/**
	 * used to create a panel for the main menu
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("static-access")
	public JPanel buildTimeTableCreatorMenu() throws IOException {

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

		// creating all of the buttons for the menu
		JButton backButton = new JButton();
		JButton saveModuleButton = new JButton();
		JButton addButton = new JButton();

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

		JTextArea textAreaModuleInput = new JTextArea();

		// Creating a scroll pane to store the text area
		JScrollPane scrollPaneModuleInput = new JScrollPane(textAreaModuleInput);
		// Setting the scroll pane to always show the vertical scroll bar
		scrollPaneModuleInput.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		// Setting the size of the scroll pane
		scrollPaneModuleInput.setPreferredSize(new Dimension(40, 20));
		scrollPaneModuleInput.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scrollPaneModuleInput.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		String[] introHours = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };

		// Create the combo box, select item at index 4.
		// Indices start at 0, so 4 specifies the pig.
		JComboBox<String> introHoursList = new JComboBox<String>(introHours);
		introHoursList.setSelectedIndex(0);

		String[] totalHours = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
				"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32",
				"33", "34", "35", "36" };

		// Create the combo box, select item at index 4.
		// Indices start at 0, so 4 specifies the pig.
		JComboBox<String> totalHoursList = new JComboBox<String>(totalHours);
		totalHoursList.setSelectedIndex(0);

		// Creating a new label for the title and setting the text
		JLabel label1 = new JLabel("Create module file");
		// Setting the font and the size of the text
		label1.setFont(new Font("Arial", Font.BOLD, 36));
		// Setting the vertical position of the text
		label1.setVerticalTextPosition(JLabel.CENTER);
		// Setting the horizontal position of the text
		label1.setHorizontalTextPosition(JLabel.CENTER);

		// Creating a new label for the title and setting the text
		JLabel label2 = new JLabel("Preview of file");
		// Setting the font and the size of the text
		label2.setFont(new Font("Arial", Font.BOLD, 12));
		// Setting the vertical position of the text
		label2.setVerticalTextPosition(JLabel.CENTER);
		// Setting the horizontal position of the text
		label2.setHorizontalTextPosition(JLabel.CENTER);

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
			// Setting the grid width of the gbc
			gbc.gridwidth = gbc.REMAINDER;

			/**
			 * creating the buttons for the panel
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
				saveModuleButton = new JButton();
				// Setting the foreground colour
				saveModuleButton.setForeground(Color.BLACK);
				// Setting the size of the button
				saveModuleButton.setPreferredSize(new Dimension(100, 35));
				// Setting the text of the button
				saveModuleButton.setText("Save File");
				// Setting the font and text size of the button
				saveModuleButton.setFont(new Font("Arial", Font.PLAIN, 12));
				// Setting the Horizontal position of the text
				saveModuleButton.setHorizontalTextPosition(JButton.CENTER);
				// Setting the Vertical position of the text
				saveModuleButton.setVerticalTextPosition(JButton.CENTER);
			}

			{
				// Creating a new button
				addButton = new JButton();
				// Setting the foreground colour
				addButton.setForeground(Color.BLACK);
				// Setting the size of the button
				addButton.setPreferredSize(new Dimension(100, 35));
				// Setting the text of the button
				addButton.setText("Add Module");
				// Setting the font and text size of the button
				addButton.setFont(new Font("Arial", Font.PLAIN, 12));
				// Setting the Horizontal position of the text
				addButton.setHorizontalTextPosition(JButton.CENTER);
				// Setting the Vertical position of the text
				addButton.setVerticalTextPosition(JButton.CENTER);
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
			commandBox.add(saveModuleButton, gbc);

			// Setting the grid width of the grid bag layout
			gbc.gridwidth = gbc.REMAINDER;

			// Adding the scroll pane to centre
			center.add(scrollPane, gbc);
			// Adding the open button to centre
			// center.add(openButton, gbc);

			// Creating a new panel
			JPanel fileBox = new JPanel();
			// Setting the panel to be transparent
			fileBox.setOpaque(false);
			// Adding a button to the panel
			fileBox.setLayout(new FlowLayout());
			fileBox.add(scrollPaneModuleInput, gbc);
			fileBox.add(introHoursList, gbc);
			fileBox.add(totalHoursList, gbc);
			// Adding a button to the panel
			fileBox.add(addButton, gbc);
			// Adding the panel into the panel
			center.add(fileBox, gbc);

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
				TimeTableCreatorMenuView gov = new TimeTableCreatorMenuView();
				// Adding the instance of the new panel to the main frame
				MainFrame.mainFrame.add(gov.buildTimeTableCreatorMenu(), BorderLayout.CENTER);
				// Repainting the frame
				MainFrame.mainFrame.repaint();
				// Revalidating the frame
				MainFrame.mainFrame.revalidate();
				// Setting the module path to null
				ModulePath = null;

			}
		});

		saveModuleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				final JFileChooser SaveAs = new JFileChooser();
			      SaveAs.setApproveButtonText("Save");
			      int actionDialog = SaveAs.showSaveDialog(null);
			      if (actionDialog != JFileChooser.APPROVE_OPTION) {
			         return;
			      }

			      File fileName = new File(SaveAs.getSelectedFile() + ".txt");
			      BufferedWriter outFile = null;
			      try {
			         outFile = new BufferedWriter(new FileWriter(fileName));

			         textArea.write(outFile);
			         JOptionPane.showMessageDialog(mainPanel, "File Saved!", null, JOptionPane.PLAIN_MESSAGE);
			      } catch (IOException ex) {
			         ex.printStackTrace();
			      } finally {
			         if (outFile != null) {
			            try {
			               outFile.close();
			            } catch (IOException e1) {
			            }
			         }
			      }
			   }

			

			
		});

		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				textArea.append("CS" + textAreaModuleInput.getText() + "," + introHoursList.getSelectedIndex() + "," + totalHoursList.getSelectedIndex() + "\n");

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

		@SuppressWarnings("serial")
		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {

				// Paining the background image
				super.paintComponent(g);
				g.drawImage(icon.getImage(), 0, 0, null);
			}
		};

		// Returning the panel
		return panel;
	}

}