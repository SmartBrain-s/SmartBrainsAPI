package br.com.smartbrains.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "movimentacao", schema = "public")
public class Movimentacao {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuarios id_usuario;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tipomovimentacao", nullable = false)
    private TipoMovimentacao id_tipomovimentacao;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    @NotNull
    @Column(name = "valor", precision = 11, scale = 2, nullable = false)
    private BigDecimal valor;

    @Column(name = "data_criacao")
    private Date dataCriacao;

}