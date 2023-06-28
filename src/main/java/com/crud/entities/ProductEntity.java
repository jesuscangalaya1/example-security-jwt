package com.crud.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@Table(name = "Producto")
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE Producto SET deleted = true WHERE id=?")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

    @Column(columnDefinition = "BOOLEAN NOT NULL DEFAULT '0'")
    private boolean deleted;


    @Lob
    @JsonIgnore
    private byte[] image;
    @Transient
    private Integer imageHashCode;

    public Integer getImageHashCode(){
        return (this.image != null) ? Arrays.hashCode(this.image) : null;
    }



}
