package team.redrock.messageBoard.dao.SongsDaoimpl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.sf.json.JSONArray;
import team.redrock.messageBoard.been.*;

import java.util.List;

public interface SongsDao {
     JsonArray searchRecord(Records records);

     void searchAlbumList(Album album);

     JsonArray searchAlbum(Album album,Records records);

     JsonObject searchSongList(SongList songList);

     void AddToList(MYList myList);

     JsonArray Push(User user);

     JSONArray getRecord(JsonArray result);

    void upadteUrl(List<RotationUrl> list);

    JsonArray getRotationUrl();

}
