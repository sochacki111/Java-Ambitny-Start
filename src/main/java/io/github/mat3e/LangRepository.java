package io.github.mat3e;

import java.security.cert.Extension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class LangRepository {

    List<Lang> findAll() {
        var session = HibernateUtil.getSessionFactory().openSession();
        // begin transaction for read only
        var transaction = session.beginTransaction();

        List result = session.createQuery( "from Lang", Lang.class).list();

        transaction.commit();
        session.close();
        return result;
    }

    Optional<Lang> findById(Integer id) {
        var session = HibernateUtil.getSessionFactory().openSession();
        // begin transaction for read only
        var transaction = session.beginTransaction();
        var result = session.get(Lang.class, id);
        transaction.commit();
        session.close();
        return Optional.ofNullable(result);
    }
}
