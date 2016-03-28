package cn.xiaoneng.study.javanio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author zhaoht
 * @date 2016/3/25 14:55
 */

public class FirstDemo {

    public static void main(String[] args) throws IOException {
      /*  RandomAccessFile accessFile = new RandomAccessFile("data","rw");
        FileChannel fileChannel = accessFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(48);
        int bytesRead = fileChannel.read(buf);
        System.out.println("Read"+bytesRead);
        buf.flip();
        while(buf.hasRemaining()){
            System.out.println((char)buf.get());
        }

        buf.clear();
        bytesRead = fileChannel.read(buf);
        accessFile.close();

        fileChannel.truncate(10);
*/


    }

}
