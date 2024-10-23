package com.sazonysabor.api.reservas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sazonysabor.api.reserva.EntidadReserva;
import com.sazonysabor.api.reserva.IRepositorioReserva;
import com.sazonysabor.api.reserva.ServicioReserva;

public class TestServicioReserva {
	@InjectMocks
    private ServicioReserva servicio;
    @Mock
    private IRepositorioReserva repositorio;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testObtenerTodos() {
        EntidadReserva reserva1 = new EntidadReserva(
    		1L, LocalDateTime.now(), 4, "Reservado"
		);
        EntidadReserva reserva2 = new EntidadReserva(
        	1L, LocalDateTime.now(), 7, "Cancelado"
    	);
        when(repositorio.findAll()).thenReturn(Arrays.asList(reserva1, reserva2));
        List<EntidadReserva> reservas = servicio.obtenerTodos();
        assertEquals(2, reservas.size());
        verify(repositorio, times(1)).findAll();
    }
    @Test
    void testObtenerUno() {
        EntidadReserva reserva = new EntidadReserva(
        	1L, LocalDateTime.now(), 4, "Reservado"
        );
        when(repositorio.findById(1L)).thenReturn(Optional.of(reserva));
        EntidadReserva reservaObtenida = servicio.obtenerUno(1L);
        assertNotNull(reservaObtenida);
        assertEquals(LocalDateTime.now(), reservaObtenida.getFechaHora());
        assertEquals(4, reservaObtenida.getNumeroPersonas());
        assertEquals("Reservado", reservaObtenida.getEstado());
    }
    @Test
    void testGuardar() {
        EntidadReserva reserva = new EntidadReserva(
        	null, LocalDateTime.now(), 4, "Reservado"
		);
        when(repositorio.save(reserva)).thenReturn(new EntidadReserva(
        	1L, LocalDateTime.now(), 4, "Reservado"
		));
        EntidadReserva reservaGuardada = servicio.guardar(reserva);
        assertNotNull(reservaGuardada.getId());
        assertEquals(1L, reservaGuardada.getId());
    }
    @Test
    void testEliminar() {
        Long id = 1L;
        servicio.eliminar(id);
        verify(repositorio, times(1)).deleteById(id);
    }
}
