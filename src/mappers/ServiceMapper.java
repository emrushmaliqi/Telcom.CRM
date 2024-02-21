package mappers;

import entities.ProductData;
import entities.ServiceData;
import models.Product;
import models.services.*;

import java.util.List;
import java.util.stream.Collectors;

public class ServiceMapper {

    ProductMapper productMapper;

    public ServiceMapper() {
        this.productMapper = new ProductMapper();
    }

    public ServiceData toServiceData(Service service) {
        List<ProductData> productDataList = productMapper.toProductData(service.getProducts());
        if(service.getType().getClass() == Voice.class) {
            Voice voice = (Voice) service.getType();
            return new ServiceData(service.getId(), -1, voice.getMinutes(), -1,  service.getCreatedDate(), service.getState(), productDataList);
        }
        if(service.getType().getClass() == Sms.class) {
            Sms sms = (Sms) service.getType();
            return new ServiceData(service.getId(), -1, -1, sms.getMessages(), service.getCreatedDate(), service.getState(), productDataList);
        }
        if(service.getType().getClass() == Data.class) {
            Data data = (Data) service.getType();
            return new ServiceData(service.getId(), data.getData(), -1, -1, service.getCreatedDate(), service.getState(), productDataList);
        }
        return new ServiceData(service.getId(), -1, -1, -1, service.getCreatedDate(), service.getState(), productDataList);
    }



    public List<ServiceData> toServiceData(List<Service> services) {
        return services.stream().map(this::toServiceData).collect(Collectors.toList());
    }

    public Service fromServiceData(ServiceData serviceData) {
        List<Product> productList = productMapper.fromProductData(serviceData.getProducts());
        if(serviceData.getMinutes() != -1) {
            Voice voice = new Voice(serviceData.getMinutes());
            return new Service(serviceData.getId(), voice, serviceData.getCreatedDate(), serviceData.getState(), productList);
        }
        if(serviceData.getMessages() != -1) {
            Sms sms = new Sms(serviceData.getMessages());
            return new Service(serviceData.getId(), sms, serviceData.getCreatedDate(), serviceData.getState(), productList);
        }
        if(serviceData.getData() != -1) {
            Data data = new Data(serviceData.getData());
            return new Service(serviceData.getId(), data, serviceData.getCreatedDate(), serviceData.getState(), productList);
        }
        return new Service(serviceData.getId(), new SimCard(), serviceData.getCreatedDate(), serviceData.getState(), productList);
    }

    public List<Service> fromServiceData(List<ServiceData> serviceDataList) {
        return serviceDataList.stream().map(this::fromServiceData).collect(Collectors.toList());
    }
}