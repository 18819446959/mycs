package cn.mycs.service.user.provider.interfaces.feign;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>demo</p>
 * <pre>
 * @author gitamacai
 * @date 2020/3/3 16:14
 * </pre>
 */
public interface DemoProvider {
    /**
     * 获取一个数据
     * @return 返回一个数据
     */
    @GetMapping("one")
    int getOne();
}
