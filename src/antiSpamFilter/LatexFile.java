package antiSpamFilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LatexFile extends FileAbstract implements JmetalFiles {

	private static TypeFile typeFile = TypeFile.LATEX;
	
	public LatexFile() {
		super(typeFile);
		
	}

	@Override
	public void compile() throws IOException {
		ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "pdflatex experimentDirectory/data");
		builder.redirectErrorStream(true);
		Process p = builder.start();
		BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;
		while (true) {
			line = r.readLine();
			System.out.println(p.exitValue());
			if (line == null || line == "") {
				p.destroy();
				r.close();
				break;
			}
			System.out.println(line);
		}
		
		//return ouput
	}

}
