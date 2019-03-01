package team.redrock.messageBoard.dao.CrawlerThreadDaoimpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface insertCrawlerDao {
    public Connection getCon();
    public ResultSet getRs();
    public void insert(Object id,Object name,Object singer,Object pic,Object lrc,
                       Object url,Object time,Object songListId) throws SQLException;
    public void insertSongList(Object songListId,Object songListName,Object songListPic,Object songListCount,Object songListPlayCount,Object songListDescription,Object songListUserId)
            throws SQLException;
}
