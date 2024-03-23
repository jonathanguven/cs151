package mvc;

import java.util.LinkedList;
import java.util.List;

public class Publisher {
    private final List<Subscriber> subscribers = new LinkedList<>();

    public void subscribe (Subscriber s) {subscribers.add(s);}

    public void unsubscribe (Subscriber s) {subscribers.remove(s);}

    public void notifySubscribers () {
        for (Subscriber s : subscribers) {
            s.update();
        }
    }
}
