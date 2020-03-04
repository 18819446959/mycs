package cn.mycs.service.user.server.controller;

import cn.mycs.service.user.provider.interfaces.feign.DemoProvider;
import cn.mycs.service.user.server.persistence.model.User;
import cn.mycs.service.user.server.service.IDemoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>请描述本类的作用</p>
 * <pre>
 * @author gitamacai
 * @date 2020/3/3 16:15
 * </pre>
 */
@RestController
@RequestMapping("/demo/")
public class DemoController implements DemoProvider {
    @Autowired
    private IDemoUserService demoUserService;
    @Override
    public int getOne() {
        User byId = demoUserService.getById(18);
        return byId.getUserType();
    }
}

