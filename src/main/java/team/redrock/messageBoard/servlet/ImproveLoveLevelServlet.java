package team.redrock.messageBoard.servlet;

import com.google.gson.JsonArray;
import team.redrock.messageBoard.been.MYList;
import team.redrock.messageBoard.been.Records;
import team.redrock.messageBoard.dao.SongsDaoimpl.SongsDaoimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/addToList")   //需要首先进行登录（获得username），提供id（看是否可以用session搞定）
                            // 和列表名可以新建或加入歌单，默认歌单为Favour。
public class ImproveLoveLevelServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        HttpSession httpSession  = request.getSession();
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        MYList myList = new MYList();
        Records records = new Records();
        if (request.getParameter("id")!=null) {
            myList.setId(Integer.parseInt(request.getParameter("id")));
            records.setId(Integer.parseInt(request.getParameter("id")));
        }

        if (request.getParameter("name")!=null) {
            records.setName(request.getParameter("name"));
        }

        if (request.getParameter("listName")!=null){
            myList.setListName(request.getParameter("listName"));
        }
        if (request.getParameter("listName")==null){
            myList.setListName("Favour");
        }

        myList.setUserName(String.valueOf(httpSession.getAttribute("username")));
        SongsDaoimpl songsDaoimpl = new SongsDaoimpl();
        songsDaoimpl.AddToList(myList);
        JsonArray result = songsDaoimpl.searchRecord(records);
        response.getWriter().write(String.valueOf(result));
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
