package team.redrock.messageBoard.dao.SongsDaoimpl;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import team.redrock.messageBoard.been.*;
import team.redrock.messageBoard.dao.CommentDao.CommentDao;
import team.redrock.messageBoard.dao.CommentDao.CommentDaoImpl;
import team.redrock.messageBoard.service.CommentService.CommentService;
import team.redrock.messageBoard.service.CommentService.CommentServiceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class SongsDaoimpl implements SongsDao {
    CommentDao commentDao = null;
    CommentService commentService = null;
    private static SongsDao instance = null;

    public SongsDaoimpl() {
        commentDao = CommentDaoImpl.getInstance();
        commentService = CommentServiceImpl.getInstance();
    }


    public static SongsDao getInstance() {
        //双重校验锁
        if (instance == null) {
            synchronized (SongsDaoimpl.class) {
                if (instance == null) {
                    instance = new SongsDaoimpl();
                }
            }
        }
        return instance;
    }


    public JsonArray searchRecord(Records records) {
        CommentServiceImpl messageBoardService = new CommentServiceImpl();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        JsonArray array = new JsonArray();
        try {
            connection = commentDao.getConnection();
            if (records.getSinger() != null) {
                String sql = "select *from mlgb.songs2 where songs2.singer =? limit 30";
                statement = connection.prepareStatement(sql);
                statement.setString(1, records.getSinger());
            } else if (records.getName() != null) {
                String sql = "select *from mlgb.songs2 where songs2.name = ? limit 30";
                statement = connection.prepareStatement(sql);
                statement.setString(1, records.getName());
            } else if (records.getId() != 0) {
                String sql = "select *from mlgb.songs2 where songs2.id = ?  ";
                statement = connection.prepareStatement(sql);
                statement.setString(1, String.valueOf(records.getId()));
            } else if (records.getExtraId() != 0) {
                String sql = "select *from mlgb.songs2 where songs2.extraId = ? ";
                statement = connection.prepareStatement(sql);
                statement.setString(1, String.valueOf(records.getExtraId()));
            }
            resultSet = statement.executeQuery();
            array = messageBoardService.covJson1(resultSet);
            commentDao.close(resultSet, statement, connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;

    }

    public void searchAlbumList(Album album) {
        CommentServiceImpl messageBoardService = new CommentServiceImpl();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        JsonArray array = new JsonArray();
        try {
            connection = commentDao.getConnection();
            if (album.getAlbumId() != null) {
                String sql = "select *from mlgb.album where album.AlbumId = ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, album.getAlbumId());
            }
            resultSet = statement.executeQuery();
            array = messageBoardService.covJson1(resultSet);
            commentDao.close(resultSet, statement, connection);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < array.size(); i++) {
            Gson gson = new Gson();
            Records records = gson.fromJson(array.get(i), Records.class);
            album.addSong(records);
        }
    }

    public JsonArray searchAlbum(Album album, Records records) {
        CommentServiceImpl messageBoardService = new CommentServiceImpl();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        JsonArray array = new JsonArray();
        try {
            connection = commentDao.getConnection();

            if (album != null && album.getAlbumSinger() != null) {
                String sql = "select * from mlgb.album where albumSinger = ? ";
                statement = connection.prepareStatement(sql);
                statement.setString(1, album.getAlbumSinger());
            } else if (records.getAlbumId() != 0) {
                String sql = "select AlbumName from mlgb.album where albumId = ? limit 1";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, records.getAlbumId());
            } else if ((records.getSinger() != null) && (records.getName() != null)) {
                String sql = "select *from mlgb.album where albumid in " +
                        "(select albumId from mlgb.songs2 where singer = ? && name = ?)";
                statement = connection.prepareStatement(sql);
                statement.setString(1, records.getSinger());
                statement.setString(2, records.getName());
            } else if (records.getSinger() != null) {
                String sql = "select *from mlgb.album where albumid in " +
                        "(select albumId from mlgb.songs2 where singer = ?)";
                statement = connection.prepareStatement(sql);
                statement.setString(1, records.getSinger());
            } else if (records.getName() != null) {
                String sql = "select *from mlgb.album where albumid in " +
                        "(select albumId from mlgb.songs2 where name = ?)";
                statement = connection.prepareStatement(sql);
                statement.setString(1, records.getName());
            } else if (records.getId() != 0) {
                String sql = "select *from mlgb.album where albumid in " +
                        "(select albumId from mlgb.songs2 where id = ?)";
                statement = connection.prepareStatement(sql);
                statement.setString(1, String.valueOf(records.getId()));
            }
            resultSet = statement.executeQuery();
            array = messageBoardService.covJson1(resultSet);
            commentDao.close(resultSet, statement, connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;

    }

    public JsonObject searchSongList(SongList songList) {
        CommentServiceImpl messageBoardService = new CommentServiceImpl();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        JsonArray jsonObject = null;
        JsonObject Array = new JsonObject();
        try {
            connection = commentDao.getConnection();
            String sql = "select *from mlgb.songs where songs.songListId like ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + songList.getSongListId() + "%");
            resultSet = statement.executeQuery();
            jsonObject = messageBoardService.covJson1(resultSet);
            String sql2 = "select* from mlgb.songlist where songlist.songListId like ?";
            statement = connection.prepareStatement(sql2);
            statement.setString(1, "%" + songList.getSongListId() + "%");
            resultSet = statement.executeQuery();
            Array = messageBoardService.covJson2(resultSet);
            String string = "songs";
            Array.add(string, jsonObject);
            System.out.println(Array);
            commentDao.close(resultSet, statement, connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Array;
    }

    public void AddToList(MYList myList) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = commentDao.getConnection();
            if (myList.getId() != 0 && myList.getListName() != null && myList.getUserName() != null) {
                String sql = " INSERT INTO mlgb.userlist(userName,ListName,id) VALUES (?,?,?) " +
                        "ON DUPLICATE KEY UPDATE loveLevel=loveLevel+1";
                statement = connection.prepareStatement(sql);
                statement.setString(3, String.valueOf(myList.getId()));
                statement.setString(2, myList.getListName());
                statement.setString(1, myList.getUserName());
                statement.executeUpdate();
                statement.close();
                connection.close();
            } else {
                System.out.println("参数不足，考虑是否进行请求转发！");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public JsonArray Push(User user) {
        CommentServiceImpl commentService = new CommentServiceImpl();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        JsonArray array = new JsonArray();
        try {
            connection = commentDao.getConnection();
            if (user.getName() != null) {
                String sql = "select * from mlgb.songs where singer in " +
                        "(select singer from mlgb.songs where id in " +
                        "(select x.id " +
                        "from (select id,lovelevel " +
                        "from  mlgb.userlist where " +
                        "userName =" +
                        "?" +
                        "order by id desc limit 5)as x)) " +
                        "order by extraid desc limit 5";
                statement = connection.prepareStatement(sql);
                statement.setString(1, user.getName());
            }
            resultSet = statement.executeQuery();
            array = commentService.covJson1(resultSet);
            commentDao.close(resultSet, statement, connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }

    public JSONArray getRecord(JsonArray result) {
        SongsDaoimpl songsDaoimpl = new SongsDaoimpl();
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < result.size(); i++) {
            Gson gson = new Gson();
            Records records = gson.fromJson(result.get(i), Records.class);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", records.getName());
            jsonObject.put("singer", records.getSinger());
            String s = String.valueOf(songsDaoimpl.searchAlbum(null, records).get(0).getAsJsonObject().get("AlbumName"));
            s = s.replaceAll("\\\"", "");
            jsonObject.put("AlbumName", s);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    public void upadteUrl(List<RotationUrl> list) {
        Connection connection = null;
        PreparedStatement statement = null;
        String DB_URL = "jdbc:mysql://47.106.224.177:3306/mlgb";
        String User = "root";
        String pass = "123456";
        try {
            connection = commentDao.getConnection(DB_URL, User, pass);
            String sql1="TRUNCATE TABLE mlgb.rotation_url ";
            statement = connection.prepareStatement(sql1);
            statement.executeUpdate();
            for (RotationUrl rotationUrl:list) {
                String sql = " INSERT INTO mlgb.rotation_url(url) VALUES (?) ";
                statement = connection.prepareStatement(sql);
                statement.setString(1, rotationUrl.getUrl());
                statement.executeUpdate();
            }
            statement.close();
            connection.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public JsonArray getRotationUrl() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        JsonArray array = new JsonArray();
        try {
            connection = commentDao.getConnection();
            String sql = "select url from mlgb.rotation_url ";
            statement = connection.prepareStatement(sql);

            resultSet = statement.executeQuery();
            array = commentService.covJson1(resultSet);
            commentDao.close(resultSet, statement, connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }


}