package pipeline;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;


public class PipelineSet {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.10.22", 6380);
        Pipeline pipelined = jedis.pipelined();
        long t1 = System.currentTimeMillis();
        for (int i=0; i < 5000000; i++) {
            pipelined.set("batch"+i,""+i);
        }
        pipelined.syncAndReturnAll();
        long t2 = System.currentTimeMillis();
        System.out.println("耗时："+(t2-t1)+"ms");
    }
}
