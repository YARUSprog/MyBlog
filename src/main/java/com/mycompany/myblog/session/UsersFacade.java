
package com.mycompany.myblog.session;

import com.mycompany.myblog.entity.Users;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author YARUS
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> {
    @PersistenceContext(unitName = "com.mycompany_MyBlog_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }
    
    public Users save(Users user) {
        if (user.getLogin() == null) {
            em.persist(user);
        } else {
            em.merge(user);
        }        
        return user;
    }
    
    public void delete(Users user) {
        Users mergedUser = em.merge(user);
        em.remove(mergedUser);        
    }
    
}
