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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TimeTableCreatorFileModuleView {

	// Creating variables
	private JPanel mainPanel;
	public static String ModulePath;

	/*
	 * constructor for the TimeTableCreatorFileModuleView
	 */
	public TimeTableCreatorFileModuleView() {

	}

	/**
	 * used to create a panel for the main menu
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("static-access")
	public JPanel buildTimeTableModuleView() throws IOException {

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
		JButton genButton = new JButton();
		JButton openButton = new JButton();
		JButton prevButton = new JButton();

		// Creating a jTexArea for showing the file contents
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);

		// Creating a scroll pane to store the text area
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(600, 250));
		textArea.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// Creating a new label for the title and setting the text
		JLabel label1 = new JLabel("Select module file");
		label1.setFont(new Font("Arial", Font.BOLD, 36));
		label1.setVerticalTextPosition(JLabel.CENTER);
		label1.setHorizontalTextPosition(JLabel.CENTER);

		// Creating a new label for the title and setting the text
		JLabel label2 = new JLabel("Preview of file");
		label2.setFont(new Font("Arial", Font.BOLD, 12));
		label2.setVerticalTextPosition(JLabel.CENTER);
		label2.setHorizontalTextPosition(JLabel.CENTER);

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
				genButton = new JButton();
				genButton.setForeground(Color.BLACK);
				genButton.setPreferredSize(new Dimension(100, 35));
				genButton.setText("Next");
				genButton.setFont(new Font("Arial", Font.PLAIN, 12));
				genButton.setHorizontalTextPosition(JButton.CENTER);
				genButton.setVerticalTextPosition(JButton.CENTER);
			}

			{
				openButton = new JButton();
				openButton.setForeground(Color.BLACK);
				openButton.setPreferredSize(new Dimension(100, 35));
				openButton.setText("Open File");
				openButton.setFont(new Font("Arial", Font.PLAIN, 12));
				openButton.setHorizontalTextPosition(JButton.CENTER);
				openButton.setVerticalTextPosition(JButton.CENTER);
			}
			{
				prevButton = new JButton();
				prevButton.setForeground(Color.BLACK);
				prevButton.setPreferredSize(new Dimension(100, 35));
				prevButton.setText("Example");
				prevButton.setFont(new Font("Arial", Font.PLAIN, 12));
				prevButton.setHorizontalTextPosition(JButton.CENTER);
				prevButton.setVerticalTextPosition(JButton.CENTER);
			}

			// Creating a new panel
			JPanel commandBox = new JPanel();
			commandBox.setOpaque(false);
			commandBox.setLayout(new FlowLayout());
			commandBox.add(backButton, gbc);
			commandBox.add(genButton, gbc);
			gbc.gridwidth = gbc.REMAINDER;
			center.add(scrollPane, gbc);

			// Creating a new panel
			JPanel fileBox = new JPanel();
			fileBox.setOpaque(false);
			fileBox.setLayout(new FlowLayout());
			fileBox.add(openButton, gbc);
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
		 * used to create an action listener to go back to previous panel.
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
		 * used to create an action listener to go to next panel.
		 */
		genButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (ModulePath != null) {

					MainFrame.mainFrame.getContentPane().removeAll();
					TimeTableCreatorFileCohortView gov = new TimeTableCreatorFileCohortView();
					MainFrame.mainFrame.add(gov.buildTimeTableCohortView(), BorderLayout.CENTER);
					MainFrame.mainFrame.repaint();
					MainFrame.mainFrame.revalidate();

				} else {

					JOptionPane.showMessageDialog(mainPanel, "Please select a file for module data!", "Attention!",
							JOptionPane.WARNING_MESSAGE);

				}

			}
		});

		/**
		 * used to create an action listener to open file.
		 */
		openButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String userhome = System.getProperty("user.home");
				JFileChooser ModulefileChooser = new JFileChooser(userhome + "\\Documets");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt", "csv", "rtf", "docx");
				ModulefileChooser.setFileFilter(filter);
				ModulefileChooser.setAcceptAllFileFilterUsed(false);
				ModulefileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				Component parent = null;
				int returnVal = ModulefileChooser.showOpenDialog(parent);
				if (returnVal == JFileChooser.APPROVE_OPTION) {

					ModulePath = ModulefileChooser.getSelectedFile().getAbsolutePath().toString();

				}

				ModulefileChooser.setOpaque(false);
				FileReader reader = null;
				textArea.setText("");
				try {
					reader = new FileReader(ModulePath);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					textArea.read(reader, ModulePath);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		/**
		 * used to create an action listener to preview file format.
		 */
		prevButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ModulePath = null;
				textArea.setText("");
				textArea.setText("Example of module format:" + "\n\n" + "CS1111,1,5" + "\n" + "CS3214,4,6" + "\n"
						+ "CS2330,0,8" + "\n" + "CS1070,5,6" + "\n" + "CS2121,2,5");

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