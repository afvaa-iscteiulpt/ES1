package antiSpamFilter.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;

import antiSpamFilter.AntiSpamFilterAutomaticConfiguration;
import antiSpamFilter.AntiSpamFilterProblem;
import antiSpamFilter.FileEmail;
import antiSpamFilter.FileRule;

public class RunGui {

	private JFrame frame;
	private JTable table;
	private ConfGui confGui;
	private FileRule fileRules;
	private FileEmail fileHam;
	private FileEmail fileSpam;


	public RunGui(FileRule fileRules, FileEmail fileHam, FileEmail fileSpam) {
		this.fileRules=fileRules;
		this.fileHam=fileHam;
		this.fileSpam=fileSpam;
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		// Gui Visuals
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
		
		
		//Action Listeners
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				confGui = new ConfGui();
			}
		});
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			//Run
				AntiSpamFilterProblem problem = new AntiSpamFilterProblem(fileRules.getNumberOfLines(), fileHam, fileSpam, fileRules);
				
				AntiSpamFilterAutomaticConfiguration antiSpamConfig = new AntiSpamFilterAutomaticConfiguration(problem);
				lblNewLabel.setText("FP: " );
				lblNewLabel_1.setText("FN: " );
			}
		});
		
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			//	show ham file
				fileHam.showTableEmail();
			}
		});
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			//	show spam file
				fileSpam.showTableEmail();

			}
		});
	}

}
