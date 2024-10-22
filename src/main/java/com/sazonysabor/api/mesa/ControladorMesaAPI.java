package com.sazonysabor.api.mesa;

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
@RequestMapping("/api/v1/mesas")
public class ControladorMesaAPI {
	@Autowired
	private ServicioMesa servicio;
	@Autowired
	private MapaMesa mapa;
	@GetMapping
	 public ResponseEntity<List<DTOMesaRes>> obtenerTodos() {
		 List<EntidadMesa> mesas = servicio.obtenerTodos();
		 if(mesas.size() == 0) return ResponseEntity.noContent().build();
		 List<DTOMesaRes> res = mesas.stream()
			 .map(mapa::obtenerRespuesta)
			 .collect(Collectors.toList());
		 return new ResponseEntity<>(res, HttpStatus.OK);
	 }
	 @GetMapping("/{id}")
	 public ResponseEntity<DTOMesaRes> obtenerUno(@PathVariable("id") Long id) {
		 EntidadMesa mesa = servicio.obtenerUno(id);
		 if(mesa == null) return ResponseEntity.notFound().build();
		 DTOMesaRes res = mapa.obtenerRespuesta(mesa);
		 return new ResponseEntity<>(res, HttpStatus.OK);
	 }
	 @PostMapping
	 public ResponseEntity<DTOMesaRes> crear(@RequestBody DTOMesaReq req) {
		 EntidadMesa mesa = mapa.obtenerEntidad(req);
		 EntidadMesa nuevaMesa = servicio.guardar(mesa);
		 DTOMesaRes res = mapa.obtenerRespuesta(nuevaMesa);
		 return new ResponseEntity<>(res, HttpStatus.CREATED);
	 }
	 @PutMapping("/{id}")
	 public ResponseEntity<DTOMesaRes> reemplazar(
		 @PathVariable("id") Long id, @RequestBody DTOMesaReq req
	 ) {
		 EntidadMesa mesa = servicio.obtenerUno(id);
		 if(mesa == null) return ResponseEntity.notFound().build();
		 mesa = mapa.actualizarEntidad(mesa, req);
		 EntidadMesa MesaActualizado = servicio.guardar(mesa);
		 DTOMesaRes res = mapa.obtenerRespuesta(MesaActualizado);
		 return new ResponseEntity<>(res, HttpStatus.OK);
	 }
	 @PatchMapping("/{id}")
	 public ResponseEntity<DTOMesaRes> actualizar(
		 @PathVariable("id") Long id, @RequestBody Map<String, Object> campos
	 ) {
		 EntidadMesa mesa = servicio.obtenerUno(id);
		 if(mesa == null) return ResponseEntity.notFound().build();
		 mesa = mapa.actualizarParcialEntidad(mesa, campos);
		 EntidadMesa MesaActualizada = servicio.guardar(mesa);
		 DTOMesaRes res = mapa.obtenerRespuesta(MesaActualizada);
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
