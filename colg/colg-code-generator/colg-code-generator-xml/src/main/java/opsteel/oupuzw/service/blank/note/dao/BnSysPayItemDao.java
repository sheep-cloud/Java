package opsteel.oupuzw.service.blank.note.dao;

import opsteel.oupuzw.api.blank.note.entity.BnSysPayItemEntity;

public interface BnSysPayItemDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BnSysPayItemEntity record);

    int insertSelective(BnSysPayItemEntity record);

    BnSysPayItemEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BnSysPayItemEntity record);

    int updateByPrimaryKey(BnSysPayItemEntity record);
}