package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "comentarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comentario {

    @Id
    @GeneratedValue
    private Integer comentario_id;
    @Column(name = "comentario")
    private String comentario;
    @Column(name = "fecha_comentario")
    private Date fecha;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "id_project")
    private Project project;


}

