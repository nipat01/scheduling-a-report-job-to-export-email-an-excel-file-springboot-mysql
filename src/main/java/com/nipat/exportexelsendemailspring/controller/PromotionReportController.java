package com.nipat.exportexelsendemailspring.controller;

import com.nipat.exportexelsendemailspring.entity.PromotionEntity;
import com.nipat.exportexelsendemailspring.service.ExportExelService;
import com.nipat.exportexelsendemailspring.service.PromotionService;
import com.nipat.exportexelsendemailspring.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/promotion")
public class PromotionReportController {
    @Autowired
    private PromotionService promotionService;

    @Autowired
    private SendEmailService sendEmailService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<PromotionEntity> PromotionReport() {
        return promotionService.PromotionAll();
    }

    @RequestMapping(value = "/testemail", method = RequestMethod.GET)
    public void TestEmail() throws MessagingException, IOException {
        sendEmailService.sendEmail();
    }

    @RequestMapping(value = "/exportfile", method = RequestMethod.GET)
    public String ExportPromotionReport() {
        return "this is exportfile promotion report";
    }

    @RequestMapping(value = "/export/exel", method = RequestMethod.GET)
    public void exportToExel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Promotion_Daily_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<PromotionEntity> listPromotion = promotionService.PromotionAll();

        ExportExelService excelExporter = new ExportExelService(listPromotion);

        excelExporter.export(response);
    }
}
