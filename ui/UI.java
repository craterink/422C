package ui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Provides interface to interact with user.
 * @author Cooper
 *
 */
public class UI {
	
	/**
	 * Gets an input string from the user.
	 * @param windowTitle Title of prompt window
	 * @param prompt Prompt for user
	 * @return String entered by user
	 */
	public static String getInputString(String windowTitle, String prompt) {
		JFrame frame = new JFrame(windowTitle);
		return JOptionPane.showInputDialog(frame, prompt);
	}
	
	/**
	 * Asks the user a YES-NO question and gets the response. 
	 * @param windowTitle Title of confirm window
	 * @param prompt Prompt for user
	 * @return User response (true = yes)
	 */
	public static boolean confirm(String windowTitle, String prompt) {
		return JOptionPane.showConfirmDialog(null, prompt, windowTitle, 
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
	}
	
	/**
	 * Displays a message to the user.
	 * @param windowTitle Alert Dialog window title
	 * @param alert Alert message
	 */
	public static void showAlert(String windowTitle, String alert) {
		JFrame frame = new JFrame(windowTitle);
		JOptionPane.showMessageDialog(frame, alert);
	}
}
