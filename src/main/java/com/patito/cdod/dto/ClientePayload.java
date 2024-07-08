package com.patito.cdod.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientePayload {
    @Email(message = "*Porfavor ingresa un correo Valido")
    @NotEmpty(message = "*Porfavor ingresa un correo")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String email;
    @NotBlank
    @NotEmpty(message = "*Porfavor ingresa una contraseña")
    private String password;
    @NotEmpty(message = "*Porfavor ingresa tu nombre completo")
    private String nombre;
    @NotEmpty(message = "*Porfavor ingresa tu direccion")
    private String direccion;
    @NotEmpty(message = "*Porfavor ingresa un telefono")
    private String telefono;
}
