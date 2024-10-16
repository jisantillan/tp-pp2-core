package org.domingus.init;

import org.domingus.app.*;
import org.domingus.interfaces.Observer;
import org.domingus.interfaces.Source;

import java.io.FileNotFoundException;
import java.util.Set;

import static java.util.Objects.nonNull;

public class DomingusFactory {

    private Discoverer discoverer;

    public DomingusFactory(){
        this.discoverer = new Discoverer();
    }

    public Domingus create(Source source, String extensionPath) throws FileNotFoundException {
        Set<Observer> observers = discoverer.discover(extensionPath);

        Domingus domingus = new Domingus();
        observers.forEach(domingus::addObserver);

        MessageAdapter messageAdapter = new MessageAdapter(domingus);
        ChangeDetector changeDetector = new ChangeDetector(messageAdapter);
        ClassroomAssignmentHistory classroomAssignmentHistory = new ClassroomAssignmentHistory(changeDetector);
        SourceListener sourceListener = new SourceListener(classroomAssignmentHistory);

        if (nonNull(source)) {
            source.suscribe(sourceListener);
        }

        return domingus;
    }

}
