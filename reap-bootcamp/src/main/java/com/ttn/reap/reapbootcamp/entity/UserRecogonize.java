package com.ttn.reap.reapbootcamp.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_recogonize")
public class UserRecogonize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer sourceId;
    private Integer destId;
    private String sourceEmail;
    private String destEmail;
    private Integer badgeId;
    private String karma;
    private String reason;
    private String badgeType;
    private Date timestamp;

    public UserRecogonize() {
    }

    public UserRecogonize(Integer sourceId, Integer destId, String sourceEmail, String destEmail, Integer badgeId, String karma, String reason, Date timestamp) {
        this.sourceId = sourceId;
        this.destId = destId;
        this.sourceEmail = sourceEmail;
        this.destEmail = destEmail;
        this.badgeId = badgeId;
        this.karma = karma;
        this.reason = reason;
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public Integer getDestId() {
        return destId;
    }

    public void setDestId(Integer destId) {
        this.destId = destId;
    }

    public String getSourceEmail() {
        return sourceEmail;
    }

    public void setSourceEmail(String sourceEmail) {
        this.sourceEmail = sourceEmail;
    }

    public String getDestEmail() {
        return destEmail;
    }

    public void setDestEmail(String destEmail) {
        this.destEmail = destEmail;
    }

    public Integer getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(Integer badgeId) {
        this.badgeId = badgeId;
    }

    public String getKarma() {
        return karma;
    }

    public void setKarma(String karma) {
        this.karma = karma;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "UserRecogonize{" +
                "id=" + id +
                ", sourceId=" + sourceId +
                ", destId=" + destId +
                ", sourceEmail='" + sourceEmail + '\'' +
                ", destEmail='" + destEmail + '\'' +
                ", badgeId=" + badgeId +
                ", karma='" + karma + '\'' +
                ", reason='" + reason + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    public String getBadgeType() {
        return badgeType;
    }

    public void setBadgeType(String badgeType) {
        this.badgeType = badgeType;
    }

    /*
    @ManyToOne
    User user;*/
}