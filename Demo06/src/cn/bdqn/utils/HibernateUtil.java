package cn.bdqn.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static Configuration configuration;
    private final static SessionFactory sessionFactory;
    
    static {
        try {
            configuration = new Configuration().configure();
            sessionFactory = configuration.buildSessionFactory();
/*            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("虚拟机关闭!释放资源");
                    sessionFactory.close();
                }
            }));*/
        } catch (HibernateException ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    private HibernateUtil() {}

    public static Session currentSession() {
        return sessionFactory.getCurrentSession();
    }
}
