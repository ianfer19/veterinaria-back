package com.example.VeterinriaXYZ.service;

import com.example.VeterinriaXYZ.dao.SecPersonaDAO;
import com.example.VeterinriaXYZ.dto.SecPersona;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class SecPersonaServiceImplTest {

    @Mock
    private SecPersonaDAO secPersonaDAO;

    @InjectMocks
    private SecPersonaServiceImpl secPersonaService;

    private SecPersona secPersona;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        secPersona = new SecPersona();
        secPersona.setId(1);
        secPersona.setDireccion("tr 24 #28-36");
        secPersona.setNombre("Iam");
        secPersona.setTipoId("CC");
        secPersona.setCiudad("Sahagún");
    }

    @Test
    void save() {
        when(secPersonaService.save(any(SecPersona.class))).thenReturn(secPersona);
        assertNotNull(secPersonaService.save(new SecPersona()));
    }

    @Test
    void update() {
    }

    @Test
    void findAll() {
        when(secPersonaService.findAll()).thenReturn(Arrays.asList(secPersona));
        assertNotNull(secPersonaService.findAll());
    }

    @Test
    void findOne() {
    }
}