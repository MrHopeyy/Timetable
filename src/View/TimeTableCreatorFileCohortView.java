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
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
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
import javax.swing.filechooser.FileNameExtensionFilter;

import Model.Main;

public class TimeTableCreatorFileCohortView {

	private JPanel mainPanel;
	public static String CohortPath;

	/*
	 * constructor for the menuView
	 */
	public TimeTableCreatorFileCohortView() {

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
		JButton genButton = new JButton();
		JButton openButton = new JButton();

		JTextArea textArea = new JTextArea(5, 20);
		JScrollPane scrollPane = new JScrollPane(textArea);
		textArea.setEditable(false);

		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(250, 250));

		JLabel label1 = new JLabel("Select Cohort File");
		label1.setFont(new Font("Arial", Font.BOLD, 36));
		label1.setVerticalTextPosition(JLabel.CENTER);
		label1.setHorizontalTextPosition(JLabel.CENTER);

		// setting the layout of the main frame to a border layout

		try {

			// creating a new panel center and setting the properties
			JPanel center = new JPanel();
			GridBagLayout thisLayout = new GridBagLayout();
			center.setLayout(thisLayout);
			center.setSize(720, 480);
			center.setOpaque(false);

			// creating a new gridbag layout for all of the buttons and adding
			// them
			GridBagConstraints gbc = new GridBagConstraints();
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
				genButton = new JButton();
				genButton.setForeground(Color.BLACK);
				genButton.setPreferredSize(new Dimension(125, 50));
				// ExitButton.setIcon(new
				// ImageIcon(this.getClass().getResource("/Files/button5.jpg")));
				genButton.setText("Next");
				genButton.setFont(new Font("Arial", Font.BOLD, 24));
				genButton.setHorizontalTextPosition(JButton.CENTER);
				genButton.setVerticalTextPosition(JButton.CENTER);
			}
			
			{
				openButton = new JButton();
				openButton.setForeground(Color.BLACK);
				openButton.setPreferredSize(new Dimension(125, 35));
				openButton.setText("Open File");
				openButton.setFont(new Font("Arial", Font.BOLD, 22));
				openButton.setHorizontalTextPosition(JButton.CENTER);
				openButton.setVerticalTextPosition(JButton.CENTER);
			}

			JPanel commandBox = new JPanel();
			commandBox.setOpaque(false);
			commandBox.setLayout(new FlowLayout());
			commandBox.add(backButton, gbc);
			commandBox.add(genButton, gbc);

			gbc.gridwidth = gbc.REMAINDER;
		//	center.add(CohortfileChooser, gbc);
			center.add(scrollPane, gbc);
			center.add(openButton, gbc);

			JPanel titleBox = new JPanel();
			titleBox.setOpaque(false);
			titleBox.setLayout(new FlowLayout());
			titleBox.add(label1);

			mainPanel.add(commandBox, BorderLayout.SOUTH);
			mainPanel.add(center, BorderLayout.CENTER);
			mainPanel.add(titleBox, BorderLayout.NORTH);

			commandBox.setOpaque(false);
			center.setOpaque(false);

		} catch (Exception e) {
			e.printStackTrace();
		}

		/**
		 * used to create an action listener to see if the user wants to quit.
		 */
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MainFrame.mainFrame.getContentPane().removeAll();
				TimeTableCreatorFileModuleView gov = new TimeTableCreatorFileModuleView();
				MainFrame.mainFrame.add(gov.buildTimeTableCreatorMenu(), BorderLayout.CENTER);
				MainFrame.mainFrame.repaint();
				MainFrame.mainFrame.revalidate();
				CohortPath = null;
				TimeTableCreatorFileModuleView.ModulePath = null;

			}
		});

		genButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (CohortPath != null) {

					MainFrame.mainFrame.getContentPane().removeAll();
					TimeTableCreatorTableView gov = new TimeTableCreatorTableView();
					MainFrame.mainFrame.add(gov.buildTimeTableCreatorMenu(), BorderLayout.CENTER);
					MainFrame.mainFrame.repaint();
					MainFrame.mainFrame.revalidate();
					try {
						Main.solve();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {

					JOptionPane.showMessageDialog(mainPanel, "Please select a file for cohort data!", "Attention!",
							JOptionPane.WARNING_MESSAGE);

				}

			}
		});
		
		openButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String userhome = System.getProperty("user.home");
				JFileChooser ModulefileChooser = new JFileChooser(userhome + "\\Documets");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt", "cvs");
				ModulefileChooser.setFileFilter(filter);
				ModulefileChooser.setAcceptAllFileFilterUsed(false);
				ModulefileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				Component parent = null;
				int returnVal = ModulefileChooser.showOpenDialog(parent);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("You chose to open this file: " + ModulefileChooser.getSelectedFile().getName());
					CohortPath = ModulefileChooser.getSelectedFile().getAbsolutePath().toString();
					System.out.println(CohortPath);
				}

				ModulefileChooser.setOpaque(false);
				
				try {
					String textLine;
					FileReader fr = new FileReader(CohortPath);
					BufferedReader reader = new BufferedReader(fr);
					         while((textLine=reader.readLine())!=null){
					             // textLine = reader.readLine(); // remove this line
					        	 textArea.read(reader,"jTextArea1");
					         } 
					}
					catch (IOException ioe) {
					System.err.println(ioe);
					System.exit(1);
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