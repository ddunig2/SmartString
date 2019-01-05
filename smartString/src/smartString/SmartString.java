package smartString;

import java.util.Scanner;
import java.util.Stack;

public class SmartString {
	Stack<String> myStack = new Stack<String>();
	StringBuilder sbuilder = new StringBuilder();
	
	
	
	
	public void insert(int position, String subString) {
		sbuilder.insert(position-1, subString);
		myStack.push("I " + (position-1) +" " + subString.length());
	}

	public void delete(int position, int length) {
		int start = position-1;
		int end = (start+length);
		
		String removedString = "";
        if (sbuilder.length() >= position+length) {
            removedString = sbuilder.substring(start, end);
        }
        else {
            removedString = sbuilder.substring(start, sbuilder.length());
        }
        myStack.push("D " + start + " " + removedString);
        sbuilder.delete(start, end);

	}
	public int getLength() {
		return sbuilder.length();
		
	}

	public void undo() {
		Scanner sc;
		String str = " ";
		str = myStack.pop();
		sc = new Scanner(str).useDelimiter(" ");
		switch(str.charAt(0)) {
		case 'I' : sc.next();
			undoDelete(sc.nextInt(), sc.nextInt());
			break;
		case 'D' : sc.next();
			undoAdd(sc.nextInt(), sc.next());
			break;
		}
	}
		
		
		public void undoAdd(int position, String subString)
	{
		if (sbuilder.length() > position) {
            sbuilder.insert(position, subString);
        }
        else {
            sbuilder.insert(sbuilder.length(), subString);
        }

	}
	public void undoDelete(int position, int length)
	{
		if (sbuilder.length() >= position+length) {
            sbuilder.delete(position, position+length);
        }
        else {
            sbuilder.delete(position, sbuilder.length());
        }
	}
	
	
	 
	public String toString()
	 {
		 return sbuilder.toString();
	 }

}
