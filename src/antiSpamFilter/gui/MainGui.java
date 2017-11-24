package antiSpamFilter.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainGui {

	private JFrame frame;

	public MainGui() {
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Load Files:");
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		
		JButton btnNewButton = new JButton("Ham");
		panel_1.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		panel_1.add(lblNewLabel_1);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		
		JButton btnNewButton_1 = new JButton("Spam");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_2.add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		panel_2.add(lblNewLabel_2);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		
		JButton btnNewButton_2 = new JButton("Ham");
		panel_3.add(btnNewButton_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		panel_3.add(lblNewLabel_3);
		
		JButton btnRunTests = new JButton("Run Tests");
		panel.add(btnRunTests);
	}

}
