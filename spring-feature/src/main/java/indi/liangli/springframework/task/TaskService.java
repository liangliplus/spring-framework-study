package indi.liangli.springframework.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author liangli
 * 2021/8/26 11:33
 */
@Service
public class TaskService {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Scheduled(fixedRate = 3000)
    public void schedule() throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(3000);
        System.out.println(sdf.format(new Date()));
    }

    @Scheduled(fixedRate = 3000)
    @Async
    public void asyncSchedule() throws InterruptedException {
        System.out.println("asyncSchedule"+Thread.currentThread().getName());
        Thread.sleep(3000);
        System.out.println("asyncSchedule" + sdf.format(new Date()));

    }




}
