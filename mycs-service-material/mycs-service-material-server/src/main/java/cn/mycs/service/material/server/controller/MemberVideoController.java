package cn.mycs.service.material.server.controller;

import cn.mycs.core.base.restful.JsonResult;
import cn.mycs.core.support.StrKit;
import cn.mycs.service.material.provider.bean.dto.ShareObjectDto;
import cn.mycs.service.material.provider.interfaces.feign.MemberVideoProvider;
import cn.mycs.service.material.server.persistence.model.Share;
import cn.mycs.service.material.server.service.IShareService;
import net.sf.cglib.beans.BeanCopier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>会员视频控制器</p>
 * <pre>
 * @author gitamacai
 * @date 2019/11/21 10:27
 * </pre>
 */
@RestController
@RequestMapping
public class MemberVideoController implements MemberVideoProvider {
    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private IShareService shareService;


    @Override
    public JsonResult<ShareObjectDto> getShareObject(@PathVariable("shareId") String shareId) {
        if (StrKit.isEmpty(shareId)) {
            log.error("shareId为空");
            return JsonResult.error("id为空");
        }
        Share share = shareService.getShareById(shareId);
        ShareObjectDto shareObjectDto = new ShareObjectDto();
        BeanCopier beanCopier = BeanCopier.create(Share.class, ShareObjectDto.class, false);
        beanCopier.copy(share, shareObjectDto, null);
        return JsonResult.success(shareObjectDto);
    }
}
