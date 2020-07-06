package cn.pomit.springwork.netty.User.Entity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
/*
用户个人邮件
 */
@Table(name="tb_mail")
public class UserMail {
    @Id
    //个人邮件id
    private Long mailId;
    private Long uid;
    //邮件主题信息
    private String title;
    //邮件发送时间
    private Date sendtime;
    //邮件读取状态.0未读 1读取
    private Integer status;
    //附件金币
    private  String annex;
    //赠送道具
    private  String daoju;
    //邮件道具领取状态0为领取，1已领取
    private Integer recevice;

    public String getAnnex() {
        return annex;
    }

    public void setAnnex(String annex) {
        this.annex = annex;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getMailId() {
        return mailId;
    }

    public void setMailId(Long mailId) {
        this.mailId = mailId;
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

    public String getDaoju() {
        return daoju;
    }

    public void setDaoju(String daoju) {
        this.daoju = daoju;
    }

    @Override
    public String toString() {
        return "UserMail{" +
                "mailId=" + mailId +
                ", uid=" + uid +
                ", title='" + title + '\'' +
                ", sendtime=" + sendtime +
                ", status=" + status +
                ", annex='" + annex + '\'' +
                ", daoju='" + daoju + '\'' +
                ", recevice=" + recevice +
                '}';
    }
}
