
package com.mycompany.myblog.session;

import com.mycompany.myblog.entity.Contacts;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author YARUS
 */
@Stateless
public class ContactsFacade extends AbstractFacade<Contacts> {
    @PersistenceContext(unitName = "com.mycompany_MyBlog_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContactsFacade() {
        super(Contacts.class);
    }
    
    public List<Contacts> findByLogin(String login){
        List resultList = null;
        if(login!=null){
           resultList = em.createNamedQuery("Contacts.findByLogin").setParameter("login", login).getResultList();
        }    
        return resultList;        
    }
}
