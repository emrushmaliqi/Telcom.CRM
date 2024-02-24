package repositories;

import models.Product;
import models.Subscription;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends Repository<Subscription> {
    Optional<List<Subscription>> findSubscribersForProduct(Product product);
}
