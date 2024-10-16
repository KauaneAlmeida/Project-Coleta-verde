package br.com.fiap.coletaverde.dto;

import java.util.Date;

public record CaminhaoCadastroDTO(Long id,
                                  String placa,
                                  String coordenadaX,
                                  String coordenadaY,
                                  Date createdAt,
                                  Date updatedAt) {
}
