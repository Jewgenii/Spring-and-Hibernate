package com.example.demo.model.cities_streets;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Table(name = "street")
@Entity
public class Street implements Serializable {
    private static final long serialVersionUID = 1L;

    public Street() {
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="streetname", unique = true)
    private String streetname;

    @OneToMany(
            mappedBy = "street",
            cascade =  CascadeType.ALL,
            orphanRemoval = true
    )
    private List<CitiesToStreets> cities = new ArrayList<>();

    public Street(String streetname) {
        this.streetname = streetname;
    }

    public List<CitiesToStreets> getCities() {
        return cities;
    }

    public void setName(String name) {
        this.streetname = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return streetname;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Street that = (Street) obj;
        return Objects.equals(id, that.id) && Objects.equals(streetname, that.streetname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,streetname);
    }
}
