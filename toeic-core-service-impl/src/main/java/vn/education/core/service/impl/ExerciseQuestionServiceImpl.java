package vn.education.core.service.impl;

import vn.education.core.dto.ExerciseQuestionDTO;
import vn.education.core.persistence.entity.ExerciseQuestionEntity;
import vn.education.core.service.ExerciseQuestionService;
import vn.education.core.service.utils.SingletonDaoUtil;
import vn.education.core.utils.ExerciseQuestionBeanUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExerciseQuestionServiceImpl implements ExerciseQuestionService {
    public Object[] findExerciseQuestionByProperties(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        List<ExerciseQuestionDTO> result = new ArrayList<ExerciseQuestionDTO>();
        Object[] objects = SingletonDaoUtil.getExerciseQuestionDaoInstance().findByProperty(property,sortExpression,sortDirection,offset,limit);
        for(ExerciseQuestionEntity item : (List<ExerciseQuestionEntity>)objects[1]) {
            ExerciseQuestionDTO dto = ExerciseQuestionBeanUtil.entity2Dto(item);
            result.add(dto);
        }
        objects[1] = result;
        return objects;
    }
}
