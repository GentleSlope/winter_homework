package team.redrock.messageBoard.servlet;

import com.google.gson.JsonArray;
import team.redrock.messageBoard.been.Records;
import team.redrock.messageBoard.dao.SongsDaoimpl.SongsDaoimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Random;
@WebServlet("/randomSearch")
public class SwitchSongServlet extends HttpServlet {
    public SwitchSongServlet(){super();}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset = utf-8");
        Records records = new Records();
        Random random = new Random(new Date().getTime());
        int i=0;
        if (i<=7000) {
            i = random.nextInt(456000);
        }
        records.setExtraId(i);
        System.out.println(records.getExtraId());
        SongsDaoimpl songsDaoimpl = new SongsDaoimpl();
        JsonArray jsonArray = songsDaoimpl.searchRecord(records);
        resp.getWriter().write(String.valueOf(jsonArray));
    }
}
