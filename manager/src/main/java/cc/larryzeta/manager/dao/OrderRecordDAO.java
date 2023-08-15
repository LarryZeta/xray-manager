package cc.larryzeta.manager.dao;

import cc.larryzeta.manager.entity.TOrderRecord;
import cc.larryzeta.manager.entity.TOrderRecordExample;
import cc.larryzeta.manager.enumeration.ReturnCodeEnum;
import cc.larryzeta.manager.exception.BizException;
import cc.larryzeta.manager.mapper.TOrderRecordMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Repository
public class OrderRecordDAO {

    @Resource
    private TOrderRecordMapper orderRecordMapper;


    public void saveTOrderRecord(TOrderRecord tOrderRecord) {

        log.info("[TUserBaseInfoDao-saveTOrderRecord] tOrderRecord=[{}]", tOrderRecord);

        try {
            orderRecordMapper.insertSelective(tOrderRecord);
        } catch (Exception e) {
            log.error("[TUserBaseInfoDao-saveTOrderRecord] error Exception:", e);
            throw e;
        }
    }

    public void updateTOrderRecord(TOrderRecord tOrderRecord) {

        log.info("[TUserBaseInfoDao-updateTOrderRecord] tOrderRecord=[{}]", tOrderRecord);

        try {

            if (tOrderRecord == null || tOrderRecord.getOrderId() == null) {
                throw new BizException(ReturnCodeEnum.EXCEPTION.code, "updateTOrderRecord no condition");
            }

            orderRecordMapper.updateByPrimaryKeySelective(tOrderRecord);
        } catch (Exception e) {
            log.error("[TUserBaseInfoDao-updateTOrderRecord] error Exception:", e);
            throw e;
        }

    }

    public List<TOrderRecord> queryTOrderRecord(TOrderRecord tOrderRecord) {

        log.info("queryTOrderRecord tOrderRecord: [{}]", tOrderRecord);

        try {

            TOrderRecordExample example = new TOrderRecordExample();
            TOrderRecordExample.Criteria criteria = example.createCriteria();

            if (tOrderRecord == null) {
                return orderRecordMapper.selectByExample(example);
            }

            if (tOrderRecord.getUserId() != null) {
                criteria.andUserIdEqualTo(tOrderRecord.getUserId());
            }

            if (StringUtils.hasText(tOrderRecord.getOrderId())) {
                criteria.andOrderIdEqualTo(tOrderRecord.getOrderId());
            }

            return orderRecordMapper.selectByExample(example);

        } catch (Exception e) {
            log.error("queryTOrderRecord error Exception:", e);
            throw e;
        }

    }

}
