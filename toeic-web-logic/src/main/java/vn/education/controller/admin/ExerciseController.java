package vn.education.controller.admin;

import vn.education.core.common.utils.UploadUtil;
import vn.education.core.web.common.WebConstant;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

@WebServlet("/admin-exercise-upload.html")
public class ExerciseController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/views/admin/exercise/upload.jsp");
        rd.forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResource");
        UploadUtil uploadUtil = new UploadUtil();
        Set<String> valueTitle = new HashSet<String>();
        uploadUtil.writeOrUpdateFile(request,valueTitle, WebConstant.EXERCISE);
        request.setAttribute(WebConstant.ALERT, WebConstant.TYPE_SUCCESS);
        request.setAttribute(WebConstant.MESSAGE_RESPONSE, bundle.getString("label.exercise.audio.image.upload.success"));
        RequestDispatcher rd = request.getRequestDispatcher("/views/admin/exercise/upload.jsp");
        rd.forward(request,response);
    }
}
