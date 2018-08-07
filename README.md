# redis发布订阅java代码实现
--- 


## 发布订阅概念：

> 订阅，取消订阅和发布实现了发布/订阅消息范式(引自wikipedia)，发送者（发布者）不是计划发送消息给特定的接收者（订阅者）。而是发布的消息分到不同的频道，不需要知道什么样的订阅者订阅。订阅者对一个或多个频道感兴趣，只需接收感兴趣的消息，不需要知道什么样的发布者发布的。这种发布者和订阅者的解耦合可以带来更大的扩展性和更加动态的网络拓扑。

## 发布订阅消息格式

> 消息是一个有三个元素的多块响应 。

第一个元素是消息类型:

    subscribe: 表示我们成功订阅到响应的第二个元素提供的频道。第三个参数代表我们现在订阅的频道的数量。
    unsubscribe:表示我们成功取消订阅到响应的第二个元素提供的频道。第三个参数代表我们目前订阅的频道的数量。当最后一个参数是0的时候，我们不再订阅到任何频道。当我们在Pub/Sub以外状态，客户端可以发出任何redis命令。
    message: 这是另外一个客户端发出的发布命令的结果。第二个元素是来源频道的名称，第三个参数是实际消息的内容。



## Java代码实现

启动订阅客户端，可以启动一个或者多个，实例如下：
```
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

```

启动发布程序

```
import redis.clients.jedis.Jedis;

/**
 * Created by clq on 2017/8/20.
 */
public class Pub {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.publish("topic:clq.biz", "Hellow World!");
    }
}

```
