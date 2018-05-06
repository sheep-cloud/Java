package opsteel.oupuzw.service.blank.note.dao;

import opsteel.oupuzw.api.blank.note.entity.BnBroSettingEntity;

public interface BnBroSettingDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BnBroSettingEntity record);

    int insertSelective(BnBroSettingEntity record);

    BnBroSettingEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BnBroSettingEntity record);

    int updateByPrimaryKey(BnBroSettingEntity record);
}