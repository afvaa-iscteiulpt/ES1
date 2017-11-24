package antiSpamFilter.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;

public class RunGui {

	private JFrame frame;
	private JTable table;

	public RunGui() {
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JSplitPane splitPane = new JSplitPane();
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnNewButton_1 = new JButton("Run");
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
		
		table = new JTable();
		panel_1.add(table, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("Configure Rules");
		panel_1.add(btnNewButton, BorderLayout.SOUTH);
		
		JLabel lblSearch = new JLabel("Search");
		panel_1.add(lblSearch, BorderLayout.NORTH);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			//	table.set
			}
		});
	}

}
