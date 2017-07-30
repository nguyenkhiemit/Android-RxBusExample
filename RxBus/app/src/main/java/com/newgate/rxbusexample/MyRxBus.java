package com.newgate.rxbusexample;


import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by khiemnd on 7/30/17.
 */

public class MyRxBus {

    private static MyRxBus rxBus = null;

    private final Subject<Object, Object> subject =
            new SerializedSubject<>(PublishSubject.create());

    public static MyRxBus instanceOf() {
        if (rxBus == null) {
            rxBus = new MyRxBus();
        }
        return rxBus;
    }

    public void setEvent(Object o) {
        subject.onNext(o);
    }

    public Observable<Object> getEvent() {
        return subject;
    }
}
