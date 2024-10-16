package br.com.fiap.coletaverde.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tb_endereco")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Endereco implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE, generator = "SEQ_ENDERECO"
    )
    @SequenceGenerator(name = "SEQ_ENDERECO",
            sequenceName = "SEQ_ENDERECO", allocationSize = 1)
    @Column(name = "id")
    private long id;

    @Column(name = "cep")
    @Size(max = 10)
    private String cep;

    @Column(name = "rua")
    @Size(max = 100)
    private String rua;

    @Column(name = "bairro")
    @Size(max = 50)
    private String bairro;

    @Column(name = "numero")
    @Size(max = 10)
    private int numero;

    @Column(name = "complemento")
    @Size(max = 30)
    private String complemento;

    @Column(name = "cidade")
    @Size(max = 30)
    private String cidade;

    @Column(name = "estado")
    @Size(max = 20)
    private String estado;

    @Column(name = "created_at")
    private Date createdAt = new Date();

    @Column(name = "updated_at")
    private Date updatedAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "tb_rota_endereco",
            joinColumns = { @JoinColumn(name = "endereco_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "rota_id", referencedColumnName = "id")})
    private Rota rota;

}
