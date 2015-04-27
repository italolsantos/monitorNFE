/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.monitor.entity;
import java.util.TreeMap;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class HibernateUtil 
{
	private static SessionFactory factory;
	private static TreeMap<Long, Session> sessionMap = new TreeMap<Long, Session>();

	static 
	{
		Configuration configuration = new AnnotationConfiguration();
		configuration.configure("hibernate.cfg.xml"); // config do hibernate no folder src do projeto
		factory = configuration.buildSessionFactory();
	}

	public synchronized static void destroy() 
	{
		Session session = sessionMap.get(Thread.currentThread().getId());

		if (session != null && session.isOpen()) 
		{
			session.close();
		}
		
		sessionMap.remove(Thread.currentThread().getId());
	}

	public synchronized static Session openSession() 
	{
		Session session = sessionMap.get(Thread.currentThread().getId());

		if (session == null || !session.isOpen())
		{
			session = factory.openSession();
			sessionMap.put(Thread.currentThread().getId(), session);
		}

		return session;
	}
}