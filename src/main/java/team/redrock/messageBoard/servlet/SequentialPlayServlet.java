package team.redrock.messageBoard.servlet;

import com.google.gson.JsonArray;
import team.redrock.messageBoard.been.Records;
import team.redrock.messageBoard.dao.SongsDaoimpl.SongsDaoimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

@WebServlet("/sequentialPlay")
public class SequentialPlayServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SequentialPlayServlet() {super();}
        @Override
        protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        }

        @Override
        protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            resp.setHeader("Access-Control-Allow-Origin", "*");
            req.setCharacterEncoding("utf-8");
            resp.setContentType("text/html;charset = utf-8");
            HttpSession httpSession = req.getSession();
            Records records = new Records();
            Random random = new Random(new Date().getTime());
            int i = -1;
            if (req.getParameter("extraid")!=null){
                httpSession.setAttribute("extraid",req.getParameter("extraid"));
                 i = Integer.parseInt(req.getParameter("extraid"));
            }
            else if ( httpSession.getAttribute("extraid")!=null) {
                 i = Integer.valueOf(String.valueOf(httpSession.getAttribute("extraid")));
                 i++;
                httpSession.setAttribute("extraid",i);

            }
            else {
                resp.getWriter().write("{\"Result\":\"Error\"}");
                return;
            }
            records.setExtraId(i);
            System.out.println(records.getExtraId());
            SongsDaoimpl songsDaoimpl = new SongsDaoimpl();
            JsonArray jsonArray = songsDaoimpl.searchRecord(records);
            resp.getWriter().write(String.valueOf(jsonArray));
        }
    }