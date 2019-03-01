package team.redrock.messageBoard.servlet;

import team.redrock.messageBoard.been.Singer;
import team.redrock.messageBoard.been.SingerList;
import team.redrock.messageBoard.service.SearchSingerServiceimpl.SearchSinger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/searchSinger")
public class SrarchSingerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public SrarchSingerServlet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Singer singer = new Singer();
        SearchSinger searchSinger = new SearchSinger();
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        singer.setName(request.getParameter("singer"));
        SingerList singerList = searchSinger.returnSingerList(singer);
        System.out.println(singerList.getArtistCount());
        String string = singerList.getSinger(0).getName();
        String string2 = singerList.getSinger(0).getImg1v1Url();
        String string3  = "[{\"singer\":\""+string+"\",\"ImgUrl\":\""+string2+"\"}]";
        response.getWriter().write(string3);
        singerList.freeList();
    }
}
