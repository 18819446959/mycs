package cn.mycs.service.material.server.service.impl;

import cn.mycs.core.base.restful.JsonResult;
import cn.mycs.core.constant.state.StatusCodeEnum;
import cn.mycs.front.service.basic.category.protocol.CategoryProtocol;
import cn.mycs.front.service.basic.category.provider.CategoryProvider;
import cn.mycs.front.service.course.material.provider.MaterialCateProvider;
import cn.mycs.front.service.course.material.provider.MaterialProvider;
import cn.mycs.front.service.course.material.provider.VideoInfoProvider;
import cn.mycs.front.service.course.material.provider.VideoProvider;
import cn.mycs.front.service.course.material.vo.VideoInfoVo;
import cn.mycs.front.service.course.material.vo.VideoVo;
import cn.mycs.front.service.user.provider.UserProvider;
import cn.mycs.front.service.user.user.protocol.UserProtocol;
import cn.mycs.service.material.feign.interfaces.feign.MemberPermissionVideoClient;
import cn.mycs.service.material.server.service.IVideoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>视频服务实现</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/20 15:30
 * </pre>
 */
@Service
public class VideoServiceImpl implements IVideoService {
    @Autowired
    private UserProvider userProvider;
    @Autowired
    private VideoInfoProvider videoInfoProvider;
    @Autowired
    private VideoProvider videoProvider;
    @Autowired
    private MaterialCateProvider materialCateProvider;
    @Autowired
    private CategoryProvider categoryProvider;
    @Autowired
    private MaterialProvider materialProvider;
    @Autowired
    private MemberPermissionVideoClient memberPermissionVideoClient;

    @Override
    public VideoInfoVo getVideoInfoVo(Long srcId) {
        try {
            return videoInfoProvider.detail(srcId);
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }

    @Override
    public VideoVo getVideo(Long videoId) {
        try {
            return videoProvider.detail(videoId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserProtocol.UserBaseVo getUser(Long uid) {
        return userProvider.getBaseUser(uid);
    }

    @Override
    public List<UserProtocol.UserBaseVo> getUserByIds(List<Long> longList) {
        return userProvider.getBaseUserByIds(longList);
    }

    @Override
    public String getCateNames(Long materialId) {
        List<Long> categoryIds = materialCateProvider.getCategory(materialId);
        List<CategoryProtocol.CategoryVo> categoryVoList = categoryProvider.getByIds(categoryIds);
        if (categoryVoList == null || categoryVoList.size() == 0) {
            return "";
        }
        List<String> collect = categoryVoList.stream().map(CategoryProtocol.CategoryVo::getName).collect(Collectors.toList());
        return StringUtils.join(collect, "、");
    }

    @Override
    public boolean checkedPermission(Long uid, Long videoId) {
        JsonResult<Integer> permission = memberPermissionVideoClient.checkedPermission(uid, videoId);
        boolean cplay = false;
        if (permission.getCode() == StatusCodeEnum.SUCCESS.getCode() && permission.getData() == 1) {
            // 是会员拥有视频，直接返回能播放
            return true;
        }
        VideoInfoVo videoInfoVo = getVideoInfoVo(videoId);
        if (videoInfoVo != null && videoInfoVo.getMaterialId() != null) {
            cplay = materialProvider.hasPermission(videoInfoVo.getMaterialId(), uid);
        }

        return cplay;
    }
}
