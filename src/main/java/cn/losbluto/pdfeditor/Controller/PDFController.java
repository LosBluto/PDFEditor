package cn.losbluto.pdfeditor.Controller;

import cn.hutool.core.util.ZipUtil;
import cn.losbluto.pdfeditor.service.PDFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author LosBluto
 * @version 1.0.0
 * @ClassName PDFController.java
 * @Description TODO
 * @createTime 2022年07月02日 20:08:00
 */
@Controller
@RequestMapping("/pdf")
public class PDFController {
    @Autowired
    PDFService pdfService;


    @PostMapping(value = "/replace",produces = "multipart/form-data")
    @ResponseBody
    public String dealPDF(@RequestParam("zipFile") MultipartFile file,
                          @RequestParam("src")String src,
                          @RequestParam("replace")String replace) {
        try {
            if (PDFService.started)
                return "任务已开始，请勿重复提交!";

            pdfService.dealPdf(file,src,replace);

            return "任务启动成功!";
        }catch (IOException e) {
            e.printStackTrace();
            return "任务启动失败!";
        }
    }


}
