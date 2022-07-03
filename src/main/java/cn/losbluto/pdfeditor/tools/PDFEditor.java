package cn.losbluto.pdfeditor.tools;

import com.aspose.pdf.Document;
import com.aspose.pdf.PageCollection;
import com.aspose.pdf.TextFragment;
import com.aspose.pdf.TextFragmentAbsorber;

import java.io.File;
import java.util.concurrent.CountDownLatch;

/**
 * @author
 * @version 1.0.0
 * @ClassName PDFEditor.java
 * @Description TODO
 * @createTime 2022年06月09日 17:29:00
 */
public class PDFEditor {

    public static void PDFReplaceWord(String srcPath, String desPath,String des,String replace){
        Document pdfDoc = new Document(srcPath);
        TextFragmentAbsorber textFragmentAbsorber = new TextFragmentAbsorber(des);      //查询器
        PageCollection pages = pdfDoc.getPages();
        pages.accept(textFragmentAbsorber);     //给文档设置查询器
        if (textFragmentAbsorber.getTextFragments() != null)
            for (TextFragment textFragment : textFragmentAbsorber.getTextFragments()) {
                if (textFragment == null)
                    continue;
                textFragment.setText(replace);
            }
        pdfDoc.save(desPath);
    }

    public static void PDFReplaceWord(String srcDir,String des,String replace) throws InterruptedException {
        File file = new File(srcDir);
        File file1 = new File(srcDir+"/des");
        if (!file1.exists())
            file1.mkdir();

        String[] files = file.list();
        if (null == files) {
            System.out.println("无文件");
            return;
        }
        CountDownLatch countDownLatch = new CountDownLatch(files.length);
        for (String s : files) {
            Runnable task = new Runnable(){
                @Override
                public void run() {
                    String[] ss = s.split("/");
                    String name = ss[ss.length-1];
                    if (name.endsWith("pdf"))
                        PDFReplaceWord(srcDir+"/"+s,file1.getPath()+"/"+name,des,replace);
                    System.out.println(s+" over");
                    countDownLatch.countDown();
                }
            };
            ThreadUtil.execute(task);   //线程池执行
        }
        countDownLatch.await();         //等待任务完成
    }


}
