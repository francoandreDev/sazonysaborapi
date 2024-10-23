package com.sazonysabor.api.reserva;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class MapaReserva {
	// EntidadReserva -> DTOReservaRes
		public DTOReservaRes obtenerRespuesta(EntidadReserva reserva) {
			if(reserva == null) return null;
			return new DTOReservaRes(
				reserva.getId(), 
				reserva.getFechaHora(),
				reserva.getNumeroPersonas(),
				reserva.getEstado()
			);
		}
		// DTOReservaReq -> EntidadReserva
		public EntidadReserva obtenerEntidad(DTOReservaReq req) {
			if (req == null) return null;
			return new EntidadReserva(
				null, 
				req.getFechaHora(),
				req.getNumeroPersonas(),
				req.getEstado()
			);
		}
		// EntidadReserva <= DTOReservaReq
		public EntidadReserva actualizarEntidad(EntidadReserva reserva, DTOReservaReq req) {
			reserva.setFechaHora(req.getFechaHora());
			reserva.setNumeroPersonas(req.getNumeroPersonas());
			reserva.setEstado(req.getEstado());
			return reserva;
		}
		// EntidadReserva <- DTOReservaReq
		public EntidadReserva actualizarParcialEntidad(EntidadReserva reserva, Map<String, Object> campos) {
			campos.forEach((clave, valor) -> {
				switch(clave) {
					case "fechaHora": 
						reserva.setFechaHora(LocalDateTime.parse((String) valor));
						break;
					case "numeroPersonas":
						reserva.setNumeroPersonas((int) valor);
						break;
					case "estado":
						reserva.setEstado((String) valor);
						break;
				}
			});
			return reserva;
		}
}
