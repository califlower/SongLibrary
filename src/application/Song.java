/*
 * Arturo Corro & Calin Gilan
 */
package application;

public class Song implements songInterface, Comparable<Song> {

        private String name;
		private String artist;
		private String album;
		private String year;

	public Song ()
	{
			this.name = "";
			this.artist = "";
			this.album = "";
			this.year = "";
	}

	public Song(String name, String artist, String album, String year)
	{
		this.name = name;
		this.artist = artist;
		this.album = album;
		this.year = year;
	}

	@Override
	public void setName(String name)
    {
        this.name = name;
	}

	@Override
	public void setArtist(String artist)
    {
	    this.artist = artist;
	}

	@Override
	public void setAlbum(String album) {
	    this.album = album;
	}
	@Override
	public void setYear(String year) {
		this.year = year;
	}

	@Override
	public String getName()
    {
	    return this.name;
	}

	@Override
	public String getArtist()
    {
	    return this.artist;
	}

	@Override
	public String getAlbum()
    {
        return this.album;
	}
	
	@Override
	public String getYear()
	{
		return this.year;
	}
	
	public String toString()
    {
        return this.name + "| " + this.artist + "| " + this.album + "| " + this.year;
    }

	@Override
	public int compareTo(Song o)
	{
		/* Just use the string comparison of the name field */
		return getName().compareToIgnoreCase(o.getName());
	}
}
