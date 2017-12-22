package antiSpamFilter.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import antiSpamFilter.api.FileEmail;
import antiSpamFilter.api.FileRule;
import antiSpamFilter.api.Rule;

/**
 * Gui da janela de processamento manual
 * 
 */
public class RunGuiManual {

	private JFrame frmMaual;
	private JTable table;
	private FileRule fileRules;
	private FileEmail fileHam;
	private FileEmail fileSpam;
	private JTextField textField;
	private DefaultTableModel model;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;

	/**
	 * Inicializador
	 * Cria a gui para mostrar a janela de conigura��o manual
	 */
	public RunGuiManual(FileRule fileRules, FileEmail fileHam, FileEmail fileSpam) {
		this.fileRules = fileRules;
		this.fileHam = fileHam;
		this.fileSpam = fileSpam;
		initialize();
		frmMaual.setVisible(true);
	}

	private void initialize() {
		// Gui Visuals
		frmMaual = new JFrame();
		frmMaual.setTitle("Manual");
		frmMaual.setBounds(300, 300, 450, 300);
		frmMaual.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JSplitPane splitPane = new JSplitPane();
		frmMaual.getContentPane().add(splitPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnGenerateRandomWeights = new JButton("Generate Random Weights");
		btnGenerateRandomWeights.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileRules.generateRandomWeightsForEachRule();
				JOptionPane.showMessageDialog(null, "New weights created!");
				tabelUpdate();
			}
		});
		panel.add(btnGenerateRandomWeights);
		
		JButton btnCalculateFp = new JButton("Calculate FP & FN");
		panel.add(btnCalculateFp);

		lblNewLabel = new JLabel("FP: ");
		panel.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("FN: ");
		panel.add(lblNewLabel_1);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainGui gui = new MainGui(fileRules,fileHam,fileSpam);
				frmMaual.setVisible(false);
			}
		});
		
		JButton btnSaveRulesTo = new JButton("Save Rules To File");
		btnSaveRulesTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileRules.replaceFileContent();
				JOptionPane.showMessageDialog(null, "File edited!");
			}
		});
		panel.add(btnSaveRulesTo);
		panel.add(btnBack);
		
		JButton btnExit = new JButton("Exit");
		panel.add(btnExit);

		JPanel panel_1 = new JPanel();
		splitPane.setRightComponent(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		// Setup table

		model = new DefaultTableModel();
		model.addColumn("Rules");
		model.addColumn("Weight [-5;5]");
		table = new JTable(model);
		table.setEnabled(false);

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
		btnExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);	
			}
		});
		
		btnCalculateFp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				double FP = fileHam.calculateFPorFN(fileRules.getHmapRules());
				double FN = fileSpam.calculateFPorFN(fileRules.getHmapRules());
				lblNewLabel.setText("FP: " + FP); // actualizar os valores
				lblNewLabel_1.setText("FN: " + FN); // actualizar os valores
				tabelUpdate();				
			}
		});

		textField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sorter.setRowFilter(RowFilter.regexFilter("(?i)" + textField.getText()));
			}
		});

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConfGui confGui = new ConfGui(fileRules, fileHam, fileSpam,2);
				frmMaual.setVisible(false);
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
