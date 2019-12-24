package com.wu.bbs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BbsApplicationTests {

    @Value("${tencloud.cos.SecretId}")
    private String secretId;
    @Value("${tencloud.cos.SecretKey}")
    private String secretKey;
    @Test
    void contextLoads() {

       /* // 1 初始化用户身份信息（secretId, secretKey）。
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置 bucket 的区域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region("ap-guangzhou");
        ClientConfig clientConfig = new ClientConfig(region);
        // 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);

        try {
            // 指定要上传的文件
            File localFile = new File("src\\main\\resources\\static\\images\\loading.gif");
            // 指定要上传到的存储桶
            String bucketName = "bbs-1259574376";
            // 指定要上传到 COS 上对象键
            String key = "images/bbs.gif";
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            System.out.println(putObjectResult);
        } catch (CosServiceException serverException) {
            serverException.printStackTrace();
        } catch (CosClientException clientException) {
            clientException.printStackTrace();
        }*/

    }

}
