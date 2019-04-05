package vn.education.core.utils;

import vn.education.core.dto.ExerciseQuestionDTO;
import vn.education.core.persistence.entity.ExerciseQuestionEntity;

public class ExerciseQuestionBeanUtil {
    public static ExerciseQuestionDTO entity2Dto(ExerciseQuestionEntity entity) {
        ExerciseQuestionDTO dto = new ExerciseQuestionDTO();
        dto.setExerciseQuestionId(entity.getExerciseQuestionId());
        dto.setAudio(entity.getAudio());
        dto.setImage(entity.getImage());
        dto.setOption1(entity.getOption1());
        dto.setOption2(entity.getOption2());
        dto.setOption3(entity.getOption3());
        dto.setOption4(entity.getOption4());
        dto.setCorrectAnswer(entity.getCorrectAnswer());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setQuestion(entity.getQuestion());
        dto.setExercise(ExerciseBeanUtil.entity2Dto(entity.getExerciseEntity()));
        return dto;
    }

    public static ExerciseQuestionEntity dto2Entity(ExerciseQuestionDTO dto) {
        ExerciseQuestionEntity entity = new ExerciseQuestionEntity();
        entity.setExerciseQuestionId(dto.getExerciseQuestionId());
        entity.setAudio(dto.getAudio());
        entity.setImage(dto.getImage());
        entity.setOption1(dto.getOption1());
        entity.setOption2(dto.getOption2());
        entity.setOption3(dto.getOption3());
        entity.setOption4(dto.getOption4());
        entity.setCorrectAnswer(dto.getCorrectAnswer());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setModifiedDate(dto.getModifiedDate());
        entity.setQuestion(dto.getQuestion());
        entity.setExerciseEntity(ExerciseBeanUtil.dto2Entity(dto.getExercise()));
        return entity;
    }
}
