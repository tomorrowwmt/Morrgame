package cn.pomit.springwork.netty.Excel;

import cn.pomit.springwork.netty.Ditu.Ditu;
import cn.pomit.springwork.netty.Monster.Boss;
import cn.pomit.springwork.netty.Monster.Monster;
import cn.pomit.springwork.netty.Skills.Entity.SkilList;
import cn.pomit.springwork.netty.Skills.Entity.Skill;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Objects;

public class JaxbUtil {
    private static Logger logger = Logger.getLogger(JaxbUtil.class);
    //xml转化为Javabean
    @SuppressWarnings("unchecked")
    public static <T> T xmlToBean(String xmlPath,Class<T> load) throws JAXBException, IOException{
        JAXBContext context=JAXBContext.newInstance(load);
        Unmarshaller unmarshaller=context.createUnmarshaller();
        return (T) unmarshaller.unmarshal(new StringReader(xmlPath));
    }
    public static Ditu xmlTojava() throws Exception {
        // 获取JAXB的上下文环境，需要传入具体的 Java bean ->
        JAXBContext context = JAXBContext.newInstance(Ditu.class);
        // 创建 UnMarshaller 实例
        Unmarshaller unmarshaller = context.createUnmarshaller();
        // 加载需要转换的XML数据 -> 这里使用InputStream，还可以使用File，Reader等
        String filepath= Objects.requireNonNull(Thread.currentThread().getContextClassLoader()
        .getResource("XML/Ditu.xml")).getPath();
        Ditu ditu =(Ditu)unmarshaller.unmarshal(new File(filepath));
        // 将结果打印到控制台
        //System.out.println(stu);
        return  ditu;
    }
    public static  Monster masxmlTojava() throws Exception {
        // 获取JAXB的上下文环境，需要传入具体的 Java bean ->
        JAXBContext context = JAXBContext.newInstance(Monster.class);
        // 创建 UnMarshaller 实例
        Unmarshaller unmarshaller = context.createUnmarshaller();
        // 加载需要转换的XML数据 -> 这里使用InputStream，还可以使用File，Reader等
        String filepath= Objects.requireNonNull(Thread.currentThread().getContextClassLoader()
                .getResource("XML/Monster.xml")).getPath();
        // 将XML数据序列化 -> 该方法的返回值为Object基类，需要强转类型
        Monster mas = (Monster)unmarshaller.unmarshal(new File(filepath));
        // 将结果打印到控制台
        //System.out.println(stu);
        return  mas;
    }
    public static Boss bossxmlTojava() throws Exception {
        // 获取JAXB的上下文环境，需要传入具体的 Java bean ->
        JAXBContext context = JAXBContext.newInstance(Boss.class);
        // 创建 UnMarshaller 实例
        Unmarshaller unmarshaller = context.createUnmarshaller();
        // 加载需要转换的XML数据 -> 这里使用InputStream，还可以使用File，Reader等
        String filepath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader()
                .getResource("XML/Boss.xml")).getPath();
        // 将XML数据序列化 -> 该方法的返回值为Object基类，需要强转类型
        Boss boss = (Boss) unmarshaller.unmarshal(new File(filepath));
        // 将结果打印到控制台
        //System.out.println(stu);
        return boss;
    }
/*
    public static List<SkilList> skillxmlTojava() throws Exception {
        // 获取JAXB的上下文环境，需要传入具体的 Java bean ->
        JAXBContext context = JAXBContext.newInstance(SkilList.class);
        // 创建 UnMarshaller 实例
        Unmarshaller unmarshaller = context.createUnmarshaller();
        // 加载需要转换的XML数据 -> 这里使用InputStream，还可以使用File，Reader等
        String filepath= Objects.requireNonNull(Thread.currentThread().getContextClassLoader()
                .getResource("XML/Skill.xml")).getPath();
        // 将XML数据序列化 -> 该方法的返回值为Object基类，需要强转类型
       Skill skill= (Skill) unmarshaller.unmarshal(new File(filepath));
        //SkilList skill = (SkilList) unmarshaller.unmarshal(new File(filepath));
        // 将结果打印到控制台
        System.out.println(skill);
        return skill.getSkilLists();
    }
 */
    public static void selectAll() throws ParserConfigurationException, IOException,  SAXException {
        String filepath= Objects.requireNonNull(Thread.currentThread().getContextClassLoader()
                .getResource("XML/Skill.xml")).getPath();
        //1.创建解析器工厂
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        //2.根据解析器工厂创建解析器
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        //3.根据xml文件生成Document
        Document document = documentBuilder.parse(filepath);
        NodeList nodeList = document.getElementsByTagName("name");
        //Node node=nodeList.item(0);
        //String textContent = node.getTextContent();
         for (int n = 0; n < nodeList.getLength(); n++) {
             Node node = nodeList.item(n);
           String textContent = node.getTextContent();
           System.out.println(textContent);
        }
    }
    public static void main(String[] args) throws Exception {
        // xmlToJavaBean();
        // xmlJavaBean1();
        //xmlTojava();
        //skillxmlTojava();
        selectAll();
    }
}

