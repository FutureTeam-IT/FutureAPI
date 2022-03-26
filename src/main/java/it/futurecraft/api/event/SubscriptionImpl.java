package it.futurecraft.api.event;

import java.util.function.Consumer;

/**
 * @since v0.2.1
 */
final class SubscriptionImpl<E extends FutureEvent> implements Subscription<E> {
    private Consumer<? super E> handler;
    private Class<E> event;

    public SubscriptionImpl(Class<E> event, Consumer<? super E> consumer) {
        this.handler = consumer;
        this.event = event;
    }

    @Override
    public Consumer<? super E> getHandler() {
        return handler;
    }

    @Override
    public Class<E> getEvent() {
        return event;
    }

    @Override
    public void unsubscribe() {
        // TODO: Implement unsubscribe logic.
    }
}
