package smartString;

import java.awt.*;
import java.awt.event.*;
//import java.util.Stack;

import javax.swing.*;

public class Application extends JFrame {
	private JButton b1, b2, b3;
	private JTextArea field;
	SmartString myString = new SmartString();

	public static void main(String[] args) {
		{
			Application myFrame = new Application();
			myFrame.setSize(250, 380);
			myFrame.setTitle("SmartString");
			myFrame.createGUI();
			myFrame.show();
		}
	}
	private void createGUI() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container pane = getContentPane();
		pane.setLayout(new FlowLayout());
		pane.setBackground(Color.WHITE);

		b1 = new JButton("Add");
		b1.addActionListener(new addButton());
		b2 = new JButton("Delete");
		b2.addActionListener(new deleteButton());
		b3 = new JButton("Undo");
		b3.addActionListener(new undoButton());
		field = new JTextArea(16,16);
        field.setLineWrap(true);
        field.setEditable(false);
        field.setBackground(Color.gray);
        field.setBorder(BorderFactory.createTitledBorder(
        BorderFactory.createEtchedBorder(), "Your String"));
        field.setAlignmentX(CENTER_ALIGNMENT);

		pane.add(b1);
		pane.add(b2);
		pane.add(b3);
		pane.add(field);
	}

	private class addButton implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (myString.getLength() == 0)
			{
				String subString = JOptionPane.showInputDialog("Enter your string");
				myString.insert(1, subString);
			}
			else {
				String subString = JOptionPane.showInputDialog("Enter your string");
				String positionString = JOptionPane.showInputDialog("Please enter the position you would like to insert to");
				int positionStart =Integer.parseInt(positionString);
				if (positionStart > myString.getLength()+1) {
					myString.insert(myString.getLength()+1, subString);
				}
				else if (positionStart < 1) 
				{
					JOptionPane.showMessageDialog(null,"Please enter a position starting at 1","Error", JOptionPane.ERROR_MESSAGE);

				}
				else {
					myString.insert(positionStart, subString);
				}
			}
			field.setText(myString.toString());			
		}
	}

	private class deleteButton implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (myString.getLength() == 0) {
				JOptionPane.showMessageDialog(null,"You have not built a string yet, there is nothing to delete","Error", JOptionPane.ERROR_MESSAGE);
			}
			else{
				String positionString = JOptionPane.showInputDialog("Please enter the position you would like to delete from, starting at 1"
						);
				String lenghtString = JOptionPane.showInputDialog("How many charecters do you wich to delete?");
				int positionStart =Integer.parseInt(positionString);
				int length =Integer.parseInt(lenghtString);
				if (myString.getLength() < length) {
					int n = JOptionPane.showConfirmDialog(
					null,"You have entered a length that is greater then your string\nwould you like to delete all?",
					lenghtString, JOptionPane.YES_NO_OPTION);
					if(n == JOptionPane.YES_OPTION) {
					myString.delete(positionStart, myString.getLength());
					}
					else {
					}
				}
				else if (positionStart>myString.getLength()+1)
				{
		            JOptionPane.showMessageDialog(null,"This position does not exist please enter another position","Error",JOptionPane.ERROR_MESSAGE);

				}
				else if(positionStart < 1) {
					JOptionPane.showMessageDialog(null,"Please enter a position starting at 1","Error", JOptionPane.ERROR_MESSAGE);

				}
				else {
					myString.delete(positionStart, length);
				}
			}
			field.setText(myString.toString());
		}
					
	}
	private class undoButton implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (myString.getLength() == 0)
			{
	            JOptionPane.showMessageDialog(null,"Sorry your list is empty","Error",JOptionPane.ERROR_MESSAGE);
			}
			else {
			myString.undo();
			field.setText(myString.toString());
			}

		}
	}
	
}