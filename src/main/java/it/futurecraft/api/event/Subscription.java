package it.futurecraft.api.event;

/**
 * Represent a subscription to event dispatch.
 *
 * @param <E> The event.
 * @since v0.2.0
 */
public interface Subscription<E extends FutureEvent> {
    /**
     * Unsubscribe from the event listening.
     */
    void unsubscribe();
}
