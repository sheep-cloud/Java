package opsteel.oupuzw.service.blank.note.dao;

import opsteel.oupuzw.api.blank.note.entity.BnInsCapitalItemEntity;

public interface BnInsCapitalItemDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BnInsCapitalItemEntity record);

    int insertSelective(BnInsCapitalItemEntity record);

    BnInsCapitalItemEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BnInsCapitalItemEntity record);

    int updateByPrimaryKey(BnInsCapitalItemEntity record);
}