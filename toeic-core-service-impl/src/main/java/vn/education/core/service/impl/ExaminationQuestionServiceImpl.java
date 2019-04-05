package vn.education.core.service.impl;

import vn.education.core.dto.ExaminationQuestionDTO;
import vn.education.core.persistence.entity.ExaminationQuestionEntity;
import vn.education.core.service.ExaminationQuestionService;
import vn.education.core.service.utils.SingletonDaoUtil;
import vn.education.core.utils.ExaminationQuestionBeanUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExaminationQuestionServiceImpl implements ExaminationQuestionService {
    public Object[] findExaminationQuestionByProperties(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        List<ExaminationQuestionDTO> result = new ArrayList<ExaminationQuestionDTO>();
        Object[] objects = SingletonDaoUtil.getExaminationQuestionDaoInstance().findByProperty(property, sortExpression, sortDirection, offset, limit);
        int count = 1;//count dung de hien thi so cau hoi len man hinh
        for (ExaminationQuestionEntity item : (List<ExaminationQuestionEntity>) objects[1]) {
            ExaminationQuestionDTO dto = ExaminationQuestionBeanUtil.entity2Dto(item);
            if(item.getParagraph() == null) {
                dto.setNumber(count);
                count ++;
            }
            result.add(dto);
        }
        objects[1] = result;
        return objects;
    }
}
