
/*
 * futureapi Copyright (C) 2022 FutureTeam-IT
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package it.futurecraft.futureapi.event;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Consumer;

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

        Subscription<E> subscription = new SubscriptionImpl<>(event, handler, this);
        subscriptions.add(subscription);

        return subscription;
    }

    @Override
    public <E extends FutureEvent> Subscription<E> subscribe(@NotNull Class<E> event, @NotNull Consumer<? super E> handler, FutureEvent.@NotNull Priority priority) {
        List<Subscription<? super E>> subscriptions = castList(this.subscriptions.computeIfAbsent(event, key -> new ArrayList<>()));

        Subscription<E> subscription = new SubscriptionImpl<>(event, handler, priority, this);
        subscriptions.add(subscription);

        return subscription;
    }

    @Override
    public <E extends FutureEvent> void dispatch(@NotNull E event) {
        List<Subscription<? super E>> subscriptions = castList(this.subscriptions.computeIfAbsent(event.getClass(), key -> new ArrayList<>()));

        subscriptions.stream().sorted(Comparator.comparing(Subscription::getPriority)).forEach(subscription -> subscription.getHandler().accept(event));
    }

    @Override
    public <E extends FutureEvent> boolean hasSubscribers(@NotNull Class<E> event) {
        return this.subscriptions.containsKey(event) && this.subscriptions.get(event).size() > 0;
    }

    @Override
    public <E extends FutureEvent> List<Subscription<? super E>> getSubscriptions(@NotNull Class<E> event) {
        return castList(this.subscriptions.computeIfAbsent(event, key -> new ArrayList<>()));
    }

    <E extends FutureEvent> void unsubscribe(Subscription<E> subscription) {
        Class<E> event = subscription.getEvent();
        List<Subscription<? super E>> subscriptions = castList((this.subscriptions.computeIfAbsent(event, key -> new ArrayList<>())));

        if (subscriptions.size() == 0) {
            return;
        }

        subscriptions.remove(subscription);
    }
}
