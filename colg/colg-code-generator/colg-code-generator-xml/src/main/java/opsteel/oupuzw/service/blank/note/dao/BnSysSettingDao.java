package opsteel.oupuzw.service.blank.note.dao;

import opsteel.oupuzw.api.blank.note.entity.BnSysSettingEntity;

public interface BnSysSettingDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BnSysSettingEntity record);

    int insertSelective(BnSysSettingEntity record);

    BnSysSettingEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BnSysSettingEntity record);

    int updateByPrimaryKey(BnSysSettingEntity record);
}