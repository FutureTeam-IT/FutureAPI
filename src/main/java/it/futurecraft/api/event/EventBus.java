package it.futurecraft.api.event;

import java.util.function.Consumer;

/**
 * The event bus.
 *
 * @since v0.2.1
 */
public interface EventBus {
    /**
     * Subscribe a consumer function to the event.
     *
     * @param event   The event to subscribe to.
     * @param handler The event handler.
     * @param <E>     The event type.
     * @return The subscription to the event.
     */
    <E extends FutureEvent> Subscription<E> subscribe(Class<E> event, Consumer<E> handler);

    /**
     * Dispatch an event.
     *
     * @param event The event to dispatch.
     * @param <E>   The event type.
     */
    <E extends FutureEvent> void dispatch(E event);
}
