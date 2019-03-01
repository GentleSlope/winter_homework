package team.redrock.messageBoard.been;

public class SongList {
    private int songListId;
    private String songListName;
    private String songListPic;
    private int songListPlayCount;
    private String songListDescription;
    private int songListUserId;
    private int songListCount;

    public int getSongListId(){return this.songListId;}
    public int getSongListPlayCount(){return this.songListPlayCount;}
    public String getSongListDescription(){return this.songListDescription;}
    public int getSongListUserId(){return this.songListUserId;}
    public String getSongListName(){return this.songListName;}
    public String getSongListPic(){return this.songListPic;}
    public int getSongListCount(){return this.songListCount;}

    public void setSongListId(int songListId){this.songListId = songListId;}
    public void setSongListCount(int songListCount){this.songListCount = songListCount;}
    public void setSongListPlayCount(int songListPlayCount){this.songListPlayCount = songListPlayCount;}
    public void setLrc(String songListDescription){this.songListDescription = songListDescription;}
    public void setSongListPic(String songListPic){this.songListPic = songListPic;}
    public void setSongListName(String songListName){this.songListName = songListName;}
    public void setSongListUserId(int   songListUserId){this.songListUserId = songListUserId;}

}
