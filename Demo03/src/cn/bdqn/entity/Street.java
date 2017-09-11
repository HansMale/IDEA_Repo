package cn.bdqn.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Street {
    private long sid;
    private String sname;
    private Long did;

    @Id
    @Column(name = "SID")
    public long getSid() {
        return sid;
    }

    public void setSid(long sid) {
        this.sid = sid;
    }

    @Basic
    @Column(name = "SNAME")
    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    @Basic
    @Column(name = "DID")
    public Long getDid() {
        return did;
    }

    public void setDid(Long did) {
        this.did = did;
    }
}
