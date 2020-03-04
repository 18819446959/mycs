package cn.mycs.service.material.server.service.impl;

import cn.mycs.front.service.user.provider.UserProvider;
import cn.mycs.front.service.user.user.protocol.UserProtocol;
import cn.mycs.service.material.server.persistence.dao.ShareMapper;
import cn.mycs.service.material.server.persistence.model.Share;
import cn.mycs.service.material.server.service.IShareService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>分享对象服务接口实现类</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/7 10:21
 * </pre>
 */
@Service
public class ShareServiceImpl extends ServiceImpl<ShareMapper, Share> implements IShareService {
    @Autowired
    private ShareMapper shareMapper;
    @Autowired
    private UserProvider userProvider;


    @Override
    public Integer howManyShare(Long srcId, int type) {
        return shareMapper.howManyShare(srcId, type);
    }

    @Override
    public UserProtocol.UserBaseVo getUser(Long uid) {
        return userProvider.getBaseUser(uid);
    }

    @Override
    public List<Share> selectSharePage(Integer offset, Integer limit, Long videoUserId, int type) {
        return shareMapper.selectSharePage(offset, limit, videoUserId, type);
    }

    @Override
    public List<Long> selectFiveShareUid(Long videoUserId, int type) {
        return shareMapper.selectFiveShareUid(videoUserId, type);
    }

    @Override
    public List<Share> selectFiveShare(Long videoUserId, int type) {
        return shareMapper.selectFiveShare(videoUserId, type);
    }

    @Override
    public Share getShareById(String shareId) {
        return shareMapper.selectById(shareId);
    }
}
