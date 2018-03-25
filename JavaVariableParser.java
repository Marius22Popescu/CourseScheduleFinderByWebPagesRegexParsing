import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Assignment 1 - Programming Languages - 01/20/2018
//Student: Marius Popescu
//Instructor: Fatma Serce
//Description: This program reads a Java source file and extracts all the variables types, names and values defined in that class.

public class JavaVariableParser {

	public static void main (String[] args) throws FileNotFoundException {
		//Input the file
		File file = new File("A.java");
		Scanner scanner = new Scanner(file);
		String input = "";
		String text = "";
		while(scanner.hasNextLine() ) {
			input = scanner.nextLine();
			text += input + "\n";
		}
		//Creating the pattern, searching for the variable an display them
		Pattern pattern = Pattern.compile("\\s* ([a-zA-Z]*) ([a-zA-Z]*)\\s?(= (.*))?;");
		Matcher matcher = pattern.matcher(text);
		while(matcher.find()) {
		System.out.println("Type: " + matcher.group(1));
    		System.out.println("Variable name: " + matcher.group(2));
    		System.out.println("Value: " + matcher.group(3));
    		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		}
	}	
}  
//*************************************************END OF PROGRAM***********************************************//
