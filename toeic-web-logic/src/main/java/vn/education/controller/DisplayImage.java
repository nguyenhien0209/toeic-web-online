package vn.education.controller;

import vn.education.core.common.constant.CoreConstant;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class DisplayImage extends HttpServlet {

    private final String imagesBase = "/" + CoreConstant.FOLDER_UPLOAD;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String imageUrl = request.getRequestURI();
        String relativeImagePath = imageUrl.substring("/repository/".length());
//        /repository/ nhu la mot co de danh dau la hinh duoc luu o dau, danh dau viec chuyen tiep
//        Luc dau edit -> web.xml -> DisplayImage
        ServletOutputStream servletOutputStream;
        servletOutputStream = response.getOutputStream();
        FileInputStream fin = new FileInputStream(imagesBase + File.separator + relativeImagePath);
        BufferedInputStream bin = new BufferedInputStream(fin);
        BufferedOutputStream bout = new BufferedOutputStream(servletOutputStream);
        int ch = 0;
        while(( ch = bin.read()) != -1) {
            bout.write(ch);
        }
        bin.close();
        bout.close();
        fin.close();
        servletOutputStream.close();
    }
}
