package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.BorderLayout;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;

import javafx.stage.FileChooser;
import testResultClasses.ExerciseCategory;
import testResultClasses.Player;
import testResultClasses.PlayerResult;
import testResultClasses.TestResult;

import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JProgressBar;

public class MainWindow {

	private JFrame frame;
	private JTextPane textGeneratedFiles;
	private JPanel panel;
	private JButton btnGenerate;
	private JTextField textField;
	private JButton btnLoadFile;
	private String excelFilePath;
	private JProgressBar progressBar;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		textField = new JTextField();
		textField.setEditable(false);
		frame.getContentPane().add(textField, BorderLayout.NORTH);
		textField.setColumns(10);
		
		progressBar = new JProgressBar();
		progressBar.setVisible(true);
		panel.add(progressBar);
		
		btnGenerate = new JButton("Generate results");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Application application = new Application();
				application.setExcelFilePath(textField.getText());
				application.run(progressBar);
				textField.setText("PDF generated");
			}
		});
		panel.add(btnGenerate);
		
		btnLoadFile = new JButton("Load excel file");
		btnLoadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int returnedValue = fileChooser.showOpenDialog(panel);
				
				if(returnedValue == JFileChooser.APPROVE_OPTION) {
					excelFilePath = fileChooser.getSelectedFile().getAbsolutePath();
					textField.setText(excelFilePath);
				}
			}
		});
		panel.add(btnLoadFile);
	}

}
