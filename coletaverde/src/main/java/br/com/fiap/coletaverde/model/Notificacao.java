package br.com.fiap.coletaverde.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tb_notificacoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class Notificacao implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE, generator = "SEQ_NOTIFICACAO"
    )
    @SequenceGenerator(name = "SEQ_NOTIFICACAO",
            sequenceName = "SEQ_NOTIFICACAO", allocationSize = 1)
    @Column(name = "id")
    private long id;

    @Column(name = "destinatario")
    @Size(max = 100)
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    private String destinatario;

    @NotBlank
    @Size(max = 100)
    @Column(name = "assunto")
    private String assunto;

    @NotBlank
    @Size(max = 4000)
    @Column(name = "mensagem")
    private String mensagem;

    @Column(name = "data_envio")
    private Date dataEnvio = new Date();
}
