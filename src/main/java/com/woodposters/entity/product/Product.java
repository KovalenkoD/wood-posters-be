package com.woodposters.entity.product;


import com.woodposters.entity.category.Category;
import com.woodposters.entity.material.Material;
import com.woodposters.entity.productType.ProductType;
import com.woodposters.entity.technology.Technology;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.ngram.NGramFilterFactory;
import org.apache.lucene.analysis.ru.RussianLightStemFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="product")
@Indexed
@AnalyzerDefs({
@AnalyzerDef(name = "treewords",
    tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
    filters = {
            @TokenFilterDef(factory = LowerCaseFilterFactory.class),
            @TokenFilterDef(factory = RussianLightStemFilterFactory.class),
            @TokenFilterDef(factory = NGramFilterFactory.class,
                    params = {@Parameter(name = "minGramSize", value = "3"),
                            @Parameter(name = "maxGramSize", value = "3")
                    })
    })
})
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name="discriminator",
        discriminatorType=DiscriminatorType.STRING
)
@DiscriminatorValue(value="P")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="product_id")
    private long id;

    @Column(name="price")
    private double price;

    @Column(name="size")
    private String size;

    @Column(name="image")
    private String image;

    @Column(name="status")
    private short status;

    @OneToMany(mappedBy="product")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @IndexedEmbedded
    private Set<ProductName> productNames;

    @OneToMany(mappedBy="product")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private Set<ProductDescription> productDescriptions;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "product_category",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    private Set<Category> categories;

    @ManyToOne(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "product_type_mapping",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_type_id")}
    )
    private ProductType productType;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "product_technology",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "technology_id")}
    )
    private Set<Technology> technologies;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "product_material",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "material_id")}
    )
    private Set<Material> materials;

    @Column(name="popular")
    private short popular;

    @Column(name="creation_date")
    @Type(type="date")
    private Date createdDate;

    public Product(){}

    public Product(double price, String size, short status, Set<ProductName> productNames, Set<ProductDescription> productDescriptions, Set<Category> categories, ProductType productType, Set<Technology> technologies, Set<Material> materials) {
        this.price = price;
        this.status = status;
        this.productNames = productNames;
        this.categories = categories;
        this.productType = productType;
        this.productDescriptions = productDescriptions;
        this.size = size;
        this.technologies = technologies;
        this.materials = materials;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<ProductName> getProductNames() {
        return productNames;
    }

    public void setProductNames(Set<ProductName> productNames) {
        this.productNames = productNames;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Set<ProductDescription> getProductDescriptions() {
        return productDescriptions;
    }

    public void setProductDescriptions(Set<ProductDescription> productDescriptions) {
        this.productDescriptions = productDescriptions;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Set<Technology> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(Set<Technology> technologies) {
        this.technologies = technologies;
    }

    public Set<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(Set<Material> materials) {
        this.materials = materials;
    }

    public short getPopular() {
        return popular;
    }

    public void setPopular(short popular) {
        this.popular = popular;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
