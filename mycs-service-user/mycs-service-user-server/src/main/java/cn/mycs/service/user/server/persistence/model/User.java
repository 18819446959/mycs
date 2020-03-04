package cn.mycs.service.user.server.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
  *<p>请描述该类的作用</p>
  *
  *<pre>
  * @author gitamacai
  * @date 2020/3/3 18:17
  *</pre>
  */
@Data

@TableName("user")
public class User extends Model<User> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "uid", type = IdType.ID_WORKER)
    private Long uid;

    /**
     * 登录名
     */
    private String username;


    /**
     * 用户角色类型，1个人-5-企业管理员7-医群平台用户
     */
    @TableField("user_type")
    private Integer userType;

}