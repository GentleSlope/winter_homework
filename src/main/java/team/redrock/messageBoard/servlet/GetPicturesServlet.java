package team.redrock.messageBoard.servlet;

import team.redrock.messageBoard.dao.SongsDaoimpl.SongsDao;

import team.redrock.messageBoard.dao.SongsDaoimpl.SongsDaoimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/getRotatonUrl")
public class GetPicturesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SongsDao songsDao = null;

        songsDao = SongsDaoimpl.getInstance();
        resp.setHeader("Access-Control-Allow-Origin","*");
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        resp.getWriter().write(String.valueOf(songsDao.getRotationUrl()));
    }

}
