/**
 * @program: bbs
 * @description: 腾讯云cos服务
 * @author: Wu
 * @create: 2019-12-24 10:53
 **/
package com.wu.bbs.provider;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.model.GeneratePresignedUrlRequest;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

@Service
public class TenCosProvider {
    @Value("${tencloud.cos.SecretId}")
    private String secretId;
    @Value("${tencloud.cos.SecretKey}")
    private String secretKey;
    @Value("${tencloud.cos.BucketName}")
    private String bucketName;          // 指定要上传到的存储桶
    @Value("${tencloud.cos.URLProfix}")   //链接URL前缀
    private String URLProfix;

    /*// 1 初始化用户身份信息（secretId, secretKey）。
    COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
    // 2 设置 bucket 的区域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
    // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
    Region region = new Region("ap-guangzhou");
    ClientConfig clientConfig = new ClientConfig(region);
    // 3 生成 cos 客户端。
    COSClient cosClient = new COSClient(cred, clientConfig);*/

    public COSClient registerCOSClient() {
        // 1 初始化用户身份信息（secretId, secretKey）。
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置 bucket 的区域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region("ap-guangzhou");
        ClientConfig clientConfig = new ClientConfig(region);
        // 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);
        return cosClient;
    }

    public String upload(InputStream inputStream, String mimeType, String fileName) {
        COSClient cosClient = registerCOSClient();
        /*// 1 初始化用户身份信息（secretId, secretKey）。
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置 bucket 的区域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region("ap-guangzhou");
        ClientConfig clientConfig = new ClientConfig(region);
        // 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);*/

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String key = "images/" + year + "/" + month + "/" + day + "/" + fileName;
        URL url = null;
        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            // 指定要上传到 COS 上对象键
            objectMetadata.setContentType(mimeType);
            PutObjectResult putObjectResult = cosClient.putObject(bucketName, key, inputStream, objectMetadata);
            GeneratePresignedUrlRequest req =
                    new GeneratePresignedUrlRequest(bucketName, key, HttpMethodName.GET);
            // 设置签名过期时间(可选), 若未进行设置, 则默认使用 ClientConfig 中的签名过期时间(1小时)
            // 这里设置签名在半个小时后过期
            Date expirationDate = new Date(System.currentTimeMillis() + 30L * 60L * 1000L);
            req.setExpiration(expirationDate);
            url = cosClient.generatePresignedUrl(req);
            cosClient.shutdown();
        } catch (CosServiceException serverException) {
            serverException.printStackTrace();
        } catch (CosClientException clientException) {
            clientException.printStackTrace();
        }
        return url.toString();
    }
}
