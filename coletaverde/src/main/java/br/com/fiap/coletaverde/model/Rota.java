package br.com.fiap.coletaverde.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tb_rota")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class Rota implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE, generator = "SEQ_ROTA"
    )
    @SequenceGenerator(name = "SEQ_ROTA",
            sequenceName = "SEQ_ROTA", allocationSize = 1)
    @Column(name = "id")
    private long id;

    @Column(name = "nome")
    @Size(max = 50)
    private String nome;

    @Column(name = "created_at")
    private Date createdAt = new Date();

    @Column(name = "updated_at")
    private Date updatedAt;

    @OneToOne(mappedBy = "rota")
    private Endereco endereco;
}
