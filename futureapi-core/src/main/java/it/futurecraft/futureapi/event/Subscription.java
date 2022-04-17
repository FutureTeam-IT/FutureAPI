package it.futurecraft.futureapi.event;

import java.util.function.Consumer;

/**
 * Represent a subscription to event dispatch.
 *
 * @param <E> The event.
 */
public interface Subscription<E extends FutureEvent> {
    /**
     * Get the event handler from the subscription.
     *
     * @return The consumer object.
     */
    Consumer<? super E> getHandler();

    /**
     * Get the event class from the subscription.
     *
     * @return The event class.
     */
    Class<E> getEvent();

    /**
     * Get the priority of the handler.
     *
     * @return The priority value.
     */
    FutureEvent.Priority getPriority();

    /**
     * Unsubscribe from the event listening.
     */
    void unsubscribe();
}
