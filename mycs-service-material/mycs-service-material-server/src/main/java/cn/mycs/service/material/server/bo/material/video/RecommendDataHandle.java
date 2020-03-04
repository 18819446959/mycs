package cn.mycs.service.material.server.bo.material.video;

import cn.mycs.core.support.DateTimeKit;
import cn.mycs.core.util.SpringContextHolder;
import cn.mycs.front.service.user.provider.UserProvider;
import cn.mycs.front.service.user.user.protocol.UserProtocol;
import cn.mycs.service.material.provider.bean.dto.RecommendDto;
import cn.mycs.service.material.server.persistence.model.Share;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/***
 * 推送数据处理类
 */
public class RecommendDataHandle {

    /***
     * 推送数据处理
     * @param shaerList 推广分享数据
     * @return 返回 List<RecommendDto>
     */
    public static List<RecommendDto> RecommendDataList(List<Share> shaerList) {
        List<RecommendDto> list = new ArrayList<>();
        if (shaerList != null && shaerList.size() > 0) {
            //查询用户信息
            Set<Long> longList = shaerList.stream().map(s -> s.getShareUid()).collect(Collectors.toSet());
            Map<Long, UserProtocol.UserBaseVo> userMap = new HashMap();
            if (longList != null && longList.size() > 0) {
                UserProvider userMapper = SpringContextHolder.getBean(UserProvider.class);
                List<UserProtocol.UserBaseVo> users = userMapper.getBaseUserByIds(new ArrayList<>(longList));
                if (users != null && users.size() > 0) {
                    for (UserProtocol.UserBaseVo u : users) {
                        userMap.put(u.getUid(), u);
                    }
                }
                users = null;
            }

            //遍历转换数据类型/处理数据
            for (Share share : shaerList) {
                RecommendDto dto = new RecommendDto();
                if (share.getShareTime() != null && share.getShareTime() > 0) {
                    dto.setTime(DateTimeKit.parseScondTime(share.getShareTime()));
                } else {
                    dto.setTime("");
                }
                dto.setRecommendReason(share.getShareReason());
                UserProtocol.UserBaseVo user = userMap.get(share.getShareUid());
                dto.setName("");
                dto.setRecommendUserAvatar("");
                if (user != null) {
                    dto.setName(user.getRealName());
                    dto.setRecommendUserAvatar(user.getPortraitUrl() == null ? "" : user.getPortraitUrl());
                }
                list.add(dto);
            }
            userMap = null;
            longList = null;
            shaerList = null;
        }
        return list;
    }
}
