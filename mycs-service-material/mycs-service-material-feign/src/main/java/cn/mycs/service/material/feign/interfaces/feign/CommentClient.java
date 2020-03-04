package cn.mycs.service.material.feign.interfaces.feign;

import cn.mycs.core.base.restful.JsonResult;
import cn.mycs.core.constant.FeignClientConstant;
import cn.mycs.service.material.feign.bean.dto.CommentSourceDto;
import cn.mycs.service.material.feign.interfaces.feign.hystrixfactory.CommentFallbackFactory;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>视频评论,到commonserver中获取数据</p>
 * <pre>
 * @author gitamacai
 * @date 2019/11/19 13:50
 * </pre>
 */
@FeignClient(value = FeignClientConstant.Service.MYCS_COMMON_SERVER, fallbackFactory = CommentFallbackFactory.class)
public interface CommentClient {
    /**
     * 获取视频评论
     *
     * @param videoId  视频id
     * @param page     页码
     * @param pageSize 分页大小
     * @return 视频评论列表
     */
    @RequestMapping("/video/comment/list")
    JsonResult<List<CommentSourceDto>> listVideoComment(@RequestParam("videoId") Integer videoId, @RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize);
}
