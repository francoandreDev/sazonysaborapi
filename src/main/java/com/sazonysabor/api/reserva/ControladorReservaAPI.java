package com.sazonysabor.api.reserva;

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
@RequestMapping("/api/v1/reservas")
public class ControladorReservaAPI {
	@Autowired
	private ServicioReserva servicio;
	@Autowired
    private MapaReserva mapa;
	@GetMapping
	public ResponseEntity<List<DTOReservaRes>> obtenerTodos() {
		List<EntidadReserva> reservas = servicio.obtenerTodos();
		if (reservas.size() == 0) return ResponseEntity.noContent().build();
		List<DTOReservaRes> res = reservas.stream()
			.map(mapa::obtenerRespuesta)
			.collect(Collectors.toList());
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<DTOReservaRes> obtenerUno(@PathVariable("id") Long id) {
		EntidadReserva reserva = servicio.obtenerUno(id);
		if (reserva == null) return ResponseEntity.notFound().build();
		DTOReservaRes res = mapa.obtenerRespuesta(reserva);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<DTOReservaRes> crear(@RequestBody DTOReservaReq req) {
		EntidadReserva reserva = mapa.obtenerEntidad(req);
		EntidadReserva nuevoReserva = servicio.guardar(reserva);
		DTOReservaRes res = mapa.obtenerRespuesta(nuevoReserva);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}
	@PutMapping("/{id}")
	public ResponseEntity<DTOReservaRes> reemplazar(
		@PathVariable("id") Long id, @RequestBody DTOReservaReq req
	) {
		EntidadReserva reserva = servicio.obtenerUno(id);
		if(reserva == null) return ResponseEntity.notFound().build();
		reserva = mapa.actualizarEntidad(reserva, req);
		EntidadReserva reservaActualizada = servicio.guardar(reserva);
		DTOReservaRes res = mapa.obtenerRespuesta(reservaActualizada);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	@PatchMapping("/{id}")
	public ResponseEntity<DTOReservaRes> actualizar(
		@PathVariable("id") Long id, @RequestBody Map<String, Object> campos
	) {
		EntidadReserva reserva = servicio.obtenerUno(id);
		if(reserva == null) return ResponseEntity.notFound().build();
		reserva = mapa.actualizarParcialEntidad(reserva, campos);
		EntidadReserva reservaActualizada = servicio.guardar(reserva);
		DTOReservaRes res = mapa.obtenerRespuesta(reservaActualizada);
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
