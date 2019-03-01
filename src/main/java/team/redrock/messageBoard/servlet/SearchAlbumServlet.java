package team.redrock.messageBoard.servlet;

import com.google.gson.JsonArray;
import team.redrock.messageBoard.been.Album;
import team.redrock.messageBoard.been.Records;
import team.redrock.messageBoard.dao.SongsDaoimpl.SongsDaoimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/searchAlbum")
public class SearchAlbumServlet extends HttpServlet {
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
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        Records record = new Records();
        Album album = new Album();
        if (req.getParameter("albumSinger")!=null){
            album.setAlbumSinger(req.getParameter("albumSinger"));
        }

        if (req.getParameter("singer")!=null) {
            record.setSinger(req.getParameter("singer"));
        }
        if (req.getParameter("id")!=null) {
            record.setId(Integer.parseInt(req.getParameter("id")));
        }
        if (req.getParameter("name")!=null) {
            record.setName(req.getParameter("name"));
        }
        SongsDaoimpl songsDaoimpl = new SongsDaoimpl();
        JsonArray result = songsDaoimpl.searchAlbum(album,record);
        resp.getWriter().write(String.valueOf(result));

    }
}
