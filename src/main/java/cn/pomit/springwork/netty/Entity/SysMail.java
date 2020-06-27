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
    //邮件主题信息
    private String title;
    //邮件发送时间
    private Date sendtime;
    //邮件读取状态.0未读 1读取
    private Integer status;
    //邮件道具领取状态0为领取，1已领取
    private Integer recevice;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRecevice() {
        return recevice;
    }

    public void setRecevice(Integer recevice) {
        this.recevice = recevice;
    }
    @Override
    public String toString() {
        return "SysMail{" +
                "sysMailId=" + sysMailId +
                ", title='" + title + '\'' +
                ", sendtime=" + sendtime +
                ", status=" + status +
                ", recevice=" + recevice +
                '}';
    }
}
