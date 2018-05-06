package opsteel.oupuzw.service.blank.note.dao;

import opsteel.oupuzw.api.blank.note.entity.BnInsProfitSumEntity;

public interface BnInsProfitSumDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BnInsProfitSumEntity record);

    int insertSelective(BnInsProfitSumEntity record);

    BnInsProfitSumEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BnInsProfitSumEntity record);

    int updateByPrimaryKey(BnInsProfitSumEntity record);
}