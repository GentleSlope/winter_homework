package team.redrock.messageBoard.servlet;

import com.google.gson.JsonArray;
import team.redrock.messageBoard.been.Records;
import team.redrock.messageBoard.been.User;
import team.redrock.messageBoard.dao.SongsDaoimpl.SongsDaoimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Random;

//只需要获得用户名
@WebServlet("/pushSongs")
public class RecommendSongsServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        HttpSession httpSession = req.getSession();
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        User user = new User();
        SongsDaoimpl songsDaoimpl = new SongsDaoimpl();

        if (httpSession.getAttribute("username") == null) {
            resp.sendRedirect("/register");
        } else {
            user.setName(String.valueOf(httpSession.getAttribute("username")));
            JsonArray jsonArray = songsDaoimpl.Push(user);
            if (songsDaoimpl.Push(user).size() < 5) {
                for (int i = 0; i < 5 - songsDaoimpl.Push(user).size(); i++) {
                    Random random = new Random();
                    int x = random.nextInt(5804);
                    Records records = new Records();
                    records.setExtraId(x);
                    jsonArray.add(songsDaoimpl.searchRecord(records));
                }
            }
            resp.getWriter().write(String.valueOf(jsonArray));
        }


    }
}
