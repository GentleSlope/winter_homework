package team.redrock.messageBoard.dao.CommentDao;

import team.redrock.messageBoard.been.Message;
import team.redrock.messageBoard.been.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public interface CommentDao {


    List<Message> findMessagesByPid(int pid,Message content);

    void insertMessage(Message message);

    Connection getConnection();

    Connection getConnection(String oone ,String two,String three);

    User login(String username, String password);

    User  SearchUser(User user);

    void addUser(User user);

    void close(ResultSet res, PreparedStatement pstmt, Connection con);


}
