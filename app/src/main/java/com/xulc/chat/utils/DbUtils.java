package com.xulc.chat.utils;


import com.xulc.chat.bean.OrderByMy;
import com.xulc.chat.table.TableChat;

import org.xutils.DbManager;
import org.xutils.common.util.KeyValue;
import org.xutils.db.Selector;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 徐椋春 on 2016/8/20.
 */

public class DbUtils {
    private static DbUtils utils;
    private static DbManager db;
    private long curMaxId;
    public static synchronized DbUtils getInstance(){
        if (utils==null){
            utils = new DbUtils();
        }
        return utils;
    }

    public long getCurMaxId() {
        if (curMaxId==0){
            List<TableChat> chats = DbUtils.getInstance().findAll(TableChat.class);
            if (chats.size()>0){
                curMaxId = chats.get(chats.size()-1).getId();
            }else {
                curMaxId = 0;
            }

        }
        return curMaxId;
    }

    public void setCurMaxId(long curMaxId) {
        this.curMaxId = curMaxId;
    }

    /**
     * 通过主键查询对象
     * @param entityType
     * @param idValue
     * @param <T>
     * @return
     */
    public <T> T findById(Class<T> entityType, Object idValue){
        T t = null;
        try {
            if (db == null) {
                DbManager.DaoConfig daoConfig = new DbManager.DaoConfig().setDbName("asset.db").setDbVersion(1);
                db = x.getDb(daoConfig);
            }
            t = db.findById(entityType,idValue);
        } catch (DbException e) {
            e.printStackTrace();
        }finally {
            return t;
        }
    }

    /**
     * 条件查找
     * @param entityType
     * @param columnName
     * @param op
     * @param value
     * @param <T>
     * @return
     */
    public <T> List<T> findByWhere(Class<T> entityType, String columnName, String op, Object value){
        List<T> list = null;
        try {
            if (db == null) {
                DbManager.DaoConfig daoConfig = new DbManager.DaoConfig().setDbName("asset.db").setDbVersion(1);
                db = x.getDb(daoConfig);
            }
            list = db.selector(entityType).where(columnName,op,value).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }finally {
            return list==null?new ArrayList<T>():list;
        }

    }

    /**
     * 单列排序条件查询
     * @param entityType
     * @param pageIndex
     * @param pageSize
     * @param columnName
     * @param desc
     * @param <T>
     * @return
     */
    public  <T> List<T> findAllByPageByOrderByWhere(Class<T> entityType, int pageIndex, int pageSize, String columnName, boolean desc,WhereBuilder builder){
        List<T> list = null;
        try {
            if (db == null) {
                DbManager.DaoConfig daoConfig = new DbManager.DaoConfig().setDbName("asset.db").setDbVersion(1);
                db = x.getDb(daoConfig);
            }
            Selector<T> mList = db.selector(entityType).where(builder);
            list = mList.orderBy(columnName,desc).limit(pageSize).offset(pageIndex * pageSize).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }finally {
            return list==null?new ArrayList<T>():list;
        }

    }

    /**
     * 查询表中全部数据
     * @param entityType
     * @param <T>
     * @return
     */
    public  <T> List<T> findAll(Class<T> entityType){
        List<T> list = null;
        try {
            if (db == null) {
                DbManager.DaoConfig daoConfig = new DbManager.DaoConfig().setDbName("asset.db").setDbVersion(1);
                db = x.getDb(daoConfig);
            }
            list = db.selector(entityType).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }finally {
            return list==null?new ArrayList<T>():list;
        }

    }

    /**
     * 分页查询
     * @param entityType
     * @param pageIndex 第几页
     * @param pageSize 页码大小
     * @param <T>
     * @return
     */
    public  <T> List<T> findAllByPage(Class<T> entityType,int pageIndex,int pageSize){
        List<T> list = null;
        try {
            if (db == null) {
                DbManager.DaoConfig daoConfig = new DbManager.DaoConfig().setDbName("asset.db").setDbVersion(1);
                db = x.getDb(daoConfig);
            }
            list = db.selector(entityType).limit(pageSize).offset(pageIndex * pageSize).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }finally {
            return list==null?new ArrayList<T>():list;
        }

    }

    /**
     * 单列排序查询
     * @param entityType
     * @param pageIndex
     * @param pageSize
     * @param columnName
     * @param desc true倒序
     * @param <T>
     * @return
     */
    public  <T> List<T> findAllByPageByOrder(Class<T> entityType, int pageIndex, int pageSize, String columnName, boolean desc){
        List<T> list = null;
        try {
            if (db == null) {
                DbManager.DaoConfig daoConfig = new DbManager.DaoConfig().setDbName("asset.db").setDbVersion(1);
                db = x.getDb(daoConfig);
            }
            list = db.selector(entityType).orderBy(columnName, desc).limit(pageSize).offset(pageIndex*pageSize).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }finally {
            return list==null?new ArrayList<T>():list;
        }

    }

    /**
     * 按列排序查询
     * @param entityType
     * @param pageIndex
     * @param pageSize
     * @param orderBies 需要排序的列 分第一排序 第二排序。。。
     * @param <T>
     * @return
     */
    public  <T> List<T> findAllByPageByOrders(Class<T> entityType, int pageIndex, int pageSize, OrderByMy... orderBies){
        List<T> list = null;
        try {
            if (db == null) {
                DbManager.DaoConfig daoConfig = new DbManager.DaoConfig().setDbName("asset.db").setDbVersion(1);
                db = x.getDb(daoConfig);
            }
            Selector<T> a = db.selector(entityType);
            for (OrderByMy orderBy:orderBies){
                a = a.orderBy(orderBy.getColumnName(),orderBy.isDesc());
            }
            list = a.limit(pageSize).offset(pageIndex*pageSize).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }finally {
            return list==null?new ArrayList<T>():list;
        }

    }

    /**
     * 保存一条数据
     * @param entity
     */
    public boolean save(Object entity){
        try {
            if (db == null){
                DbManager.DaoConfig daoConfig = new DbManager.DaoConfig().setDbName("asset.db").setDbVersion(1);
                db = x.getDb(daoConfig);
            }
            db.save(entity);
            return true;
        } catch (DbException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除表中一条数据
     * @param entity
     */
    public void delete(Object entity){
        try {
            if (db == null){
                DbManager.DaoConfig daoConfig = new DbManager.DaoConfig().setDbName("asset.db").setDbVersion(1);
                db = x.getDb(daoConfig);
            }
            db.delete(entity);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除表中所有数据
     * @param entityType
     */
    public void deleteAll(Class<?> entityType){
        try {
            if (db == null){
                DbManager.DaoConfig daoConfig = new DbManager.DaoConfig().setDbName("asset.db").setDbVersion(1);
                db = x.getDb(daoConfig);
            }
            db.delete(entityType);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 按条件删除
     * @param entityType
     * @param columnName
     * @param op
     * @param value
     */
    public void deleteByWhere(Class<?> entityType, String columnName, String op, Object value){
        try {
            if (db == null){
                DbManager.DaoConfig daoConfig = new DbManager.DaoConfig().setDbName("asset.db").setDbVersion(1);
                db = x.getDb(daoConfig);
            }
            db.delete(entityType, WhereBuilder.b(columnName, op, value));
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新表中数据
     * @param entityType
     * @param columnName 条件列名
     * @param op 条件运算符
     * @param value 条件值
     * @param nameValuePairs 需要更新的KeyValue数组
     */
    public void updateByWhere(Class<?> entityType, String columnName, String op, Object value, KeyValue... nameValuePairs){
        try {
            if (db == null){
                DbManager.DaoConfig daoConfig = new DbManager.DaoConfig().setDbName("asset.db").setDbVersion(1);
                db = x.getDb(daoConfig);
            }
            db.update(entityType, WhereBuilder.b(columnName, op, value), nameValuePairs);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据主键更新表数据
     * @param entityType
     * @param idValue
     * @param nameValuePairs
     */
    public void updateById(Class<?> entityType, Object idValue, KeyValue... nameValuePairs){
        try {
            if (db == null) {
                DbManager.DaoConfig daoConfig = new DbManager.DaoConfig().setDbName("asset.db").setDbVersion(1);
                db = x.getDb(daoConfig);
            }
            db.update(entityType, WhereBuilder.b("id", "=", idValue), nameValuePairs);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}
