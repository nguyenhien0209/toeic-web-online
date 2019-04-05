package vn.education.core.utils;

import vn.education.core.dto.ResultDTO;
import vn.education.core.persistence.entity.ResultEntity;

public class ResultBeanUtil {
    public static ResultDTO entity2Dto(ResultEntity entity) {
        ResultDTO dto = new ResultDTO();
        dto.setResultId(entity.getResultId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setListenScore(entity.getListenScore());
        dto.setReadingScore(entity.getReadingScore());
        dto.setExamination(ExaminationBeanUtil.entity2Dto(entity.getExaminationEntity()));
        dto.setUser(UserBeanUtil.entity2Dto(entity.getUser()));
        return dto;
    }

    public static ResultEntity dto2Entity(ResultDTO dto) {
        ResultEntity entity = new ResultEntity();
        entity.setResultId(dto.getResultId());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setModifiedDate(dto.getModifiedDate());
        entity.setListenScore(dto.getListenScore());
        entity.setReadingScore(dto.getReadingScore());
        entity.setExaminationEntity(ExaminationBeanUtil.dto2Entity(dto.getExamination()));
        entity.setUser(UserBeanUtil.dto2Entity(dto.getUser()));
        return entity;
    }
}
