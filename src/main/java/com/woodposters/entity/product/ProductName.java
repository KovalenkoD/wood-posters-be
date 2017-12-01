package com.woodposters.entity.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.woodposters.beans.Locale;
import com.woodposters.entity.LocaleName;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.ngram.NGramFilterFactory;
import org.apache.lucene.analysis.ru.RussianLightStemFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="product_name")
@AnalyzerDefs({
        @AnalyzerDef(name = "treewordsname",
                tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
                filters = {
                        @TokenFilterDef(factory = LowerCaseFilterFactory.class),
                        @TokenFilterDef(factory = RussianLightStemFilterFactory.class),
                        @TokenFilterDef(factory = NGramFilterFactory.class,
                                params = {@org.hibernate.search.annotations.Parameter(name = "minGramSize", value = "3"),
                                        @org.hibernate.search.annotations.Parameter(name = "maxGramSize", value = "3")
                                })
                })
})
public class ProductName implements LocaleName, Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="name")
    @Field(analyzer=@Analyzer(definition="treewordsname"))
    private String name;

    @Column(name="locale")
    @Enumerated(EnumType.STRING)
    private Locale locale;

    @ManyToOne
    @JoinColumn(name="product_id", nullable=false)
    @JsonIgnore
    private Product product;

    public ProductName(){}

    public ProductName(String name, Locale locale, Product product){
        this.name=name;
        this.locale=locale;
        this.product=product;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
