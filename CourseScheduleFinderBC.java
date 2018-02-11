import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Assignment 1 - Programming Languages - 01/20/2018
//Student: Marius Popescu
//Instructor: Fatma Serce
//Description: This program will provide the user with the course schedule for a particular course
//in a given program at Bellevue College.

public class CourseScheduleFinderBC {

	public static void main(String[] args) throws Exception {
		
	  //Create a InputStreamReader using the standard input stream
		InputStreamReader isr = new InputStreamReader(System.in);
	  //Create a BufferReader using the InputStreamReader created
		BufferedReader answer = new BufferedReader(isr);
	  //Prompt the user for the quarter and read the answer using the BufferReader
		System.out.println("Enter the quarter: "); 
		String quarter = answer.readLine();
	  //Prompt the user for the year and read the answer using the BufferReader
		System.out.println("Enter the year: "); 
		String year = answer.readLine();
	  //Prompt the user for the program initial letter and read the answer using the BufferReader
		System.out.println("Enter the initial for the program: "); 
		String programInitial = answer.readLine();

      //Updating the URL and searching for classes
	    URL bc = new URL("https://www.bellevuecollege.edu/classes/"+quarter+year);
	  //Create a BufferReader in order to input the web page
	    BufferedReader in = new BufferedReader(new InputStreamReader(bc.openStream()));  
	    String inputLine = "";
	    String text = "";
	    while ((inputLine = in.readLine()) != null) {  
	        text += inputLine + "\n";                 
	    } 
	    in.close();
	    
	  //Create a pattern, check and display the programs that are starting with the desired program initial letter
	    Pattern pattern = Pattern.compile("<a href=\".*\">("+programInitial+".*)</a> (\\(.*\\))");
	    Matcher matcher = pattern.matcher(text);
	    System.out.println("Programs:");
	    while(matcher.find()) {
	    		System.out.println( matcher.group(1)+ matcher.group(2) );	    		
	    }
	  //Prompt the user for the program name and read the answer using the BufferReader
	    System.out.println("Enter the Program's name: ");
	  	String programName = answer.readLine();
	  //Prompt the user for the course ID and read the answer using the BufferReader
	    System.out.println("Enter the course ID: ");
	  	String courseID = answer.readLine();	
	  //Get rid of a everything after the space and obtain the abbreviation for the class name
	  	String nAbbrev = courseID.split("\\ ")[0];
	  	
	  //Updating the URL and searching for the desired class
	    bc = new URL("https://www.bellevuecollege.edu/classes/"+quarter+year+"/"+nAbbrev);   
	    BufferedReader in1 = new BufferedReader(new InputStreamReader(bc.openStream()));
	    inputLine = "";
	    text = "";
	    while ((inputLine = in1.readLine()) != null) {  
	        text += inputLine + "\n";                 
	    } 
	    in.close();
	  	
	  //Create a pattern, check and display the desired course information
	    pattern = Pattern.compile("<span class=\"courseID\">"+courseID+"</span> <span class=\"courseTitle\">(.*)</span>"
	    	+ "[\\S\\s]*?Item number: </span>(.*)</span>[\\S\\s]*?<a href=\"https://www.bellevuecollege.edu/directory/.*\">(.*)</a>");
	    matcher = pattern.matcher(text);
	    while(matcher.find()) {
	    		System.out.println("=============================================================");
	    		System.out.println("Code: "+courseID);
	    		System.out.println( "Title: " +matcher.group(1));
	    		System.out.println( "Item#: " +matcher.group(2)); 
	    		System.out.println( "Instructor: " +matcher.group(3));  
	    		System.out.println( "Days: " );  //+matcher.group(4)
	    		System.out.println("=============================================================");
	    }	  		
	}	
}
//*****************************************************END OF PROGRAM*************************************************//