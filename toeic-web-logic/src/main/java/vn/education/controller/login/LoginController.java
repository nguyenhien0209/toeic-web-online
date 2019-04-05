package vn.education.controller.login;

import org.apache.log4j.Logger;
import vn.education.command.UserCommand;
import vn.education.core.common.utils.SessionUtil;
import vn.education.core.dto.CheckLogin;
import vn.education.core.dto.UserDTO;
import vn.education.core.service.UserService;
import vn.education.core.service.impl.UserServiceImpl;
import vn.education.core.web.common.WebConstant;
import vn.education.core.web.utils.FormUtil;
import vn.education.core.web.utils.SingletonServiceUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;
//import java.util.logging.Logger;

@WebServlet( urlPatterns = {"/login.html", "/logout.html"})
public class LoginController extends HttpServlet {
    private final Logger log = Logger.getLogger(this.getClass());
    ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResource");
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter(WebConstant.ACTION);
        if( action.equals(WebConstant.LOGIN)) {
            RequestDispatcher rd= request.getRequestDispatcher("/views/login/login.jsp");
            rd.forward(request,response);
        } else if (  action.equals(WebConstant.LOGOUT)) {
            SessionUtil.getInstance().remove(request,WebConstant.LOGIN_NAME);
            response.sendRedirect("/home.html");
        }
    }

    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserCommand command = FormUtil.populate(UserCommand.class, request);
        UserDTO pojo = command.getPojo();
        if(pojo != null) {
            CheckLogin checkLogin = SingletonServiceUtil.getUserServiceInstance().checkLogin(pojo.getName(), pojo.getPassword());
            if(checkLogin.getUserExist()) {
                SessionUtil.getInstance().putValue(request, WebConstant.LOGIN_NAME, pojo.getName());
                if(checkLogin.getRoleName().equals(WebConstant.ROLE_ADMIN)) {
                    response.sendRedirect("/admin-home.html");
                } else if(checkLogin.getRoleName().equals(WebConstant.ROLE_USER)) {
                    response.sendRedirect("/home.html");
                }
            } else {
                request.setAttribute(WebConstant.ALERT, WebConstant.TYPE_ERROR );
                request.setAttribute(WebConstant.MESSAGE_RESPONSE, bundle.getString("label.name.password.wrong"));
                RequestDispatcher rd = request.getRequestDispatcher("/views/login/login.jsp");
                rd.forward(request, response);
            }
        }
    }
}
