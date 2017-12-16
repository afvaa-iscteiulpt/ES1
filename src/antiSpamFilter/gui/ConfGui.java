package antiSpamFilter.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConfGui {

	private JFrame frame;
	private JTextField textField;
	private JTable table;


	public ConfGui() {
		frame.setVisible(true);
		initialize();
	}

	private void initialize() {
		// Gui Visuals
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
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
		
		JButton btnNewButton_5 = new JButton("Close");
		panel.add(btnNewButton_5);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Search:");
		panel_1.add(lblNewLabel_1);
		
		table = new JTable();
		panel_1.add(table);
		
		JButton btnNewButton = new JButton("Add New Rule");
		panel_1.add(btnNewButton);
		
		
		//Action Listeners
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
				//Apply
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
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Close
				frame.setVisible(false);
				
			}
		});
		
		//Add Action listener para a tabela.
		
		
		
	}

}
