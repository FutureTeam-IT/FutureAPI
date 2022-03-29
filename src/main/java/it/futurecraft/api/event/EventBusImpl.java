package it.futurecraft.api.event;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Consumer;

/**
 * @since v0.2.2
 */
final class EventBusImpl implements EventBus {
    private final Map<Class<? extends FutureEvent>, List<Subscription<? super FutureEvent>>> subscriptions;

    public EventBusImpl() {
        this.subscriptions = new HashMap<>();
    }

    private static <T extends R, R> List<R> castList(List<T> list) {
        return (List<R>) list;
    }

    @Override
    public <E extends FutureEvent> Subscription<E> subscribe(@NotNull Class<E> event, @NotNull Consumer<? super E> handler) {
        List<Subscription<? super E>> subscriptions = castList(this.subscriptions.computeIfAbsent(event, key -> new ArrayList<>()));

        Subscription<E> subscription = new SubscriptionImpl<>(event, handler);
        subscriptions.add(subscription);

        return subscription;
    }

    @Override
    public <E extends FutureEvent> Subscription<E> subscribe(@NotNull Class<E> event, @NotNull Consumer<? super E> handler, FutureEvent.@NotNull Priority priority) {
        List<Subscription<? super E>> subscriptions = castList(this.subscriptions.computeIfAbsent(event, key -> new ArrayList<>()));

        Subscription<E> subscription = new SubscriptionImpl<>(event, handler, priority);
        subscriptions.add(subscription);

        return subscription;
    }

    @Override
    public <E extends FutureEvent> void dispatch(@NotNull E event) {
        List<Subscription<? super E>> subscriptions = castList(this.subscriptions.computeIfAbsent(event.getClass(), key -> new ArrayList<>()));

        subscriptions.stream()
                .sorted(Comparator.comparing(Subscription::getPriority))
                .forEach(subscription -> subscription.getHandler().accept(event));
    }

    @Override
    public <E extends FutureEvent> boolean hasSubscribers(@NotNull Class<E> event) {
        return this.subscriptions.containsKey(event) && this.subscriptions.get(event).size() > 0;
    }

    @Override
    public <E extends FutureEvent> List<Subscription<? super E>> getSubscriptions(@NotNull Class<E> event) {
        return castList(this.subscriptions.computeIfAbsent(event, key -> new ArrayList<>()));
    }
}
