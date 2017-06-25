
package com.mycompany.myblog.session;

import com.mycompany.myblog.entity.Articles;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author YARUS
 */
@Stateless
public class ArticlesFacade extends AbstractFacade<Articles> {
    @PersistenceContext(unitName = "com.mycompany_MyBlog_war_1.0-SNAPSHOTPU")
    private EntityManager em;
        
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArticlesFacade() {
        super(Articles.class);
    }
        
    public List<Articles> findByTheme(String theme){
        List resultList = null;
        if(theme!=null){
           resultList = em.createNamedQuery("Articles.findByTheme").setParameter("theme", theme).getResultList();
        }    
        return resultList;        
    }
    
    public List<String> findAllTheme(){         
        List<String> resultList = em.createNamedQuery("Articles.findAllTheme").getResultList();            
        return resultList;        
    }
    
    public Articles save(Articles article) {
        if (article.getId() == null) {
            em.persist(article);
        } else {
            em.merge(article);
        }        
        return article;
    }
    
    public void delete(Articles article) {
        Articles mergedArticle = em.merge(article);
        em.remove(mergedArticle);        
    }
}
