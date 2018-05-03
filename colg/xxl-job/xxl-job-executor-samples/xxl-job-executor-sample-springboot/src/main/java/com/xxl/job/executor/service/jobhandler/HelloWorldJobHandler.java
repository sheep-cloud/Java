package com.xxl.job.executor.service.jobhandler;

import org.springframework.stereotype.Component;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;

/**
 * HelloWorld任务；
 *
 * @author colg
 */
@JobHandler("helloWorldJobHandler")
@Component
public class HelloWorldJobHandler extends IJobHandler {

    @Override
    public ReturnT<String> execute(String param) throws Exception {
        XxlJobLogger.log("XXL-JOB Hello World");
        
        System.out.println("Hello World！ 今晚吃鸡，大吉大利！");
        return SUCCESS;
    }

}
