package tcp_test;

import redis.clients.jedis.Jedis;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2022/8/18 14:30
 * @Description:
 */
public class TcpTestMain {
    public static void main(String[] args) throws InterruptedException {
        Jedis jedis = new Jedis("192.168.10.22", 6380);
        jedis.set("testName","666");
        Thread.sleep(3000000);
    }
}
