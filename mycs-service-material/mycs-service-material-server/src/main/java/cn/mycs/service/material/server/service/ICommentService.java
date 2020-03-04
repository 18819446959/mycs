package cn.mycs.service.material.server.service;

import cn.mycs.service.material.feign.bean.dto.CommentSourceDto;

import java.util.List;

/**
 * <p>视频评论服务</p>
 * <pre>
 * @author gitamacai
 * @date 2019/11/19 14:21
 * </pre>
 */
public interface ICommentService {

    /**
     * 获取视频评论
     *
     * @param videoId  视频id
     * @param page     页码
     * @param pageSize 分页大小
     * @return 视频评论列表
     */
    List<CommentSourceDto> listVideoComment(Integer videoId, Integer page, Integer pageSize);
}
