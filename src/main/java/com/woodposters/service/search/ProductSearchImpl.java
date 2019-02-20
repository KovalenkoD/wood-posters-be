package com.woodposters.service.search;

import com.woodposters.entity.product.Product;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.PhraseTermination;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Service
public class ProductSearchImpl implements  ProductSearch {
    @Autowired
    private EntityManager entityManager;

    @PostConstruct
    public void initializeHibernateSearch() {

        try {
            FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    @Override
    public List<Product> fullSearch(String searchTerm) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Product.class).get();

        BooleanQuery.Builder builder = new BooleanQuery.Builder();

        builder.add(queryBuilder.keyword()
                .wildcard()
                .onFields("productNames.name")
                .matching(String.format("*%s*", StringUtils.lowerCase(searchTerm)))
                .createQuery(), BooleanClause.Occur.MUST);

        Query query = builder.build();

        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query, Product.class);

        /*FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Product.class).get();

        PhraseTermination phraseTermination = queryBuilder.phrase().onField("productNames.name").sentence(searchTerm);
        Query luceneQuery = phraseTermination.createQuery();
        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Product.class);*/

        // execute search

        List<Product> articleList = null;
        try {
            articleList = fullTextQuery.getResultList();
        } catch (NoResultException nre) {
            System.out.println(nre);

        }

        return articleList;


    }
}
