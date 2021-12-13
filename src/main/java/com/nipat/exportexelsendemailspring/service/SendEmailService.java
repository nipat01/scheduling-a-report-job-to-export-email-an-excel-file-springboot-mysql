package com.nipat.exportexelsendemailspring.service;

import com.nipat.exportexelsendemailspring.entity.PromotionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;

@Service
@Component
public class SendEmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ExportExelService exportExelService;

    @Autowired
    private PromotionService promotionService;

    @Scheduled(cron = "10 * * * * ?")
    public void sendEmail() throws MessagingException, IOException {
        System.out.println("sendEmail()");
        String from = "nipat1612@gmail.com";
        String to = "nipat1612@gmail.com";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setSubject("Promotion Daily");
        helper.setFrom(from);
        helper.setTo(to);

        helper.setText("<b>Dear friend</b>,<br><i>Please look at the file attached.</i>", true);
        List<PromotionEntity> listPromotion = promotionService.PromotionAll();
        ExportExelService excelExporter = new ExportExelService(listPromotion);

        byte[] excelFileAsBytes  = excelExporter.AttachmentExel();
        ByteArrayResource resource = new ByteArrayResource(excelFileAsBytes);
        helper.addAttachment("Promotion.xlsx", resource);

        mailSender.send(message);
    }
}
