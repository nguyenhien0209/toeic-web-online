package vn.education.core.service.impl;

import vn.education.core.dto.ExaminationDTO;
import vn.education.core.dto.ExerciseDTO;
import vn.education.core.persistence.entity.ExaminationEntity;
import vn.education.core.persistence.entity.ExerciseEntity;
import vn.education.core.service.ExaminationService;
import vn.education.core.service.utils.SingletonDaoUtil;
import vn.education.core.utils.ExaminationBeanUtil;
import vn.education.core.utils.ExerciseBeanUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExaminationServiceImpl implements ExaminationService {
    public Object[] findExaminationByProperties(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        List<ExaminationDTO> result = new ArrayList<ExaminationDTO>();
        Object[] objects = SingletonDaoUtil.getExaminationDaoInstance().findByProperty(property, sortExpression, sortDirection, offset, limit);
        for (ExaminationEntity item : (List<ExaminationEntity>) objects[1]) {
            ExaminationDTO dto = ExaminationBeanUtil.entity2Dto(item);
            result.add(dto);
        }
        objects[1] = result;
        return objects;
    }
}
