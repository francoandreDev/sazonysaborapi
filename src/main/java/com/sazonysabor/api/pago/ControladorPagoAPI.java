package com.sazonysabor.api.pago;

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
@RequestMapping("/api/v1/pagos")
public class ControladorPagoAPI {
	@Autowired
	private ServicioPago servicio;
	@Autowired
	private MapaPago mapa;
	@GetMapping
	public ResponseEntity<List<DTOPagoRes>> obtenerTodos() {
		List<EntidadPago> pagos = servicio.obtenerTodos();
		if (pagos.size() == 0) return ResponseEntity.noContent().build();
		List<DTOPagoRes> res = pagos.stream()
			.map(mapa::obtenerRespuesta)
			.collect(Collectors.toList());
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<DTOPagoRes> obtenerUno(@PathVariable("id") Long id) {
		EntidadPago pago = servicio.obtenerUno(id);
		if (pago == null) return ResponseEntity.notFound().build();
		DTOPagoRes res = mapa.obtenerRespuesta(pago);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<DTOPagoRes> crear(@RequestBody DTOPagoReq req) {
		EntidadPago pago = mapa.obtenerEntidad(req);
		EntidadPago nuevoPago = servicio.guardar(pago);
		DTOPagoRes res = mapa.obtenerRespuesta(nuevoPago);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}
	@PutMapping("/{id}")
	public ResponseEntity<DTOPagoRes> reemplazar(
		@PathVariable("id") Long id, @RequestBody DTOPagoReq req
	) {
		EntidadPago pago = servicio.obtenerUno(id);
		if(pago == null) return ResponseEntity.notFound().build();
		pago = mapa.actualizarEntidad(pago, req);
		EntidadPago pagoActualizado = servicio.guardar(pago);
		DTOPagoRes res = mapa.obtenerRespuesta(pagoActualizado);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	@PatchMapping("/{id}")
	public ResponseEntity<DTOPagoRes> actualizar(
		@PathVariable("id") Long id, @RequestBody Map<String, Object> campos
	) {
		EntidadPago pago = servicio.obtenerUno(id);
		if(pago == null) return ResponseEntity.notFound().build();
		pago = mapa.actualizarParcialEntidad(pago, campos);
		EntidadPago pagoActualizado = servicio.guardar(pago);
		DTOPagoRes res = mapa.obtenerRespuesta(pagoActualizado);
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
