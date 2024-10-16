package org.domingus.app;

import java.util.List;

public class MessageAdapter {

    private Domingus domingus;
    public MessageAdapter(Domingus domingus){
        this.domingus = domingus;
    }

    public void execute(List<String> changes){
        domingus.notifyAll(generateMessage(changes));
    }
    private String generateMessage(List<String> changes){
        StringBuilder sb = new StringBuilder();

        for (String change : changes) {
            sb.append("Se han detectado cambios en la oferta académica, nueva versión nro: ").append(change).append("\n");
        }

        return sb.toString();
    }
}
