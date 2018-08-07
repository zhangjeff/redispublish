import redis.clients.jedis.Jedis;

/**
 * Created by clq on 2017/8/20.
 */
public class Pub {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.publish("topic:clq.biz", "Hellow World!22222");
    }
}
