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
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
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

	// Creating variables
	private JPanel mainPanel;
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
	public JPanel buildTimeTableCohortView() throws IOException {

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
		JButton saveModuleButton = new JButton();
		JButton addButton = new JButton();

		// Creating a jTexArea for showing the file contents
		JTextArea textArea = new JTextArea();
		//textArea.setEditable(false);

		// Creating a jTexArea for making a module code
		JTextArea textAreaCohortCode = new JTextArea();

		// Creating a scroll pane to store the text area
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(600, 250));
		textArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// Creating a scroll pane to store the text area
		JScrollPane scrollPaneCohortCode = new JScrollPane(textAreaCohortCode);
		scrollPaneCohortCode.setPreferredSize(new Dimension(60, 30));
		textAreaCohortCode.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		scrollPaneCohortCode.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		scrollPaneCohortCode.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scrollPaneCohortCode.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		// List<Integer> moduleCode = new ArrayList<Integer>();
		// for (int i = 1000; i <= 9999; ++i) {
		// moduleCode.add(i);
		// }
		// JComboBox<?> moduleCodeComboBox = new
		// JComboBox<Object>(moduleCode.toArray());

		List<Integer> introHours = new ArrayList<Integer>();
		for (int i = 0; i <= 9; ++i) {
			introHours.add(i);
		}
		JComboBox<?> introHoursList = new JComboBox<Object>(introHours.toArray());
		introHoursList.setSelectedIndex(0);

		List<Integer> totalHours = new ArrayList<Integer>();
		for (int i = 1; i <= 36; ++i) {
			totalHours.add(i);
		}
		JComboBox<?> totalHoursList = new JComboBox<Object>(totalHours.toArray());
		totalHoursList.setSelectedIndex(0);

		// Creating a new label for the title and setting the text
		JLabel label1 = new JLabel("Create module file");
		label1.setFont(new Font("Arial", Font.BOLD, 36));
		label1.setVerticalTextPosition(JLabel.CENTER);
		label1.setHorizontalTextPosition(JLabel.CENTER);

		JLabel textAreaModuleInputLabel = new JLabel("Module Code:");
		textAreaModuleInputLabel.setFont(new Font("Arial", Font.BOLD, 11));
		JLabel textAreaIntroInputLabel = new JLabel("Intro Hours:");
		textAreaIntroInputLabel.setFont(new Font("Arial", Font.BOLD, 11));
		JLabel textAreaTotalInputLabel = new JLabel("Total Hours:");
		textAreaTotalInputLabel.setFont(new Font("Arial", Font.BOLD, 11));

		try {

			// Creating a new panel centre
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
				backButton.setText("Back");
				backButton.setFont(new Font("Arial", Font.PLAIN, 12));
				backButton.setHorizontalTextPosition(JButton.CENTER);
				backButton.setVerticalTextPosition(JButton.CENTER);
			}
			{
				saveModuleButton = new JButton();
				saveModuleButton.setForeground(Color.BLACK);
				saveModuleButton.setPreferredSize(new Dimension(100, 35));
				saveModuleButton.setText("Save File");
				saveModuleButton.setFont(new Font("Arial", Font.PLAIN, 12));
				saveModuleButton.setHorizontalTextPosition(JButton.CENTER);
				saveModuleButton.setVerticalTextPosition(JButton.CENTER);
			}

			{
				addButton = new JButton();
				addButton.setForeground(Color.BLACK);
				addButton.setPreferredSize(new Dimension(100, 35));
				addButton.setText("Add Module");
				addButton.setFont(new Font("Arial", Font.PLAIN, 12));
				addButton.setHorizontalTextPosition(JButton.CENTER);
				addButton.setVerticalTextPosition(JButton.CENTER);
			}

			// Creating a new panel
			JPanel commandBox = new JPanel();
			commandBox.setOpaque(false);
			commandBox.setLayout(new FlowLayout());
			commandBox.add(backButton, gbc);
			commandBox.add(saveModuleButton, gbc);
			gbc.gridwidth = gbc.REMAINDER;
			center.add(scrollPane, gbc);

			// Creating a new panel
			JPanel moduleNumberBox = new JPanel();
			moduleNumberBox.setOpaque(false);
			moduleNumberBox.setLayout(new FlowLayout());
			moduleNumberBox.add(textAreaModuleInputLabel, gbc);
			moduleNumberBox.add(scrollPaneCohortCode, gbc);

			// Creating a new panel
			JPanel introNumberBox = new JPanel();
			introNumberBox.setOpaque(false);
			introNumberBox.setLayout(new FlowLayout());
			introNumberBox.add(textAreaIntroInputLabel, gbc);
			introNumberBox.add(introHoursList, gbc);

			// Creating a new panel
			JPanel totalNumberBox = new JPanel();
			totalNumberBox.setOpaque(false);
			totalNumberBox.setLayout(new FlowLayout());
			totalNumberBox.add(textAreaTotalInputLabel, gbc);
			totalNumberBox.add(totalHoursList, gbc);

			// Creating a new panel
			JPanel fileBox = new JPanel();
			fileBox.setOpaque(false);
			fileBox.setLayout(new FlowLayout());
			fileBox.add(moduleNumberBox, gbc);
			fileBox.add(introNumberBox, gbc);
			fileBox.add(totalNumberBox, gbc);
			fileBox.add(addButton, gbc);
			center.add(fileBox, gbc);

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
		 * used to create an action listener to see if the user wants to go to
		 * previous panel.
		 */
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MainFrame.mainFrame.getContentPane().removeAll();
				TimeTableCreatorMenuView gov = new TimeTableCreatorMenuView();
				MainFrame.mainFrame.add(gov.buildTimeTableCreatorMenu(), BorderLayout.CENTER);
				MainFrame.mainFrame.repaint();
				MainFrame.mainFrame.revalidate();
				ModulePath = null;

			}
		});

		/**
		 * used to create an action listener to save module fie.
		 */
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
				String moduleCodeRead = textAreaCohortCode.getText();
				System.out.println("here: " + moduleCodeRead);
				if( moduleCodeRead.equals("") || moduleCodeRead.equals(" ") || moduleCodeRead.equals(null)){
					moduleCodeRead = "AA1234";
					textArea.append(moduleCodeRead + "," + introHoursList.getSelectedItem() + ","
							+ totalHoursList.getSelectedItem() + "\n");
				}else{
					textArea.append(moduleCodeRead + "," + introHoursList.getSelectedItem() + ","
							+ totalHoursList.getSelectedItem() + "\n");

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