package com.luistahuite.user.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Schema(name = "Phone", description = "Model represents a Phone.")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "CHAR(36)")
    @Schema(name = "id", required = true, example = "979942b2-8697-48c8-b231-62f0722300ef", defaultValue = "", description = "Id de teléfono")
    private UUID id;
    @Schema(name = "number", required = true, example = "1234567", defaultValue = "", description = "Número de teléfono")
    private Integer number;
    @Schema(name = "citycode", required = true, example = "1", defaultValue = "", description = "Código de área del teléfono")
    private Integer citycode;
    @Schema(name = "contrycode", required = true, example = "502", defaultValue = "", description = "Código de ciudad del teléfono")
    private Integer contrycode;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,targetEntity = User.class)
    @JoinColumn(name = "userId", nullable = true)
    private User user;
}
