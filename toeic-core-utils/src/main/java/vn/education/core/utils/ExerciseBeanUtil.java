package vn.education.core.utils;

import vn.education.core.dto.ExerciseDTO;
import vn.education.core.persistence.entity.ExerciseEntity;

public class ExerciseBeanUtil {
    //Map theo cac cot trong database
    public static ExerciseDTO entity2Dto(ExerciseEntity entity) {
        ExerciseDTO dto = new ExerciseDTO();
        dto.setExerciseId(entity.getExerciseId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setName(entity.getName());
        dto.setType(entity.getType());
        return dto;
    }

    public static ExerciseEntity dto2Entity(ExerciseDTO dto) {
        ExerciseEntity entity = new ExerciseEntity();
        entity.setExerciseId(dto.getExerciseId());
        entity.setName(dto.getName());
        entity.setType(dto.getType());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setModifiedDate(dto.getModifiedDate());
        return entity;
    }
}
