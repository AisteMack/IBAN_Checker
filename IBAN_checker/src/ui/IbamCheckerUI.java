package ui;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.SwingConstants;

import checker.Checker;
import checker.Iban;
import checker.IbanFile;
import java.awt.Color;

public class IbamCheckerUI {

	private JFrame frmIbanChecker;
	private JTextField textField_name;
	private JTextField textField_path;
	private Checker checker;
	/**
	 * Launch the application.
	 */
	
	public void run() {
				try {
					IbamCheckerUI window = new IbamCheckerUI(this.checker);
					window.frmIbanChecker.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
	}
	

	/**
	 * Create the application.
	 */
	public IbamCheckerUI(Checker checker) {
		initialize();
		this.checker = checker;
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmIbanChecker = new JFrame();
		frmIbanChecker.setTitle("IBAN Checker");
		frmIbanChecker.setBounds(100, 100, 450, 350);
		frmIbanChecker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmIbanChecker.getContentPane().setLayout(null);
		
		textField_name = new JTextField();
		textField_name.setBounds(72, 235, 290, 20);
		frmIbanChecker.getContentPane().add(textField_name);
		textField_name.setColumns(10);
		textField_name.setVisible(false);
		
		textField_path = new JTextField();
		textField_path.setBounds(72, 174, 290, 20);
		frmIbanChecker.getContentPane().add(textField_path);
		textField_path.setColumns(10);
		textField_path.setVisible(false);
		
		JRadioButton rdbtn_interactive = new JRadioButton("Check IBAN number interactive");
		rdbtn_interactive.setBounds(72, 69, 290, 23);
		frmIbanChecker.getContentPane().add(rdbtn_interactive);
		
		JRadioButton rdbtn_file = new JRadioButton("Check IBAN numbers from a text file");
		rdbtn_file.setBounds(72, 98, 290, 23);
		frmIbanChecker.getContentPane().add(rdbtn_file);
		
		ButtonGroup editableGroup = new ButtonGroup();
	    editableGroup.add(rdbtn_interactive);
	    editableGroup.add(rdbtn_file);
	    
		JButton btnNewButton = new JButton("Validate IBAN");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(96, 205, 24));
		btnNewButton.setBounds(238, 277, 124, 23);
		frmIbanChecker.getContentPane().add(btnNewButton);
		
		
		JLabel label_path = new JLabel("New label");
		label_path.setBounds(72, 149, 290, 14);
		frmIbanChecker.getContentPane().add(label_path);
		label_path.setVisible(false);
		
		JLabel label_name = new JLabel("New label");
		label_name.setBounds(72, 210, 290, 14);
		frmIbanChecker.getContentPane().add(label_name);
		label_name.setVisible(false);
		
		JLabel lable_title = new JLabel("IBAN Checker");
		lable_title.setHorizontalAlignment(SwingConstants.CENTER);
		lable_title.setFont(new Font("Tahoma", Font.PLAIN, 29));
		lable_title.setBounds(72, 11, 290, 34);
		frmIbanChecker.getContentPane().add(lable_title);
		
		JLabel lable_answer = new JLabel("New label");
		lable_answer.setBounds(72, 266, 156, 34);
		frmIbanChecker.getContentPane().add(lable_answer);
		lable_answer.setVisible(false);
		
		rdbtn_file.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	rdbtn_file.setForeground(new Color(96, 205, 24));
	        	rdbtn_interactive.setForeground(new Color(0,0,0));
	        	lable_answer.setVisible(false);
	        	textField_name.setVisible(true);
	        	textField_name.setText("");
	            label_name.setVisible(true);
	            label_path.setVisible(true);
	            textField_path.setVisible(true);
	            textField_path.setText("");
	            btnNewButton.setVisible(true);
	            label_path.setVisible(true);
	            label_path.setText("Please enter file path here:");
	            label_name.setText("Please enter file name(file.txt) here:");
	        }
	    });

	    rdbtn_interactive.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	rdbtn_interactive.setForeground(new Color(96, 205, 24));
	        	rdbtn_file.setForeground(new Color(0, 0, 0));
	        	lable_answer.setVisible(false);
	            textField_name.setVisible(false);
	            label_name.setVisible(false);
	            label_path.setVisible(true);
	            textField_path.setText("");
	            textField_path.setVisible(true);
	            btnNewButton.setVisible(true);
	            label_path.setVisible(true);
	            label_path.setText("Please enter IBAN here:");
	            

	        }
	    });
	    
	    btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtn_interactive.isSelected()) {
					String iban;
					iban = textField_path.getText();
					if(checker.checkIban(new Iban(iban))) {
						lable_answer.setText("IBAN is valid");
						lable_answer.setVisible(true);
					}else {
						lable_answer.setText("Invalid IBAN");
						lable_answer.setVisible(true);
					}
					
				}else if(rdbtn_file.isSelected()) {
					String path = textField_path.getText();
					String name = textField_name.getText();
					lable_answer.setText("Checking...");
					lable_answer.setVisible(true);
					try {
						IbanFile fileChecker = new IbanFile(path, name, checker);
						if(fileChecker.checkIbans(path, name)) {
							lable_answer.setText("Checking competed");
						}
					} catch (FileNotFoundException e1) {
						lable_answer.setText("Error: File not Found");
					} catch (IOException e1) {
						lable_answer.setText(e1.getMessage());
					}
				}
			}
		});
	}
}
