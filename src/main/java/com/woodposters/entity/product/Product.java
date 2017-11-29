package com.woodposters.entity.product;


import com.woodposters.entity.category.Category;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.ngram.NGramFilterFactory;
import org.apache.lucene.analysis.ru.RussianLightStemFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.annotations.Cascade;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
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

    @Column(name="type")
    @Field(analyzer=@Analyzer(definition="treewords"))
    private String type;

    @Column(name="status")
    private short status;

    @OneToMany(mappedBy="product")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @IndexedEmbedded
    private Set<ProductName> productNames;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "product_category",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    Set<Category> categories;

    public Product(){}

    public Product(double price, String type, short status, Set<ProductName> productNames, Set<Category> categories) {
        this.price = price;
        this.type = type;
        this.status = status;
        this.productNames = productNames;
        this.categories = categories;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
