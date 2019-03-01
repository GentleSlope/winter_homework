package team.redrock.messageBoard.been;

public class Records {
    private int id;
    private String name;
    private String singer;
    private String pic;
    private String lrc;
    private String url;
    private int time;
    private int songListId;
    private String listName;
    private int extraId;
    private int albumId;

    public int getAlbumId() {
        return albumId;
    }

    public int getExtraId() {
        return extraId;
    }

    public String getListName(){return this.listName;}
    public int getId(){return this.id;}
    public int getSongListId(){return this.songListId;}
    public String getPic(){return this.pic;}
    public String getLrc(){return this.lrc;}
    public String getUrl(){return this.url;}
    public String getName(){return this.name;}
    public String getSinger(){return this.singer;}
    public int getTime(){return this.time;}

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public void setExtraId(int extraId) {
        this.extraId = extraId;
    }
    public void setListName(String listName){this.listName = listName;}
    public void setId(int id){this.id = id;}
    public void setSongListId(int songListId){this.songListId = songListId;}
    public void setTime(int time){this.time = time;}
    public void setPic(String pic){this.pic = pic;}
    public void setLrc(String lrc){this.lrc = lrc;}
    public void setSinger(String singer){this.singer = singer;}
    public void setName(String name){this.name = name;}
    public void setUrl(String singer){this.url = url;}
}
