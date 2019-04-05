package vn.education.core.service;

import vn.education.core.dto.ExaminationQuestionDTO;
import vn.education.core.dto.ResultDTO;

import java.util.List;

public interface ResultService {
    ResultDTO saveResult(String userName, Integer examinationId, List<ExaminationQuestionDTO> examinationQuestions);
}
