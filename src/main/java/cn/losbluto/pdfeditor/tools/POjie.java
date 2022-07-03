package cn.losbluto.pdfeditor.tools;

import javassist.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.*;
import com.aspose.pdf.*;


/**
 * @author
 * @version 1.0.0
 * @ClassName POjie.java
 * @Description TODO 破解修改aspose验证的字节码
 * @createTime 2022年06月11日 10:06:00
 */
public class POjie {
    public static void test() throws Exception {
        String license2 = "<License>\n"
                + "  <Data>\n"
                + "    <Products>\n"
                + "      <Product>Aspose.Total for Java</Product>\n"
                + "      <Product>Aspose.Words for Java</Product>\n"
                + "    </Products>\n"
                + "    <EditionType>Professional</EditionType>\n"
                + "    <SubscriptionExpiry>20991231</SubscriptionExpiry>\n"
                + "    <LicenseExpiry>20991231</LicenseExpiry>\n"
                + "    <SerialNumber>8bfe198c-7f0c-4ef8-8ff0-acc3237bf0d7</SerialNumber>\n"
                + "  </Data>\n"
                + "  <Signature>111</Signature>\n"
                + "</License>";
        InputStream is2 = new ByteArrayInputStream(
                license2.getBytes("UTF-8"));
        License asposeLic2 = new License();
        asposeLic2.setLicense(is2);
        Document doc1 = new Document("C:\\Users\\蒋辰洋\\Desktop\\杭州电子科技大学第二学士学位申请表.pdf");
        for (int i = 1; i <= doc1.getPages().size(); i++) {
            String imagePath = "C:\\Users\\蒋辰洋\\Desktop\\" +  i + ".png";
            File file = new File(imagePath); // 新建一个文件
            FileOutputStream os = new FileOutputStream(file);
            com.aspose.pdf.devices.Resolution reso = new com.aspose.pdf.devices.Resolution(
                    200);
            com.aspose.pdf.devices.JpegDevice jpegDevice = new com.aspose.pdf.devices.JpegDevice(
                    reso, 100);
            jpegDevice.process(doc1.getPages().get_Item(i), os);
        }
    }

    public static void pojie() throws NotFoundException, IOException, CannotCompileException {
        ClassPool.getDefault().insertClassPath("D:\\envirienment\\apache-maven-3.8.4\\repository\\com\\aspose\\aspose-pdf\\21.6\\aspose-pdf-21.6\\aspose-pdf-21.6-origin.jar");
        CtClass c2 = ClassPool.getDefault().getCtClass("com.aspose.pdf.l9y");
        CtMethod[] declaredMethods = c2.getDeclaredMethods();
        for (CtMethod method : declaredMethods)
        {
            CtClass[] parameterTypes;
            try {
                parameterTypes = method.getParameterTypes();
            }catch (Exception e){
                continue;
            }
            if ("lI".equals(method.getName()) && parameterTypes.length == 1
                    && parameterTypes[0].getName().equals("java.io.InputStream")) {
                method.setBody("{this.ly = new java.util.Date(Long.MAX_VALUE);this.l0if = com.aspose.pdf.l9n.lf;com.aspose.pdf.internal.imaging.internal.p14.Helper.help1();lI(this);}");
            }
        }
        c2.writeFile();
    }

    public static void main(String[] args) throws Exception {
        test();
    }
}
