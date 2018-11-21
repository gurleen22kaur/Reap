package com.ttn.reap.reapbootcamp.entity;

import javax.persistence.*;


@Entity
@Table(name = "badges")
public class Badge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer badgeId;

    private String badge;
    private Integer points;

    public Badge() {
    }

    public Badge(String badge, Integer points) {
        this.badge = badge;
        this.points = points;
    }

    public Integer getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(Integer badgeId) {
        this.badgeId = badgeId;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Badge{" +
                "badgeId=" + badgeId +
                ", badge='" + badge + '\'' +
                ", points=" + points +
                '}';
    }
}
