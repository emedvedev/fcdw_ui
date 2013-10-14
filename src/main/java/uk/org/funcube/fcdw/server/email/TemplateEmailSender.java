package uk.org.funcube.fcdw.server.email;

import java.util.Map;

public interface TemplateEmailSender {
	void sendEmailUsingTemplate(String fromAddress, String toAddress, String[] bccAddresses, String subject, String templateLocation,
			Map<String, Object> model);

}
