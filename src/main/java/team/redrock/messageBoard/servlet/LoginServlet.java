package team.redrock.messageBoard.servlet;
import team.redrock.messageBoard.been.User;
import team.redrock.messageBoard.dao.CommentDao.CommentDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    public LoginServlet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        HttpSession ses = request.getSession();
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        CommentDaoImpl userDAO=new CommentDaoImpl();
        User user= userDAO.login(username, password);
        if(user!=null){
            ses.setAttribute("username",username);
            ses.setAttribute("password",password);
            ses.setMaxInactiveInterval(30*6000);
            response.getWriter().write("[{\"result\":\"登陆成功\"}]");
        }else{
            response.getWriter().write("[{\"result\":\"用户名或者秘密不存在\"}]");

        }
    }
}
