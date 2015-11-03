import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileReader;

import java.util.StringTokenizer;

public class Columnize{
    /*
      Author: Dustin Hu
      Date: 10-10-2014
      Purpose: To fit a input file into columns
      
      Fields: 
       
      Methods: 

          main: The Main of the program
      
          writeToFile: Writes the input to a file

          justifyRight: Right justifies
          justifyRight: Right justifies, given the width to justify to 

          justifyCentre: Centre justifies
          justifyCentre: Centre justifies, given the width to justify to 

          prepareForWriting: Prepares the array for writing
	  prepareForWriting: Prepares the array for writing, with a number of columns

          increaseSize: adds a line to the string array

          justifyFull: Justifies fully given a size
          justifyFull: Justifies a string fully

          toArray: Sticks the string into an array at every \n character

          justifyLeft: Justifies it left given a size
          justifyLeft: Justifies the string left
          getFile: Gets the file from the user
     */

    public static void main(String[] args) throws IOException{
	// Author: Dustin Hu
	// Date: 14-10-2014
	// Purpose: To be the m/ain 
	// Input: None
	// Output: None
	BufferedReader input = new BufferedReader(
						  new InputStreamReader(System.in));
	String [] left;
	String [] full;
	String [] columned;
	String file;
	String output;
	int width = 0; 
	int columns = 0;

	
	System.out.println("Your input file?");
	System.out.print("> ");
	file = getFile();

	while (width < 20 || width > 50){
	    System.out.println("The width of your columns (20 - 50)");
	    System.out.print("> ");
	    width = Integer.parseInt(String.valueOf(input.readLine()));
	}
	
	while (columns < 1 || columns > 4){
	    System.out.println("Number of columns (1-4)");
	    System.out.print("> ");
	    columns = Integer.parseInt(String.valueOf(input.readLine()));
	}
	
	left = justifyLeft(file, width);
	full = new String [left.length];

	for (int i = 0; i < left.length; i++){
	    full[i] = justifyFull(left[i], width);
	}

	columned = prepareForWriting(full, columns);
	
	System.out.println("The name of your output file? (Minus the extension)");
	output = input.readLine();
	
	writeToFile(output, columned);
	
	
    }
    
    public static void writeToFile(String outputName, String [] input) throws IOException{
	// Author: Dustin Hu
	// Date: 14-10-2014
	// Purpose: To write the input to a file
	// Input: THe file to write to (The name), and the input file
	// Output: None
	PrintWriter output = new PrintWriter(new FileWriter(outputName + ".txt"));
	for (int i = 0; i < input.length; i ++){
	    output.println(input[i]);
	}
	output.close();
    }

    public static String justifyRight(String input, int width){
	// Author: Dustin Hu
	// Date: 12-10-2014
	// Purpsoe: To right justify a string
	// Input: The string to justify & the width
	// Output: THe right justified string

	int spacesToThirtyFive;
	String spaces = "";

	input = input.trim();
	spacesToThirtyFive = width - input.length();
	for (int i = 0; i < spacesToThirtyFive; i++){
	    spaces = spaces + " ";
	}
	return spaces + input;
    }
    public static String justifyRight(String input){
	// Author: Dustin Hu
	// Date: 12-10-2014
	// Purpsoe: To right justify a string
	// Input: The string to justify
	// Output: THe right justified string

	int spacesToThirtyFive;
	String spaces = "";

	input = input.trim();
	spacesToThirtyFive = 35 - input.length();
	for (int i = 0; i < spacesToThirtyFive; i++){
	    spaces = spaces + " ";
	}
	return spaces + input;
    }

    public static String justifyCentre(String input){
	// Author: Dustin Hu
	// Date: 12-10-2014
	// Purpsoe: To centre justify a string
	// Input: The string to justify
	// Output: THe centre justified string

	
	input = input.trim();
	String spaces = "";
	int spacesToThirtyFive = 35 - input.length();

	for (int i = 0; i < spacesToThirtyFive / 2; i++){
	    spaces = spaces + " ";
	}

	return spaces + input;
	
    }
    public static String justifyCentre(String input, int width){
	// Author: Dustin Hu
	// Date: 12-10-2014
	// Purpsoe: To centre justify a string
	// Input: The string to justify an dthe width to justify to
	// Output: THe centre justified string

	
	input = input.trim();
	String spaces = "";
	int spacesToThirtyFive = width - input.length();
	for (int i = 0; i < spacesToThirtyFive / 2; i++){
	    spaces = spaces + " ";
	}
	return spaces + input;
	
    }


    public static String [] prepareForWriting (String [] input, int columns){
	// Author: Dustin Hu
	// Date: 12-10-2014
	// Purpose: Prepares the array for writing
	// Input: The input array
	// Output: THe array readied for wrtiting

	String current = "";
	String [] output = new String [1];
	int position = 0;
	int offset = input.length / columns;
	
	if (input.length % columns != 0){
	    offset = offset + 1;
	}

	for (int i = 0; i < offset; i++){
	    current = input[i];
	    position = i + offset; 
	    while (position < input.length){
		current = current + "     " + input[position];
		position = position + offset;
	    }
	    output[i] = current ;
	    output = increaseSize(output);
	    current = "";
	    
	}
	return output;
	// int position = 0;
	// int height = input.length / columns ;
	// String [] output = new String [1];
	// String spaces = "     ";
	// String current = "";

	// if (input.length % columns == 0){
	//     for (int i = 0; i < input.length / columns ; i++){
	// 	current = "";
	// 	for (int b = 0; b < columns ; b++ ){
	// 	    current = current + input[i + ((columns ) * b)] + spaces;
	// 	}
	// 	output[i] = current;
	// 	output = increaseSize(output);
	//     }
	// }
	// else{
	//     output = new String [input.length / columns + 1];
	//     for (int i = 0; i < output.length; i++){
	// 	output[i] = "";
	//     }

	//     while (position < input.length  ){
	// 	for (int i = 0; i < output.length; i++){
	// 	    if (position < input.length){
	// 		output[i] = output[i] + input[position] + spaces;
	// 		position++;
	// 	    }
	// 	}
	//     }
	// }
	// return output;
	
    }
    public static String [] prepareForWriting (String [] input){
	// Author: Dustin Hu
	// Date: 12-10-2014
	// Purpose: Prepares the array for writing
	// Input: The input array
	// Output: THe array readied for wrtiting
	
	String [] output = new String [1];

	if (input.length % 2 == 0){
	    for (int i = 0; i < (input.length / 2) - 1; i++){
		output[i] = input[i] + "     " + input[(input.length / 2) + i ];
		output = increaseSize(output);
	    }
	    output[(input.length / 2) - 1] = input[(input.length/2)] + "     " + input[input.length - 1];
	}
	else{
	    for (int i = 0; i < input.length / 2 + 1 ; i++){
		output[i] = input[i] + "     ";
		if (i != (input.length / 2)){
		    output = increaseSize(output);
		}
	    }
	    for (int i = 0; i < input.length / 2 + 1 ; i++){
		output[i] = output[i] + input[(input.length/2) + i];
	    }
	}
	return output;
	
    }


    public static String [] increaseSize(String [] input){
	// Author: Dusitn Hu
	// Date: 12-10-2014
	// Purpose: To add a line to the back of the array
	// Input: The original arry
	// Output: The array, one line larger
	
	String [] output = new String [input.length + 1];
	for (int i = 0; i < input.length; i++){
	     output[i] = input[i];
	 }
	 return output;
     }

    public static String justifyFull(String input, int size){
	// Author: Dustin Hu
	// Date: 11-10-2014
	// Purpose: To justify a single line fully
	// Input: The string to justify
	// OUtput: THe justified line
	String old = input;
	String output = "";
	String spaces = "";
	int spacesToThirtyFive = size - input.length();
	int spacesInInput = 0;

	for (int i = 0; i < input.length(); i++){
	    if (input.charAt(i) == ' '){
		spacesInInput++;
	    }
	}

	if (spacesToThirtyFive != 0 && spacesInInput != 0){
	    if (spacesInInput ==  spacesToThirtyFive){
		output = input.replaceAll(" ", "  ");
	    }

	    else if (spacesInInput > spacesToThirtyFive){
		for (int i = 0; i < spacesToThirtyFive; i++){
		    output = output + old.substring(0, old.indexOf(" ")) + "  ";
		    old = old.substring(old.indexOf(" ") + 1);
		}
		output = output + old;
	    }
	    else if (spacesToThirtyFive > spacesInInput){
		for (int i = 0; i < (spacesToThirtyFive / spacesInInput); i++){
		    spaces = spaces + " ";
		}
		spaces = spaces + " ";

		input = input.replaceAll(" ", spaces);
		old = old.replaceAll(" ", spaces);
	    
		for (int i = 0; i < spacesToThirtyFive % spacesInInput; i++){
		    output = output + old.substring(0, old.indexOf(spaces) + spaces.length())  + " ";
		    old = old.substring(old.indexOf(spaces) + spaces.length());
		}
		output = output + old;
	
	    }
	}
	else{
	    output = input;
	}
	return output;
	
    }
    public static String justifyFull(String input){
	// Author: Dustin Hu
	// Date: 11-10-2014
	// Purpose: To justify a single line fully
	// Input: The string to justify
	// OUtput: THe justified line
	String old = input;
	String output = "";
	String spaces = "";
	int spacesToThirtyFive = 35 - input.length();
	int spacesInInput = 0;

	for (int i = 0; i < input.length(); i++){
	    if (input.charAt(i) == ' '){
		spacesInInput++;
	    }
	}

	if (spacesToThirtyFive != 0 && spacesInInput != 0){
	    if (spacesInInput ==  spacesToThirtyFive){
		output = input.replaceAll(" ", "  ");
	    }

	    else if (spacesInInput > spacesToThirtyFive){
		for (int i = 0; i < spacesToThirtyFive; i++){
		    output = output + old.substring(0, old.indexOf(" ")) + "  ";
		    old = old.substring(old.indexOf(" ") + 1);
		}
		output = output + old;
	    }
	    else if (spacesToThirtyFive > spacesInInput){
		for (int i = 0; i < (spacesToThirtyFive / spacesInInput); i++){
		    spaces = spaces + " ";
		}
		spaces = spaces + " ";

		input = input.replaceAll(" ", spaces);
		old = old.replaceAll(" ", spaces);
	    
		for (int i = 0; i < spacesToThirtyFive % spacesInInput; i++){
		    output = output + old.substring(0, old.indexOf(spaces) + spaces.length())  + " ";
		    old = old.substring(old.indexOf(spaces) + spaces.length());
		}
		output = output + old;
	
	    }
	}
	else{
	    output = input;
	}
	return output;
	
    }

    public static String [] toArray(String input){
	// Author: DUstin Hu
	// Date: 11-10-2014
	// Purpose: To change the string into an array at every \n character
	// Input: The string to put in
	// Output: THe string [] with the input in it


	int size = 0;

	String current = "";
	String [] output;

	for (int i = 0; i < input.length(); i++){
	    if (input.charAt(i) == '\n'){
		size++;
	    }
	}
	output = new String [size];

	for (int i = 0; i < size - 1; i++){
	    current = input.substring(0, input.indexOf("\n"));
	    output[i] = current;
	    input = input.substring(input.indexOf("\n") + 1);
	}
	output[size - 1] = input;
	return output;

}

    public static String[] justifyLeft(String input, int size){
	// Author: Dustin Hu
	// Date: 10-10-2014
	// Purpose: Justifies a single line to the left
	// Input: The string to justify & the size to justify to
	// OUtput: The justified string

	int line = 0;
	String current = "";
	StringTokenizer splitWords = new StringTokenizer(input);
	String [] output = new String[1];
	String [] words = new String [splitWords.countTokens()];
	
	for (int i = 0; i < words.length; i++ ){
	    words[i] = splitWords.nextToken();
	}


	for (int i = 0; i < words.length; i++){
	    if ((current + words[i]).length()  < size){
		current = current + words[i].trim() + " ";
	    }
	    else{
		output[line] = current.trim();
		line = line + 1;
		output = increaseSize(output);
		current = words[i] + " ";
	    }
	}
	output[line] = current.trim();
	

	return output;
    }
    public static String[] justifyLeft(String input){
	// Author: Dustin Hu
	// Date: 10-10-2014
	// Purpose: Justifies a single line to the left
	// Input: The string to justify
	// OUtput: The justified string

	int line = 0;
	String current = "";
	StringTokenizer splitWords = new StringTokenizer(input);
	String [] output = new String[1];
	String [] words = new String [splitWords.countTokens()];
	
	for (int i = 0; i < words.length; i++ ){
	    words[i] = splitWords.nextToken();
	}


	for (int i = 0; i < words.length; i++){
	    if ((current + words[i]).length()  < 35){
		current = current + words[i].trim() + " ";
	    }
	    else{
		output[line] = current.trim();
		line = line + 1;
		output = increaseSize(output);
		current = words[i] + " ";
	    }
	}
	output[line] = current.trim();
	

	return output;

	// boolean loop = true;
	
	
	// int nextWordPosition = 0;
	// int currentLength = 0;
	
	// String current = "";
	// String output = "";
	// input = input.trim();

	// while (loop){
	//     if (input.indexOf(" ") == -1){
	// 	loop = false;
	//     }
	//     else if (input.length() == 0){
	// 	loop = false;
	//     }
	//     else{
	// 	if (currentLength < 35){
	// 	    input = input.trim();
	// 	    nextWordPosition = input.indexOf(" ");
	// 	    if ((currentLength + nextWordPosition) < 35) {
	// 		current = current +  input.substring(0, nextWordPosition).trim() + " ";

	// 		input = input.substring(nextWordPosition + 1).trim();

	// 		currentLength = current.length();
	// 	    }
	// 	    else{
	// 		currentLength = 36;
	// 	    }
	// 	}
	// 	else{
	// 	    output = output +  current + "\n";
	// 	    current = "";
	// 	    currentLength = 0;
	// 	}
	//     }

	// }

	// return output;
    }

    public static String getFile() throws IOException{
	// Author: Dustin Hu
	// Date: 10-10-2014
	// Purpose: To get a file from the user
	// Input: None
	// OUtput: THe entire file turned into a string
	String fileName;
	String output = "";

	BufferedReader file;
	BufferedReader userInput = new BufferedReader(
						      new InputStreamReader(System.in));

	fileName = userInput.readLine();

	file = new BufferedReader(new FileReader(fileName));

	while (file.ready()){
	    output = output + file.readLine();
	}
	return output;
    }

}
