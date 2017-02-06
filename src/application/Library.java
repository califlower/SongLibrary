package application;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Library 
{
	static ArrayList<Song> getList()
	{
		try
		{
			FileInputStream input = new FileInputStream("lib.txt");
			InputStreamReader reader = new InputStreamReader(input, "UTF-8");


		}
		catch (IOException e)
		{
			System.out.println("IO Exception");
		}

		return null;
	}
	
	static void addToList(Song s)
	{
		try
		{
			OutputStreamWriter w = new OutputStreamWriter(new FileOutputStream("lib.txt", true), "UTF-8");

			BufferedWriter bWriter = new BufferedWriter(w);
			bWriter.write(s.toString());
			bWriter.close();
		}
		catch (IOException e)
		{
			System.out.println("IO Exception");
		}
	}
	
	
	static void editSong(int i)
	{
		
	}
}
