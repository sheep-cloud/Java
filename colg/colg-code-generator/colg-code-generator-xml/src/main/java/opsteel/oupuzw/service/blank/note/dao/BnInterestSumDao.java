package opsteel.oupuzw.service.blank.note.dao;

import opsteel.oupuzw.api.blank.note.entity.BnInterestSumEntity;

public interface BnInterestSumDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BnInterestSumEntity record);

    int insertSelective(BnInterestSumEntity record);

    BnInterestSumEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BnInterestSumEntity record);

    int updateByPrimaryKey(BnInterestSumEntity record);
}