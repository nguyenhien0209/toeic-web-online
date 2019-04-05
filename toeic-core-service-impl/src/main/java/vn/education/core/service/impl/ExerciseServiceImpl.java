package vn.education.core.service.impl;

import vn.education.core.dto.ExerciseDTO;
import vn.education.core.persistence.entity.ExerciseEntity;
import vn.education.core.service.ExerciseService;
import vn.education.core.service.utils.SingletonDaoUtil;
import vn.education.core.utils.ExerciseBeanUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExerciseServiceImpl implements ExerciseService {
    public Object[] findExerciseByProperties(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        List<ExerciseDTO> result = new ArrayList<ExerciseDTO>();
        Object[] objects = SingletonDaoUtil.getExerciseDaoInstance().findByProperty(property,sortExpression,sortDirection,offset,limit);
        for(ExerciseEntity item : (List<ExerciseEntity>)objects[1]) {
            ExerciseDTO dto = ExerciseBeanUtil.entity2Dto(item);
            result.add(dto);
        }
        objects[1] = result;
        return objects;
    }
}
