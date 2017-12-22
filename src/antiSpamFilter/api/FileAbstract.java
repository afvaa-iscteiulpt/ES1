package antiSpamFilter.api;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Class FileAbstract - class para o carregamento dos ficheiros. É usada pelos
 * ficheiros ham, sapm e rules.
 * 
 */

public abstract class FileAbstract {

	private String name;
	private String path;
	private int numberOfLines;
	private TypeFile typeFile;
	private List<String> allLines = new ArrayList<String>();
	private StatusFile statusFile;

	/**
	 * Inicializador
	 * 
	 * @param TypeFile
	 *            - EMAIL ou RULE
	 */
	public FileAbstract(TypeFile type) {
		typeFile = type;

		showFileDialog();
	}

	/**
	 * Mostra a janela de file chooser para escolher os ficheiros
	 * 
	 * @return void
	 */
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

	/**
	 * Verifica se o ficheiro foi aprovado ou nao e guarda esse ficheiro.
	 * 
	 * @return void
	 */
	private void getResultedFile(int returnValue, JFileChooser chooser) {

		if (returnValue == JFileChooser.APPROVE_OPTION) {

			this.setStatusFile(StatusFile.APPROVED);
			approveOption(chooser);

		} else if (returnValue == JFileChooser.CANCEL_OPTION) {

			this.setStatusFile(StatusFile.NOTAPPROVED);

		}
	}

	/**
	 * Guarda o nome e path do ficheiro carregado
	 * 
	 * @return void
	 */
	private void approveOption(JFileChooser chooser) {

		java.io.File file = chooser.getSelectedFile();

		String name = chooser.getSelectedFile().getName();
		setName(name);

		String path = chooser.getSelectedFile().getAbsolutePath();
		setPath(path);

		readFile(file);
	}

	/**
	 * Lê o ficheiro e guarda a sua informação
	 * 
	 * @return void
	 */
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

	/**
	 * Retorna o nome do ficheiro
	 * 
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Retorna o path do ficheiro
	 * 
	 * @return String
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Retorna o numero de linhas do ficheiro
	 * 
	 * @return int
	 */
	public int getNumberOfLines() {
		return numberOfLines;
	}

	/**
	 * Retorna o tipo do ficheiro - RULE ou EMAIL
	 * 
	 * @return TypeFile
	 */
	public TypeFile getTypeFile() {
		return typeFile;
	}

	/**
	 * Guarda o nome do ficheiro
	 * 
	 * @param String
	 * @return void
	 */
	private void setName(String name) {
		this.name = name;
	}

	/**
	 * Guarda o numero de linhas do ficheiro
	 * 
	 * @param int
	 * @return void
	 */
	private void setNumberOfLines(int numberOfLines) {
		this.numberOfLines = numberOfLines;
	}

	/**
	 * Guarda o path do ficheiro
	 * 
	 * @param String
	 * @return void
	 */
	private void setPath(String path) {
		this.path = path;
	}

	/**
	 * Retorna todas as linhas do ficheiro
	 * 
	 * @return List<String>
	 */
	public List<String> getAllLines() {
		return allLines;
	}

	/**
	 * Retorna o estado do ficheiro - APPROVED ou NOTAPPROVED
	 * 
	 * @return StatusFile
	 */
	public StatusFile getStatusFile() {
		return statusFile;
	}

	/**
	 * Define o estado do ficheiro - APPROVED ou NOTAPPROVED
	 * 
	 * @param StatusFile
	 * @return void
	 */
	public void setStatusFile(StatusFile statusFile) {
		this.statusFile = statusFile;
	}
}
