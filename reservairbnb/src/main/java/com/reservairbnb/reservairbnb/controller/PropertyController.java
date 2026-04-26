package com.reservairbnb.reservairbnb.controller;

import com.reservairbnb.reservairbnb.model.Property;
import com.reservairbnb.reservairbnb.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping
    public List<Property> getAllProperties() {
        return propertyService.obtenerTodas();
    }

    @PostMapping
    public Property createProperty(@RequestBody Property property) {
        return propertyService.guardarPropiedad(property);
    }
}