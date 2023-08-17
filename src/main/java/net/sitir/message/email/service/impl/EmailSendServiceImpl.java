package net.sitir.message.email.service.impl;

import javax.annotation.PostConstruct;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import net.sitir.message.email.domain.Email;
import net.sitir.message.email.service.EmailSendService;

/**
 * 邮件发送
 * @author cwl
 * 2023-8-14
 */
@Service
public class EmailSendServiceImpl implements EmailSendService{
    
    private static final Logger logger = LoggerFactory.getLogger(EmailSendServiceImpl.class);
	
    @Autowired
	private JavaMailSender mailSender;

	@Value("${spring.mail.username}")
	public String USER_NAME;
	
	
	// static {
	// 	 System.setProperty("mail.mime.splitlongparameters","false");
	// }

	/* @PostConstruct
	public void init() throws Exception{
		Email e = new Email();
		e.setContent("#######");
		e.setSubject("test");
		String[] s = {"779333472@qq.com"};
		e.setRecipients(s);
		this.send(e);
	} */

	@Override
	public void send(Email mail) throws Exception {
		logger.info("发送邮件：{}",mail.getContent());
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(USER_NAME);
		message.setTo(mail.getRecipients());
		message.setSubject(mail.getSubject());
		message.setText(mail.getContent());
		mailSender.send(message);
	}

	@Override
	public void sendHtml(Email mail) throws Exception {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(USER_NAME);
		helper.setTo(mail.getRecipients());
		helper.setSubject(mail.getSubject());
        helper.setText(mail.getContent());
		/*helper.setText(
				"<html><body><img src=\"cid:springcloud\" ></body></html>",
				true);
		// 发送图片
		 File file = ResourceUtils.getFile("classpath:static"
				+ Constants.SF_FILE_SEPARATOR + "image"
				+ Constants.SF_FILE_SEPARATOR + "springcloud.png");
		helper.addInline("springcloud", file);
		// 发送附件
		file = ResourceUtils.getFile("classpath:static"
				+ Constants.SF_FILE_SEPARATOR + "file"
				+ Constants.SF_FILE_SEPARATOR + "xx.zip");
		helper.addAttachment("科帮网", file); */
		mailSender.send(message);
	}



}
