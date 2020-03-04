package cn.mycs.service.material.server.service.impl;

import cn.mycs.core.base.restful.JsonResult;
import cn.mycs.service.material.feign.bean.dto.CommentSourceDto;
import cn.mycs.service.material.feign.interfaces.feign.CommentClient;
import cn.mycs.service.material.server.service.ICommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * <p>视频评论服务 接口实现</p>
 * <pre>
 * @author gitamacai
 * @date 2019/11/19 14:21
 * </pre>
 */
@Service("commentService")
public class CommentServiceImpl implements ICommentService {
    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommentClient commentClient;

    @Override
    public List<CommentSourceDto> listVideoComment(Integer videoId, Integer page, Integer pageSize) {
        JsonResult<List<CommentSourceDto>> listJsonResult = commentClient.listVideoComment(videoId, page, pageSize);
        if (listJsonResult.isSuccess()) {
            return listJsonResult.getData();
        }
        log.error("请求视频评论列表失败，请求参数为：视频id：{}，页码：{}，分页大小:{}", videoId, page, pageSize);
        return Collections.emptyList();
    }
}
