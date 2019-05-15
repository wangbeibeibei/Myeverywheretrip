package com.example.bei_i_bei.everywheretrip.util;

import com.example.bei_i_bei.everywheretrip.base.BaseApp;
import com.example.bei_i_bei.everywheretrip.bean.FocusBean;
import com.example.bei_i_bei.everywheretrip.dao.DaoMaster;
import com.example.bei_i_bei.everywheretrip.dao.DaoSession;
import com.example.bei_i_bei.everywheretrip.dao.FocusBeanDao;

import java.util.List;

public class DButls {
    private static DButls dButls;
    private final FocusBeanDao focusBeanDao;

    private DButls() {

        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(BaseApp.getsBaseApp(), "wb.db");
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        focusBeanDao = daoSession.getFocusBeanDao();
    }

    public static DButls getdButls() {
        if (dButls == null) {
            synchronized (DButls.class) {
                if (dButls == null) {
                    dButls = new DButls();
                }
            }
        }
        return dButls;
    }


    //插入
    public void insertAll(FocusBean focusBean) {
        if (has(focusBean)) {
            return;
        }
        focusBeanDao.insertOrReplace(focusBean);
    }

    public List<FocusBean> queryAll() {
        return focusBeanDao.queryBuilder().list();
    }

    public boolean has(FocusBean focusBean) {
        List<FocusBean> list = focusBeanDao.queryBuilder().where(FocusBeanDao.Properties.Name.eq(focusBean.getName())).list();
        if (list != null && list.size() > 0) {
            return true;
        }
        return false;
    }

    public FocusBean getFocusBean(String name) {
        return focusBeanDao.queryBuilder().where(FocusBeanDao.Properties.Name.eq(name)).build().unique();
    }
}
