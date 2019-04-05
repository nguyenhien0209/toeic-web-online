package vn.education.core.service;

import org.hibernate.exception.ConstraintViolationException;
import vn.education.core.dto.ListenGuidelineDTO;

import java.util.List;
import java.util.Map;

public interface ListenGuidelineService {
    Object[] findListenGuidelineProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    ListenGuidelineDTO findByListenGuidelineId(String property, Integer listenGuidelineId);
    void saveListenGuideline(ListenGuidelineDTO listenGuidelineDTO) throws ConstraintViolationException;
    ListenGuidelineDTO updateListenGuideline (ListenGuidelineDTO listenGuidelineDTO);
    Integer delete (List<Integer> ids);
}
