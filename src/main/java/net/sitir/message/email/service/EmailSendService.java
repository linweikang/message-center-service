package net.sitir.message.email.service;

import net.sitir.message.email.domain.Email;

/**
 * 邮件发送接口
 * @author cwl 
 * 2023-8-14
 */
public interface EmailSendService {
    
    /**
     * 纯文本
     * @param mail
     */
	void send(Email mail) throws Exception;

	/**
     * html内容
     * @param mail
     * @throws Exception
     */
	void sendHtml(Email mail) throws Exception;

	 /**
	  * 模版发送 freemarker
	  */
	//   void sendFreemarker(Email mail) throws Exception;
	 
      /**
	  * 模版发送 thymeleaf(弃用、需要配合模板)
	  *
	  */
	//  void sendThymeleaf(Email mail) throws Exception;

}
