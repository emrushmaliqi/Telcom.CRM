package repositories;

import models.Product;
import models.Subscription;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends Repository<Product> {
    Optional<List<Product>> findCheaperThanX(double price);
    Optional<List<Product>> findExpiringInXDays(int days);

}
