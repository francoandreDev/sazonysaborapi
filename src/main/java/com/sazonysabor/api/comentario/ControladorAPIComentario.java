package com.sazonysabor.api.comentario;

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
@RequestMapping("/api/v1/comentarios")
public class ControladorAPIComentario {
	 @Autowired
	 private ServicioComentario servicio;
	 @Autowired
	 private MapaComentario mapa;
	 @GetMapping
	 public ResponseEntity<List<DTOComentarioRes>> obtenerTodos() {
		 List<EntidadComentario> comentarios = servicio.obtenerTodos();
		 if(comentarios.size() == 0) return ResponseEntity.noContent().build();
		 List<DTOComentarioRes> res = comentarios.stream()
			 .map(mapa::obtenerRespuesta)
			 .collect(Collectors.toList());
		 return new ResponseEntity<>(res, HttpStatus.OK);
	 }
	 @GetMapping("/{id}")
	 public ResponseEntity<DTOComentarioRes> obtenerUno(@PathVariable("id") Long id) {
		 EntidadComentario comentario = servicio.obtenerUno(id);
		 if(comentario == null) return ResponseEntity.notFound().build();
		 DTOComentarioRes res = mapa.obtenerRespuesta(comentario);
		 return new ResponseEntity<>(res, HttpStatus.OK);
	 }
	 @PostMapping
	 public ResponseEntity<DTOComentarioRes> crear(@RequestBody DTOComentarioReq req) {
		 EntidadComentario comentario = mapa.obtenerEntidad(req);
		 EntidadComentario nuevoComentario = servicio.guardar(comentario);
		 DTOComentarioRes res = mapa.obtenerRespuesta(nuevoComentario);
		 return new ResponseEntity<>(res, HttpStatus.CREATED);
	 }
	 @PutMapping("/{id}")
	 public ResponseEntity<DTOComentarioRes> reemplazar(
		 @PathVariable("id") Long id, @RequestBody DTOComentarioReq req
	 ) {
		 EntidadComentario comentario = servicio.obtenerUno(id);
		 if(comentario == null) return ResponseEntity.notFound().build();
		 comentario = mapa.actualizarEntidad(comentario, req);
		 EntidadComentario comentarioActualizado = servicio.guardar(comentario);
		 DTOComentarioRes res = mapa.obtenerRespuesta(comentarioActualizado);
		 return new ResponseEntity<>(res, HttpStatus.OK);
	 }
	 @PatchMapping("/{id}")
	 public ResponseEntity<DTOComentarioRes> actualizar(
		 @PathVariable("id") Long id, @RequestBody Map<String, Object> campos
	 ) {
		 EntidadComentario comentario = servicio.obtenerUno(id);
		 if(comentario == null) return ResponseEntity.notFound().build();
		 comentario = mapa.actualizarParcialEntidad(comentario, campos);
		 EntidadComentario comentarioActualizado = servicio.guardar(comentario);
		 DTOComentarioRes res = mapa.obtenerRespuesta(comentarioActualizado);
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
