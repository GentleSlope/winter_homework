package team.redrock.messageBoard.been;

import java.util.ArrayList;
import java.util.List;

public class SingerList {
    private static List<Singer> singerList = new ArrayList<>();
    private static int artistCount = 0;


    public void add(Singer singer) {
        singerList.add(singer);
    }

    public  int getSize() {
        return singerList.size();
    }

    public  List<Singer> getList() {
        return singerList;
    }

    public  Singer getSinger(int index) {
        return singerList.get(index);
    }

    public void setArtistCount(int artistCount) {
        this.artistCount = artistCount;
    }

    public int getArtistCount() {
        return this.artistCount;
    }
    public void freeList(){singerList.clear(); }
}

