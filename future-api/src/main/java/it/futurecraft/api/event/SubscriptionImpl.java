package it.futurecraft.api.event;

import static it.futurecraft.api.event.FutureEvent.Priority;

import java.util.function.Consumer;

/**
 * @since v0.2.1
 */
final class SubscriptionImpl<E extends FutureEvent> implements Subscription<E> {
    private final Consumer<? super E> handler;
    private final Class<E> event;
    private final Priority priority;
    private final EventBusImpl bus;

    public SubscriptionImpl(Class<E> event, Consumer<? super E> handler, Priority priority, EventBusImpl bus) {
        this.handler = handler;
        this.event = event;
        this.priority = priority;
        this.bus = bus;
    }

    public SubscriptionImpl(Class<E> event, Consumer<? super E> consumer, EventBusImpl bus) {
        this(event, consumer, Priority.LOW, bus);
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
    public Priority getPriority() {
        return priority;
    }

    @Override
    public void unsubscribe() {
        bus.unsubscribe(this);
    }
}
