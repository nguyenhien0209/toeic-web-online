package vn.education.controller.web;

import vn.education.command.ExerciseQuestionCommand;
import vn.education.core.dto.ExerciseQuestionDTO;
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

@WebServlet(urlPatterns = {"/bai-tap-thuc-hanh.html","/ajax-bai-tap-dap-an.html"})
public class ExerciseQuestionController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ExerciseQuestionCommand command = FormUtil.populate(ExerciseQuestionCommand.class, request);
        getListExerciseQuestion(request, command);
        request.setAttribute(WebConstant.LIST_ITEMS, command);
        RequestDispatcher rd = request.getRequestDispatcher("/views/web/exercise/detail.jsp");
        rd.forward(request,response);
    }

    private void getListExerciseQuestion(HttpServletRequest request, ExerciseQuestionCommand command) {
        Map<String, Object> properties = buildMapProperties(request, command);
        command.setMaxPageItems(1);
        RequestUtil.initSearchBeanManual(command);
        Object[] objects = SingletonServiceUtil.getExerciseQuestionServiceInstance().findExerciseQuestionByProperties(properties,command.getSortExpression(), command.getSortDirection(), command.getFirstItem(), command.getMaxPageItems());
        command.setListResult((List<ExerciseQuestionDTO>) objects[1]);
        command.setTotalItems(Integer.parseInt(objects[0].toString()));
        command.setTotalPages((int)Math.ceil((double) command.getTotalItems() / command.getMaxPageItems()));
    }

    private Map<String, Object> buildMapProperties(HttpServletRequest request, ExerciseQuestionCommand command) {
        ExerciseQuestionDTO pojo = command.getPojo();
        Map<String, Object> properties = new HashMap<String, Object>();
//        if(pojo.getExercise() != null && pojo.getExercise().getExerciseId() != null) {
//            properties.put("exerciseid", pojo.getExercise().getExerciseId());
//        }
        if(request.getParameter("exerciseId") != null) {
            properties.put("exerciseid", request.getParameter("exerciseId"));
        }
        return properties;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ExerciseQuestionCommand command = FormUtil.populate(ExerciseQuestionCommand.class, request);
        getListExerciseQuestion(request, command);
        for(ExerciseQuestionDTO item : command.getListResult()) {
            if(!command.getAnswerUser().equals(item.getCorrectAnswer())) {
                command.setCheckAnswer(true);
            }
        }
        request.setAttribute(WebConstant.LIST_ITEMS, command);
        RequestDispatcher rd = request.getRequestDispatcher("/views/web/exercise/result.jsp");
        rd.forward(request,response);
    }
}
