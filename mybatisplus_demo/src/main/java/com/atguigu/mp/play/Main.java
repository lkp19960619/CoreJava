package com.atguigu.mp.play;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

import java.util.function.Consumer;

/**
 * @Author: 李开鹏
 * @Date: 2020/5/23 18:21
 * @Version 1.0
 * @Description
 **/
public class Main extends Application {
    public static void main(String[] args) {
//        ConnectionDanMu cdm = new ConnectionDanMu();
//        cdm.setRequest_url("https://api.live.bilibili.com/xlive/web-room/v1/dM/gethistory");
//        cdm.setRoom_id("");
//        cdm.getConnection();
//        String data = cdm.getDanMuData();
//        ObservableList<DanMuDataFormat> danMuList = ConnectionDanMu.dataFormat(data);
//        danMuList.forEach(t-> System.out.println("用户："+t.getNickname()+"\t"+"弹幕："+t.getText()+"\t"+"时间： "+t.getTimeline()));
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ConnectionDanMu cdm = new ConnectionDanMu();
        cdm.setRequest_url("https://api.live.bilibili.com/xlive/web-room/v1/dM/gethistory");
        VBox root = new VBox();
        root.setStyle("-fx-background-color: #ffffff");
        HBox hBox = new HBox(10);
//        hBox.setStyle("-fx-background-color: #ff3");
        hBox.setAlignment(Pos.CENTER);
        hBox.setPrefHeight(40);
        Button getDanMu = new Button("获取弹幕");
        Button close = new Button("关闭连接");
        //文本域
        TextField roomId = new TextField();
        //文本的提示信息
        roomId.setPromptText("请输入B站直播房间号");
        //定义数据
        TableView<DanMuDataFormat> tableView = new TableView<>();
        //设置table的每一行的宽度是一样的
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setTableMenuButtonVisible(true);
        TableColumn<DanMuDataFormat, String> tc_name = new TableColumn<>("用户");
        TableColumn<DanMuDataFormat, String> tc_text = new TableColumn<>("弹幕");
        TableColumn<DanMuDataFormat, String> tc_time = new TableColumn<>("时间");
        tc_name.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DanMuDataFormat, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DanMuDataFormat, String> param) {
                SimpleStringProperty ssp = new SimpleStringProperty(param.getValue().getNickname());
                return ssp;
            }
        });
        tc_text.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DanMuDataFormat, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DanMuDataFormat, String> param) {
                SimpleStringProperty ssp = new SimpleStringProperty(param.getValue().getText());
                return ssp;
            }
        });
        tc_time.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DanMuDataFormat, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DanMuDataFormat, String> param) {
                SimpleStringProperty ssp = new SimpleStringProperty(param.getValue().getTimeline());
                return ssp;
            }
        });

        tableView.getColumns().add(tc_name);
        tableView.getColumns().add(tc_text);
        tableView.getColumns().add(tc_time);

        VBox.setVgrow(tableView, Priority.ALWAYS);
        hBox.getChildren().addAll(getDanMu,roomId,close);
        root.getChildren().addAll(hBox,tableView);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.setTitle("B站直播弹幕抓取工具-技术支持demo");
        primaryStage.show();
        primaryStage.setAlwaysOnTop(true);

        DanMuService dms = new DanMuService(cdm);
        dms.valueProperty().addListener(new ChangeListener<ObservableList<DanMuDataFormat>>() {
            @Override
            public void changed(ObservableValue<? extends ObservableList<DanMuDataFormat>> observable, ObservableList<DanMuDataFormat> oldValue, ObservableList<DanMuDataFormat> newValue) {
                if(newValue != null){
//                    newValue.forEach(new Consumer<DanMuDataFormat>() {
//                        @Override
//                        public void accept(DanMuDataFormat danMuDataFormat) {
//                            System.out.println("用户："+danMuDataFormat.getNickname()+"\t"+"弹幕："+danMuDataFormat.getText()+"\t"+"时间： "+danMuDataFormat.getTimeline());
//                        }
//                    });
                    tableView.setItems(newValue);
                    tableView.scrollTo(newValue.size()-1);
                }
            }
        });
        //为按钮绑定事件
        getDanMu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //判断文本域中内容是否为空
                if(roomId.equals("")==false){
                    cdm.setRoom_id(roomId.getText());
                    dms.setDelay(Duration.seconds(0));
                    dms.setPeriod(Duration.seconds(1));
                    dms.start();
                    //当点击了按钮之后设置这个按钮不可用
                    getDanMu.setDisable(true);
                    roomId.setDisable(true);
                }
            }
        });

        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cdm.close();
                dms.cancel();
                dms.reset();
                //断开链接之后，恢复按钮
                getDanMu.setDisable(false);
                roomId.setDisable(false);
            }
        });
    }
}
