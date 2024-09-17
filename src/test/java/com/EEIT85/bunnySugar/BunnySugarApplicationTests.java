package com.EEIT85.bunnySugar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
class BunnySugarApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private JavaMailSender javaMailSender;

	@Test
	public void sendTestEmail() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("willy718066@gmail.com"); //設置收件人信箱
		message.setSubject("Test Email"); //設置信箱主題
		message.setText("This is a test email."); //設置信箱內容
		javaMailSender.send(message); //發送郵件
	}

}
