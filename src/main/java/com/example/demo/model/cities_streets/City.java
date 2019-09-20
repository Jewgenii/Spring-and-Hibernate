package com.example.demo.model.cities_streets;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Table(name="city",uniqueConstraints = @UniqueConstraint(name = "uniqueCityName", columnNames = {"cityname"}))
@Entity
public class City implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="cityname",length = 255)
    private String cityname;

    public Long getId() {
        return id;
    }

    @OneToMany(
            mappedBy = "city",
            cascade =  CascadeType.ALL,
            orphanRemoval = true
    )
    private List<CitiesToStreets> streets = new ArrayList<>();

    public void addStreet(Street street){
        CitiesToStreets citiesToStreets = new CitiesToStreets(this,street);
        streets.add(citiesToStreets);
        street.getCities().add(citiesToStreets);
    }

    public City() {
    }

    public void removeStreet(Street street){
        for (Iterator<CitiesToStreets> iter = streets.iterator();iter.hasNext();) {
            CitiesToStreets citiesToStreets = iter.next();
            if(citiesToStreets.getStreet().equals(street)&&citiesToStreets.getCity().equals(this)){
                iter.remove();
                citiesToStreets.getStreet().getCities().remove(citiesToStreets);
                citiesToStreets.setCity(null);
                citiesToStreets.setStreet(null);
            }
        }
    }

    public void setName(String name) {
        this.cityname = name;
    }

    public City(String cityname) {
        this.cityname = cityname;
    }

    public String getName() {
        return cityname;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        City that = (City) obj;
        return Objects.equals(id, that.id) && Objects.equals(cityname, that.cityname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,cityname);
    }
}
