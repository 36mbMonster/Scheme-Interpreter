
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Scanner {

    private int index = 0;
    private char[] raw;
    
    ArrayList<String> trueLines = new ArrayList<String>();
    
    private String currentToken = "";
    
    private final String[] SYSTEM_WORDS = {
        "and", "begin", "cond", "define", "else",
        "if", "lambda", "let", "letrec", "let*",
        "not", "or", "quote", "cons", "car", "cdr"
    };
    private final char[] SYMBOLS = {
        '!', '@', '#', '$', '%', '^', '&',
        '(', ')', '-', '_', '+', '=', '{', '}',
        '[', ']', '|', '\\', ':', '\"', '\'',
        '<', ',', '>', '.', '/', '~', '`'
    };

    public Scanner(File input) throws IOException {
        FileInputStream inStream = new FileInputStream(input);
        raw = new char[Character.MAX_VALUE];
        index = 0;
        String currentLine = "";

        int i = 0;
        int current = inStream.read();
        while (current != -1) 
        {
      	  	if(current != '\n')
      	  		currentLine += (char)current;
      	  	else
      	  	{
      	  		trueLines.add(currentLine);
      	  		currentLine = "";
      	  		//System.out.println(trueLines.get(trueLines.size()-1));
      	  	}
      	  	
            raw[i] = (char) current;
            current = inStream.read();
            i++;
        }
        inStream.close();
    }

    public Scanner() {
    }

    public ArrayList<String> readInTokens() {
        ArrayList<String> tokens = new ArrayList<String>();

        String token = "";
        boolean comment = false;
        for (int i = 0; i < raw.length; i++) {
            if (raw[i] == ';') {
                comment = true;
            } else if (raw[i] == ' ' && !comment) {
                token = token.trim();
                if (!token.equals("")) {
                    tokens.add(token);
                }
                token = "";
            } else if (isSymbol(raw[i]) && !comment) {
                token = token.trim();
                if (!token.equals("")) {
                    tokens.add(token);
                }
                tokens.add(new String("" + raw[i]));
                token = "";
            } else if (!comment) {
                token += raw[i];
            } else if (comment && raw[i] == '\n') {
                comment = false;
            }
        }

        return tokens;
    }

    public String[] nextToken() 
    {
        String out[] = new String[2];
        
//        if (raw[index] == ';') 
//        {
//      	  
//      	  skipThroughComments();
//        } 
//        if (raw[index] == ' ') 
//        {
//            currentToken = currentToken.trim();
//            
//            if (!currentToken.equals("")) 
//            {
//                out[0] = currentToken;
//            }
//            currentToken = "";
//            index++;
//        } 
        
//        if(raw[index] == '\n')
//      	  System.out.println(trueLines.remove(0));
        
        while(raw[index] == ';' || raw[index] == ' ')
        {
	        if(raw[index] == ';') 
	      	  skipThroughComments();
	        
	        if(raw[index] == ' ')
	        {
	           currentToken = currentToken.trim();
	            
	           if (!currentToken.equals("")) 
	           {
	         	  out[0] = currentToken;
	           }
	           else
	         	  skipThroughWhitespace();
	        }
        }
        
        if (isSymbol(raw[index])) 
        {
            currentToken = currentToken.trim();
            if (!currentToken.equals("")) 
            {
                out[0] = currentToken;
            } 
            else 
            {
                out[0] = new String("" + raw[index]);
                index++;
                currentToken = "";
            }
        }
        else
        {
      	  //If the token is a word
      	  while(raw[index] != ' ')
      	  {
      		  	currentToken += raw[index];
      	  		index++;
      	  }
      	  out[0] = currentToken;
      	  currentToken = "";
        }

        //System.out.println(out[0]);
        if (out[0].length() == 1 && isSymbol(out[0].charAt(0))) 
            out[1] = "symbol";
        else if (isNumber(out[0])) 
            out[1] = "number";
        else if (isSystemWord(out[0])) 
            out[1] = "reserved_word";
        else
            out[1] = "word";

        System.out.print(out[0] + " " + out[1] + " ");
        return out;

    }

    private void skipThroughComments()
    {

       while(raw[index] != '\n')
           index++;

       index++;
       
       if(raw[index] == ';')
      	 skipThroughComments();
       else
      	 return;
    }
    
    private void skipThroughWhitespace()
    {
   	 while(raw[index] == ' ')
   	 {
   		 index++;
//   		 if(raw[index] == '\n')
//   			 System.out.println(trueLines.remove(0));
   	 }
    }
    
    //Simple helper method for determining whether the character is a symbol
    //by using constants within the Character class.
    private boolean isSymbol(char achar) {
        for (char c : SYMBOLS) {
            if (achar == c) {
                return true;
            }
        }
        return false;
    }

    //Simple helper method for determining whether the character is a number
    private boolean isNumber(String token) {
        try {
            Integer.parseInt(token);
            return true;
        } catch (NumberFormatException nfe) {
        }
        return false;
    }

    //Simple helper method for determining whether the character is a reserved
    //system word or not.
    private boolean isSystemWord(String token) {
        for (int i = 0; i < SYSTEM_WORDS.length; i++) {
            if (token.equals(SYSTEM_WORDS[i])) {
                return true;
            }
        }
        return false;
    }

    public void printTokens(ArrayList<String> tokens) {
        for (int i = 0; i < tokens.size(); i++) {
            String current = tokens.get(i);
            System.out.print(current);
            if (isSymbol(current.charAt(0))) {
                System.out.println(" symbol");
            } else if (isSystemWord(current)) {
                System.out.println(" reserved word");
            } else if (isNumber(current)) {
                System.out.println(" number");
            } else {
                System.out.println(" word");
            }
        }
    }
}
