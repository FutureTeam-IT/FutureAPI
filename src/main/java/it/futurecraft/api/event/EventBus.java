package it.futurecraft.api.event;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

/**
 * The event bus.
 *
 * @since v0.2.1
 */
public interface EventBus {
    /**
     * Create a new instance of the event bus.
     *
     * @return The event bus instance.
     */
    static EventBus create() {
        return new EventSubImpl();
    }

    /**
     * Subscribe a consumer function to the event.
     *
     * @param event   The event to subscribe to.
     * @param handler The event handler.
     * @param <E>     The event type.
     * @return The subscription to the event.
     */
    <E extends FutureEvent> Subscription<E> subscribe(@NotNull Class<E> event, @NotNull Consumer<? super E> handler);

    /**
     * Dispatch an event.
     *
     * @param event The event to dispatch.
     * @param <E>   The event type.
     */
    <E extends FutureEvent> void dispatch(@NotNull E event);

    /**
     * Check whether the event has subscribers.
     *
     * @param <E>   The event type.
     * @param event The event to check.
     * @return <code>true</code> If the event has subscribers.
     */
    <E extends FutureEvent> boolean hasSubscribers(@NotNull Class<E> event);

    /**
     * Get subscriptions list for the event.
     *
     * @param event The event to check.
     * @param <E>   The event type.
     * @return The subscription list.
     */
    <E extends FutureEvent> List<Subscription<? super E>> getSubscriptions(@NotNull Class<E> event);
}
