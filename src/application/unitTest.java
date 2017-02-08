package application;

import java.util.ArrayList;

public class unitTest
{

    public static void testAll()
    {
        Song s = new Song();

        if (s.getName().compareToIgnoreCase("") == 0)
            System.out.println("GOOD");
        else
            System.out.println("Failed");

        if (s.getAlbum().compareToIgnoreCase("") == 0)
            System.out.println("GOOD");
        else
            System.out.println("Failed");

        if (s.getArtist().compareToIgnoreCase("") == 0)
            System.out.println("GOOD");
        else
            System.out.println("Failed");


        s.setName("Test");
        s.setAlbum("Test");
        s.setArtist("Test");

        if (s.getName().compareToIgnoreCase("Test") == 0)
            System.out.println("GOOD");
        else
            System.out.println("Failed");


        if (s.getAlbum().compareToIgnoreCase("Test") == 0)
            System.out.println("GOOD");
        else
            System.out.println("Failed");


        if (s.getArtist().compareToIgnoreCase("Test") == 0)
            System.out.println("GOOD");
        else
            System.out.println("Failed");


        Library.addSong(s);











    }
}
