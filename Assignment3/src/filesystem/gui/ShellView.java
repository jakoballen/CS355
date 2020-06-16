/*
 * FileSystemView - view class to display file system interaction through shell
 * 
 * Last Modified - Paul J. Wagner, 3/5/2020
 */
package filesystem.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import filesystem.general.Shell;
import filesystem.utilities.Observer;

public class ShellView extends JFrame implements Observer {
	// - data
	private static final long serialVersionUID = 1L; 	// serial id 
	private Shell shell = null;							// the shell environment
	JLabel    commandInputLabel = null;					// command input label
	JTextField commandInputText = null;					// command input text field
	JLabel    consoleDisplayLabel = null;				// console output display label
	JTextArea consoleDisplayArea = null;				// console output display area
	JLabel    fileDisplayLabel = null;					// file system display label
	JTextArea fileSystemDisplayArea = null;				// file system display area

	// - methods
	// -- constructors
	// --- file system constructor
	public ShellView(Shell shell) {
		super();
		this.shell = shell;
		initialize();
	}	// end - constructor
	
	// -- other methods
	// --- initialize - set up the graphical display
	private void initialize() {

		JPanel fileSystemApplicationContentPane = new JPanel();		// overall content pane
		JLabel title = new JLabel();							// title for pane

		Button runButton = new Button("Execute");	// button to execute a single command
					
		try {
			// set up the application frame
			setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
			if(System.getProperty("os.name").toLowerCase().contains("mac os x")) {
				setSize(640, 580);
			}else{
				setSize(655, 600);
			}
			setTitle("File System Project");
	
			// construct the title label
			title.setFont(new java.awt.Font("Arial", 1, 36));
			title.setText("Shell / File System");
			title.setBounds(13, 10, 600, 40);
			title.setForeground(java.awt.Color.blue);
			title.setHorizontalAlignment(SwingConstants.CENTER);
			
			// construct the input text label
			commandInputLabel = new JLabel("Command Input: ");
			commandInputLabel.setBounds(30, 50, 150, 25);
			
			// construct the input text field
			commandInputText = new JTextField(50);
			commandInputText.setBounds(30, 70, 275, 30);
			
			// construct the console display label
			consoleDisplayLabel = new JLabel("Console Output Display: ");
			consoleDisplayLabel.setBounds(30, 140, 200, 25);
			
			// construct the console display text area
			consoleDisplayArea = new JTextArea(10, 35);
			consoleDisplayArea.setBounds(30, 160, 275, 370);
			consoleDisplayArea.setEditable(false);
			
			// construct the file display label
			fileDisplayLabel = new JLabel("File System Display: ");
			fileDisplayLabel.setBounds(340, 50, 200, 25);
			
			// construct the file system display text area
			fileSystemDisplayArea = new JTextArea(10, 35);
			fileSystemDisplayArea.setBounds(335, 70, 275, 460);
			fileSystemDisplayArea.setText(shell.getFileSystem().genDisplay());
			fileSystemDisplayArea.setEditable(false);

			// construct the Execute button	
			runButton.setBounds(30, 100, 100, 35);
			runButton.addActionListener( new ActionListener(){
				public void actionPerformed(ActionEvent e){						
					// set which tests are to be executed based on check boxes
					String inputString = getInputText();	
					// execute those tests
					String resultString = shell.executeCurrentCommandString(inputString);
					// display it
					consoleDisplayArea.setText(resultString);
				} } );

			// --- construct the highest level content-pane
			fileSystemApplicationContentPane.setLayout(null);
			fileSystemApplicationContentPane.add(title);
			fileSystemApplicationContentPane.add(commandInputLabel);	
			fileSystemApplicationContentPane.add(commandInputText);
			fileSystemApplicationContentPane.add(consoleDisplayLabel);	
			fileSystemApplicationContentPane.add(consoleDisplayArea);
			fileSystemApplicationContentPane.add(fileDisplayLabel);
			fileSystemApplicationContentPane.add(fileSystemDisplayArea);
			fileSystemApplicationContentPane.add(runButton);
				
			// --- finally, set the content pane
			setContentPane(fileSystemApplicationContentPane);
		} catch (java.lang.Throwable ivjExc) {
			System.err.println("Exception occurred in initialize() of File System Application");
			ivjExc.printStackTrace(System.out);
		}
	}	// end - method initialize

	// -- update() - update display when file system changes
	public void update(){
		fileSystemDisplayArea.setText(shell.getFileSystem().genDisplay());
	}
	
	/**
	 * getInputText - get the input text from a text field
	 * @return String
	 */
	public String getInputText() {
		String result = commandInputText.getText();
		return result;
	}	// end - method getInputText

	/**
	 * main - starts the application.
	 * @param args an array of command-line arguments
	 */
	public static void main(java.lang.String[] args) {
		
		try {
			// set up the models and controllers
			//FileSystem theFileSystem = new FileSystem();
			Shell theShell = new Shell();
			ShellView aShellView = new ShellView(theShell);
			theShell.getFileSystem().getRoot().registerObserver(aShellView);
	
			// set up window closure
			aShellView.addWindowListener(new java.awt.event.WindowAdapter() {
				public void windowClosed(java.awt.event.WindowEvent e) {
					System.exit(0);
				};
			});
	
			// enable window visibility
			aShellView.setVisible(true);

		} catch (Throwable exception) {
			System.err.println("Exception occurred in main() of ShellView");
			exception.printStackTrace(System.out);
		}
	}	// end - method main

}	// end - class FileSystemView
