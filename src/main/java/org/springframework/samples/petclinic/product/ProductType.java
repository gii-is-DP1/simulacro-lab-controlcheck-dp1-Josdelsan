package org.springframework.samples.petclinic.product;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ProductType extends BaseEntity{
    
    @NotNull
    @Size(min = 3, max = 50)
    @Column(unique = true)
    String name;

    @OneToMany(mappedBy = "productType")
    Set<Product> products;
}
