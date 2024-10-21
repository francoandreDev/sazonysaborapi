package com.sazonysabor.api.usuarios;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sazonysabor.api.usuario.EntidadUsuario;
import com.sazonysabor.api.usuario.IRepositorioUsuario;
import com.sazonysabor.api.usuario.ServicioUsuario;
public class TestServicioUsuario {

    @InjectMocks
    private ServicioUsuario servicio;

    @Mock
    private IRepositorioUsuario repositorio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerTodos() {
        EntidadUsuario usuario1 = new EntidadUsuario(1L, "Juan Perez", "juanperez@gmail.com", "contraseña123", "+51 900 000 000");
        EntidadUsuario usuario2 = new EntidadUsuario(2L, "Luisa Perez", "luisaperez@gmail.com", "contraseña456", "+51 900 000 001");
        
        when(repositorio.findAll()).thenReturn(Arrays.asList(usuario1, usuario2));
        
        List<EntidadUsuario> usuarios = servicio.obtenerTodos();
        
        assertEquals(2, usuarios.size());
        verify(repositorio, times(1)).findAll();
    }

    @Test
    void testObtenerUno() {
        EntidadUsuario usuario = new EntidadUsuario(
        	1L, "Juan Perez", "juanperez@gmail.com", "contraseña123", "+51 900 000 000"
        );
        
        when(repositorio.findById(1L)).thenReturn(Optional.of(usuario));
        
        EntidadUsuario usuarioObtenido = servicio.obtenerUno(1L);
        
        assertNotNull(usuarioObtenido);
        assertEquals("Juan Perez", usuarioObtenido.getNombre());
        assertEquals("juanperez@gmail.com", usuarioObtenido.getCorreo());
        assertEquals("contraseña123", usuarioObtenido.getContrasena());
        assertEquals("+51 900 000 000", usuarioObtenido.getTelefono());
    }

    @Test
    void testGuardar() {
        EntidadUsuario usuario = new EntidadUsuario(null, "Nuevo Usuario", "nuevo@gmail.com", "password", "+51 912 345 678");
        
        when(repositorio.save(usuario)).thenReturn(new EntidadUsuario(1L, "Nuevo Usuario", "nuevo@gmail.com", "password", "+51 912 345 678"));
        
        EntidadUsuario usuarioGuardado = servicio.guardar(usuario);
        
        assertNotNull(usuarioGuardado.getId());
        assertEquals(1L, usuarioGuardado.getId());
    }

    @Test
    void testEliminar() {
        Long id = 1L;
        servicio.eliminar(id);
        verify(repositorio, times(1)).deleteById(id);
    }
}
