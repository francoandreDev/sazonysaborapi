package com.sazonysabor.api.mesas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sazonysabor.api.mesa.EntidadMesa;
import com.sazonysabor.api.mesa.IRepositorioMesa;
import com.sazonysabor.api.mesa.ServicioMesa;

public class TestServicioMesa {
	@InjectMocks
    private ServicioMesa servicio;
    @Mock
    private IRepositorioMesa repositorio;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testObtenerTodos() {
        EntidadMesa mesa1 = new EntidadMesa(1L, 5, true);
        EntidadMesa mesa2 = new EntidadMesa(2L, 10, false);
        when(repositorio.findAll()).thenReturn(Arrays.asList(mesa1, mesa2));
        List<EntidadMesa> mesas = servicio.obtenerTodos();
        assertEquals(2, mesas.size());
        verify(repositorio, times(1)).findAll();
    }
    @Test
    void testObtenerUno() {
        EntidadMesa mesa = new EntidadMesa(1L, 5, true);
        when(repositorio.findById(1L)).thenReturn(Optional.of(mesa));
        EntidadMesa mesaObtenida = servicio.obtenerUno(1L);
        assertNotNull(mesaObtenida);
        assertEquals(5, mesaObtenida.getCapacidad());
        assertEquals(true, mesaObtenida.isEstado());
    }
    @Test
    void testGuardar() {
        EntidadMesa mesa = new EntidadMesa(null, 5, true);
        when(repositorio.save(mesa)).thenReturn(new EntidadMesa(1L, 15, false));
        EntidadMesa mesaGuardada = servicio.guardar(mesa);
        assertNotNull(mesaGuardada.getId());
        assertEquals(1L, mesaGuardada.getId());
    }
    @Test
    void testEliminar() {
        Long id = 1L;
        servicio.eliminar(id);
        verify(repositorio, times(1)).deleteById(id);
    }
}
