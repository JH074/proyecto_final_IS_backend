package org.ncapas.canchitas.DTOs.response;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MotivoRechazoDTO {

    @Size(max = 200, message = "El motivo no puede superar los 200 caracteres")
    private String motivo;
}
