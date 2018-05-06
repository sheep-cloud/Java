package opsteel.oupuzw.service.blank.note.dao;

import opsteel.oupuzw.api.blank.note.entity.BnInsProfitEntity;

public interface BnInsProfitDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BnInsProfitEntity record);

    int insertSelective(BnInsProfitEntity record);

    BnInsProfitEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BnInsProfitEntity record);

    int updateByPrimaryKey(BnInsProfitEntity record);
}