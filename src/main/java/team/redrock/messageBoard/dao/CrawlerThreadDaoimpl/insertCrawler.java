package team.redrock.messageBoard.dao.CrawlerThreadDaoimpl;

import java.sql.*;

public class insertCrawler implements insertCrawlerDao {
    Connection con;
    PreparedStatement statement;
    ResultSet rs;

    public Connection getCon() {
        return con;
    }
    public Statement getStatement(){
        return statement;
    }

    public ResultSet getRs() {
        return rs;
    }
    public insertCrawler(Connection con) {
        this.con = con;
    }
    public void insert(Object id,Object name,Object singer,Object pic,Object lrc,Object url,Object time,Object songListId) throws SQLException {

//            while(i == 1){
//            String sql= "drop table if exists mlgb.records";
//            String sql1 ="CREATE TABLE mlgb.records ("
//                   +" `id` varchar(50) NOT NULL,"
//           +" `name` varchar(50) NOT NULL,"
//           + "`Singer` varchar(50) NOT NULL,"
//            +"`pic` varchar(255) NOT NULL,"
//            +"`lrc` varchar(255) NOT NULL,"
//           + "`url` varchar(255) NOT NULL,"
//           + "`time` varchar(255) NOT NULL,"
//            + "PRIMARY KEY (`id`))";
//                statement = con.prepareStatement(sql);
//                statement.executeUpdate();
//                statement = con.prepareStatement(sql1);
//                statement.executeUpdate();
//                i++;
//            }
        String sql2 = "INSERT into mlgb.songs(id,name,singer,pic,lrc,url,time,songListId)" +
                " values(?,?,?,?,?,?,?,?)";
        statement = con.prepareStatement(sql2);
        statement.setString(1, String.valueOf(id));
        statement.setString(2, String.valueOf(name));
        statement.setString(3, String.valueOf(singer));
        statement.setString(4, String.valueOf(pic));
        statement.setString(5, String.valueOf(lrc));
        statement.setString(6, String.valueOf(url));
        statement.setString(7, String.valueOf(time));
        statement.setString(8,String.valueOf(songListId));
        statement.executeUpdate();
    }
    public void insertSongList(Object songListId,Object songListName,Object songListPic,Object songListCount,Object songListPlayCount,Object songListDescription,Object songListUserId)
            throws SQLException {

        String sql2 = "INSERT into mlgb.songList(songListId,songListName,songListPic,songListCount,songListPlayCount,songListDescription,songListUserId )" +
                " values(?,?,?,?,?,?,?)";
        statement = con.prepareStatement(sql2);
        statement.setString(1, String.valueOf(songListId));
        statement.setString(2, String.valueOf(songListName));
        statement.setString(4, String.valueOf(songListCount));
        statement.setString(3, String.valueOf(songListPic));
        statement.setString(5, String.valueOf(songListPlayCount));
        statement.setString(6, String.valueOf(songListDescription));
        statement.setString(7, String.valueOf(songListUserId));
        statement.executeUpdate();
    }






}

