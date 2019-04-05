package vn.education.core.utils;

import vn.education.core.dto.ExaminationQuestionDTO;
import vn.education.core.dto.ExerciseQuestionDTO;
import vn.education.core.persistence.entity.ExaminationQuestionEntity;
import vn.education.core.persistence.entity.ExerciseQuestionEntity;

public class ExaminationQuestionBeanUtil {
    public static ExaminationQuestionDTO entity2Dto(ExaminationQuestionEntity entity) {
        ExaminationQuestionDTO dto = new ExaminationQuestionDTO();
        dto.setExaminationQuestionId(entity.getExaminationQuestionId());
        dto.setImage(entity.getImage());
        dto.setAudio(entity.getAudio());
        dto.setQuestion(entity.getQuestion());
        dto.setOption1(entity.getOption1());
        dto.setOption2(entity.getOption2());
        dto.setOption3(entity.getOption3());
        dto.setOption4(entity.getOption4());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setCorrectAnswer(entity.getCorrectAnswer());
        dto.setParagraph(entity.getParagraph());
        dto.setType(entity.getType());
        dto.setExamination(ExaminationBeanUtil.entity2Dto(entity.getExaminationEntity()));
        return dto;
    }

    public static ExaminationQuestionEntity dto2Entity(ExaminationQuestionDTO dto) {
        ExaminationQuestionEntity entity = new ExaminationQuestionEntity();
        entity.setExaminationQuestionId(dto.getExaminationQuestionId());
        entity.setImage(dto.getImage());
        entity.setAudio(dto.getAudio());
        entity.setQuestion(dto.getQuestion());
        entity.setOption1(dto.getOption1());
        entity.setOption2(dto.getOption2());
        entity.setOption3(dto.getOption3());
        entity.setOption4(dto.getOption4());
        entity.setModifiedDate(dto.getModifiedDate());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setCorrectAnswer(dto.getCorrectAnswer());
        entity.setParagraph(dto.getParagraph());
        entity.setType(dto.getType());
        entity.setExaminationEntity(ExaminationBeanUtil.dto2Entity(dto.getExamination()));
        return entity;
    }
}
