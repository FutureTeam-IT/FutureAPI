package it.futurecraft.api.event;

import java.util.function.Consumer;

/**
 * Represent a subscription to event dispatch.
 *
 * @param <E> The event.
 * @since v0.2.0
 */
public interface Subscription<E extends FutureEvent> {
    /**
     * Get the event handler from the subscription.
     *
     * @return The consumer object.
     * @since v0.2.1
     */
    Consumer<E> getHandler();

    /**
     * Get the event class from the subscription.
     *
     * @return The event class.
     * @since v0.2.1
     */
    Class<E> getEvent();

    /**
     * Unsubscribe from the event listening.
     */
    void unsubscribe();
}
