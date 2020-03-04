package cn.mycs.service.material.provider.interfaces.feign;

import cn.mycs.core.base.restful.JsonResult;
import cn.mycs.service.material.provider.bean.dto.ShareObjectDto;
import cn.mycs.service.material.provider.bean.dto.VideoUserLinkDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>请描述本类的作用</p>
 * <pre>
 * @author gitamacai
 * @date 2019/11/20 16:48
 * </pre>
 */
public interface MemberVideoProvider {

    /**
     * 获取分享对象
     *
     * @param shareId 分享id
     * @return 分享对象
     */
    @RequestMapping(value = "/share/get/object/{shareId}", method = RequestMethod.GET)
    JsonResult<ShareObjectDto> getShareObject(@PathVariable("shareId") String shareId);
}
