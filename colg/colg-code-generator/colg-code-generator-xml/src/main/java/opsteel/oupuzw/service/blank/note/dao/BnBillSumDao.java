package opsteel.oupuzw.service.blank.note.dao;

import opsteel.oupuzw.api.blank.note.entity.BnBillSumEntity;

public interface BnBillSumDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BnBillSumEntity record);

    int insertSelective(BnBillSumEntity record);

    BnBillSumEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BnBillSumEntity record);

    int updateByPrimaryKey(BnBillSumEntity record);
}