
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

package it.futurecraft.futureapi.database.entity;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.atomic.AtomicReference;

public final class BindingHandler implements InvocationHandler {
    private final AtomicReference<Method> reference;

    BindingHandler(AtomicReference<Method> reference) {
        this.reference = reference;
    }

    public static <E extends Entity<E>> E handler(Class<E> clazz, AtomicReference<Method> ref) {
        BindingHandler handler = new BindingHandler(ref);
        return (E) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, handler);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getDeclaringClass() == Object.class || method.getDeclaringClass() == Entity.class) {
            throw new IllegalAccessException("Unsupported method: " + method);
        }

        reference.set(method);

        return Default.byType(method.getReturnType()).getValue();
    }

    enum Default {
        INTEGER(0), DECIMAL(0.0d), OBJECT(null);

        private final Object value;

        Default(Object value) {
            this.value = value;
        }

        static Default byType(Class<?> clazz) {
            if (clazz == Integer.class || clazz == Short.class || clazz == Long.class) {
                return INTEGER;
            }

            if (clazz == Double.class) {
                return DECIMAL;
            }

            return OBJECT;
        }

        public Object getValue() {
            return value;
        }
    }

}
