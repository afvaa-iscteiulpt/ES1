package antiSpamFilter.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import antiSpamFilter.FileEmail;
import antiSpamFilter.FileRule;
import antiSpamFilter.Rule;

public class ConfGui {

	private JFrame frame;
	private JTable table;
	private FileRule fileRules;
	private FileEmail fileHam;
	private FileEmail fileSpam;
	private JTextField textField_1;
	DefaultTableModel model;
	private JTextField txtRuleName;
	private JTextField textField1;

	public ConfGui(FileRule fileRules, FileEmail fileHam, FileEmail fileSpam) {
		this.fileRules = fileRules;
		this.fileHam = fileHam;
		this.fileSpam = fileSpam;
		initialize();
	}

	private void initialize() {
		// Gui Visuals
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(300, 300, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		JButton btnNewButton_3 = new JButton("Delete Rule(s)");
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Show Spam File");
		panel.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Show Ham File");
		panel.add(btnNewButton_5);

		JButton btnSave = new JButton("Save");
		panel.add(btnSave);
		
		JButton btnBack = new JButton("Back");
		panel.add(btnBack);
		
		JButton btnExit = new JButton("Exit");
		panel.add(btnExit);

		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setAlignOnBaseline(true);
		flowLayout.setVgap(2);
		flowLayout.setHgap(2);
		panel_1.add(panel_3, BorderLayout.NORTH);

		JLabel lblNewLabel_1 = new JLabel("Search:");
		panel_3.add(lblNewLabel_1, BorderLayout.NORTH);

		textField_1 = new JTextField();
		panel_3.add(textField_1);
		textField_1.setColumns(10);

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

		JButton btnNewButton = new JButton("Add New Rule");

		JPanel panel_2 = new JPanel();

		panel_2.setLayout(new BorderLayout());

		JPanel panel_4 = new JPanel();

		JLabel labelRule = new JLabel("Rule: ");

		panel_4.add(labelRule);

		txtRuleName = new JTextField();
		txtRuleName.setText("Name");
		panel_4.add(txtRuleName);
		txtRuleName.setColumns(5);

		textField1 = new JTextField();
		textField1.setText("Weight");
		panel_4.add(textField1);
		textField1.setColumns(5);

		panel_2.add(panel_4, BorderLayout.CENTER);
		panel_2.add(btnNewButton, BorderLayout.SOUTH);

		panel_1.add(panel_2, BorderLayout.SOUTH);

		textField_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sorter.setRowFilter(RowFilter.regexFilter("(?i)" + textField_1.getText()));
			}
		});

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Add New Rule
				if (Double.parseDouble(textField1.getText()) <= 5 && Double.parseDouble(textField1.getText()) >= -5
						&& txtRuleName != null && txtRuleName.getText() != "Name" && txtRuleName.getText() != "") {
					fileRules.createNewRule(txtRuleName.getText(), textField1.getText());
					txtRuleName.setText("Name");
					textField1.setText("Weight");
					tabelUpdate();
				}
			}
		});
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Delete Rule(s)
				if (!(table.getSelectedRow() == -1)) {
					int column = 0;
					int row = table.getSelectedRow();
					String value = table.getModel().getValueAt(row, column).toString();
					if (!value.equals(null)) {
						String[] arrayDel = {value};
						fileRules.deleteRules(arrayDel);
					}
					tabelUpdate();
				}
			}
		});
		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fileRules.replaceFileContent();
			}
		});
		table.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					double changed = Double.valueOf(table.getValueAt(e.getFirstRow(), e.getColumn()).toString());
					if (e.getColumn() == 1) {
						// Rules
						if (changed <= 5.0 && changed >= -5.0) {
							fileRules.getHmapRules().get(table.getValueAt(e.getFirstRow(), 0)).setRuleWeight(table.getValueAt(e.getFirstRow(), e.getColumn()).toString());
							tabelUpdate();
						}
					}
				}
			}
		});
	}

	private void tabelUpdate() {
		model.setRowCount(0);
		for (Entry<String, Rule> entry : fileRules.getHmapRules().entrySet()) {
			model.addRow(new Object[] { entry.getKey(), entry.getValue().getRuleWeight() });
		}
	}

}
