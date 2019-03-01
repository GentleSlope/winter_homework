package team.redrock.messageBoard.servlet;

import com.google.gson.JsonObject;
import team.redrock.messageBoard.been.SongList;
import team.redrock.messageBoard.dao.SongsDaoimpl.SongsDaoimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/searchList")
public class SearchListServlet extends HttpServlet {
    public SearchListServlet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        SongList songList = new SongList();
        request.getParameter("songListId");
        songList.setSongListId(Integer.parseInt(request.getParameter("songListId")));
        SongsDaoimpl songsDaoimpl = new SongsDaoimpl();
        JsonObject result = songsDaoimpl.searchSongList(songList);
        response.getWriter().write("["+String.valueOf(result)+"]");
    }
}
