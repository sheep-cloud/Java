package opsteel.oupuzw.service.blank.note.dao;

import opsteel.oupuzw.api.blank.note.entity.BnInsCapitalSumEntity;

public interface BnInsCapitalSumDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BnInsCapitalSumEntity record);

    int insertSelective(BnInsCapitalSumEntity record);

    BnInsCapitalSumEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BnInsCapitalSumEntity record);

    int updateByPrimaryKey(BnInsCapitalSumEntity record);
}