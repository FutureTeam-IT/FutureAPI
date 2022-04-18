
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

import java.util.function.Consumer;

final class SubscriptionImpl<E extends FutureEvent> implements Subscription<E> {
    private final Consumer<? super E> handler;
    private final Class<E> event;
    private final FutureEvent.Priority priority;
    private final EventBusImpl bus;

    public SubscriptionImpl(Class<E> event, Consumer<? super E> handler, FutureEvent.Priority priority, EventBusImpl bus) {
        this.handler = handler;
        this.event = event;
        this.priority = priority;
        this.bus = bus;
    }

    public SubscriptionImpl(Class<E> event, Consumer<? super E> consumer, EventBusImpl bus) {
        this(event, consumer, FutureEvent.Priority.LOW, bus);
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
    public FutureEvent.Priority getPriority() {
        return priority;
    }

    @Override
    public void unsubscribe() {
        bus.unsubscribe(this);
    }
}
