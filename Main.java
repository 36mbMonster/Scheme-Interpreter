/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Steven
 */

public class Main {

	public String car(ArrayList<String> a)
	{
		if (a.size() != 0)
			return a.get(0);
		else
			return null;

	}
	
	public ArrayList<String> cdr(ArrayList<String> a)
	{
		ArrayList<String> b = new ArrayList<String>();
		if (a.size() == 0)
			return b;
		else
		{
			for (int i = 1; i < a.size(); i++)
				b.add(a.get(i));
			return b;
		}
	}
	
	public ArrayList<String> cons(String s, ArrayList<String> a)
	{
		a.add(0, s);
		return a;
	}
    
	public boolean is_null(ArrayList<String> a)
	{
		if (a.size() == 0)
			return true;
		else
			return false;
	}
	
	/**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        File input = new File("input.lisp");
        Scanner sc = new Scanner(input);
        StevenBinary listTree = new StevenBinary(sc);
        listTree.print(listTree.root);
    }
}
