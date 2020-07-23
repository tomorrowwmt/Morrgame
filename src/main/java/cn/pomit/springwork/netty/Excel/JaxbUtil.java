package cn.pomit.springwork.netty.Excel;

import cn.pomit.springwork.netty.Ditu.Ditu;
import cn.pomit.springwork.netty.Monster.Monster;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Objects;

public class JaxbUtil {
    //xml转化为Javabean
    @SuppressWarnings("unchecked")
    public static <T> T xmlToBean(String xmlPath,Class<T> load) throws JAXBException, IOException{
        JAXBContext context=JAXBContext.newInstance(load);
        Unmarshaller unmarshaller=context.createUnmarshaller();
        return (T) unmarshaller.unmarshal(new StringReader(xmlPath));
    }

    public static void main(String[] args) throws Exception {
       // xmlToJavaBean();
       // xmlJavaBean1();
        xmlTojava();
    }

    public static Ditu xmlToJavaBean() throws Exception{
        String xml="<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n"+
                "<Ditu>\r\n"+
                " <mid>1</mid>\r\n"+
                " <mname>起始之地</mname>\r\n"+
                " <des>出生的地点</des>\r\n"+
                " <neighbor>魔化之地</neighbor>\r\n"+
                "  <monsterStr>[{\"id\":1,\"count\":3}][{\"id\":2,\"count\":2}]</monsterStr>\r\n"+
                "</Ditu>";
        Ditu ditu=JaxbUtil.xmlToBean(xml,Ditu.class);
        return ditu;
    }
    public static Monster xmlJavaBean1() throws Exception{
        String xml1="<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n"+
                "<Monster>\r\n"+
                " <id>1</id>\r\n"+
                " <name>雪域魔王</name>\r\n"+
                " <hp>500</hp>\r\n"+
                "<sendExp>30</sendExp>\r\n"+
                " <id>2</id>\r\n"+
                " <name>九天狐王</name>\r\n"+
                " <hp>500</hp>\r\n"+
                "<sendExp>30</sendExp>\r\n"+
                "</Monster>";
        //String filepath= Objects.requireNonNull(Thread.currentThread().getContextClassLoader()
        //.getResource("XML/Ditu.xml")).getPath();
        Monster monster=JaxbUtil.xmlToBean(xml1,Monster.class);
        System.out.println(monster);
        return monster;
    }
    public static Ditu xmlTojava() throws Exception {
        // 获取JAXB的上下文环境，需要传入具体的 Java bean ->
        JAXBContext context = JAXBContext.newInstance(Ditu.class);
        // 创建 UnMarshaller 实例
        Unmarshaller unmarshaller = context.createUnmarshaller();
        // 加载需要转换的XML数据 -> 这里使用InputStream，还可以使用File，Reader等
        String filepath= Objects.requireNonNull(Thread.currentThread().getContextClassLoader()
        .getResource("XML/Ditu.xml")).getPath();
        //InputStream stream = SimpleTest.class.getClassLoader().getResourceAsStream("lesson1.xml");
        // 将XML数据序列化 -> 该方法的返回值为Object基类，需要强转类型
        Ditu ditu =(Ditu)unmarshaller.unmarshal(new File(filepath));
        // 将结果打印到控制台
        //System.out.println(stu);
        return  ditu;
    }
}

