package com.zhaosen.base;

import com.zhaosen.util.AddScalar;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class BaseDao<T> {
    private Class<T> entityClass;
    @Autowired
    private HibernateTemplate hibernateTemplate;

	public BaseDao() {
        Type genType = this.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
        this.entityClass = (Class)params[0];
    }

    public T load(Serializable id) {
        return this.getHibernateTemplate().load(this.entityClass, id);
    }

    public T get(Serializable id) {
        return this.getHibernateTemplate().get(this.entityClass, id);
    }

    public List<T> loadAll() {
        return this.getHibernateTemplate().loadAll(this.entityClass);
    }

    public void deleteAll(Collection<?> entities) {
        if(entities != null) {
            this.getHibernateTemplate().deleteAll(entities);
        }

    }

    public void realDel(T entity) {
        this.getHibernateTemplate().delete(entity);
    }

    public String save(T entity) {
        return this.getHibernateTemplate().save(entity).toString();
    }

    public void saveOrUpdate(T entity) {
        this.getHibernateTemplate().saveOrUpdate(entity);
    }

    public void saveOrUpdateAll(List<T> entities) {
        this.getHibernateTemplate().saveOrUpdateAll(entities);
    }

    public void update(T entity) {
        this.getHibernateTemplate().update(entity);
    }

    public int bulkUpdate(String hql) {
        return this.getHibernateTemplate().bulkUpdate(hql);
    }

    public int bulkUpdate(String hql, Object[] values) {
        return this.getHibernateTemplate().bulkUpdate(hql, values);
    }

    public List<T> find(String hql) {
        return this.getHibernateTemplate().find(hql);
    }

    public List find(String hql, Object[] params) {
        return this.getHibernateTemplate().find(hql, params);
    }

    public List find(final String hql, final List<Object> params) {
        return (List)this.getHibernateTemplate().execute(new HibernateCallback() {
            public List<?> doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                if(params != null) {
                    for(int i = 0; i < params.size(); ++i) {
                        query.setParameter(i, params.get(i));
                    }
                }

                return query.list();
            }
        });
    }

    public HibernateTemplate getHibernateTemplate() {
        return this.hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public Session getSession() {
        return SessionFactoryUtils.getSession(this.hibernateTemplate.getSessionFactory(), true);
    }

    public List<?> getHQLPageList(final String hql, final List<Object> values, final int firstResult, final int maxResults) {
        return (List)this.getHibernateTemplate().execute(new HibernateCallback() {
            public List<?> doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                if(values != null) {
                    for(int i = 0; i < values.size(); ++i) {
                        query.setParameter(i, values.get(i));
                    }
                }

                return query.setFirstResult(firstResult).setMaxResults(maxResults).list();
            }
        });
    }

    public Integer getHQLCount(final String hql, final List<Object> values) {
        return (Integer)this.getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(hql);
                if(values != null) {
                    for(int i = 0; i < values.size(); ++i) {
                        query.setParameter(i, values.get(i));
                    }
                }

                return Integer.valueOf(Integer.parseInt(query.uniqueResult().toString()));
            }
        });
    }

    public Object getHQLSum(final String hql, final List<Object> values) {
        return this.getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(hql);
                if(values != null) {
                    for(int i = 0; i < values.size(); ++i) {
                        query.setParameter(i, values.get(i));
                    }
                }

                return query.uniqueResult();
            }
        });
    }

    public List<?> getSQLPageList(final String sql, final List<Object> values, final Class<?> beanClass, final List<String> fieldList, final int firstResult, final int maxResults) {
        return (List)this.getHibernateTemplate().execute(new HibernateCallback() {
            public List<?> doInHibernate(Session session) throws HibernateException {
                SQLQuery query = session.createSQLQuery(sql);
                query.setCacheable(false);
                if(fieldList != null) {
                    AddScalar.addSclar(query, beanClass, fieldList);
                } else {
                    AddScalar.addSclar(query, beanClass);
                }

                if(beanClass != null) {
                    query.setResultTransformer(Transformers.aliasToBean(beanClass));
                }

                if(values != null) {
                    for(int i = 0; i < values.size(); ++i) {
                        query.setParameter(i, values.get(i));
                    }
                }

                return query.setFirstResult(firstResult).setMaxResults(maxResults).list();
            }
        });
    }

    public List<?> getSQLPageList(final String sql, final List<Object> values, final int firstResult, final int maxResults) {
        return (List)this.getHibernateTemplate().execute(new HibernateCallback() {
            public List<?> doInHibernate(Session session) throws HibernateException {
                SQLQuery query = session.createSQLQuery(sql);
                query.setCacheable(false);
                if(values != null) {
                    for(int i = 0; i < values.size(); ++i) {
                        query.setParameter(i, values.get(i));
                    }
                }

                return query.setFirstResult(firstResult).setMaxResults(maxResults).list();
            }
        });
    }

    public Integer getSQLCount(final String sql, final List<Object> values) {
        return (Integer)this.getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                SQLQuery query = session.createSQLQuery(sql);
                query.setCacheable(false);
                if(values != null) {
                    for(int i = 0; i < values.size(); ++i) {
                        query.setParameter(i, values.get(i));
                    }
                }

                return Integer.valueOf(Integer.parseInt(query.uniqueResult().toString()));
            }
        });
    }

    public List<?> findBySQL(final String sql, final List<Object> values, final Class<?> beanClass, final List<String> fieldList) {
        return (List)this.getHibernateTemplate().execute(new HibernateCallback() {
            public List<?> doInHibernate(Session session) throws HibernateException {
                SQLQuery query = session.createSQLQuery(sql);
                query.setCacheable(false);
                if(fieldList != null) {
                    AddScalar.addSclar(query, beanClass, fieldList);
                } else {
                    AddScalar.addSclar(query, beanClass);
                }

                if(beanClass != null) {
                    query.setResultTransformer(Transformers.aliasToBean(beanClass));
                }

                if(values != null) {
                    for(int i = 0; i < values.size(); ++i) {
                        query.setParameter(i, values.get(i));
                    }
                }

                return query.list();
            }
        });
    }

    public List<?> findBySQL(final String sql, final List<Object> values) {
        return (List)this.getHibernateTemplate().execute(new HibernateCallback() {
            public List<?> doInHibernate(Session session) throws HibernateException {
                SQLQuery query = session.createSQLQuery(sql);
                query.setCacheable(false);
                if(values != null) {
                    for(int i = 0; i < values.size(); ++i) {
                        query.setParameter(i, values.get(i));
                    }
                }

                return query.list();
            }
        });
    }

    public List<?> getHQLList(final String hql, final List<Object> values) {
        return (List)this.getHibernateTemplate().execute(new HibernateCallback() {
            public List<?> doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                if(values != null) {
                    for(int i = 0; i < values.size(); ++i) {
                        query.setParameter(i, values.get(i));
                    }
                }

                return query.list();
            }
        });
    }

    public int executeSQL(final String sql, final List<Object> values) {
        return ((Integer)this.getHibernateTemplate().execute(new HibernateCallback() {
            public Integer doInHibernate(Session session) throws HibernateException {
                SQLQuery query = session.createSQLQuery(sql);
                query.setCacheable(false);
                if(values != null) {
                    for(int i = 0; i < values.size(); ++i) {
                        query.setParameter(i, values.get(i));
                    }
                }

                return Integer.valueOf(query.executeUpdate());
            }
        })).intValue();
    }

    public int executeSQL(final String sql, final Object[] values) {
        return ((Integer)this.getHibernateTemplate().execute(new HibernateCallback() {
            public Integer doInHibernate(Session session) throws HibernateException {
                SQLQuery query = session.createSQLQuery(sql);
                query.setCacheable(false);
                if(values != null) {
                    for(int i = 0; i < values.length; ++i) {
                        query.setParameter(i, values[i]);
                    }
                }

                return Integer.valueOf(query.executeUpdate());
            }
        })).intValue();
    }
}
