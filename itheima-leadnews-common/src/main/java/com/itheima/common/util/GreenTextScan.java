package com.itheima.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.green.model.v20180509.TextScanRequest;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.http.HttpResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.*;

@Getter
@Setter
@Component
@PropertySource("classpath:aliyun.properties")
@ConfigurationProperties(prefix = "aliyun")
public class GreenTextScan {

    private String accessKeyId;
    private String secret;

    //可以进行优化 优化成 数组或者是List集合 因为一次性可以执行不超过100个任务
    // https://www.aliyun.com/price/product?spm=5176.55804.J_911092.btn4.3d9875daIbvZvU#/lvwang/detail

    /**
     * 基本描述：根据您调用API的文本扫描条数计费。影响扫描费用的因素如下：
     * 1. 2017年8月1日起正式商业化；
     * 2. 一次请求最多支持100个任务，一个任务就是算一条文本，每条文本限制10,000个字符，超出限制该请求所有任务全部失败，不计费。当前的计费周期为1天1次；
     * 3. 每名用户从开始调用之日起算（包含当天），31天内拥有每日3,000条免费文本扫描额度，不区分算法结果是否确定，第32天开始将不再享有免费量；
     * 4. 31天内每日超出3,000条的部分需要付费，价格按照当日总扫描量匹配阶梯价收费，每日扫描量越大，单价越低。
     * 5.计费项code :text_scan
     *
     * @param  contents 要审核的内容  list
     * @return  map  key: sugesstion  value --?pass/review/block
     * @throws Exception
     */
    public Map greeTextScan(List<String> contents) throws Exception {
        IClientProfile profile = DefaultProfile
                .getProfile("cn-shanghai", accessKeyId, secret);
        DefaultProfile
                .addEndpoint("cn-shanghai", "cn-shanghai", "Green", "green.cn-shanghai.aliyuncs.com");
        IAcsClient client = new DefaultAcsClient(profile);
        TextScanRequest textScanRequest = new TextScanRequest();
        textScanRequest.setAcceptFormat(FormatType.JSON); // 指定api返回格式
        textScanRequest.setHttpContentType(FormatType.JSON);
        textScanRequest.setMethod(com.aliyuncs.http.MethodType.POST); // 指定请求方法
        textScanRequest.setEncoding("UTF-8");
        textScanRequest.setRegionId("cn-shanghai");
        List<Map<String, Object>> tasks = new ArrayList<Map<String, Object>>();
        for (String content : contents) {
            Map<String, Object> task1 = new LinkedHashMap<String, Object>();
            task1.put("dataId", UUID.randomUUID().toString());
            /**
             * 待检测的文本，长度不超过10000个字符
             */
            task1.put("content", content);
            tasks.add(task1);
        }

        JSONObject data = new JSONObject();

        /**
         * 检测场景，文本垃圾检测传递：antispam
         **/
        data.put("scenes", Arrays.asList("antispam"));
        data.put("tasks", tasks);
        System.out.println(JSON.toJSONString(data, true));
        textScanRequest.setHttpContent(data.toJSONString().getBytes("UTF-8"), "UTF-8", FormatType.JSON);
        // 请务必设置超时时间
        textScanRequest.setConnectTimeout(3000);
        textScanRequest.setReadTimeout(6000);

        Map<String, String> resultMap = new HashMap<>();
        try {
            HttpResponse httpResponse = client.doAction(textScanRequest);
            if (httpResponse.isSuccess()) {
                JSONObject scrResponse = JSON.parseObject(new String(httpResponse.getHttpContent(), "UTF-8"));
                System.out.println(JSON.toJSONString(scrResponse, true));
                if (200 == scrResponse.getInteger("code")) {
                    JSONArray taskResults = scrResponse.getJSONArray("data");
                    for (Object taskResult : taskResults) {
                        if (200 == ((JSONObject) taskResult).getInteger("code")) {
                            JSONArray sceneResults = ((JSONObject) taskResult).getJSONArray("results");
                            for (Object sceneResult : sceneResults) {
                                String scene = ((JSONObject) sceneResult).getString("scene");
                                String label = ((JSONObject) sceneResult).getString("label");
                                String suggestion = ((JSONObject) sceneResult).getString("suggestion");
                                System.out.println("suggestion = [" + label + "]");
                                if (!suggestion.equals("pass")) {
                                    resultMap.put("suggestion", suggestion);
                                    resultMap.put("label", label);
                                    return resultMap;
                                }

                            }
                        } else {
                            return null;
                        }
                    }
                    resultMap.put("suggestion", "pass");
                    return resultMap;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }    

}