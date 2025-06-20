// DTOs/response/ZonaComboDTO.java
package org.ncapas.canchitas.DTOs.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ZonaComboDTO {
    private Integer id;
    private String nombre;   // “San Salvador – Centro”, por ejemplo
}
