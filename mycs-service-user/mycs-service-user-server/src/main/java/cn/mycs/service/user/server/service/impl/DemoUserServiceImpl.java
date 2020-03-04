package cn.mycs.service.user.server.service.impl;

import cn.mycs.service.user.server.persistence.dao.UserMapper;
import cn.mycs.service.user.server.persistence.model.User;
import cn.mycs.service.user.server.service.IDemoUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>测试用户接口实现</p>
 * <pre>
 * @author gitamacai
 * @date 2020/3/3 18:05
 * </pre>
 */
@Service
public class DemoUserServiceImpl extends ServiceImpl<UserMapper, User> implements IDemoUserService {


}
