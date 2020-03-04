package cn.mycs.service.member.server.service.impl;

import cn.mycs.core.support.DateTimeKit;
import cn.mycs.service.member.server.persistence.dao.MemberIdentityVideoLinkMapper;
import cn.mycs.service.member.server.persistence.dao.MemberMapper;
import cn.mycs.service.member.server.persistence.model.Member;
import cn.mycs.service.member.server.service.IMemberServerVideo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <p>会员视频相关服务接口实现类</p>
 * <p>
 * <pre>
 * @author gitamacai
 * @date 2019/11/22 15:42
 * </pre>
 */
@Service
public class MemberServerVideoImpl implements IMemberServerVideo {
    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private MemberIdentityVideoLinkMapper memberVideoMapper;

    @Override
    public boolean checkedPermission(@PathVariable("uid") Long uid, @PathVariable("videoId") Long videoId) {
        Member member = memberMapper.selectByUid(uid);
        boolean havePermission = false;
        if (member != null) {
            Integer endTime = member.getEndTime();
            if (endTime > DateTimeKit.currentDaySecond()) {
                Integer count = memberVideoMapper.memberHaveVideo(member.getMemberIdentityId(), videoId);
                if (count > 0) {
                    havePermission = true;
                }
            }
        }
        return havePermission;
    }
}
