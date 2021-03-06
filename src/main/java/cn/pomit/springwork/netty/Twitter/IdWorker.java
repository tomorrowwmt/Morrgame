package cn.pomit.springwork.netty.Twitter;
/*
 */

public class IdWorker{
    //工作id
    private long workerId;
    //数据id
    private long datacenterId;
    //序列号
    private long sequence;

    public IdWorker(long workerId, long datacenterId, long sequence){
        // sanity check for workerId
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0",
                    maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0",
                    maxDatacenterId));
        }
        System.out.printf("worker starting. timestamp left shift %d, datacenter id bits %d, worker id bits %d, " +
                        "sequence bits %d, workerid %d",
                timestampLeftShift, datacenterIdBits, workerIdBits, sequenceBits, workerId);
        this.workerId = workerId;
        this.datacenterId = datacenterId;
        this.sequence = sequence;
    }

    /** 开始时间戳 (2015-01-01) */
    private final long twepoch = 1420041600000L;
    //长度
    private static final long workerIdBits = 5L;
    private static final long datacenterIdBits = 5L;
    //最大值
    private static final long maxWorkerId = -1L ^ (-1L << workerIdBits);
    private static final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    //序列号id长度
    private static final long sequenceBits = 10L;
    //序列号最大值
    private static final long sequenceMask = -1L ^ (-1L << sequenceBits);
    //工作id需要左移的位数
    private static final long workerIdShift = sequenceBits;
    //数据id需要左移位数
    private long datacenterIdShift = sequenceBits + workerIdBits;
    //时间戳需要左移位数
    private static final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    //上次时间戳，初始值为负数
    private static long  lastTimestamp = -1L;

    public long getWorkerId(){
        return workerId;
    }

    public long getDatacenterId(){
        return datacenterId;
    }
    //下一个ID生成算法
    public synchronized long nextId() {
        long timestamp = timeGen();
        //获取当前时间戳如果小于上次时间戳，则表示时间戳获取出现异常
        if (timestamp < lastTimestamp) {
            System.err.printf("clock is moving backwards.  Rejecting requests until %d.", lastTimestamp);
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
                    lastTimestamp - timestamp));
        }

        //获取当前时间戳如果等于上次时间戳（同一毫秒内），则在序列号加一；否则序列号赋值为0，从0开始。
        if (lastTimestamp == timestamp) {
            //用mask防止溢出
            sequence = (sequence + 1) & sequenceMask;
            //这段代码通过位与运算保证计算的结果范围始终是 0-4095 ！
            long seqMask = -1L ^ (-1L << 12L); //计算12位能耐存储的最大正整数，相当于：2^12-1 = 4095
            System.out.println("seqMask: "+seqMask);
            System.out.println(1L & seqMask);
            System.out.println(2L & seqMask);
            System.out.println(3L & seqMask);
            System.out.println(4L & seqMask);
            System.out.println(4095L & seqMask);
            System.out.println(4096L & seqMask);
            System.out.println(4097L & seqMask);
            System.out.println(4098L & seqMask);
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }

        //将上次时间戳值刷新
        lastTimestamp = timestamp;

        /**
         * 返回结果：
         * (timestamp - twepoch) << timestampLeftShift) 表示将时间戳减去初始时间戳，再左移相应位数
         * (datacenterId << datacenterIdShift) 表示将数据id左移相应位数
         * (workerId << workerIdShift) 表示将工作id左移相应位数
         * | 是按位或运算符，例如：x | y，只有当x，y都为0的时候结果才为0，其它情况结果都为1。
         * 因为个部分只有相应位上的值有意义，其它位上都是0，所以将各部分的值进行 | 运算就能得到最终拼接好的id
         */
        return ((timestamp - twepoch) << timestampLeftShift) |
                (datacenterId << datacenterIdShift) |
                (workerId << workerIdShift) |
                sequence;
    }

    //获取时间戳，并与上次时间戳比较
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    //获取系统时间戳
    private long timeGen(){
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        IdWorker worker = new IdWorker(1,1,0);
        for (int i = 0; i < 300; i++) {
            System.out.println(worker.nextId());
        }
        long l = worker.nextId();
        System.out.println(l);
    }

}