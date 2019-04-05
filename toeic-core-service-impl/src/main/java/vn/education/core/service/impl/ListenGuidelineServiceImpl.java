package vn.education.core.service.impl;

import org.hibernate.exception.ConstraintViolationException;
import vn.education.core.dao.ListenGuidelineDao;
import vn.education.core.daoimpl.ListenGuidelineDaoImpl;
import vn.education.core.dto.ListenGuidelineDTO;
import vn.education.core.persistence.entity.ListenGuidelineEntity;
import vn.education.core.service.ListenGuidelineService;
import vn.education.core.service.utils.SingletonDaoUtil;
import vn.education.core.utils.ListenGuidelineBeanUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListenGuidelineServiceImpl implements ListenGuidelineService {
    public Object[] findListenGuidelineProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        List<ListenGuidelineDTO> result = new ArrayList<ListenGuidelineDTO>();
        Object[] objects = SingletonDaoUtil.getListenGuidelineDaoInstance().findByProperty(property,sortExpression,sortDirection,offset,limit);
        for(ListenGuidelineEntity item : (List<ListenGuidelineEntity>)objects[1]) {
            ListenGuidelineDTO dto = ListenGuidelineBeanUtil.entity2Dto(item);
            result.add(dto);
        }
        objects[1] = result;
        return objects;
    }

    public ListenGuidelineDTO findByListenGuidelineId(String property, Integer listenGuidelineId) {

        ListenGuidelineEntity listenGuidelineEntity = SingletonDaoUtil.getListenGuidelineDaoInstance().findEqualUnique(property, listenGuidelineId);
        ListenGuidelineDTO listenGuidelineDTO = ListenGuidelineBeanUtil.entity2Dto(listenGuidelineEntity);
        return  listenGuidelineDTO;
    }

    public void saveListenGuideline(ListenGuidelineDTO listenGuidelineDTO) throws ConstraintViolationException {
        Timestamp createDate = new Timestamp(System.currentTimeMillis());
        listenGuidelineDTO.setCreatedDate(createDate);
        SingletonDaoUtil.getListenGuidelineDaoInstance().save(ListenGuidelineBeanUtil.dto2Entity(listenGuidelineDTO));
    }

    public ListenGuidelineDTO updateListenGuideline(ListenGuidelineDTO listenGuidelineDTO) {
        Timestamp modifiedDate = new Timestamp(System.currentTimeMillis());
        listenGuidelineDTO.setModifiedDate(modifiedDate);
        ListenGuidelineEntity listenGuidelineEntity = SingletonDaoUtil.getListenGuidelineDaoInstance().update(ListenGuidelineBeanUtil.dto2Entity(listenGuidelineDTO));
        return ListenGuidelineBeanUtil.entity2Dto(listenGuidelineEntity);
    }

    public Integer delete(List<Integer> ids) {
        Integer result = SingletonDaoUtil.getListenGuidelineDaoInstance().delete(ids);
        return result;
    }
}
