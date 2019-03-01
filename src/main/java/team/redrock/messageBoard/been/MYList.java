package team.redrock.messageBoard.been;

public class MYList {
    private int id;
    private int loveLevel;
    private String listName;
    private String userName;

    public String getListName(){return listName;}
    public int getId(){return this.id;}
    public int getLoveLevel(){return loveLevel;}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String username){this.userName = username;}
    public void setListName(String listName){this.listName = listName;}
    public void setId(int id){this.id = id;}
    public void setLoveLevel(int loveLevel){this.loveLevel = loveLevel;}
}
