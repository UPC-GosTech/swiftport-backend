package com.gostech.swiftportbackend.resources.domain.model.valueobjects;

import com.gostech.swiftportbackend.resources.domain.model.entities.Location;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Embeddable
public class LocationsList {

    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL)
    private List<Location> locationItems;

    public LocationsList() {
        this.locationItems = new ArrayList<>();
    }
}
