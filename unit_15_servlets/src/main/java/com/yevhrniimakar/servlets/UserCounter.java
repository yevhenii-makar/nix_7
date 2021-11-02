package com.yevhrniimakar.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet(name = "sample-servlet", urlPatterns = "/sample")
public class UserCounter extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        ConcurrentHashMap<String, String> uniqueIP = InMemoryDB.getMap();
        PrintWriter responseBody = resp.getWriter();

        String currentClient = req.getParameter("client") != null ? req.getParameter("client") : "anonymous";
        String currentIp = req.getRemoteAddr();

        uniqueIP.put(currentIp, currentClient);
        resp.setContentType("text/html");

        responseBody.println("<h1 align=\"center\">List IP:</h1>");

        for (String s : uniqueIP.keySet()) {

            if (s.equals(currentIp)) {
                responseBody.println("<h1 align=\"left\" ><i> ip: " + s + "name:" + uniqueIP.get(s) + "</i></h1>");
            } else {
                responseBody.println("<h1 align=\"left\" > ip: " + s + "name:" + uniqueIP.get(s) + "</h1>");
            }
        }
    }
}
