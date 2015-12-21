package com.zhaosen.util;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;

public class AddScalar {
    public AddScalar() {
    }

    public static <T> void addSclar(SQLQuery sqlQuery, Class<T> clazz) {
        if(clazz == null) {
            throw new NullPointerException("[clazz] could not be null!");
        } else {
            Field[] fields = clazz.getDeclaredFields();
            Field[] var6 = fields;
            int var5 = fields.length;

            for(int var4 = 0; var4 < var5; ++var4) {
                Field field = var6[var4];
                if(field.getType() != Long.TYPE && field.getType() != Long.class) {
                    if(field.getType() != Integer.TYPE && field.getType() != Integer.class) {
                        if(field.getType() != Character.TYPE && field.getType() != Character.class) {
                            if(field.getType() != Short.TYPE && field.getType() != Short.class) {
                                if(field.getType() != Double.TYPE && field.getType() != Double.class) {
                                    if(field.getType() != Float.TYPE && field.getType() != Float.class) {
                                        if(field.getType() != Boolean.TYPE && field.getType() != Boolean.class) {
                                            if(field.getType() == String.class) {
                                                sqlQuery.addScalar(field.getName(), Hibernate.STRING);
                                            } else if(field.getType() == Date.class) {
                                                sqlQuery.addScalar(field.getName(), Hibernate.TIMESTAMP);
                                            }
                                        } else {
                                            sqlQuery.addScalar(field.getName(), Hibernate.BOOLEAN);
                                        }
                                    } else {
                                        sqlQuery.addScalar(field.getName(), Hibernate.FLOAT);
                                    }
                                } else {
                                    sqlQuery.addScalar(field.getName(), Hibernate.DOUBLE);
                                }
                            } else {
                                sqlQuery.addScalar(field.getName(), Hibernate.SHORT);
                            }
                        } else {
                            sqlQuery.addScalar(field.getName(), Hibernate.CHARACTER);
                        }
                    } else {
                        sqlQuery.addScalar(field.getName(), Hibernate.INTEGER);
                    }
                } else {
                    sqlQuery.addScalar(field.getName(), Hibernate.LONG);
                }
            }

        }
    }

    @SuppressWarnings("rawtypes")
	public static <T> void addSclar(SQLQuery sqlQuery, Class<T> clazz, List<String> fieldList) {
        if(clazz == null) {
            throw new NullPointerException("[clazz] could not be null!");
        } else {
            if(fieldList != null && fieldList.size() > 0) {
                Field[] fields = clazz.getDeclaredFields();
                Iterator var5 = fieldList.iterator();

                while(var5.hasNext()) {
                    String fieldName = (String)var5.next();
                    Field[] var9 = fields;
                    int var8 = fields.length;

                    for(int var7 = 0; var7 < var8; ++var7) {
                        Field field = var9[var7];
                        if(fieldName.equals(field.getName())) {
                            if(field.getType() != Long.TYPE && field.getType() != Long.class) {
                                if(field.getType() != Integer.TYPE && field.getType() != Integer.class) {
                                    if(field.getType() != Character.TYPE && field.getType() != Character.class) {
                                        if(field.getType() != Short.TYPE && field.getType() != Short.class) {
                                            if(field.getType() != Double.TYPE && field.getType() != Double.class) {
                                                if(field.getType() != Float.TYPE && field.getType() != Float.class) {
                                                    if(field.getType() != Boolean.TYPE && field.getType() != Boolean.class) {
                                                        if(field.getType() == String.class) {
                                                            sqlQuery.addScalar(field.getName(), Hibernate.STRING);
                                                        } else if(field.getType() == Date.class) {
                                                            sqlQuery.addScalar(field.getName(), Hibernate.TIMESTAMP);
                                                        }
                                                    } else {
                                                        sqlQuery.addScalar(field.getName(), Hibernate.BOOLEAN);
                                                    }
                                                } else {
                                                    sqlQuery.addScalar(field.getName(), Hibernate.FLOAT);
                                                }
                                            } else {
                                                sqlQuery.addScalar(field.getName(), Hibernate.DOUBLE);
                                            }
                                        } else {
                                            sqlQuery.addScalar(field.getName(), Hibernate.SHORT);
                                        }
                                    } else {
                                        sqlQuery.addScalar(field.getName(), Hibernate.CHARACTER);
                                    }
                                } else {
                                    sqlQuery.addScalar(field.getName(), Hibernate.INTEGER);
                                }
                            } else {
                                sqlQuery.addScalar(field.getName(), Hibernate.LONG);
                            }
                        }
                    }
                }
            }

        }
    }
}
