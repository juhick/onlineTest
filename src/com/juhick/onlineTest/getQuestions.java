package com.juhick.onlineTest;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class getQuestions extends javax.servlet.http.HttpServlet {
    private static String JDBC_DRIVER;
    private static String DB_URL;

    private static String USER;
    private static String PASS;

    static {
        try{
            ResourceBundle rb =ResourceBundle.getBundle("/com/juhick/resources/db", Locale.getDefault());
            JDBC_DRIVER = rb.getString("db.JDBC_DRIVER");
            DB_URL = rb.getString("db.DB_URL");
            USER = rb.getString("db.USER");
            PASS = rb.getString("db.PASS");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        Connection conn = null;
        Statement stmt = null;

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        ServletContext context = request.getSession().getServletContext();

        List<input> inputList = new ArrayList<>();
        List<choice> singleChoiceList = new ArrayList<>();
        List<choice> multiChoiceList = new ArrayList<>();
        List<check> checkList = new ArrayList<>();

        try{
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt =  conn.createStatement();
            String sql;

            //填空题
            sql = "SELECT qId, lContent, rContent FROM inputQ";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                int qId = rs.getInt("qId");
                String lContent = rs.getString("lContent");
                String rContent = rs.getString("rContent");

                input iq = new input(qId, lContent, rContent);
                inputList.add(iq);
            }

            //单选题
            sql = "SELECT qId, qName, choiceA, choiceB, choiceC, choiceD FROM singleChoice";
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                int qId = rs.getInt("qId");
                String qName = rs.getString("qName");
                String choiceA = rs.getString("choiceA");
                String choiceB = rs.getString("choiceB");
                String choiceC = rs.getString("choiceC");
                String choiceD = rs.getString("choiceD");

                choice scq = new choice(qId,qName, choiceA, choiceB, choiceC, choiceD);
                singleChoiceList.add(scq);
            }

            //多选题
            sql = "SELECT qId, qName, choiceA, choiceB, choiceC, choiceD FROM multiChoice";
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                int qId = rs.getInt("qId");
                String qName = rs.getString("qName");
                String choiceA = rs.getString("choiceA");
                String choiceB = rs.getString("choiceB");
                String choiceC = rs.getString("choiceC");
                String choiceD = rs.getString("choiceD");

                choice scq = new choice(qId,qName, choiceA, choiceB, choiceC, choiceD);
                multiChoiceList.add(scq);
            }

            //判断题
            sql = "SELECT qId, qName FROM checkQ";
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                int qId = rs.getInt("qId");
                String qName = rs.getString("qName");

                check cq = new check(qId, qName);
                checkList.add(cq);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (ClassNotFoundException e) {
            System.out.println("ERROR：未找到JDBC驱动！");
        } catch (SQLException e) {
            System.out.println("ERROR：SQL语句出错！");
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

        context.setAttribute("input", inputList);
        context.setAttribute("singleChoices", singleChoiceList);
        context.setAttribute("multiChoices", multiChoiceList);
        context.setAttribute("check", checkList);
        RequestDispatcher rd = request.getRequestDispatcher("jsp/test.jsp");
        rd.forward(request, response);

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request, response);
    }
}
