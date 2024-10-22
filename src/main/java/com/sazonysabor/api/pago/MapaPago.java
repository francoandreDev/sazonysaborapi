package com.sazonysabor.api.pago;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class MapaPago {
	// EntidadPago -> DTOPagoRes
		public DTOPagoRes obtenerRespuesta(EntidadPago pago) {
			if(pago == null) return null;
			return new DTOPagoRes(
				pago.getId(), 
				pago.getMonto(),
				pago.getFecha(),
				pago.getModoPago()
			);
		}
		// DTOPagoReq -> EntidadPago
		public EntidadPago obtenerEntidad(DTOPagoReq req) {
			if (req == null) return null;
			return new EntidadPago(
				null, 
				req.getMonto(),
				req.getFecha(),
				req.getModoPago()
			);
		}
		// EntidadPago <= DTOPagoReq
		public EntidadPago actualizarEntidad(EntidadPago pago, DTOPagoReq req) {
			pago.setMonto(req.getMonto());
			pago.setFecha(req.getFecha());
			pago.setModoPago(req.getModoPago());
			return pago;
		}
		// EntidadPago <- DTOPagoReq
		public EntidadPago actualizarParcialEntidad(EntidadPago pago, Map<String, Object> campos) {
			campos.forEach((clave, valor) -> {
				switch(clave) {
					case "monto": 
						pago.setMonto((double) valor);
						break;
					case "fecha":
						pago.setFecha(LocalDateTime.parse((String) valor));
						break;
					case "modoPago":
						pago.setModoPago((String) valor);
						break;
				}
				// the comments don't be updated here
			});
			return pago;
		}
}
