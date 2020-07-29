package cn.pomit.springwork.netty.Skills.Entity;

import javax.xml.bind.annotation.*;
import java.util.List;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",propOrder = {
        "skilLists",
})
@XmlRootElement(name="Root")
public class SkilList {
    @XmlElement(name = "Skill")
    private List<Skill> skilLists;
    public List<Skill> getSkilLists() {
        return skilLists;
    }

    public void setSkilLists(List<Skill> skilLists) {
        this.skilLists = skilLists;
    }

    @Override
    public String toString() {
        return "SkilList{" +
                "skillList=" + skilLists +
                '}';
    }
}
