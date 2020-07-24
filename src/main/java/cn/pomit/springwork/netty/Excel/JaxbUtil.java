package cn.pomit.springwork.netty.Excel;

import cn.pomit.springwork.netty.Ditu.Ditu;
import cn.pomit.springwork.netty.Monster.Boss;
import cn.pomit.springwork.netty.Monster.Monster;
import cn.pomit.springwork.netty.Skills.Entity.SkilList;
import cn.pomit.springwork.netty.Skills.Entity.Skill;
import org.apache.log4j.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
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
        String filepath= Objects.requireNonNull(Thread.currentThread().getContextClassLoader()
                .getResource("XML/Boss.xml")).getPath();
        // 将XML数据序列化 -> 该方法的返回值为Object基类，需要强转类型
        Boss boss = (Boss)unmarshaller.unmarshal(new File(filepath));
        // 将结果打印到控制台
        //System.out.println(stu);
        return  boss;
    }
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
    public static void main(String[] args) throws Exception {
        // xmlToJavaBean();
        // xmlJavaBean1();
        //xmlTojava();
        skillxmlTojava();
    }
}

