package com.lbm.ribbon.consumer;

import com.lbm.ribbon.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HelloService helloService;

    /**
     * 负载均衡
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @RequestMapping("/consumer")
    public String helloConsumer() throws ExecutionException, InterruptedException {

        return restTemplate.getForEntity("http://HELLO-SERVICE/hello",String.class).getBody();
    }

    /**
     * 熔断器
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @RequestMapping("/consumer2")
    public String consumer2() throws ExecutionException, InterruptedException {
        return helloService.helloService();
    }

    /**
     * 熔断器-降级
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @RequestMapping("/consumer3")
    public String consumer3() throws ExecutionException, InterruptedException {

        HelloServiceCommand command = new HelloServiceCommand("hello",restTemplate);
        String result = command.execute();
        return result;
    }

    @RequestMapping("/consumer4")
    public String consumer4() throws ExecutionException, InterruptedException {

        List<String> list = new ArrayList<>();
        HelloServiceObserveCommand command = new HelloServiceObserveCommand("hello",restTemplate);
        //热执行
        Observable<String> observable = command.observe();
        //冷执行
//        Observable<String> observable =command.toObservable();
        //订阅
        observable.subscribe(new Observer<String>() {
            //请求完成的方法
            @Override
            public void onCompleted() {
                System.out.println("会聚完了所有查询请求");
            }
            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }
            //订阅调用事件，结果会聚的地方，用集合去装返回的结果会聚起来。
            @Override
            public void onNext(String s) {
                System.out.println("结果来了.....");
                list.add(s);
            }
        });

        return list.toString();

    }
}