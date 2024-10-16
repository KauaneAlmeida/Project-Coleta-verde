package br.com.fiap.coletaverde.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tb_tipo_residuo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TipoResiduo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TIPORESIDUO")
    @SequenceGenerator(name = "SEQ_TIPORESIDUO", sequenceName = "SEQ_TIPORESIDUO", allocationSize = 1)
    @Column(name = "id")
    private long id;

    @Column(name = "nome")
    @Size(max = 30)
    private String nome;

    @Column(name = "descricao")
    @Size(max = 50)
    private String descricao;

    @Column(name = "created_at")
    private Date createdAt = new Date();

    @Column(name = "updated_at")
    private Date updatedAt;

    @OneToOne(mappedBy = "tipoResiduo")
    private Coleta coleta;
}
