package com.consumer.management.api.Service.Impl;

import com.consumer.management.api.Exception.ConsumerException;
import com.consumer.management.api.Model.Enum.ErrorEnum;
import com.consumer.management.api.Model.Request.CreateOrderRequest;
import com.consumer.management.api.Model.Request.PackedLunchRequest;
import com.consumer.management.api.Model.Response.CreateOrderResponse;
import com.consumer.management.api.Model.Response.ItemsResponse;
import com.consumer.management.api.Model.Response.PackedLunchResponse;
import com.consumer.management.api.Model.Response.PackedLunchWeightResponse;
import com.consumer.management.api.Repository.Entity.CustomerEntity;
import com.consumer.management.api.Repository.Entity.FoodPackedLunchEntity;
import com.consumer.management.api.Repository.Entity.OrderEntity;
import com.consumer.management.api.Repository.Entity.PackedLunchEntity;
import com.consumer.management.api.Repository.Interface.FoodPackedLunchRepository;
import com.consumer.management.api.Repository.Interface.OrderRepository;
import com.consumer.management.api.Repository.Interface.PackedLunchRepository;
import com.consumer.management.api.Service.Interface.OrderService;
import com.consumer.management.api.Service.Interface.PackedLunchService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {

    private final PackedLunchService packedLunchService;
    private final OrderRepository orderRepository;
    private final PackedLunchRepository packedLunchRepository;
    private static final String PENDING = "PENDING";

    @Autowired
    private FoodPackedLunchRepository foodPackedLunchRepository;

    @Autowired
    public OrderServiceImpl(PackedLunchService packedLunchService, OrderRepository orderRepository, PackedLunchRepository packedLunchRepository) {
        this.packedLunchService = packedLunchService;
        this.orderRepository = orderRepository;
        this.packedLunchRepository = packedLunchRepository;
    }


    @Override
    public CreateOrderResponse createOrder(CreateOrderRequest request) {
        try{
            List<PackedLunchRequest> errors = new ArrayList<>();
            OrderEntity entity = orderRepository.save(mapOrderEntity(request));
            AtomicReference<BigDecimal> total = new AtomicReference<>(BigDecimal.ZERO);

            request.getLunchs().forEach(
                    lunch -> {
                        PackedLunchResponse resp = validateLunchCreate(lunch);
                        if(resp.isSuccess()){
                            packedLunchRepository.save(mapPackedLunchEntity(entity, resp, lunch));
                            total.set(total.get().add(resp.getPrice()));
                        } else {
                            errors.add(lunch);
                        }
                    }
            );

            entity.setTotal(total.get());
            orderRepository.save(entity);

            return mapOrderResponse(total, entity.getId(), errors);
        } catch (Exception ex) {
            throw new ConsumerException(ex, ErrorEnum.ERROR_CREATE_ORDER.getHttpStatus(),
                    ErrorEnum.ERROR_CREATE_ORDER.getErrorCode(),
                    ErrorEnum.ERROR_CREATE_ORDER.getTituloMessage(),
                    ErrorEnum.ERROR_CREATE_ORDER.getErrorMessage());
        }
    }

    @Override
    public List<ItemsResponse> getItemsByOrderId(int orderId) {
        List<Object[]> results;
        if(orderId == 0){
            results = new ArrayList<>();
            List<OrderEntity> pendingOrders = orderRepository.findStatusPending();
            pendingOrders.forEach(
                    order -> {
                        List<Object[]> resultsOrder = foodPackedLunchRepository.findItemsByOrderId(order.getId());
                        results.addAll(resultsOrder);
                    }
            );
        } else{
            results = foodPackedLunchRepository.findItemsByOrderId(orderId);
        }
        Map<Integer, ItemsResponse> packedLunchMap = new HashMap<>();

        for (Object[] row : results) {
            String accompaniment = (String) row[0];
            String meat = (String) row[1];
            Integer packedLunchId = (Integer) row[2];

            ItemsResponse itemsResponse = packedLunchMap.computeIfAbsent(packedLunchId, id ->
                    ItemsResponse.builder()
                            .packedLunchId(packedLunchId)
                            .foods(new ArrayList<>())
                            .build()
            );

            if (accompaniment != null) {
                itemsResponse.getFoods().add(accompaniment);
            }
            if (meat != null) {
                itemsResponse.getFoods().add(meat);
            }
        }

        return new ArrayList<>(packedLunchMap.values());
    }


    private CreateOrderResponse mapOrderResponse(AtomicReference<BigDecimal> total, Integer id, List<PackedLunchRequest> errors) {
        CreateOrderResponse resp = new CreateOrderResponse();
        resp.setErros(errors);
        resp.setTotalPrice(total.get());
        resp.setOrderId(id);
        return resp;
    }

    private PackedLunchEntity mapPackedLunchEntity(OrderEntity entity, PackedLunchResponse resp, PackedLunchRequest lunch) {
        PackedLunchEntity packedLunchEntity = new PackedLunchEntity();

        packedLunchEntity.setId(0);
        packedLunchEntity.setPackedLunchWeight(resp.getIdWeight());
        packedLunchEntity.setOrder(entity.getId());
        packedLunchEntity.setFoodPackedLunchEntity(mapListFoods(lunch, packedLunchEntity));

        return packedLunchEntity;

    }

    private List<FoodPackedLunchEntity> mapListFoods(PackedLunchRequest request, PackedLunchEntity packedLunchEntity) {
        List<FoodPackedLunchEntity> foodPackedLunchEntities = new ArrayList<>();

        request.getMeats().forEach(
                meat -> {
                    FoodPackedLunchEntity foodPackedLunchEntity = new FoodPackedLunchEntity();
                    foodPackedLunchEntity.setId(0);
                    foodPackedLunchEntity.setIdMeat(meat);
                    foodPackedLunchEntity.setIdAccompaniment(null);
                    foodPackedLunchEntity.setPackedLunchEntity(packedLunchEntity);
                    foodPackedLunchEntities.add(foodPackedLunchEntity);
                }
        );

        request.getAccompaniments().forEach(
                acompaniment -> {
                    FoodPackedLunchEntity foodPackedLunchEntity = new FoodPackedLunchEntity();
                    foodPackedLunchEntity.setId(0);
                    foodPackedLunchEntity.setIdMeat(null);
                    foodPackedLunchEntity.setIdAccompaniment(acompaniment);
                    foodPackedLunchEntity.setPackedLunchEntity(packedLunchEntity);
                    foodPackedLunchEntities.add(foodPackedLunchEntity);
                }
        );

        return foodPackedLunchEntities;

    }

    private PackedLunchResponse validateLunchCreate(PackedLunchRequest lunch) {
        PackedLunchWeightResponse resp = packedLunchService.listWeightsByName(lunch.getWeight());

        if(resp.getQuantityMeat() < lunch.getMeats().size()
                || resp.getQuantityAccompaniment() < lunch.getAccompaniments().size()){

            return new PackedLunchResponse(false, BigDecimal.ZERO, 0);
        };

        return new PackedLunchResponse(true, resp.getPrice(), resp.getId());
    }

    private OrderEntity mapOrderEntity(CreateOrderRequest request) {
        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setId(0);
        orderEntity.setTotal(BigDecimal.ZERO);
        orderEntity.setStatus(PENDING);
        orderEntity.setCustomer(mapCustomerEntity(request, orderEntity));

        return orderEntity;
    }

    private CustomerEntity mapCustomerEntity(CreateOrderRequest request, OrderEntity orderEntity) {
        CustomerEntity entity = new CustomerEntity();

        entity.setId(0);
        entity.setOrder(orderEntity);
        entity.setName(request.getNameCustomer());
        entity.setDtRegister(new Date());

        return entity;
    }
}
