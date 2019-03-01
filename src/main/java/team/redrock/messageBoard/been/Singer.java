package team.redrock.messageBoard.been;

import java.util.List;

public class Singer {
    private int id;
    private String name;
    private List<String> alias;
    private String picUrl;
    private String trans;
    private String img1v1Url;
    private int albumSize;
    private int picId;
    private int accountId;
    private int img1v1;
    private int mvSize;
    private boolean followed;

    public String getName(){return this.name;}
    public int getId(){return this.id;}
    public int getPicId(){return this.picId;}
    public String getPicUrl(){return this.picUrl;}
    public String getTrans(){return this.trans;}
    public String getImg1v1Url(){return this.img1v1Url;}
    public List<String> getAlias(){return this.alias;}
    public boolean getFollowed(){return this.followed;}
    public int getAlbumSize(){return this.albumSize;}
    public int getAccountId(){return this.accountId;}
    public int getMvSize(){return this.mvSize;}
    public int getImg1v1(){return this.img1v1;}


    public void setName(String name){this.name = name;}
    public void setId(int id){this.id = id;}
    public void setPicId(int picId){this.picId = picId;}
    public void setAlbumSize(int albumSize){this.albumSize = albumSize;}
    public void setPicUrl(String picUrl){this.picUrl = picUrl;}
    public void setTrans(String trans){this.trans = trans;}
    public void setFollowed(boolean followed){this.followed = followed;}
    public void addAlias(String alia){this.alias.add(alia);}
    public void setImg1v1Url(String img1v1Url){this.img1v1Url = img1v1Url;}
    public void getAccountId(int accountId){this.accountId = accountId;}
    public void getMvSize(int mvSize){this.mvSize = mvSize;}
    public void getImg1v1(int img1v1){this.img1v1 = img1v1;}
}
