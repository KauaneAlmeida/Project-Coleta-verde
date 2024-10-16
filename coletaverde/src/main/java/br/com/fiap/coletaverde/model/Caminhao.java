package br.com.fiap.coletaverde.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name="tb_caminhao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Caminhao implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CAMINHAO")
    @SequenceGenerator(name = "SEQ_CAMINHAO", sequenceName = "SEQ_CAMINHAO", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 10, message = "Digite no maximo 10 caracteres")
    @Column(name = "placa")
    private String placa;

    @Column(name = "coordenada_x")
    private String coordenadaX;

    @Column(name = "coordenada_y")
    private String coordenadaY;

    @Column(name = "created_at")
    private Date createdAt = new Date();

    @Column(name = "updated_at")
    private Date updatedAt;
}
