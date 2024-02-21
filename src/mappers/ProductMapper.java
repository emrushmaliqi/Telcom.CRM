package mappers;

import entities.ProductData;
import entities.ServiceData;
import entities.SubscriptionData;
import models.Product;
import models.Subscription;
import models.services.Service;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    ServiceMapper serviceMapper;
    SubscriptionMapper subscriptionMapper;
    public ProductMapper() {
        serviceMapper = new ServiceMapper();
        subscriptionMapper = new SubscriptionMapper();
    }

    public ProductData toProductData(Product product) {
        List<ServiceData> serviceDataList = serviceMapper.toServiceData(product.getServices());
        List<SubscriptionData> subscriptionList = subscriptionMapper.toSubscriptionData(product.getSubscriptions());

        return new ProductData(product.getId(), product.getName(), product.getPrice(), product.getFromDateTime(), product.getToDateTime(), serviceDataList, subscriptionList);
    }

    public List<ProductData> toProductData(List<Product> products) {
        return products.stream().map(this::toProductData).collect(Collectors.toList());
    }

    public Product fromProductData(ProductData pd) {
        List<Service> serviceList = serviceMapper.fromServiceData(pd.getServices());
        List<Subscription> subscriptionList = subscriptionMapper.fromSubscriptionData(pd.getSubscriptions());

        return new Product(pd.getId(), pd.getName(), pd.getPrice(), pd.getFromDateTime(), pd.getToDateTime(), serviceList, subscriptionList);
    }

    public List<Product> fromProductData(List<ProductData> productDataList) {
        return productDataList.stream().map(this::fromProductData).collect(Collectors.toList());
    }

}
