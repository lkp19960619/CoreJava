package com.atguigu.mp.play;

import javafx.collections.ObservableList;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;

/**
 * @Author: 李开鹏
 * @Date: 2020/5/23 23:06
 * @Version 1.0
 * @Description
 **/
public class DanMuService extends ScheduledService<ObservableList<DanMuDataFormat>> {
    private ConnectionDanMu cdm;

    public DanMuService(ConnectionDanMu cdm) {
        this.cdm = cdm;
    }

    @Override
    protected Task<ObservableList<DanMuDataFormat>> createTask() {
        Task<ObservableList<DanMuDataFormat>> task = new Task<ObservableList<DanMuDataFormat>>() {
            @Override
            protected ObservableList<DanMuDataFormat> call() throws Exception {
                //获取连接
                cdm.getConnection();
                //获取数据
                String data = cdm.getDanMuData();
                //解析数据
                ObservableList<DanMuDataFormat> list = ConnectionDanMu.dataFormat(data);
                return list;
            }
        };
        return task;
    }
}
