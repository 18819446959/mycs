package cn.mycs.front.api.app;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @program: mycs-java
 * @description: 用户服务测试
 * @author: shenhw
 * @create: 2019-10-14 20:12
 **/
@SpringBootTest(classes = {MycsFrontApiApp.class})
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class MycsFrontApiAppTest {
    @Autowired
    WebApplicationContext context;
    protected MockMvc mockMvc;
    @Before
    public void initMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

}
