package antiSpamFilter.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import antiSpamFilter.FileEmail;
import antiSpamFilter.FileRule;
import antiSpamFilter.Rule;

import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.Map.Entry;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

public class ConfGui {

	private JFrame frame;
	private JTextField textField;
	private JTable table;
	private FileRule fileRules;
	private FileEmail fileHam;
	private FileEmail fileSpam;
	private JTextField textField_1;


	public ConfGui(FileRule fileRules, FileEmail fileHam, FileEmail fileSpam) {
		this.fileRules = fileRules;
		this.fileHam=fileHam;
		this.fileSpam=fileSpam;
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
		
		JButton btnNewButton_1 = new JButton("Generate Random Weights");
		panel.add(btnNewButton_1);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		
		JButton btnNewButton_2 = new JButton("Apply");
		panel_2.add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("[-5;5]");
		panel_2.add(lblNewLabel);
		
		textField = new JTextField();
		panel_2.add(textField);
		textField.setColumns(5);
		
		JButton btnNewButton_3 = new JButton("Delete Rule(s)");
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Save To File");
		panel.add(btnNewButton_4);
		
		
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
		
		
		//Setup table
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Rules");
		model.addColumn("Weight");
		for(Entry<String, Rule> entry : fileRules.getHmapRules().entrySet()) {
			model.addRow(new Object[]{entry.getKey(),entry.getValue().getRuleWeight()});
		}
		JTable table = new JTable(model);
		
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(sorter);
		
		//
		
		panel_1.add(new JScrollPane(table), BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("Add New Rule");
		panel_1.add(btnNewButton, BorderLayout.SOUTH);
		
		
		
		//Action Listeners

		textField_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sorter.setRowFilter(RowFilter.regexFilter("(?i)" + textField_1.getText()));
			}
		});
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Add New Rule
				//fileRules.createNewRule("LALALA", 4);
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Generate Random Weights
				//fileRules.generateRandomWeightsForEachRule();

			}
		});
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Apply & close
				RunGui run = new RunGui(fileRules, fileHam, fileSpam);
				frame.setVisible(false);
				
			}
		});
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Delete Rule(s)
				//fileRules.deleteRule("LALALA");

				
			}
		});
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Save To File
				//fileRules.replaceFileContent();

			}
		});

		
		//Add Action listener para a tabela.
		
		
		
	}

}
