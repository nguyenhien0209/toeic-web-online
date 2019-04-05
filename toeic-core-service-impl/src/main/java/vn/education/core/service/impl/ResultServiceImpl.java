package vn.education.core.service.impl;

import vn.education.core.dto.ExaminationQuestionDTO;
import vn.education.core.dto.ResultDTO;
import vn.education.core.persistence.entity.ExaminationEntity;
import vn.education.core.persistence.entity.ResultEntity;
import vn.education.core.persistence.entity.UserEntity;
import vn.education.core.service.ResultService;
import vn.education.core.service.utils.SingletonDaoUtil;
import vn.education.core.utils.ResultBeanUtil;

import java.sql.Timestamp;
import java.util.List;

public class ResultServiceImpl implements ResultService {
    public ResultDTO saveResult(String userName, Integer examinationId, List<ExaminationQuestionDTO> examinationQuestions) {
        ResultDTO resultDTO = new ResultDTO();
        if(userName != null && examinationId != null && examinationQuestions != null) {
            UserEntity user = SingletonDaoUtil.getUserDaoInstance().findEqualUnique("name", userName);
            ExaminationEntity examination = SingletonDaoUtil.getExaminationDaoInstance().findById(examinationId);
            ResultEntity resultEntity = new ResultEntity();
            calculateListenAndReadingScore(resultEntity, examinationQuestions);
            initDataToResultEntity(resultEntity, user, examination);
            resultEntity = SingletonDaoUtil.getResultDaoInstance().save(resultEntity);
            resultDTO = ResultBeanUtil.entity2Dto(resultEntity);
        }
        return resultDTO;
    }

    private void initDataToResultEntity(ResultEntity resultEntity, UserEntity user, ExaminationEntity examination) {
        Timestamp createdDate = new Timestamp(System.currentTimeMillis());
        resultEntity.setExaminationEntity(examination);
        resultEntity.setUser(user);
        resultEntity.setCreatedDate(createdDate);
    }

    private void calculateListenAndReadingScore(ResultEntity resultEntity, List<ExaminationQuestionDTO> examinationQuestions) {
        int listenScore = 0;
        int readingScore = 0;
        for(ExaminationQuestionDTO item : examinationQuestions) {
            if(item.getAnswerUser() != null ) {
                if(item.getAnswerUser().equals(item.getCorrectAnswer())) {
                    if(item.getNumber() <=4) {
                        listenScore ++;
                    } else {
                        readingScore ++;
                    }
                }
            }
        }
        resultEntity.setListenScore(listenScore);
        resultEntity.setReadingScore(readingScore);
    }
}
