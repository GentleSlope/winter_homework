package team.redrock.messageBoard.been;

import java.util.List;

public class Album {
    private String AlbumName;
    private String AlbumId;
    private String AlbumSinger;
    private String pic;
    private List<Records> Songs;

    public void setAlbumSinger(String albumSinger) {
        AlbumSinger = albumSinger;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getAlbumSinger() {
        return AlbumSinger;
    }

    public String getPic() {
        return pic;
    }

    public void setAlbumId(String albumId) {
        AlbumId = albumId;
    }

    public void setAlbumName(String albumName) {
        AlbumName = albumName;
    }

    public void addSong(Records records) {
        Songs.add(records);
    }

    public String getAlbumId() {
        return AlbumId;
    }

    public String getAlbumName() {
        return AlbumName;
    }

    public List<Records> getSongs() {
        return Songs;
    }
}
