package com.example.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "cities_to_streets")
public class CitiesToStreets implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private CitiesToStreetsID id;
/*    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;*/

    public CitiesToStreets() {
    }

    @Column(name="created_on")
    private Date time = new Date();

    public CitiesToStreets( City city, Street street) {
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

    @MapsId("cityid")
    @ManyToOne(fetch = FetchType.LAZY)
    private City city;

    @MapsId("streetid")
    @ManyToOne(fetch = FetchType.LAZY)
    private Street street;

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
