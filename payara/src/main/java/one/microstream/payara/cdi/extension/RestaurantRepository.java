package one.microstream.payara.cdi.extension;

import java.util.Collection;
import java.util.Optional;

public interface RestaurantRepository {
    Collection<Item> getAll();

    Item save(Item item);

    Optional<Item> findById(String id);

    void deleteById(String id);
}
