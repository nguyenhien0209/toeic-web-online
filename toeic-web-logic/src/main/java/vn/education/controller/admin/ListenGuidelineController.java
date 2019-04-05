package vn.education.controller.admin;


import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import vn.education.command.ListenGuidelineCommand;
import vn.education.core.common.utils.UploadUtil;
import vn.education.core.dto.ListenGuidelineDTO;
import vn.education.core.service.ListenGuidelineService;
import vn.education.core.service.impl.ListenGuidelineServiceImpl;
import vn.education.core.web.common.WebConstant;
import vn.education.core.web.utils.FormUtil;
import vn.education.core.web.utils.RequestUtil;
import vn.education.core.web.utils.SingletonServiceUtil;
import vn.education.core.web.utils.WebCommonUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

@WebServlet(urlPatterns = {"/admin-guideline-listen-list.html","/admin-guideline-listen-edit.html"})
public class ListenGuidelineController extends HttpServlet {
    private final Logger log = Logger.getLogger(this.getClass());
    protected void doGet (HttpServletRequest request, HttpServletResponse response)
                 throws ServletException, IOException {
        ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResource");
        HttpSession session = request.getSession();
        ListenGuidelineCommand command = FormUtil.populate(ListenGuidelineCommand.class,request);
        if(command.getUrlType() !=null && command.getUrlType().equals(WebConstant.URL_LIST)) {
            if(command.getCrudaction() != null && command.getCrudaction().equals(WebConstant.REDIRECT_DELETE)) {
                List<Integer> ids = new ArrayList<Integer>();
                for (String item : command.getCheckLists()) {
                    ids.add(Integer.parseInt(item));
                }
                Integer result = SingletonServiceUtil.getListenGuidelineServiceInstance().delete(ids);
                if(result != ids.size()) {
                    command.setCrudaction(WebConstant.REDIRECT_ERROR);
                }
            }
            executeSearchListenGuideline(request, command);
            if(command.getCrudaction() != null) {
                Map<String, String> mapMessage = buildMapRedirecMessage(bundle);
                WebCommonUtil.addRedirectMessage(request, command.getCrudaction(), mapMessage);
            }
            request.setAttribute(WebConstant.LIST_ITEMS, command);
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/listenguideline/list.jsp");
            rd.forward(request, response);
        } else if(command.getUrlType() !=null && command.getUrlType().equals(WebConstant.URL_EDIT)) {
            if(command.getPojo() != null && command.getPojo().getListenGuidelineId()!= null) {
                command.setPojo(SingletonServiceUtil.getListenGuidelineServiceInstance().findByListenGuidelineId("listenGuidelineId", command.getPojo().getListenGuidelineId()));
            }
            request.setAttribute(WebConstant.FORM_ITEM, command);
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/listenguideline/edit.jsp");
            rd.forward(request, response);
        }
    }

    private void executeSearchListenGuideline(HttpServletRequest request, ListenGuidelineCommand command) {
        Map<String, Object> properties = buildMapProperties(command);
        RequestUtil.initSearchBean(request, command);
        Object[] objects = SingletonServiceUtil.getListenGuidelineServiceInstance().findListenGuidelineProperty(properties,command.getSortExpression(),command.getSortDirection(),command.getFirstItem(),command.getMaxPageItems());
        command.setListResult((List<ListenGuidelineDTO>) objects[1]);
        command.setTotalItems(Integer.parseInt(objects[0].toString()));
    }

    private Map<String, Object> buildMapProperties(ListenGuidelineCommand command) {
        Map<String, Object> properties = new HashMap<String, Object>();
        if(StringUtils.isNotBlank(command.getPojo().getTitle())) {
            properties.put("title", command.getPojo().getTitle().trim());
        }
        return properties;
    }

    private Map<String, String> buildMapRedirecMessage( ResourceBundle bundle) {
        Map<String, String> mapMessage = new HashMap<String, String>();
        mapMessage.put(WebConstant.REDIRECT_INSERT, bundle.getString("label.listen.guideline.add.success"));
        mapMessage.put(WebConstant.REDIRECT_UPDATE, bundle.getString("label.listen.guideline.update.success"));
        mapMessage.put(WebConstant.REDIRECT_DELETE, bundle.getString("label.listen.guideline.delete.success"));
        mapMessage.put(WebConstant.REDIRECT_ERROR, bundle.getString("label.message.error"));
        return mapMessage;
    }

    protected void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResource");
        UploadUtil uploadUtil = new UploadUtil();
        ListenGuidelineCommand command = new ListenGuidelineCommand();
        Set<String> valueTitle = buildSetValueListenGuideline();
        Object[] objects = uploadUtil.writeOrUpdateFile(request, valueTitle,WebConstant.LISTENGUIDELINE);
        Boolean checkStatusUploadImage = (Boolean) objects[0];
        if(!checkStatusUploadImage) {
            response.sendRedirect("/admin-guideline-listen-list.html?urlType=url_list&&crudaction=redirect_error");
        } else {
            ListenGuidelineDTO dto = command.getPojo();
            if(StringUtils.isNotBlank(objects[2].toString())) {
                dto.setImage(objects[2].toString());
            }
            Map<String, String> mapValue = (Map<String, String>) objects[3];
            dto = returnValueOfListenGuidelineDTO(dto, mapValue);
            if( dto != null) {
                if( dto.getListenGuidelineId() != null) {
                    ListenGuidelineDTO listenGuidelineDTO = SingletonServiceUtil.getListenGuidelineServiceInstance().findByListenGuidelineId("listenGuidelineId", dto.getListenGuidelineId() );
                    if(dto.getImage() == null) {
                        dto.setImage(listenGuidelineDTO.getImage());
                    }
                    dto.setCreatedDate(listenGuidelineDTO.getCreatedDate());
                    ListenGuidelineDTO result = SingletonServiceUtil.getListenGuidelineServiceInstance().updateListenGuideline(dto);
                    if(result != null) {
                        response.sendRedirect("/admin-guideline-listen-list.html?urlType=url_list&&crudaction=redirect_update");
                    } else {
                        response.sendRedirect("/admin-guideline-listen-list.html?urlType=url_list&&crudaction=redirect_error");
                    }
                } else {
                    try {
                        SingletonServiceUtil.getListenGuidelineServiceInstance().saveListenGuideline(dto);
                        response.sendRedirect("/admin-guideline-listen-list.html?urlType=url_list&&crudaction=redirect_insert");
                    } catch (ConstraintViolationException e) {
                        log.error(e.getMessage(), e);
                        response.sendRedirect("/admin-guideline-listen-list.html?urlType=url_list&&crudaction=redirect_error");
                    }
                }
            }
        }
    }

    private ListenGuidelineDTO returnValueOfListenGuidelineDTO(ListenGuidelineDTO dto, Map<String, String> mapValue) {
        for(Map.Entry<String,String> item : mapValue.entrySet()) {
            if(item.getKey().equals("pojo.title")) {
                dto.setTitle(item.getValue());
            } else if (item.getKey().equals("pojo.content")) {
                dto.setContent(item.getValue());
            } else if (item.getKey().equals("pojo.listenGuidelineId")) {
                dto.setListenGuidelineId(Integer.parseInt(item.getValue().toString()));
            }
        }

        return dto;
    }

    private Set<String> buildSetValueListenGuideline() {
        Set<String> returnValue = new HashSet<String>();
        returnValue.add("pojo.listenGuidelineId");
        returnValue.add("pojo.title");
        returnValue.add("pojo.content");
        return returnValue;
    }
}
