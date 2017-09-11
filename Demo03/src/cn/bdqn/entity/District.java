package cn.bdqn.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class District {
    private long did;
    private String dname;

    @Id
    @Column(name = "DID")
    public long getDid() {
        return did;
    }

    public void setDid(long did) {
        this.did = did;
    }

    @Basic
    @Column(name = "DNAME")
    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

}
