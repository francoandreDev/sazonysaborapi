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
	
	@Autowired
    private MapaUsuario mapa;
	
	@GetMapping
	public ResponseEntity<List<DTOUsuarioRes>> obtenerTodos() {
		List<EntidadUsuario> usuarios = servicio.obtenerTodos();
		if (usuarios.size() == 0) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		List<DTOUsuarioRes> res = usuarios.stream()
			.map(mapa::obtenerRespuesta)
			.collect(Collectors.toList());
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DTOUsuarioRes> obtenerUno(@PathVariable("id") Long id) {
		EntidadUsuario usuario = servicio.obtenerUno(id);
		if (usuario == null) return ResponseEntity.notFound().build();
		DTOUsuarioRes res = mapa.obtenerRespuesta(usuario);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<DTOUsuarioRes> crear(@RequestBody DTOUsuarioReq req) {
		EntidadUsuario usuario = mapa.obtenerEntidad(req);
		EntidadUsuario nuevoUsuario = servicio.guardar(usuario);
		DTOUsuarioRes res = mapa.obtenerRespuesta(nuevoUsuario);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<DTOUsuarioRes> reemplazar(
		@PathVariable("id") Long id, @RequestBody DTOUsuarioReq req
	) {
		EntidadUsuario usuario = servicio.obtenerUno(id);
		if(usuario == null) return ResponseEntity.notFound().build();
		usuario = mapa.actualizarEntidad(usuario, req);
		EntidadUsuario usuarioActualizado = servicio.guardar(usuario);
		DTOUsuarioRes res = mapa.obtenerRespuesta(usuarioActualizado);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<DTOUsuarioRes> actualizar(
		@PathVariable("id") Long id, @RequestBody Map<String, Object> campos
	) {
		EntidadUsuario usuario = servicio.obtenerUno(id);
		if(usuario == null) return ResponseEntity.notFound().build();
		usuario = mapa.actualizarParcialEntidad(usuario, campos);
		EntidadUsuario usuarioActualizado = servicio.guardar(usuario);
		DTOUsuarioRes res = mapa.obtenerRespuesta(usuarioActualizado);
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
