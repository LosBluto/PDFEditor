package cn.losbluto.pdfeditor;

import cn.hutool.core.net.NetUtil;
import cn.losbluto.pdfeditor.service.PDFService;
import cn.losbluto.pdfeditor.tools.ThreadUtil;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author LosBluto
 * @version 1.0.0
 * @ClassName cn.losbluto.pdfeditor.tools.App.java
 * @Description TODO
 * @createTime 2022年07月02日 19:57:00
 */
@SpringBootApplication
public class App {
    public static void main(String[] args){
        int port = 80;
        if (!NetUtil.isUsableLocalPort(port)) {
            System.out.println(port + "端口被占用");
            System.exit(2);
        }
        new SpringApplicationBuilder(App.class).properties("server.port="+port).run(args);
        ThreadUtil.init();
        PDFService.initDir();
    }
}
