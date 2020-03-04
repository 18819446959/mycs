package cn.mycs.front.api.web.demo.controller;

import cn.mycs.core.base.restful.JsonResult;
import cn.mycs.front.api.web.feign.interfaces.DemoClient;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>测试controller</p>
 * <p>
 * <pre>
 * @author gitamacai
 * @date 2020/3/3 14:46
 * </pre>
 */
@RestController
@RequestMapping("/demo")
@Api("DEMO")
@Validated
public class DemoController {
    @Autowired
    private DemoClient demoClient;

    @RequestMapping("/one")
    public JsonResult getObn() {
        int one = demoClient.getOne();
        return JsonResult.success(one);
    }
}