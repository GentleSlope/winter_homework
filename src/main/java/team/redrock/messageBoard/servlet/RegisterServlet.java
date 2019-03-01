package team.redrock.messageBoard.servlet;
import team.redrock.messageBoard.been.User;
import team.redrock.messageBoard.dao.CommentDao.CommentDaoImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    public RegisterServlet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPut(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        int id=Integer.valueOf(request.getParameter("id"));
        String name=request.getParameter("username");
        String password=request.getParameter("password");
        User user=new User();
        user.setId(id);
        user.setName(name);
        user.setPassword(password);
        CommentDaoImpl userDAO=new CommentDaoImpl();
        User user2= userDAO.SearchUser(user);
        if(user2!=null){
            response.getWriter().write("[{\"result\":\"账号或id已存在\"}]");
        }else{
            userDAO.addUser(user);
            response.getWriter().write("[{\"result\":\"注册成功\"}]");

        }
    }
}

