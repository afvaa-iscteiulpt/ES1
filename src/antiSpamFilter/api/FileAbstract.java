package antiSpamFilter.api;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public abstract class FileAbstract {

	private String name;
	private String path;
	private int numberOfLines;
	private TypeFile typeFile;
	private List<String> allLines = new ArrayList<String>();
	private StatusFile statusFile; 

	public FileAbstract(TypeFile type) {
		typeFile = type;

		showFileDialog();
	}

	private void showFileDialog() {

		String userDir = System.getProperty("user.home");
		JFileChooser chooser = new JFileChooser(userDir + "/Desktop");
		FileNameExtensionFilter filterToUse = null;

		if (typeFile == TypeFile.RULE) {
			filterToUse = new FileNameExtensionFilter(".cf files", "cf");

		} else if (typeFile == TypeFile.EMAIL) {
			filterToUse = new FileNameExtensionFilter(".log files", "log");
		}

		chooser.setFileFilter(filterToUse);
		chooser.setAcceptAllFileFilterUsed(false);

		int returnValue = chooser.showOpenDialog(null);

		getResultedFile(returnValue, chooser);

	}

	private void getResultedFile(int returnValue, JFileChooser chooser) {

		if (returnValue == JFileChooser.APPROVE_OPTION) {

			this.setStatusFile(StatusFile.APPROVED);
			approveOption(chooser);

		} else if (returnValue == JFileChooser.CANCEL_OPTION) {

			this.setStatusFile(StatusFile.NOTAPPROVED);

		}
	}

	private void approveOption(JFileChooser chooser) {

		java.io.File file = chooser.getSelectedFile();

		String name = chooser.getSelectedFile().getName();
		setName(name);

		String path = chooser.getSelectedFile().getAbsolutePath();
		setPath(path);

		readFile(file);
	}

	private void readFile(java.io.File file) {

		BufferedReader reader = null;

		try {
			reader = new java.io.BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}

		String line = null;
		try {
			line = reader.readLine();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		int numberOfLines = 0;
		while (line != null) {

			this.allLines.add(line);

			try {
				numberOfLines++;
				line = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			setNumberOfLines(numberOfLines);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getName() {
		return name;
	}

	public String getPath() {
		return path;
	}

	public int getNumberOfLines() {
		return numberOfLines;
	}

	public TypeFile getTypeFile() {
		return typeFile;
	}

	private void setName(String name) {
		this.name = name;
	}

	private void setNumberOfLines(int numberOfLines) {
		this.numberOfLines = numberOfLines;
	}

	private void setPath(String path) {
		this.path = path;
	}

	public List<String> getAllLines() {
		return allLines;
	}

	public StatusFile getStatusFile() {
		return statusFile;
	}

	public void setStatusFile(StatusFile statusFile) {
		this.statusFile = statusFile;
	}
}
