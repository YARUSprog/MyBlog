
package com.mycompany.myblog.session;

import com.mycompany.myblog.entity.Groupuser;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author YARUS
 */
@Stateless
public class GroupuserFacade extends AbstractFacade<Groupuser> {
    @PersistenceContext(unitName = "com.mycompany_MyBlog_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GroupuserFacade() {
        super(Groupuser.class);
    }
    
    public List<Groupuser> findByName(String name){
        List resultList = null;
        if(name!=null){
           resultList = em.createNamedQuery("Groupuser.findByName").setParameter("name", name).getResultList();
        }    
        return resultList;        
    }
    
}
