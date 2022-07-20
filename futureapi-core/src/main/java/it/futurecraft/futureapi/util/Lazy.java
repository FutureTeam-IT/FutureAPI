
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

package it.futurecraft.futureapi.util;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

/**
 * A utility class for lazy initialization.
 * <p>
 * A resource is lazy-initialized when its value is provided only when it is first needed.
 *
 * @param <T> The type of the resource.
 */
public abstract class Lazy<T> {
    protected T value;

    private Lazy() {
        this.value = null;
    }

    /**
     * Create a new lazy resource.
     *
     * @param supplier The initializer of the resource.
     * @param <T>      The type of the resource.
     * @return The lazy resource.
     */
    public static <T> Lazy<T> by(Supplier<T> supplier) {
        return new SynchronousLazy<>(supplier);
    }

    /**
     * Create a new lazy resource initialized asynchronously.
     *
     * @param future The initializer of the resource.
     * @param <T>    The type of the resource.
     * @return The lazy resource.
     */
    public static <T> Lazy<T> by(CompletableFuture<T> future) {
        return new AsynchronousLazy<>(future);
    }

    /**
     * Get and initialize the resource.
     *
     * @return The resource.
     */
    @Nullable public abstract T get();

    /**
     * Resets the value of the resource.
     */
    void reset() {
        this.value = null;
    }

    public static class SynchronousLazy<T> extends Lazy<T> {
        private final Supplier<T> supplier;

        public SynchronousLazy(Supplier<T> supplier) {
            this.supplier = supplier;
        }

        @Override
        public T get() {
            if (super.value == null) {
                super.value = supplier.get();
            }
            return super.value;
        }
    }

    public static class AsynchronousLazy<T> extends Lazy<T> {
        private final CompletableFuture<T> supplier;

        public AsynchronousLazy(CompletableFuture<T> supplier) {
            this.supplier = supplier;
        }

        @Override
        public T get() {
            if (super.value == null) {
                try {
                    super.value = supplier.get();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            return super.value;
        }
    }
}
