package team.redrock.messageBoard.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "loginFilter",
        urlPatterns = "/SearchComments")
public class LoginFiLter implements Filter{

    public void init(FilterConfig config) throws ServletException{}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException ,ServletException{
        HttpServletRequest req = (HttpServletRequest)request;
        HttpSession ses = req.getSession();
        if(ses.getAttribute("username")!=null){
            chain.doFilter(request,response);
        }else {
            response.getWriter().write("{\"status\":\"10001\"}");
        }
    }
    @Override
    public void destroy(){}

    }
