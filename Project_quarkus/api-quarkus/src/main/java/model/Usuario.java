package model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue
    private Integer usuario_id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "correo_electronico")
    private String email;


}
