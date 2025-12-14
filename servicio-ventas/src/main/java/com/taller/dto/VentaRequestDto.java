package com.taller.dto;

import lombok.Data;
import java.util.List;

@Data
public class VentaRequestDto {
    private Long idUsuario;
    private List<ItemVentaDto> items;
}