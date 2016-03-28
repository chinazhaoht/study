package cn.xiaoneng.study.flume;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.PollableSource;
import org.apache.flume.conf.Configurable;
import org.apache.flume.event.EventBuilder;
import org.apache.flume.source.AbstractSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author zhaoht
 * @date 2016/3/24 18:37
 */

public class MySource extends AbstractSource implements Configurable,PollableSource {

    private static final Logger logger = LoggerFactory.getLogger(MySource.class);

    private String logPath;
    private RandomAccessFile file;
    private AtomicLong autoLine;
    private StringBuilder oldLine = new StringBuilder();

    public synchronized void start(){
        logger.info("MySource starting..............");
        autoLine = new AtomicLong(0);
        try{
            file = new RandomAccessFile(logPath,"r");
        } catch (FileNotFoundException e) {
            logger.error("file open error.......",e);
            e.printStackTrace();
        }
        super.start();
    }

    public synchronized void stop(){
        try{
            file.close();
        } catch (IOException e) {
            logger.error("file close error.....",e);
            e.printStackTrace();
        }
        autoLine = new AtomicLong(0);
        super.stop();
    }
    @Override
    public void configure(Context context) {
        this.logPath = context.getString("logPath");
    }

    @Override
    public Status process() throws EventDeliveryException {
        try{
            String line = file.readLine();
            if(line == null){
                return Status.BACKOFF;
            }

            if(autoLine.intValue()%4==0){
                logger.info("oldLine:" + oldLine.toString());
                byte[] body = oldLine.toString().getBytes();
                Event event = EventBuilder.withBody(body);
                getChannelProcessor().processEvent(event);
                oldLine = new StringBuilder();
                autoLine = new AtomicLong(0);
            }
            oldLine.append(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Status.READY;
    }
}
