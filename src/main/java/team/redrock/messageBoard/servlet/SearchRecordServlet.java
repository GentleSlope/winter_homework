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

@WebServlet("/searchRecord")
public class SearchRecordServlet extends HttpServlet {
    private String Error = "{\"result\":\"Error\"}";
    private static final long serialVersionUID = 1L;
    public SearchRecordServlet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        Records record = new Records();
        if (request.getParameter("singer")!=null) {
            record.setSinger(request.getParameter("singer"));
        }
        if (request.getParameter("id")!=null) {
            record.setId(Integer.parseInt(request.getParameter("id")));
        }
        if (request.getParameter("name")!=null) {
            record.setName(request.getParameter("name"));
        }
//        else {
//            response.getWriter().write(Error);
//            return;
//        }
        SongsDaoimpl songsDaoimpl = new SongsDaoimpl();
        JsonArray result = songsDaoimpl.searchRecord(record);
        response.getWriter().write(String.valueOf(result));
//        response.getWriter().write(String.valueOf(songsDaoimpl.getRecord(result)));
    }
}