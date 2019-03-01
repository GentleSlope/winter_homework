package team.redrock.messageBoard.servlet;

import team.redrock.messageBoard.been.Message;
import team.redrock.messageBoard.service.CommentService.CommentService;
import team.redrock.messageBoard.service.CommentService.CommentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

@WebServlet("/SearchComments")
public class SearchCommentsServlet extends HttpServlet {
    CommentService commentService;

    @Override
    public void init() {
        commentService = CommentServiceImpl.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        Message message = new Message();
        message.setExtraId(Integer.parseInt(req.getParameter("extraId")));
        List<Message> messageList = commentService.findAllMessages(message);
        String res = commentService.messagesToJson(messageList);

        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        resp.getOutputStream()
                )
        );
        writer.write(res);
        writer.flush();
        writer.close();
    }


}
