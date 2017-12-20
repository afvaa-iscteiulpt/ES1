package antiSpamFilter.gui;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import antiSpamFilter.FileEmail;
import antiSpamFilter.FileRule;
import antiSpamFilter.StatusFile;
import antiSpamFilter.TypeEmail;
import javax.swing.SwingConstants;

public class MainGui {

	private JFrame frame;
	private FileRule fileRules;
	private FileEmail fileHam;
	private FileEmail fileSpam;
	private RunGui runGui;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGui window = new MainGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainGui() {
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {

		// Gui Visuals
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblNewLabel = new JLabel("Load Files:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel.add(panel_1);

		JButton btnNewButton = new JButton("Rules");
		panel_1.add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel("No File Selected");
		panel_1.add(lblNewLabel_1);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2);

		JButton btnNewButton_1 = new JButton("Spam");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_2.add(btnNewButton_1);

		JLabel lblNewLabel_2 = new JLabel("No File Selected");
		panel_2.add(lblNewLabel_2);

		JPanel panel_3 = new JPanel();
		panel.add(panel_3);

		JButton btnNewButton_2 = new JButton("Ham");
		panel_3.add(btnNewButton_2);

		JLabel lblNewLabel_3 = new JLabel("No File Selected");
		panel_3.add(lblNewLabel_3);
		
		JButton btnReset = new JButton("Reset");
		panel.add(btnReset);
		
		JButton btnRunauto = new JButton("Run (Auto)");
		panel.add(btnRunauto);

		JButton btnRunTests = new JButton("Run (Manual)");
		panel.add(btnRunTests);

		// Action Listeners
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fileRules = new FileRule();
				if ( fileRules.getStatusFile() == StatusFile.APPROVED) {
					lblNewLabel_1.setText("Path: " + fileRules.getPath() + "\n" + " Number Of Lines: " + fileRules.getNumberOfLines());
				}
			}
		});

		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fileHam = new FileEmail(TypeEmail.HAM);
				if ( fileHam.getStatusFile() == StatusFile.APPROVED) {
					lblNewLabel_2.setText("Path: " + fileHam.getPath() + "\n" + " Number Of Lines: " + fileHam.getNumberOfLines());
				}

			}
		});

		btnNewButton_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fileSpam = new FileEmail(TypeEmail.SPAM);
				if ( fileSpam.getStatusFile() == StatusFile.APPROVED) {
					lblNewLabel_3.setText("Path: " + fileSpam.getPath() + "\n" + " Number Of Lines: " + fileSpam.getNumberOfLines());
				}
			}
		});

		btnRunTests.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Check if all files are loaded.
				if (fileRules != null && fileHam != null && fileSpam != null
						&& fileRules.getStatusFile() == StatusFile.APPROVED
						&& fileHam.getStatusFile() == StatusFile.APPROVED
						&& fileSpam.getStatusFile() == StatusFile.APPROVED) {
					runGui = new RunGui(fileRules, fileHam, fileSpam);
					frame.setVisible(false);
					System.out.println(
							fileHam.getNumberOfLines() + fileRules.getNumberOfLines() + fileSpam.getNumberOfLines());

				} else {
					// Display popup Warning the user
					JOptionPane.showMessageDialog(null, "Input Files NOT approved, please correct it.");
				}
			}
		});
		
		btnRunauto.addActionListener(new ActionListener() {
			
			private ConfGui confgui;

			@Override
			public void actionPerformed(ActionEvent e) {
				// Check if all files are loaded.
				if (fileRules != null && fileHam != null && fileSpam != null
						&& fileRules.getStatusFile() == StatusFile.APPROVED
						&& fileHam.getStatusFile() == StatusFile.APPROVED
						&& fileSpam.getStatusFile() == StatusFile.APPROVED) {
					confgui = new ConfGui(fileRules, fileHam, fileSpam);
					//runGui = new RunGui(fileRules, fileHam, fileSpam);
					frame.setVisible(false);
					System.out.println(
							fileHam.getNumberOfLines() + fileRules.getNumberOfLines() + fileSpam.getNumberOfLines());

				} else {
					// Display popup Warning the user
					JOptionPane.showMessageDialog(null, "Input Files NOT approved, please correct it.");
				}
			}
		});
		
		btnReset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				lblNewLabel_1.setText("No File Selected");
				lblNewLabel_2.setText("No File Selected");
				lblNewLabel_3.setText("No File Selected");
				fileHam=null;
				fileRules=null;
				fileSpam=null;
				
			}
		});

	}

}
