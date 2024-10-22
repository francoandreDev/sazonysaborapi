package com.sazonysabor.api.pagos;

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

import com.sazonysabor.api.pago.EntidadPago;
import com.sazonysabor.api.pago.IRepositorioPago;
import com.sazonysabor.api.pago.ServicioPago;

public class TestServicioPago {
	@InjectMocks
    private ServicioPago servicio;
    @Mock
    private IRepositorioPago repositorio;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testObtenerTodos() {
        EntidadPago pago1 = new EntidadPago(
    		1L, 500.90, LocalDateTime.now(), "Débito"
		);
        EntidadPago pago2 = new EntidadPago(
        	2L, 1000.00, LocalDateTime.now(), "Efectivo"
    	);
        when(repositorio.findAll()).thenReturn(Arrays.asList(pago1, pago2));
        List<EntidadPago> pagos = servicio.obtenerTodos();
        assertEquals(2, pagos.size());
        verify(repositorio, times(1)).findAll();
    }
    @Test
    void testObtenerUno() {
        EntidadPago pago = new EntidadPago(
        		1L, 500.90, LocalDateTime.of(2024, 10, 02, 16, 41, 00), "Débito"
        );
        when(repositorio.findById(1L)).thenReturn(Optional.of(pago));
        EntidadPago pagoObtenido = servicio.obtenerUno(1L);
        assertNotNull(pagoObtenido);
        assertEquals(500.90, pagoObtenido.getMonto());
        assertEquals(LocalDateTime.of(2024, 10, 02, 16, 41, 00), pagoObtenido.getFecha());
        assertEquals("Débito", pagoObtenido.getModoPago());
    }
    @Test
    void testGuardar() {
        EntidadPago pago = new EntidadPago(
        	null, 500.90, LocalDateTime.now(), "Débito"
		);
        when(repositorio.save(pago)).thenReturn(new EntidadPago(
        	1L, 500.90, LocalDateTime.now(), "Débito"
		));
        EntidadPago pagoGuardado = servicio.guardar(pago);
        assertNotNull(pagoGuardado.getId());
        assertEquals(1L, pagoGuardado.getId());
    }
    @Test
    void testEliminar() {
        Long id = 1L;
        servicio.eliminar(id);
        verify(repositorio, times(1)).deleteById(id);
    }
}
