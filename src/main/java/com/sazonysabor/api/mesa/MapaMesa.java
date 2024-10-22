package com.sazonysabor.api.mesa;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class MapaMesa {
	// EntidadMesa -> DTOMesaRes
	public DTOMesaRes obtenerRespuesta(EntidadMesa mesa) {
		if(mesa == null) return null;
		return new DTOMesaRes(
			mesa.getId(),
			mesa.getCapacidad(),
			mesa.isEstado()
		);
	}
	// DTOMesaReq -> EntidadMesa
	public EntidadMesa obtenerEntidad(DTOMesaReq req) {
		if(req == null) return null;
		return new EntidadMesa(
			null,
			req.getCapacidad(),
			req.isEstado()
		);
	}
	// EntidadMesa <= DTOMesaReq
	public EntidadMesa actualizarEntidad(EntidadMesa mesa, DTOMesaReq req) {
		mesa.setCapacidad(req.getCapacidad());
		mesa.setEstado(req.isEstado());
		return mesa;
	}
	// EntidadMesa <- DTOMesaReq
	public EntidadMesa actualizarParcialEntidad(EntidadMesa mesa, Map<String, Object> campos) {
		campos.forEach((clave, valor) -> {
			switch(clave) {
				case "capacidad":
					mesa.setCapacidad((int) valor);
					break;
				case "estado":
					mesa.setEstado((boolean) valor);
					break;
			}
		});
		return mesa;
	}
}
