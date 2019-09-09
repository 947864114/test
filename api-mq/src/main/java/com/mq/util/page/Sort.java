package com.mq.util.page;

import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.*;


public class Sort implements Iterable<Sort.Order>, Serializable {
    public static final Sort.Direction DEFAULT_DIRECTION;
    private final List<Order> orders;

    public Sort(Sort.Order... orders) {
        this(Arrays.asList(orders));
    }

    public Sort(List<Order> orders) {
        if(null != orders && !orders.isEmpty()) {
            this.orders = orders;
        } else {
            throw new IllegalArgumentException("You have to provide at least one sort property to sort by!");
        }
    }

    public Sort(String... properties) {
        this(DEFAULT_DIRECTION, properties);
    }

    public Sort(Sort.Direction direction, String... properties) {
        this(direction, (List)(properties == null?new ArrayList():Arrays.asList(properties)));
    }

    public Sort(Sort.Direction direction, List<String> properties) {
        if(properties != null && !properties.isEmpty()) {
            this.orders = new ArrayList(properties.size());
            Iterator i$ = properties.iterator();

            while(i$.hasNext()) {
                String property = (String)i$.next();
                this.orders.add(new Sort.Order(direction, property));
            }

        } else {
            throw new IllegalArgumentException("You have to provide at least one property to sort by!");
        }
    }

    public Sort and(Sort sort) {
        if(sort == null) {
            return this;
        } else {
            ArrayList these = new ArrayList(this.orders);
            Iterator i$ = sort.iterator();

            while(i$.hasNext()) {
                Sort.Order order = (Sort.Order)i$.next();
                these.add(order);
            }

            return new Sort(these);
        }
    }

    public Sort.Order getOrderFor(String property) {
        Iterator i$ = this.iterator();

        Sort.Order order;
        do {
            if(!i$.hasNext()) {
                return null;
            }

            order = (Sort.Order)i$.next();
        } while(!order.getProperty().equals(property));

        return order;
    }

    public Iterator<Order> iterator() {
        return this.orders.iterator();
    }

    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        } else if(!(obj instanceof Sort)) {
            return false;
        } else {
            Sort that = (Sort)obj;
            return this.orders.equals(that.orders);
        }
    }

    public int hashCode() {
        byte result = 17;
        int result1 = 31 * result + this.orders.hashCode();
        return result1;
    }

    public String toString() {
        return StringUtils.collectionToCommaDelimitedString(this.orders);
    }

    static {
        DEFAULT_DIRECTION = Sort.Direction.ASC;
    }

    public static class Order implements Serializable {
        private static final long serialVersionUID = 1522511010900108987L;
        private final Sort.Direction direction;
        private final String property;

        public Order(Sort.Direction direction, String property) {
            if(!StringUtils.hasText(property)) {
                throw new IllegalArgumentException("Property must not null or empty!");
            } else {
                this.direction = direction == null? Sort.DEFAULT_DIRECTION:direction;
                this.property = property;
            }
        }

        public Order(String property) {
            this(Sort.DEFAULT_DIRECTION, property);
        }

        /** @deprecated */
        @Deprecated
        public static List<Order> create(Sort.Direction direction, Iterable<String> properties) {
            ArrayList orders = new ArrayList();
            Iterator i$ = properties.iterator();

            while(i$.hasNext()) {
                String property = (String)i$.next();
                orders.add(new Sort.Order(direction, property));
            }

            return orders;
        }

        public Sort.Direction getDirection() {
            return this.direction;
        }

        public String getProperty() {
            return this.property;
        }

        public boolean isAscending() {
            return this.direction.equals(Sort.Direction.ASC);
        }

        public Sort.Order with(Sort.Direction order) {
            return new Sort.Order(order, this.property);
        }

        public Sort withProperties(String... properties) {
            return new Sort(this.direction, properties);
        }

        public int hashCode() {
            byte result = 17;
            int result1 = 31 * result + this.direction.hashCode();
            result1 = 31 * result1 + this.property.hashCode();
            return result1;
        }

        public boolean equals(Object obj) {
            if(this == obj) {
                return true;
            } else if(!(obj instanceof Sort.Order)) {
                return false;
            } else {
                Sort.Order that = (Sort.Order)obj;
                return this.direction.equals(that.direction) && this.property.equals(that.property);
            }
        }

        public String toString() {
            return String.format("%s: %s", new Object[]{this.property, this.direction});
        }
    }

    public static enum Direction {
        ASC,
        DESC;

        private Direction() {
        }

        public static Sort.Direction fromString(String value) {
            try {
                return valueOf(value.toUpperCase(Locale.US));
            } catch (Exception var2) {
                throw new IllegalArgumentException(String.format("Invalid value \'%s\' for orders given! Has to be either \'desc\' or \'asc\' (case insensitive).", new Object[]{value}), var2);
            }
        }
    }
}
