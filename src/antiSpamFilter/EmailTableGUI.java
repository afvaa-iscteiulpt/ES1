package antiSpamFilter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.GridLayout;

public class EmailTableGUI extends JPanel {
	
    public EmailTableGUI(Object[][] data, String[] columnNames) {
        super(new GridLayout(1,0));
        
        final JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
    }
    
    public static void createAndShowGUI(Object[][] data, String[] columnNames, String typeEmail) {
        //Create and set up the window.
        JFrame frame = new JFrame("Email Table - " + typeEmail + " file ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        EmailTableGUI newContentPane = new EmailTableGUI(data, columnNames);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

}
