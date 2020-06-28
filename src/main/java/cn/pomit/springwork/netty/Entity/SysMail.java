package cn.pomit.springwork.netty.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
/*
全服邮件
 */
@Table(name="tb_sysmail")
public class SysMail {
    @Id
    //全服系统邮件Id
    private Long sysMailId;
    //发送者
    private  String sender;
    //邮件信标题
    private String title;
    //邮件内容
    private String contect;
    //邮件发送时间
    private Date sendtime;

    public Long getSysMailId() {
        return sysMailId;
    }
    public void setSysMailId(Long sysMailId) {
        this.sysMailId = sysMailId;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContect() {
        return contect;
    }

    public void setContect(String contect) {
        this.contect = contect;
    }

    @Override
    public String toString() {
        return "SysMail{" +
                "sysMailId=" + sysMailId +
                ", sender='" + sender + '\'' +
                ", title='" + title + '\'' +
                ", contect='" + contect + '\'' +
                ", sendtime=" + sendtime +
                '}';
    }
}
