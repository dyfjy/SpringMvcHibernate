package com.alexgaoyh.util.email;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 发送邮件的方法，直接调用EmailUtil.send(subject, content, to)，即可发送邮件
 * @author gaoyihang
 *
 */
public class EmailUtil {
	
	private EmailUtil() {
		// Utility classes should not have a public constructor
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtil.class);
	
	/**
	 * @参数名：@param subject 邮件主题
	 * @参数名：@param content 邮件主题内容
	 * @参数名：@param to 收件人Email地址
	 */
	public static void send(String subject, String content, String to){
		ClassPathXmlApplicationContext context =  new ClassPathXmlApplicationContext("spring-smtp-mail.xml");
 
    	Email mm = (Email) context.getBean("simpleMail");
        try {
        	LOGGER.info("Params : createTime = ["+ (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()) +"] ,  subject = [" + subject + "] ,  content = [" + content + "] ,  to = [" + to + "]");
			mm.sendMail(subject, content, to);
			context.close();
		} catch (MessagingException e) {
			LOGGER.info("context", e);
		}
    }
	public static void main(String[] args) {
		send("java程序测试", "系统发送，请勿回复", "511860475@qq.com");
	}
}
