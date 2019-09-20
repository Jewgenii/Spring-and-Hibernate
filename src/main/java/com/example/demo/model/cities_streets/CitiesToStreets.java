package com.example.demo.model.cities_streets;

import org.hibernate.annotations.GenerationTime;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "cities_to_streets",uniqueConstraints = {@UniqueConstraint(name = "unique_city_street",columnNames = {"city_id","street_id"})})
public class CitiesToStreets implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long _id;

    public Long getId() {
        return _id;
    }
    @Embedded
    private CitiesToStreetsID id;

    public CitiesToStreets() { }

    @Column(name="created_on")
    private Date time = new Date();

    public CitiesToStreets(City city, Street street) {
        this.id = new CitiesToStreetsID(city.getId(),street.getId());
        this.city = city;
        this.street = street;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public City getCity() {
        return city;
    }

    public Street getStreet() {
        return street;
    }
    @MapsId("city")
    @ManyToOne(fetch = FetchType.LAZY)
    private City city;

    @MapsId("street")
    @ManyToOne(fetch = FetchType.LAZY)
    private Street street;

    @Column(name = "`timestamp`")
    @Temporal(value = TemporalType.DATE)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Date date;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        CitiesToStreets that = (CitiesToStreets) obj;
        return Objects.equals(city, that.city) && Objects.equals(street, that.street) && Objects.equals(id,that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,city,street);
    }
}
