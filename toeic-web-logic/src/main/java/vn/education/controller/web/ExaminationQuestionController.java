package vn.education.controller.web;

import vn.education.command.ExaminationQuestionCommand;
import vn.education.core.common.utils.SessionUtil;
import vn.education.core.dto.ExaminationQuestionDTO;
import vn.education.core.dto.ResultDTO;
import vn.education.core.web.common.WebConstant;
import vn.education.core.web.utils.FormUtil;
import vn.education.core.web.utils.RequestUtil;
import vn.education.core.web.utils.SingletonServiceUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/bai-thi-thuc-hanh.html","/bai-thi-dap-an.html"})
public class ExaminationQuestionController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ExaminationQuestionCommand command = FormUtil.populate(ExaminationQuestionCommand.class, request);
        getExaminationQuestion(request, command);
        request.setAttribute(WebConstant.LIST_ITEMS, command);
        RequestDispatcher rd = request.getRequestDispatcher("/views/web/examination/detail.jsp");
        rd.forward(request,response);
    }

    private void getExaminationQuestion(HttpServletRequest request, ExaminationQuestionCommand command) {
        Map<String, Object> properties = buildMapProperties(request);
        Object[] objects = SingletonServiceUtil.getExaminationQuestionServiceInstance().findExaminationQuestionByProperties(properties,command.getSortExpression(), command.getSortDirection(), null, null);
        command.setListResult((List<ExaminationQuestionDTO>) objects[1]);
    }

    private Map<String, Object> buildMapProperties(HttpServletRequest request) {
        Map<String, Object> properties = new HashMap<String, Object>();
        if(request.getParameter("examinationId") != null) {
            properties.put("examinationid", request.getParameter("examinationId"));
        }
        return properties;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ExaminationQuestionCommand command = new ExaminationQuestionCommand();
        Integer examinationId = Integer.parseInt(request.getParameter("examinationId"));
        command.setExaminationId(examinationId);
        getExaminationQuestion(request, command);
        for(ExaminationQuestionDTO item : command.getListResult()) {
            if(request.getParameter("answerUser[" + item.getExaminationQuestionId()+ "]") != null) {
                item.setAnswerUser(request.getParameter("answerUser[" + item.getExaminationQuestionId()+ "]"));
            }
        }
        String userName = (String) SessionUtil.getInstance().getValue(request, WebConstant.LOGIN_NAME);
        ResultDTO resultDTO = SingletonServiceUtil.getResultServiceInstance().saveResult(userName, examinationId, command.getListResult());
        command.setReadingScore(resultDTO.getReadingScore());
        command.setListenScore(resultDTO.getListenScore());
        request.setAttribute(WebConstant.LIST_ITEMS, command);
        RequestDispatcher rd = request.getRequestDispatcher("/views/web/examination/result.jsp");
        rd.forward(request,response);
    }
}
