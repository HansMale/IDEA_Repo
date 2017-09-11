package cn.bdqn.dao;

import org.hibernate.Session;

import cn.bdqn.utils.HibernateUtil;


public class BaseDao {
	public Session currentSession(){
		return HibernateUtil.currentSession();
	}
}
