package cn.yanweijia.slimming.service.impl;

import cn.yanweijia.slimming.dao.IRunRecordDAO;
import cn.yanweijia.slimming.model.RunRecord;
import cn.yanweijia.slimming.model.RunRecordExample;
import cn.yanweijia.slimming.service.ISportService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("sportService")
public class SportServiceImpl implements ISportService {
    @Resource
    private IRunRecordDAO runRecordDAO;

    @Override
    public List<RunRecord> listRunRecoard(int userid, Date startTime, Date endTime) {
        RunRecordExample example = new RunRecordExample();
        example.or().andUserIdEqualTo(userid).andStarttimeBetween(startTime, endTime);
        return runRecordDAO.selectByExampleWithBLOBs(example);
    }

    @Override
    public int saveRunRecoard(RunRecord record) {
        record.setId(null);
        return runRecordDAO.insert(record);
    }
}
