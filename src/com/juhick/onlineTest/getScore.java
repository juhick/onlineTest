package com.juhick.onlineTest;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet(name = "getScore")
public class getScore extends HttpServlet {

    private static String JDBC_DRIVER;
    private static String DB_URL;

    private static String USER;
    private static String PASS;

    static {
        try{
            ResourceBundle rb = ResourceBundle.getBundle("/com/juhick/resources/db", Locale.getDefault());
            JDBC_DRIVER = rb.getString("db.JDBC_DRIVER");
            DB_URL = rb.getString("db.DB_URL");
            USER = rb.getString("db.USER");
            PASS = rb.getString("db.PASS");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        ServletContext context = request.getSession().getServletContext();

        List<input> inputList = (List<input>) context.getAttribute("input");
        List<choice> singleChoiceList = (List<choice>) context.getAttribute("singleChoices");
        List<choice> multiChoiceList = (List<choice>) context.getAttribute("multiChoices");
        List<check> checkList = (List<check>) context.getAttribute("check");
        Connection conn = null;
        Statement stmt = null;

        try{
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String sql;
            ResultSet rs = null;

            for (input iq : inputList){
                String ans = request.getParameter("i" + iq.getqId());
                sql = "SELECT anwser FROM inputQ WHERE qId=" + iq.getqId();
                rs = stmt.executeQuery(sql);

                if (rs.next()){
                    String anwser = rs.getString("anwser").strip();
                    if (anwser.equals(ans)){
                        System.out.println("填空题第" + iq.getqId() + "正确");
                    }
                }


                System.out.println(ans);
            }

            for(choice sc : singleChoiceList){
                String ans = request.getParameter("sc" + sc.getqId());

                sql = "SELECT anwser FROM singleChoice WHERE qId=" + sc.getqId();
                rs = stmt.executeQuery(sql);

                if (rs.next()){
                    String anwser = rs.getString("anwser").strip();
                    if (anwser.equals(ans)){
                        System.out.println("单选题第" + sc.getqId() + "正确");
                    }
                }

                System.out.println(ans);
            }

            for(choice mc : multiChoiceList){
                StringBuilder result = new StringBuilder();
                String[] anws = request.getParameterValues("mc" + mc.getqId());
                for (String anw : anws) {
                    result.append(anw);
                    System.out.print(anw);
                }

                System.out.println();

                sql = "SELECT anwser FROM multiChoice WHERE qId=" + mc.getqId();
                rs = stmt.executeQuery(sql);

                if (rs.next()){
                    String anwser = rs.getString("anwser").strip();
                    if (anwser.contentEquals(result)){
                        System.out.println("多选题第" + mc.getqId() + "正确");
                    }
                }
            }

            for (check ck : checkList){
                String ans = request.getParameter("ck" + ck.getqId());

                sql = "SELECT anwser FROM checkQ WHERE qId=" + ck.getqId();
                rs = stmt.executeQuery(sql);

                if (rs.next()){
                    String anwser = rs.getString("anwser").strip();
                    if (anwser.equals(ans)){
                        System.out.println("判断题第" + ck.getqId() + "正确");
                    }
                }

                System.out.println(ans);
            }

            assert rs != null;
            rs.close();
            stmt.close();
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try{
                if(conn!=null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
