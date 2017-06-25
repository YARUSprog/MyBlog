
package com.mycompany.myblog.session;

import com.mycompany.myblog.entity.Messages;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author YARUS
 */
@Stateless
public class MessagesFacade extends AbstractFacade<Messages> {
    @PersistenceContext(unitName = "com.mycompany_MyBlog_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MessagesFacade() {
        super(Messages.class);
    }
    
}
