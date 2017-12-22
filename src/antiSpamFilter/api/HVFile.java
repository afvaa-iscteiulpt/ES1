package antiSpamFilter.api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.swing.JOptionPane;

public class HVFile implements JmetalFiles {

	private static TypeFile typeFile = TypeFile.HV;
	
	public HVFile() {
	}

	@Override
	public void compile() throws IOException {
		String result = "";

		try {
			String ss = null;
			Runtime obj = null;
			String current = new java.io.File(".").getCanonicalPath();

			String fullPath = current + "\\experimentDirectory\\AntiSpamStudy\\R\\HV.Boxplot.R";

			//C:/PROGRA~1/R/R-3.4.3/bin/
			Process p = Runtime.getRuntime().exec("cmd.exe /c Rscript.exe " + fullPath);
			
			BufferedWriter writeer = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
			writeer.write("dir");
			writeer.flush();
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			
			while ((ss = stdInput.readLine()) != null) {
				result += "\n" + ss;
			}
			
			while ((ss = stdError.readLine()) != null) {
				result += "\n" + ss;
	        }

		} catch (IOException e) {
			System.out.println(e.toString());
		}

		System.out.println(result);
		JOptionPane.showMessageDialog(null, "OUTPUT:\n\n\n" + result);
	}
	
}
