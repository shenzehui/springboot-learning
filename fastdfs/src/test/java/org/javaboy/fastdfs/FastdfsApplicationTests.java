package org.javaboy.fastdfs;

import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.junit.jupiter.api.Test;
import org.omg.CORBA.NameValuePair;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class FastdfsApplicationTests {

    @Test
    void contextLoads() throws MyException, IOException {
        ClientGlobal.initByProperties("fastdfs-client.properties");
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getTrackerServer();
        StorageServer storageServer = null;
        StorageClient1 client1 = new StorageClient1(trackerServer, storageServer);
        NameValuePair pairs[] = null;
        String fileId = client1.upload_file1("C:\\Users\\ASUS\\Pictures\\Saved Pictures\\1649909249121.jpg", "jpg", null);
        System.out.println(fileId);
    }

}
