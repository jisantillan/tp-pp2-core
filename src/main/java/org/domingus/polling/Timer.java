package org.domingus.polling;

import java.util.HashSet;
import java.util.Set;

import org.domingus.interfaces.Observable;
import org.domingus.interfaces.Observer;

public class Timer implements Observable {
    private Integer timeInterval;
    private Set<Observer> observers;


    public Timer(Integer timeInterval){
    	observers = new HashSet<>();
        this.timeInterval = timeInterval;
    }

    public void start() throws InterruptedException {
        while (true) {
            Thread.sleep(this.timeInterval);
            notifyObservers(null);
        }
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Object arg) {
        observers.forEach((observer -> observer.update(null)));
    }

}
