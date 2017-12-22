package antiSpamFilter.gui;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.apache.commons.lang3.concurrent.ConcurrentException;

import antiSpamFilter.AntiSpamFilterAutomaticConfiguration;
import antiSpamFilter.AntiSpamFilterProblem;
import antiSpamFilter.FileEmail;
import antiSpamFilter.FileRule;
import antiSpamFilter.StatusFile;
import antiSpamFilter.TypeEmail;

public class MainGui {

	private JFrame frame;
	private FileRule fileRules;
	private FileEmail fileHam;
	private FileEmail fileSpam;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGui window = new MainGui(null,null,null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainGui(FileRule fileRule, FileEmail fileHam, FileEmail fileSpam) {
		if (fileRule != null && fileHam != null && fileSpam != null) {
			this.fileRules = fileRule;
			this.fileHam = fileHam;
			this.fileSpam = fileSpam;
			
		}
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
		
		JButton btnConfigRules = new JButton("Config Rules");
		btnConfigRules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileRules != null && fileHam != null && fileSpam != null
						&& fileRules.getStatusFile() == StatusFile.APPROVED
						&& fileHam.getStatusFile() == StatusFile.APPROVED
						&& fileSpam.getStatusFile() == StatusFile.APPROVED) {
					ConfGui gui = new ConfGui(fileRules, fileHam, fileSpam, 0);
					frame.setVisible(false);
				} else {
					// Display popup Warning the user
					JOptionPane.showMessageDialog(null, "Input Files NOT approved, please correct it.");
				}
			}
		});
		panel.add(btnConfigRules);

		JButton btnRunauto = new JButton("Run (Manual)");
		panel.add(btnRunauto);

		JButton btnRunTests = new JButton("Run (Auto)");
		panel.add(btnRunTests);

		// Action Listeners
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fileRules = new FileRule();
				if (fileRules.getStatusFile() == StatusFile.APPROVED) {
					lblNewLabel_1.setText("Path: " + fileRules.getPath() + "\n" + " Number Of Lines: "
							+ fileRules.getNumberOfLines());
				}
			}
		});

		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fileHam = new FileEmail(TypeEmail.HAM);
				if (fileHam.getStatusFile() == StatusFile.APPROVED) {
					lblNewLabel_2.setText(
							"Path: " + fileHam.getPath() + "\n" + " Number Of Lines: " + fileHam.getNumberOfLines());
				}

			}
		});

		btnNewButton_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fileSpam = new FileEmail(TypeEmail.SPAM);
				if (fileSpam.getStatusFile() == StatusFile.APPROVED) {
					lblNewLabel_3.setText(
							"Path: " + fileSpam.getPath() + "\n" + " Number Of Lines: " + fileSpam.getNumberOfLines());
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
					RunGuiAuto runGui = new RunGuiAuto(fileRules, fileHam, fileSpam);
					frame.setVisible(false);

				} else {
					// Display popup Warning the user
					JOptionPane.showMessageDialog(null, "Input Files NOT approved, please correct it.");
				}
			}
		});

		btnRunauto.addActionListener(new ActionListener() {


			@Override
			public void actionPerformed(ActionEvent e) {
				// Check if all files are loaded.
				if (fileRules != null && fileHam != null && fileSpam != null
						&& fileRules.getStatusFile() == StatusFile.APPROVED
						&& fileHam.getStatusFile() == StatusFile.APPROVED
						&& fileSpam.getStatusFile() == StatusFile.APPROVED) {
					// runGui = new RunGui(fileRules, fileHam, fileSpam);
					RunGuiManual manual = new RunGuiManual(fileRules, fileHam, fileSpam);
					frame.setVisible(false);
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
				fileHam = null;
				fileRules = null;
				fileSpam = null;

			}
		});
		if (fileRules != null && fileHam != null && fileSpam != null ) {
			lblNewLabel_1.setText("Path: " + fileRules.getPath() + "\n" + " Number Of Lines: "
					+ fileRules.getNumberOfLines());
			lblNewLabel_2.setText(
					"Path: " + fileHam.getPath() + "\n" + " Number Of Lines: " + fileHam.getNumberOfLines());
			lblNewLabel_3.setText(
					"Path: " + fileSpam.getPath() + "\n" + " Number Of Lines: " + fileSpam.getNumberOfLines());
			
		}
	}

}
