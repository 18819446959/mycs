package cn.mycs.service.material.server.bo.material.video;

import cn.mycs.common.util.RuleUtil;
import cn.mycs.core.constant.CommonConstant;
import cn.mycs.core.support.DateTimeKit;
import cn.mycs.core.util.SpringContextHolder;
import cn.mycs.front.service.course.material.vo.VideoInfoVo;

import cn.mycs.front.service.course.material.vo.VideoVo;
import cn.mycs.service.material.provider.bean.dto.PlayDto;
import cn.mycs.service.material.server.exception.NotExitsVideoException;
import cn.mycs.service.material.server.service.IVideoService;
import cn.mycs.service.material.feign.bean.dto.StudyLogNewDto;
import cn.mycs.service.material.server.exception.NotExitsVideoException;
import cn.mycs.service.material.server.service.IStudyLogService;
import cn.mycs.service.material.server.service.IVideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>播放接口</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/6 13:59
 * </pre>
 */
public abstract class BasePlay {
    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 视频播放
     *
     * @param uid   播放者id
     * @param material 要播放的视频
     * @return 返回可以用于播放是信息
     */
    public abstract PlayDto play(Long uid, IdentityVideo material) throws NotExitsVideoException;

    PlayInfo getPlayUrl(Long uid, IdentityVideo identitymaterial) throws NotExitsVideoException {
        IVideoService materialService = SpringContextHolder.getBean("videoServiceImpl");
        VideoInfoVo materialInfoVo = materialService.getVideoInfoVo(identitymaterial.getVideoUserId());
        PlayInfo playInfo = new PlayInfo();
        playInfo.setPlayUrl("");
        if (materialInfoVo == null || materialInfoVo.getVideoId() == null) {
            log.error("观看视频，不存在该视频");
            throw new NotExitsVideoException("不存在该视频");
        }
        VideoVo material = materialService.getVideo(materialInfoVo.getVideoId());
        if (material == null || material.getVideoId() == null ) {
            log.error("观看视频，不存在该视频");
            throw new NotExitsVideoException("不存在该视频");
        }
        String hdmaterialSrc = RuleUtil.getVideoUrl(material.getUploadUid(), material.getVideoId(), material.getServerId(), CommonConstant.RES_VIDEO_MP4, 0);
        // 检测出高清源地址
        if (hdmaterialSrc.contains(";")) {
            String[] split = hdmaterialSrc.split(";");
            hdmaterialSrc = split[split.length - 1];
        }

        playInfo.setPlayUrl(hdmaterialSrc);
        Integer studyLogId = addWatchLog(materialInfoVo.getVideoInfoId().intValue(), material.getUploadUid(), uid);
        playInfo.setStudyLogId(studyLogId);
        return playInfo;
        // return "http://v1.mycs.cn/22/7417/40627/PTo9PjpyNiY.mp4";
    }


    private Integer addWatchLog(Integer materialId, Long ownerId, Long studyUid) {
        // 记录观看日志
        StudyLogNewDto studyLog = createStudyLog(materialId, ownerId, studyUid);
        IStudyLogService studyLogNewMapper = SpringContextHolder.getBean("studyLogService");
        return studyLogNewMapper.addStudyLog(studyLog);

    }

    private StudyLogNewDto createStudyLog(Integer materialId, Long ownerId, Long studyUid) {
        StudyLogNewDto studyLogNew = new StudyLogNewDto();
        studyLogNew.setGoodsId(materialId);
        studyLogNew.setGoodsType(0);
        studyLogNew.setCourseId(0);
        studyLogNew.setChapterId(0);
        studyLogNew.setVideoId(materialId);
        studyLogNew.setAddTime(DateTimeKit.currentTimeSecond());
        studyLogNew.setStudyTime(0);
        studyLogNew.setPayType(0);
        studyLogNew.setDevice(5);
        studyLogNew.setRecid(0);
        studyLogNew.setTaskId(0);
        studyLogNew.setAccuracy(0f);
        studyLogNew.setPassed(0);
        studyLogNew.setEndTime(0);
        studyLogNew.setVideoTimeSpot(0);
        studyLogNew.setStudyUid(studyUid);
        studyLogNew.setOwnerUid(ownerId);
        return studyLogNew;
    }

    class PlayInfo {
        private String playUrl;
        private int studyLogId;

        public String getPlayUrl() {
            return playUrl;
        }

        public void setPlayUrl(String playUrl) {
            this.playUrl = playUrl;
        }

        public int getStudyLogId() {
            return studyLogId;
        }

        public void setStudyLogId(int studyLogId) {
            this.studyLogId = studyLogId;
        }
    }
}
