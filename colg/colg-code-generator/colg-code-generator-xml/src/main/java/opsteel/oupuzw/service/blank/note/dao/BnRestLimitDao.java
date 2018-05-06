package opsteel.oupuzw.service.blank.note.dao;

import opsteel.oupuzw.api.blank.note.entity.BnRestLimitEntity;

public interface BnRestLimitDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BnRestLimitEntity record);

    int insertSelective(BnRestLimitEntity record);

    BnRestLimitEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BnRestLimitEntity record);

    int updateByPrimaryKey(BnRestLimitEntity record);
}