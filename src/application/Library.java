package application;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Library
{

    public static ArrayList<Song> getList()
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
                String[] tempArr = l.split("[|]");

                if (tempArr.length == 4)
                {
                    Song s = new Song(tempArr[0].trim(), tempArr[1].trim(), tempArr[2].trim(), tempArr[3].trim());

                    out.add(s);
                }
            }

            return out;


        } catch (IOException e)
        {
            System.out.println("IO Exception");
            return null;
        }


    }

    public static void addSong(Song s)
    {
        /*Get old list, add then sort */

        ArrayList<Song> n = Library.getList();

        if (n == null)
            n = new ArrayList<Song>();

        n.add(s);
        Collections.sort(n);

        try
        {
            OutputStreamWriter w = new OutputStreamWriter(new FileOutputStream("lib.txt", false), "UTF-8");

            BufferedWriter bWriter = new BufferedWriter(w);

            for (Song x : n)
            {
                bWriter.write(x.toString());
                bWriter.newLine();
            }

            bWriter.close();

        } catch (IOException e)
        {
            System.out.println("IO Exception");
        }
    }

    public static void removeSong(int i)
    {

        ArrayList<Song> n = getList();


        if (n == null)
            n = new ArrayList<Song>();

        if (i > n.size() - 1 || i < 0)
            return;

        n.remove(i);

        Collections.sort(n);


        try
        {
            OutputStreamWriter w = new OutputStreamWriter(new FileOutputStream("lib.txt", false), "UTF-8");

            BufferedWriter bWriter = new BufferedWriter(w);

            for (Song x : n)
            {
                bWriter.write(x.toString());
                bWriter.newLine();
            }

            bWriter.close();

        } catch (IOException e)
        {
            System.out.println("IO Exception");
        }

    }

    public static void editSong(Song s, int i)
    {
        ArrayList<Song> n = getList();

        if (n == null)
            n = new ArrayList<>();

        n.remove(i);
        n.add(s);

        Collections.sort(n);


        try
        {
            OutputStreamWriter w = new OutputStreamWriter(new FileOutputStream("lib.txt", false), "UTF-8");

            BufferedWriter bWriter = new BufferedWriter(w);

            for (Song x : n)
            {
                bWriter.write(x.toString());
                bWriter.newLine();
            }

            bWriter.close();

        } catch (IOException e)
        {
            System.out.println("IO Exception");
        }

    }
    public static boolean libContains(Song s)
    {
        ArrayList<Song> n = getList();

        if (n == null)
            return false;
        else if (n.isEmpty())
            return false;

        for (Song x: n)
        {
            if (s.getName().compareTo(x.getName()) == 0 && s.getArtist().compareTo(x.getName()) == 0)
                return true;
        }

        return false;
    }
}
