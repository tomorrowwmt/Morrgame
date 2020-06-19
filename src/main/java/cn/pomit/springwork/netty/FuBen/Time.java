package cn.pomit.springwork.netty.FuBen;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时器演示：倒计时
 * @author Administrator
 *
 */
public class Time {

    public static void main(String[] args) {
        new Time().daojishi();

    }
    public void daojishi(){
        //min 分钟
        int min=1;
        long start=System.currentTimeMillis();
        //end 计算结束时间
        final long end=start+min*60*1000;

        final Timer timer=new Timer();
        //延迟0毫秒（即立即执行）开始，每隔1000毫秒执行一次
        timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                //show是剩余时间，即要显示的时间
                long show=end-System.currentTimeMillis();
                long h=show/1000/60/60;//时
                long m=show/1000/60%60;//分
                long s=show/1000%60;//秒
                System.out.println(h+"时"+m+"分"+s+"秒");
            }
        },0,1000);
        //计时结束时候，停止全部timer计时计划任务
        timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                timer.cancel();
            }

        }, new Date(end));
    }
}