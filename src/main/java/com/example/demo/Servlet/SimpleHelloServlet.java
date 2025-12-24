package com.example.demo.servlet;

import jakarta.servlet.http.*;
import java.io.IOException;

public class SimpleHelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setStatus(200);
        resp.getWriter().write("OK");
    }
}
