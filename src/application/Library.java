package application;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Library 
{
	static ArrayList<Song> getList()
	{
		try
		{
			FileInputStream input = new FileInputStream("lib.txt");
			InputStreamReader r = new InputStreamReader(input, "UTF-8");
			BufferedReader bReader = new BufferedReader(r);

			String l;
			ArrayList<Song> out = new ArrayList<>();

			while ((l = bReader.readLine()) != null)
			{
				String[] tempArr = l.split("|");

				Song s = new Song(tempArr[0], tempArr[1], tempArr[2]);

				out.add(s);

			}

			return out;



		}
		catch (IOException e)
		{
			System.out.println("IO Exception");
			return null;
		}


	}
	
	static void addToList(Song s)
	{
		try
		{
			OutputStreamWriter w = new OutputStreamWriter(new FileOutputStream("lib.txt", false), "UTF-8");

			BufferedWriter bWriter = new BufferedWriter(w);

			/*Get old list, add then sort */
			ArrayList<Song> n = getList();

			if (n == null)
				n = new ArrayList<Song>();
			n.add(s);
			Collections.sort(n);

			for (Song x : n)
				bWriter.write(x.toString());

			bWriter.close();

		}
		catch (IOException e)
		{
			System.out.println("IO Exception");
		}
	}
	
	
	static void editSong(Song s, int i)
	{
		ArrayList<Song> n = getList();

		if (n == null)
			n = new ArrayList<Song>();

		n.remove(i);
		n.add(s);

		Collections.sort(n);


		try
		{
			OutputStreamWriter w = new OutputStreamWriter(new FileOutputStream("lib.txt", false), "UTF-8");

			BufferedWriter bWriter = new BufferedWriter(w);

			for (Song x : n)
				bWriter.write(x.toString());

			bWriter.close();

		}
		catch (IOException e)
		{
			System.out.println("IO Exception");
		}









	}
}
