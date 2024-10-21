package com.sazonysabor.api.usuario;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/usuarios")
public class ControladorAPIUsuario {
	@Autowired
	private ServicioUsuario servicio;
	
	@GetMapping
	public ResponseEntity<List<DTOUsuarioRes>> obtenerTodos() {
		List<EntidadUsuario> usuarios = servicio.obtenerTodos();
		if (usuarios.size() == 0) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		List<DTOUsuarioRes> res = usuarios.stream()
			.map(usuario -> new DTOUsuarioRes(
					usuario.getId(), 
					usuario.getNombre(), 
					usuario.getCorreo(), 
					usuario.getContrasena(), 
					usuario.getTelefono()
			))
			.collect(Collectors.toList());
		
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DTOUsuarioRes> obtenerUno(@PathVariable("id") Long id) {
		EntidadUsuario usuario = servicio.obtenerUno(id);
		if (usuario == null) return ResponseEntity.notFound().build();
		
		DTOUsuarioRes res = new DTOUsuarioRes(
			usuario.getId(), 
			usuario.getNombre(), 
			usuario.getCorreo(), 
			usuario.getContrasena(), 
			usuario.getTelefono()
		);
		
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<DTOUsuarioRes> crear(@RequestBody DTOUsuarioReq req) {
		EntidadUsuario usuario = new EntidadUsuario(
			null, req.getNombre(), req.getCorreo(), req.getContrasena(), req.getTelefono()
		);
		
		EntidadUsuario nuevoUsuario = servicio.guardar(usuario);
		
		DTOUsuarioRes res = new DTOUsuarioRes(
			nuevoUsuario.getId(), 
			nuevoUsuario.getNombre(), 
			nuevoUsuario.getCorreo(), 
			nuevoUsuario.getContrasena(), 
			nuevoUsuario.getTelefono()
		);
		
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<DTOUsuarioRes> reemplazar(
		@PathVariable("id") Long id, @RequestBody DTOUsuarioReq req
	) {
		EntidadUsuario usuario = servicio.obtenerUno(id);
		if(usuario == null) return ResponseEntity.notFound().build();
		
		usuario.setNombre(req.getNombre());
		usuario.setCorreo(req.getCorreo());
		usuario.setContrasena(req.getContrasena());
		usuario.setTelefono(req.getTelefono());
		
		EntidadUsuario usuarioActualizado = servicio.guardar(usuario);
		
		DTOUsuarioRes res = new DTOUsuarioRes(
			usuarioActualizado.getId(), 
			usuarioActualizado.getNombre(), 
			usuarioActualizado.getCorreo(), 
			usuarioActualizado.getContrasena(), 
			usuarioActualizado.getTelefono()
		);
		
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<DTOUsuarioRes> actualizar(
		@PathVariable("id") Long id, @RequestBody Map<String, Object> campos
	) {
		EntidadUsuario usuario = servicio.obtenerUno(id);
		if(usuario == null) return ResponseEntity.notFound().build();
		
		campos.forEach((clave, valor) -> {
			switch(clave) {
				case "nombre": 
					usuario.setNombre((String) valor);
					break;
				case "correo":
					usuario.setCorreo((String) valor);
					break;
				case "contrasena":
					usuario.setContrasena((String) valor);
					break;
				case "telefono":
					usuario.setTelefono((String) valor);
					break;
			}
		});
		
		EntidadUsuario usuarioActualizado = servicio.guardar(usuario);
		
		DTOUsuarioRes res = new DTOUsuarioRes(
			usuarioActualizado.getId(), 
			usuarioActualizado.getNombre(), 
			usuarioActualizado.getCorreo(), 
			usuarioActualizado.getContrasena(), 
			usuarioActualizado.getTelefono()
		);
		
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Long id) {
		if(servicio.obtenerUno(id) == null) return ResponseEntity.notFound().build();
		
		servicio.eliminar(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.OPTIONS)
	public ResponseEntity<String> verMetodos() {
		HttpHeaders encabezados = new HttpHeaders();
        encabezados.add("Allow", "GET, POST, PUT, DELETE, HEAD, OPTIONS");
        return new ResponseEntity<>(encabezados.toString(), HttpStatus.OK);
	}
}
