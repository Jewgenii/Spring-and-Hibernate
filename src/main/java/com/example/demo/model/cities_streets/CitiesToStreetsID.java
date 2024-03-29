package com.example.demo.model.cities_streets;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Table(uniqueConstraints = @UniqueConstraint(name = "uniquecitystreet", columnNames = {"city_id,street_id"}))
public class CitiesToStreetsID implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "city_id")
    private Long cityId;

    @Column(name = "street_id")
    private Long streetId;

    public CitiesToStreetsID() {
    }

    public CitiesToStreetsID(Long cityId, Long streetId) {
        this.cityId = cityId;
        this.streetId = streetId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityId,streetId);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj==null||obj.getClass()!=this.getClass()) return false;
        CitiesToStreetsID that = (CitiesToStreetsID) obj;
        return Objects.equals(this.cityId,that.cityId) && Objects.equals(this.streetId,that.streetId);
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public void setStreetId(Long streetId) {
        this.streetId = streetId;
    }

    public Long getCityId() {
        return cityId;
    }

    public Long getStreetId() {
        return streetId;
    }
}
