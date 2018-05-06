package opsteel.oupuzw.service.blank.note.dao;

import opsteel.oupuzw.api.blank.note.entity.BnInterestItemEntity;

public interface BnInterestItemDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BnInterestItemEntity record);

    int insertSelective(BnInterestItemEntity record);

    BnInterestItemEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BnInterestItemEntity record);

    int updateByPrimaryKey(BnInterestItemEntity record);
}