package opsteel.oupuzw.service.blank.note.dao;

import opsteel.oupuzw.api.blank.note.entity.BnSysPaySumEntity;

public interface BnSysPaySumDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BnSysPaySumEntity record);

    int insertSelective(BnSysPaySumEntity record);

    BnSysPaySumEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BnSysPaySumEntity record);

    int updateByPrimaryKey(BnSysPaySumEntity record);
}