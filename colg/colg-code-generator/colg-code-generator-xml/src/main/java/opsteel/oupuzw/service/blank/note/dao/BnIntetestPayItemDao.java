package opsteel.oupuzw.service.blank.note.dao;

import opsteel.oupuzw.api.blank.note.entity.BnIntetestPayItemEntity;

public interface BnIntetestPayItemDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BnIntetestPayItemEntity record);

    int insertSelective(BnIntetestPayItemEntity record);

    BnIntetestPayItemEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BnIntetestPayItemEntity record);

    int updateByPrimaryKey(BnIntetestPayItemEntity record);
}