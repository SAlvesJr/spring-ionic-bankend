package com.SAlvesjr.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.SAlvesjr.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfimationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

}
