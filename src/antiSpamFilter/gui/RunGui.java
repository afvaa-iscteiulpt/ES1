package antiSpamFilter.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import antiSpamFilter.AntiSpamFilterAutomaticConfiguration;
import antiSpamFilter.AntiSpamFilterProblem;
import antiSpamFilter.FileEmail;
import antiSpamFilter.FileRule;
import antiSpamFilter.Rule;
import antiSpamFilter.StatusFile;

public class RunGui {

	private JFrame frame;
	private JTable table;
	private ConfGui confGui;
	private FileRule fileRules;
	private FileEmail fileHam;
	private FileEmail fileSpam;
	AntiSpamFilterProblem problem;
	private JTextField textField;

	public RunGui(FileRule fileRules, FileEmail fileHam, FileEmail fileSpam) {
		this.fileRules = fileRules;
		this.fileHam = fileHam;
		this.fileSpam = fileSpam;
		if (fileRules != null && fileHam != null && fileSpam != null && fileRules.getStatusFile() == StatusFile.APPROVED
				&& fileHam.getStatusFile() == StatusFile.APPROVED && fileSpam.getStatusFile() == StatusFile.APPROVED) {
			problem = new AntiSpamFilterProblem(fileRules.getNumberOfLines(), fileHam, fileSpam, fileRules);
			problem.createSolution();
		} else {
			System.out.println("Problem Initializing AntiSpamFilter...");
		}
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		// Gui Visuals
		frame = new JFrame();
		frame.setBounds(300, 300, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JSplitPane splitPane = new JSplitPane();
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		JButton btnNewButton_1 = new JButton("ReRun");
		panel.add(btnNewButton_1);

		JLabel lblNewLabel = new JLabel("FP: ");
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("FN: ");
		panel.add(lblNewLabel_1);

		JButton btnNewButton_3 = new JButton("Show Spam File");
		panel.add(btnNewButton_3);

		JButton btnNewButton_2 = new JButton("Show Ham File");
		panel.add(btnNewButton_2);

		JPanel panel_1 = new JPanel();
		splitPane.setRightComponent(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		// Setup table
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Rules");
		model.addColumn("Weight");
		for (Entry<String, Rule> entry : fileRules.getHmapRules().entrySet()) {
			model.addRow(new Object[] { entry.getKey(), entry.getValue().getRuleWeight() });
		}
		JTable table = new JTable(model);

		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(sorter);

		//

		panel_1.add(new JScrollPane(table), BorderLayout.CENTER);

		JButton btnNewButton = new JButton("Configure Rules");
		panel_1.add(btnNewButton, BorderLayout.SOUTH);

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.NORTH);

		textField = new JTextField();

		JLabel lblSearch = new JLabel("Search");
		panel_2.add(lblSearch);
		panel_2.add(textField);
		textField.setColumns(10);
		table.setCellSelectionEnabled(false);
		table.setRowSelectionAllowed(false);
		table.setColumnSelectionAllowed(true);

		// Action Listeners

		textField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sorter.setRowFilter(RowFilter.regexFilter("(?i)" + textField.getText()));
			}
		});

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				confGui = new ConfGui(fileRules, fileHam, fileSpam);
				frame.setVisible(false);
			}
		});

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Run With Auto config

				AntiSpamFilterAutomaticConfiguration antiSpamConfig = new AntiSpamFilterAutomaticConfiguration(problem);
				try {
					antiSpamConfig.runSolution();
				} catch (IOException e) {
					e.printStackTrace();
				}
				double FP = fileHam.calculateFPorFN(fileRules.getHmapRules());
				double FN = fileSpam.calculateFPorFN(fileRules.getHmapRules());
				lblNewLabel.setText("FP: " + FP); // actualizar os valores
				lblNewLabel_1.setText("FN: " + FN); // actualizar os valores
			}
		});

		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// show ham file
				fileHam.showTableEmail();
			}
		});
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// show spam file
				fileSpam.showTableEmail();

			}
		});
	}

}
