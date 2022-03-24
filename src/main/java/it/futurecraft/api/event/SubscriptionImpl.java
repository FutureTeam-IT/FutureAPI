package it.futurecraft.api.event;

import java.util.function.Consumer;

class SubscriptionImpl<E extends FutureEvent> implements Subscription<E> {
    private Consumer<E> consumer;
    private Class<E> event;

    public SubscriptionImpl(Consumer<E> consumer, Class<E> event) {
        this.consumer = consumer;
        this.event = event;
    }

    @Override
    public Consumer<E> getHandler() {
        return consumer;
    }

    @Override
    public Class<E> getEvent() {
        return event;
    }

    @Override
    public void unsubscribe() {

    }
}
