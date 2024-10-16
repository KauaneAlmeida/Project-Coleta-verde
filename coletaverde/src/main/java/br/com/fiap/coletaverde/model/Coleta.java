package br.com.fiap.coletaverde.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tb_coleta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Coleta implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_COLETA")
    @SequenceGenerator(name = "SEQ_COLETA", sequenceName = "SEQ_COLETA", allocationSize = 1)
    @Column(name = "id")
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rota_id", referencedColumnName = "id")
    private Rota rotaID;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "caminhao_id", referencedColumnName = "id")
    private Caminhao caminhaoID;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco enderecoID;

    @Column(name = "previsao_horario")
    private Date previsaoHorario;

    @Column(name = "created_at")
    private Date createdAt = new Date();

    @Column(name = "updated_at")
    private Date updatedAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "tb_coleta_tipo_residuo",
    joinColumns = { @JoinColumn(name = "coleta_id", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "tipo_residuo_id", referencedColumnName = "id")})
    private TipoResiduo tipoResiduo;
}
