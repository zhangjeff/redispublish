import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * Created by clq on 2017/8/20.
 */
public class Sub {
    public static void main(String []args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.subscribe(new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                System.out.println(message);
                super.onMessage(channel, message);
            }
        }, "topic:clq.biz");
    }
}
