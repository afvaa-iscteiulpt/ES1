package antiSpamFilter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import javax.swing.JOptionPane;

public class LatexFile implements JmetalFiles {

	private static TypeFile typeFile = TypeFile.LATEX;
	
	public LatexFile() {

	}

	@Override
	public void compile() throws IOException {
		String result = "";

		try {
			String ss = null;
			Runtime obj = null;
			String current = new java.io.File(".").getCanonicalPath();

			String fullPath = current + "\\experimentDirectory\\AntiSpamStudy\\latex\\AntiSpamStudy.tex";

			String resultPath = current + "\\experimentDirectory\\AntiSpamStudy\\latex";
					
			Process p = Runtime.getRuntime().exec("cmd.exe /c pdflatex -aux-directory=" + resultPath + " -output-directory=" + resultPath + " " + fullPath);

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

		JOptionPane.showMessageDialog(null, "OUTPUT:\n\n\n" + result);
	}

}
