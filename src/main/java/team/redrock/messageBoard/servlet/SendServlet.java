package team.redrock.messageBoard.servlet;

import team.redrock.messageBoard.been.Message;
import team.redrock.messageBoard.service.CommentService.CommentService;
import team.redrock.messageBoard.service.CommentService.CommentServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

@WebServlet("/sendComments")
public class SendServlet extends HttpServlet {

    private static final String ERROR="{\"status\":\"10001\"}";
    private static final String OK="{\"status\":\"10000\"}";

    private CommentService commentService;

    @Override
    public void init() {
        commentService = CommentServiceImpl.getInstance();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String res = ERROR;

        response.setHeader("Access-Control-Allow-Origin", "*");
        HttpSession httpSession = request.getSession();
        String username = String.valueOf(httpSession.getAttribute("username"));
        String text = null;
        int extraId = 0;
        int pid = 0;
        if(username ==null){
            response.getWriter().write(res);
            return;
        }
        if (request.getParameter("text")!=null) {
             text = request.getParameter("text");
        }
        if (request.getParameter("extraId")!=null) {
             extraId = Integer.parseInt(request.getParameter("extraId"));
        }
        if (request.getParameter("pid")!=null) {
             pid = Integer.parseInt(request.getParameter("pid"));
        }
        Message message = new Message(username, text, pid, extraId);


        if (commentService.insertContent(message)) {
            res = OK;
        }

        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        response.getOutputStream()
                )
        );

        writer.write(res);
        writer.flush();
        writer.close();
    }

}
