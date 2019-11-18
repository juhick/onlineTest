package com.juhick.onlineTest;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "getScore")
public class getScore extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        ServletContext context = request.getSession().getServletContext();

        List<input> inputList = (List<input>) context.getAttribute("input");
        List<choice> singleChoiceList = (List<choice>) context.getAttribute("singleChoices");
        List<choice> multiChoiceList = (List<choice>) context.getAttribute("multiChoices");
        List<check> checkList = (List<check>) context.getAttribute("check");

        for (input iq : inputList){
            String ans = request.getParameter("i" + iq.getqId());
            System.out.println(ans);
        }

        for(choice sc : singleChoiceList){
            String ans = request.getParameter("sc" + sc.getqId());
            System.out.println(ans);
        }

        for(choice mc : multiChoiceList){
            String[] anws = request.getParameterValues("mc" + mc.getqId());
            for (String anw : anws) {
                System.out.print(anw);
            }
            System.out.println();
        }

        for (check ck : checkList){
            String ans = request.getParameter("ck" + ck.getqId());
            System.out.println(ans);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
