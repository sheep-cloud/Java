package opsteel.oupuzw.service.blank.note.dao;

import opsteel.oupuzw.api.blank.note.entity.BnInsCapitalEntity;

public interface BnInsCapitalDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BnInsCapitalEntity record);

    int insertSelective(BnInsCapitalEntity record);

    BnInsCapitalEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BnInsCapitalEntity record);

    int updateByPrimaryKey(BnInsCapitalEntity record);
}