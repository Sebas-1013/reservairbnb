package com.reservairbnb.reservairbnb.service;

import com.reservairbnb.reservairbnb.model.Property;
import com.reservairbnb.reservairbnb.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    public List<Property> obtenerTodas() {
        return propertyRepository.findAll();
    }

    public Property guardarPropiedad(Property property) {
        return propertyRepository.save(property);
    }

    public Property buscarPorId(Long id) {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Propiedad no encontrada"));
    }
}