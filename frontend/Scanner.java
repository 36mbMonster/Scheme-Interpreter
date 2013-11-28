package frontend;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PushbackInputStream;
import java.util.ArrayList;

/**
* @author Jonathan Vaccaro
* @date 2013
*
* Scheme interpreter token scanner v2
* Scan your tokens with style!
*/

public class Scanner
{
	private final String[] SYSTEM_WORDS =
	{
		"and", "begin", "cond", "define", "else",
        "if", "lambda", "let", "letrec", "let*",
        "not", "or", "quote", "cons", "car", "cdr"
	};

	private final char[] SYMBOLS = 
	{
		'{', '}', '(', ')', '[', ']',
		',', '\'', '\\', '\"', '#' 
	};

	String currentToken;
	File raw;
	PushbackInputStream iStream;

	public Scanner(){}
	public Scanner(File raw) throws IOException
	{
		this.raw = raw;
		iStream = new PushbackInputStream(new FileInputStream(raw));
		currentToken = "";
	}

	public String[] nextToken() throws IOException
	{
		//Return array of size two containing the token and its type.
		String[] out = new String[2];
		int currentChar = iStream.read();

		if(currentChar != -1)
		{
			//It's a symbol.
			if(isSymbol(currentChar))
			{
				//This is for if the first char is a symbol
				//because currentToken would be empty.
				if(currentToken == "")
				{
					out[0] = ""+(char)currentChar;
					out[1] = processType(out[0]);
				}
				//If the first token has been done already.
				else
				{
					//finish current token
					out[0] = currentToken;
					out[1] = processType(currentToken);
					//make new token for symbol
					currentToken = new String(""+(char)currentChar);
				}
			}
			//It's a comment.
			else if((char) currentChar == ';')
			{
				skipLine();
				return nextToken();
			}
			//It's whitespace.
			else if((char) currentChar == ' ' || (char) currentChar == '\n' 
				|| (char) currentChar == '\r')
			{
				//finish current token, if not empty.
				if(!currentToken.trim().equals(""))
				{
					out[0] = currentToken;
					out[1] = processType(currentToken);
					//make new current token
					currentToken = new String("");
					return out;
				}

				//skip the whitespace
				skipWhitespace(currentChar);
				//look for more tokens if we don't
				//have any currently.
				if(out[0] == null)
					return nextToken();
			}
			//It's a number or word of some kind.
			else
			{
				//We can't return the token yet because it
				//hasn't been completely built yet.
				currentToken += (char)currentChar;
				//recursively call nextToken() until we have
				//a complete string to return.
				out[0] = nextToken()[0];
				//null token protection
				if(out[0] != null)
				{
					//We have a complete token!
					//Evaluate its type and reset currentToken.
					out[1] = processType(out[0]);
					currentToken = "";
				}
			}
		}
		else
			iStream.close();

		return out;
	}

	private boolean isSymbol(int in)
	{
		char achar = (char) in;

		for(int i = 0; i < SYMBOLS.length; i++)
		{
			if(achar == SYMBOLS[i])
				return true;
		}
		return false;
	}

	private boolean isNumber(int in) throws NumberFormatException
	{
		char achar = (char) in;

		if(Character.isDigit(achar) || achar == '.')
        	return true;
        return false;
	}

	private boolean isNumber(String token) throws NumberFormatException
	{
		for(int i = 0; i < token.length(); i++)
		{
			if(!isNumber(token.charAt(i)))
				return false;
		}
		return true;
	}

	private boolean isSystemWord(String token) 
	{
    	for (int i = 0; i < SYSTEM_WORDS.length; i++) 
    	{
   			if (token.equals(SYSTEM_WORDS[i])) 
    			return true;
    	}
    	return false;
    }

	private void skipLine() throws IOException
	{
		int current = iStream.read();
		while((char)current != '\n' && (char)current != '\r')
			current = iStream.read();
		if((char)current == ';')
			skipLine();
	}

	private void skipWhitespace(int in) throws IOException
	{
		int current = in;
		while(current == ' ' || current == '\n' || current == '\r')
			current = iStream.read();
		//unread the character that wasn't whitespace
		//so it can be processed by nextToken().
		iStream.unread(current);	
	}

	public String processType(String token)
	{
        if (token.length() == 1 && isSymbol(token.charAt(0))) 
            return "symbol";
        else if (isNumber(token)) 
            return "number";
        else if (isSystemWord(token)) 
            return "reserved_word";
        else
        	return "word";
	}
} 
