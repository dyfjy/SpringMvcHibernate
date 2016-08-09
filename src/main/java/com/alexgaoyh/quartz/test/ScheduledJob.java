package com.alexgaoyh.quartz.test;

import org.springframework.stereotype.Component;


@Component("ScheduledJob")
public class ScheduledJob{
 
    protected void jobTask() {
    	System.out.println("自动任务执行了 ");
    }
    
    
}