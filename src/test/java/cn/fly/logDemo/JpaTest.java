package cn.fly.logDemo;

import cn.fly.logDemo.config.SpringUtils;
import cn.fly.logDemo.model.repository.RylbRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.InetAddress;

/**
 * @author fly
 * @date 2023/3/13
 * @description
 */


@SpringBootTest
public class JpaTest {

    @Test
    public void testJpaStarter() {
        System.out.println(SpringUtils.getBean(RylbRepository.class).findByDmid("10"));
    }

    @Test
    public void testInet() {
        InetAddress loopbackAddress = InetAddress.getLoopbackAddress();
        System.out.println(loopbackAddress);
    }


}