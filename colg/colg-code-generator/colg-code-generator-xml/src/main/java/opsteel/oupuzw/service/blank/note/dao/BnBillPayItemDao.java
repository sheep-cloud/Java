package opsteel.oupuzw.service.blank.note.dao;

import opsteel.oupuzw.api.blank.note.entity.BnBillPayItemEntity;

public interface BnBillPayItemDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BnBillPayItemEntity record);

    int insertSelective(BnBillPayItemEntity record);

    BnBillPayItemEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BnBillPayItemEntity record);

    int updateByPrimaryKey(BnBillPayItemEntity record);
}