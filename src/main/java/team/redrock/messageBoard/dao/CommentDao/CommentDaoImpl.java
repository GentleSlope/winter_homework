package team.redrock.messageBoard.dao.CommentDao;


import team.redrock.messageBoard.been.Message;
import team.redrock.messageBoard.been.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoImpl implements CommentDao {

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mlgb";
    private static final String USER = "root";
    private static final String PASS = "123456";

    private static CommentDao instance = null;

    //static 加载这个类的时候加载数据库连接的驱动
    static {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static CommentDao getInstance() {
        //双重校验锁 防止高并发的情况下new出来多个message_boardDao的实例
        if (instance == null) {
            synchronized (CommentDao.class) {
                if (instance == null) {
                    instance = new CommentDaoImpl();
                }
            }
        }
        return instance;
    }

    public Connection getConnection(String DB_URL1,String USER1,String PASS1) {
        Connection con = null;
        try {
            con = DriverManager.getConnection(DB_URL1, USER1, PASS1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public Connection getConnection(){
        Connection con = null;
        try {
            con = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;

    }

    public void insertMessage(Message message) {
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO message_board(username,text,pid,extraId) VALUE(?,?,?,?)";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, message.getUsername());
            pstmt.setString(2, message.getText());
            pstmt.setInt(3, message.getPid());
            pstmt.setInt(4,message.getExtraId());
            pstmt.execute();
        } catch (SQLException e) {
            try {
                pstmt.close();
                con.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public  User  login(String username,String password) {
        User u=null;
        Connection connection =null;
        PreparedStatement pstmt=null;
        ResultSet resultSet=null;
        //赋值
        try {
            connection=this.getConnection();
            //静态sql语句
            String sql = "select * from mlgb.userInfo where name=? and password=?";
            pstmt = (PreparedStatement) connection.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            resultSet = pstmt.executeQuery();
            if(resultSet.next()){
                u=new User();
                u.setName(resultSet.getString("name"));
                u.setPassword(resultSet.getString("password"));
                System.out.println("登录成功！");
            }else{
                System.out.println("用户名或者密码错误！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.close(resultSet,pstmt, connection);
        }
        return u;

    }

    public  User  SearchUser(User user) {
        User u=null;
        Connection connection =null;
        PreparedStatement pstmt=null;
        ResultSet resultSet=null;
        //赋值
        try {
            connection=this.getConnection();
            //静态sql语句
            String sql = "select * from mlgb.userInfo where name=? or id = ? ";
            pstmt = (PreparedStatement) connection.prepareStatement(sql);
            pstmt.setString(1, user.getName());
            pstmt.setInt(2, user.getId());
            resultSet = pstmt.executeQuery();
            if(resultSet.next()){
                u=new User();
                u.setName(resultSet.getString("name"));
                u.setPassword(resultSet.getString("password"));

            }else{
                System.out.println("有户名不存在！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.close(resultSet,pstmt, connection);
        }
        return u;

    }

    public List<Message> findMessagesByPid(int pid, Message content) {
        String sql = "SELECT * FROM mlgb.message_board WHERE pid = ? and extraId = ?";
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet res = null;
        List<Message> list = new ArrayList<Message>();
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, pid);
            pstmt.setInt(2,content.getExtraId());
            res = pstmt.executeQuery();
            while (res.next()) {
                Message message = new Message();
                message.setId(res.getInt("id"));
                message.setPid(res.getInt("pid"));
                message.setText(res.getString("text"));
                message.setUsername(res.getString("username"));
                list.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(res, pstmt, con);
        }
        return list;
    }
    public void addUser(User user) {
        Connection connection = null;
        PreparedStatement psmt = null;
        try {
            connection = this.getConnection();
            String sql  ="insert into mlgb.userInfo(name,id,password) values(?,?,?);";

            psmt =  connection.prepareStatement(sql);
            psmt.setString(1, user.getName());
            psmt.setInt(2, user.getId());
            psmt.setString(3,user.getPassword());
            psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                psmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }




    public void close(ResultSet res, PreparedStatement pstmt, Connection con) {
        try {
            if (res!=null){
            res.close();}
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
