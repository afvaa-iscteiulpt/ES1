package antiSpamFilter.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import antiSpamFilter.FileEmail;
import antiSpamFilter.FileRule;
import antiSpamFilter.Rule;

public class RunGui {

	private JFrame frame;
	private JTable table;
	private FileRule fileRules;
	private FileEmail fileHam;
	private FileEmail fileSpam;
	private JTextField textField;
	private DefaultTableModel model;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;

	public RunGui(FileRule fileRules, FileEmail fileHam, FileEmail fileSpam) {
		this.fileRules = fileRules;
		this.fileHam = fileHam;
		this.fileSpam = fileSpam;
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

		lblNewLabel = new JLabel("FP: ");
		panel.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("FN: ");
		panel.add(lblNewLabel_1);

		JButton btnNewButton_3 = new JButton("Show Spam File");
		panel.add(btnNewButton_3);

		JButton btnNewButton_2 = new JButton("Show Ham File");
		panel.add(btnNewButton_2);

		JPanel panel_1 = new JPanel();
		splitPane.setRightComponent(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		// Setup table

		model = new DefaultTableModel();
		model.addColumn("Rules");
		model.addColumn("Weight [-5;5]");
		table = new JTable(model);

		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
		table.setRowSorter(sorter);
		tabelUpdate();

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
				ConfGui confGui = new ConfGui(fileRules, fileHam, fileSpam);
				frame.setVisible(false);
			}
		});

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Run With Auto config
				runExperiment();

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
		runExperiment();
	}

	private void tabelUpdate() {
		model.setRowCount(0);
		for (Entry<String, Rule> entry : fileRules.getHmapRules().entrySet()) {
			model.addRow(new Object[] { entry.getKey(), entry.getValue().getRuleWeight() });
		}
	}

	public void runExperiment() {
		// Run
		double FP = fileHam.calculateFPorFN(fileRules.getHmapRules());
		double FN = fileSpam.calculateFPorFN(fileRules.getHmapRules());
		lblNewLabel.setText("FP: " + FP); // actualizar os valores
		lblNewLabel_1.setText("FN: " + FN); // actualizar os valores
		tabelUpdate();
	}

}
