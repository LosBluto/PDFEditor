package cn.losbluto.pdfeditor.service;

import cn.hutool.core.util.ZipUtil;
import cn.losbluto.pdfeditor.Controller.PDFController;
import cn.losbluto.pdfeditor.tools.EmailTool;
import cn.losbluto.pdfeditor.tools.PDFEditor;
import cn.losbluto.pdfeditor.tools.ThreadUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.CountDownLatch;

/**
 * @author LosBluto
 * @version 1.0.0
 * @ClassName PDFService.java
 * @Description TODO
 * @createTime 2022年07月02日 20:06:00
 */
@Service
public class PDFService {
    public static volatile boolean started = false;

    public static final String dir = System.getProperty("user.dir");

    public static final String inputDir = dir + "/files/";
    public static final String outputDir = inputDir + "/output/";
    public static final String zipDir = inputDir + "/result/";

    public static void initDir(){
        File f = new File(inputDir);
        if (!f.exists())
            f.mkdir();
        f = new File(outputDir);
        if (!f.exists())
            f.mkdir();
        f = new File(zipDir);
        if (!f.exists())
            f.mkdir();
    }


    public void dealPdf(MultipartFile input, String src, String replace) throws IOException {
        File file = new File(inputDir+"/test.zip");
        input.transferTo(file);

        new Thread(new Runnable() {
            @Override
            public void run() {
                File dir = new File(outputDir);
                changeStartStatus(true);                //开始任务锁

                try {
                    ZipUtil.unzip(file, dir, Charset.forName("GBK"));            //解压文件
                    PDFEditor.PDFReplaceWord(dir.getAbsolutePath(), src, replace);            //处理pdf
                    String zipPath = zipDir + System.currentTimeMillis() + ".zip";
                    ZipUtil.zip(dir.getAbsolutePath(), zipPath);
                        //2510829067@qq.com
                    EmailTool.send("2510829067@qq.com", zipPath);           //发邮件
                    System.out.println("deal over!!!");
                }catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("deal error!!!");
                }
                deleteFiles(dir.listFiles());
                changeStartStatus(false);               //关闭任务锁
            }
        }).start();
    }

    private void deleteFiles(File... files){
        if (files != null)
            for (File file : files) {
                if (file.isDirectory())
                    deleteFiles(file.listFiles());
                file.delete();
            }
    }

    private void changeStartStatus(boolean b) {
        synchronized (PDFController.class) {
            started = b;
        }
    }
}
