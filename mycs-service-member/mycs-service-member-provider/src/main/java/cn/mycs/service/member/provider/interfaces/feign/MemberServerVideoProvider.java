package cn.mycs.service.member.provider.interfaces.feign;

import cn.mycs.core.base.restful.JsonResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>会员视频相关接口</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/3 13:52
 * </pre>
 */
public interface MemberServerVideoProvider {

    /**
     * 检测是否是会员，并且该会员是否拥有该视频
     *
     * @param uid     用户id
     * @param videoId 视频id
     * @return code 1 是会员并且拥有该视频可以进行播放，0 没有该视频的播放权
     */
    @RequestMapping(value = "/member/video/checked/{uid}/{videoId}", method = RequestMethod.GET)
    JsonResult<Integer> checkedPermission(@PathVariable("uid") Long uid, @PathVariable("videoId") Long videoId);


}
