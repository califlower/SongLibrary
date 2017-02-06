package application;

public class Song implements songInterface {

        private String name;
		private String artist;
		private String album;

	public Song ()
		{
			this.name = "";
			this.artist = "";
			this.album = "";
		}
	public Song(String name, String artist, String album)
	{
		this.name = name;
		this.artist = artist;
		this.album = album;
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
	public String toString()
    {
        return this.name + "|" + this.artist + "|" + this.album;
    }

}
