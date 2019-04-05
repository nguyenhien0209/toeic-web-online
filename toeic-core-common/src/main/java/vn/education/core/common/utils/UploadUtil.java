package vn.education.core.common.utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import vn.education.core.common.constant.CoreConstant;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UploadUtil {
    private final Logger log = Logger.getLogger(this.getClass());
    private final int maxMemorySize = 1024*1024*3;
    private final int maxRequestSize = 1024*1024*50;
    public Object[] writeOrUpdateFile(HttpServletRequest request, Set<String> titleValue, String path) {
        String address = "/" + CoreConstant.FOLDER_UPLOAD;
        checkAndCreateFolder(address, path);
        boolean check = true;
        String fileLocation = null;
        String name = null;
        Map<String, String> mapReturnValue = new HashMap<String, String>();
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if(!isMultipart) {
            check = false;
            System.out.println("have not ectype multipart/form");
        }
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();

        diskFileItemFactory.setSizeThreshold(maxMemorySize);
        diskFileItemFactory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        servletFileUpload.setSizeMax(maxRequestSize);

        try {
            List<FileItem> items = servletFileUpload.parseRequest(request);
            for(FileItem item : items) {
                if(!item.isFormField()) {
                    name  = item.getName();
                    if(StringUtils.isNotBlank(name)) {
                        File fileUpload = new File(address+ File.separator + path + File.separator + name);
                        fileLocation = address+ File.separator + path + File.separator + name;
                        boolean isExist = fileUpload.exists();
                        try {
                            if(isExist) {
                                fileUpload.delete();
                                item.write(fileUpload);
                            } else {
                                item.write(fileUpload);
                            }
                        } catch (Exception e) {
                            check = false;
                            log.error(e.getMessage(), e);
                        }
                    }
                } else {
                    if(titleValue != null) {
                        String nameField = item.getFieldName();
                        String valueField = null;
                        try {
                            valueField = item.getString("UTF-8");
                        } catch (UnsupportedEncodingException e) {
                         }
                        if(titleValue.contains(nameField)) {
                            mapReturnValue.put(nameField, valueField);
                        }
                    }
                }
            }
        } catch (FileUploadException e) {
            check = false;
            log.error(e.getMessage(), e);
        }
        String fileName = "";
        if(StringUtils.isNotBlank(name)) {
            fileName = path + File.separator + name;
        }
        return new Object[]{check, fileLocation, fileName, mapReturnValue};
    }

    private void checkAndCreateFolder(String address, String path) {
        File folderRoot = new File(address);
        if (!folderRoot.exists()) {
            folderRoot.mkdirs();
        }

        File folderChild = new File(address + File.separator + path);
        if(!folderChild.exists()) {
            folderChild.mkdirs();
        }
    }
}
