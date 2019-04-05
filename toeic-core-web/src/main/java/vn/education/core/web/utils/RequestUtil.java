package vn.education.core.web.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import vn.education.core.web.command.AbstractCommand;

import javax.servlet.http.HttpServletRequest;
 public  class  RequestUtil {
     static int a = 01;
    public static void initSearchBean(HttpServletRequest request, AbstractCommand bean) {
        if(bean != null) {
            String sortExpressing = request.getParameter(new ParamEncoder(bean.getTableId()).encodeParameterName(TableTagParameters.PARAMETER_SORT));
            String sortDirection = request.getParameter(new ParamEncoder(bean.getTableId()).encodeParameterName(TableTagParameters.PARAMETER_ORDER));
            String pageStr = request.getParameter(new ParamEncoder(bean.getTableId()).encodeParameterName(TableTagParameters.PARAMETER_PAGE));
            Integer page = 1;
            if(StringUtils.isNotBlank(pageStr)) { //Kiem tra pageStr ="" hay khong
                try{
                    page = Integer.valueOf(pageStr);
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
            bean.setPage(page);
            bean.setSortDirection(sortDirection);
            bean.setSortExpression(sortExpressing);
            bean.setFirstItem((bean.getPage()-1)*bean.getMaxPageItems());
        }
    }

    public static void initSearchBeanManual(AbstractCommand command) {
        if(command != null) {
            Integer page  = 1;
            if( command.getPage() != 0) {
                page = command.getPage();
            }
            command.setPage(page);
            command.setFirstItem((command.getPage()-1)*command.getMaxPageItems());
        }
    }
}
