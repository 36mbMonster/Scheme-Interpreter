package CodeTest;

import java.util.ArrayList;

public class Test
{
	public static void main(String[]args)
	{
		ArrayList<String> al = new ArrayList<String>();
		al.add("One");
		al.add("Two");
		al.add("Three");
		
		ArrayList<String> b;
		System.out.println("Car of al = " + car(al));
		b = cdr(al);
		print(b);
		
		cons("Zero", b);
		print(b);
		
		if (is_null(b))
			System.out.println("b is null");
		else
			System.out.println("b is not null");
		
		b = new ArrayList<String>();
		if (is_null(b))
			System.out.println("b is null");
		else
			System.out.println("b is not null");
		
		String x = "2";
		String y = "4";
		System.out.println(x + " + " + y + " = " + plus(x,y));
		System.out.println(x + " - " + y + " = " + minus(x,y));
		System.out.println(x + " * " + y + " = " + multiply(x,y));
		System.out.println(x + " / " + y + " = " + divide(x,y));
		
		String test = "One Two Three Four Five";
		b = list(test);
		print(b);
		b.set(1, "One");
		
		if (equals(b.get(0), b.get(1)))
			System.out.println(b.get(0) + " is equal to " + b.get(1));
		else
			System.out.println(b.get(0) + " is not equal to " + b.get(1));
		
		b.set(1, "Two");
		
		if (equals(b.get(0), b.get(1)))
			System.out.println(b.get(0) + " is equal to " + b.get(1));
		else
			System.out.println(b.get(0) + " is not equal to " + b.get(1));
		
		if (and(true,false))
			System.out.println("true");
		else
			System.out.println("false");
		
		if (or(true,true))
			System.out.println("true");
		else
			System.out.println("false");
	}
	
	public static String car(ArrayList<String> a)
	{
		if (a.size() != 0)
			return a.get(0);
		else
			return null;

	}
	
	public static ArrayList<String> cdr(ArrayList<String> a)
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
	
	public static ArrayList<String> cons(String s, ArrayList<String> a)
	{
		a.add(0, s);
		return a;
	}
    
	public static boolean is_null(ArrayList<String> a)
	{
		if (a.size() == 0)
			return true;
		else
			return false;
	}
	
	public static int plus(String a, String b)
	{
		int x = Integer.parseInt(a);
		int y = Integer.parseInt(b);
		return x + y;
	}

	public static int minus(String a, String b)
	{
		int x = Integer.parseInt(a);
		int y = Integer.parseInt(b);
		return x - y;
	}
	
	public static int multiply(String a, String b)
	{
		int x = Integer.parseInt(a);
		int y = Integer.parseInt(b);
		return x * y;
	}
	
	public static double divide(String a, String b)
	{
		int x = Integer.parseInt(a);
		int y = Integer.parseInt(b);
		return (double)x / y;
	}

	public static ArrayList<String> list(String s)
	{
		ArrayList<String> a = new ArrayList<String>();
		String[] words = s.split(" ");
		for (int i = 0; i < words.length; i++)
			a.add(words[i]);

		return a;
	}
	
	public static boolean equals(String a, String b)
	{
		return a.equals(b);
	}
	
	public static boolean and(boolean a, boolean b)
	{
		return a&&b;
	}
	
	public static boolean or(boolean a, boolean b)
	{
		return a||b;
	}
	
	public static void print(ArrayList<String> a)
	{
		for (int i = 0; i < a.size(); i++)
			System.out.print(a.get(i) + " ");
		System.out.println();
	}
}
