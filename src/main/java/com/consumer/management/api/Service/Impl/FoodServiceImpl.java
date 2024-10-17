package com.consumer.management.api.Service.Impl;

import com.consumer.management.api.Exception.ConsumerException;
import com.consumer.management.api.Model.Enum.ErrorEnum;
import com.consumer.management.api.Model.Request.AccompanimentRequest;
import com.consumer.management.api.Model.Request.FoodRequest;
import com.consumer.management.api.Model.Request.MeatRequest;
import com.consumer.management.api.Model.Request.UpdateFoodStatus;
import com.consumer.management.api.Model.Response.AccompanimentResponse;
import com.consumer.management.api.Model.Response.FoodResponse;
import com.consumer.management.api.Model.Response.MeatResponse;
import com.consumer.management.api.Repository.Entity.AccompanimentEntity;
import com.consumer.management.api.Repository.Entity.MeatEntity;
import com.consumer.management.api.Repository.Interface.AccompanimentRepository;
import com.consumer.management.api.Repository.Interface.MeatRepository;
import com.consumer.management.api.Service.Interface.FoodService;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class FoodServiceImpl implements FoodService {
    private final AccompanimentRepository accompanimentRepository;
    private final MeatRepository meatRepository;

    private final String NO_STOCK = "OUT_OF_STOCK";
    private final String STOCK = "STOCK";

    @Autowired
    public FoodServiceImpl(AccompanimentRepository accompanimentRepository, MeatRepository meatRepository) {
        this.accompanimentRepository = accompanimentRepository;
        this.meatRepository = meatRepository;
    }

    @Override
    public FoodResponse createFoodsOfDay(FoodRequest foodRequest) {
       try{
           List<AccompanimentEntity> entitiesAccompaniment = new ArrayList<>();
           List<MeatEntity> entitiesMeat = new ArrayList<>();

           if(!foodRequest.getAccompaniment().isEmpty()){
               entitiesAccompaniment = (List<AccompanimentEntity>) accompanimentRepository.saveAll(mapAccompanimentRequestToEntity(foodRequest.getAccompaniment()));
            }

            if(!foodRequest.getMeat().isEmpty()){
                entitiesMeat = (List<MeatEntity>) meatRepository.saveAll(mapMeatRequestToEntity(foodRequest.getMeat()));
            }

            return mapListFoodResponse(entitiesMeat, entitiesAccompaniment);
       } catch (Exception ex) {
           throw new ConsumerException(ex, ErrorEnum.ERROR_INSERT_FOOD_OF_DAY.getHttpStatus(),
                   ErrorEnum.ERROR_INSERT_FOOD_OF_DAY.getErrorCode(),
                   ErrorEnum.ERROR_INSERT_FOOD_OF_DAY.getTituloMessage(),
                   ErrorEnum.ERROR_INSERT_FOOD_OF_DAY.getErrorMessage());
       }
    }

    @Override
    @Transactional
    public boolean updateFoodOfDay(UpdateFoodStatus update) {
        try{
            if(update.isAccompaniment()){
                accompanimentRepository.updateStatusAccompaniment(update.isStock() ? STOCK : NO_STOCK, update.getId());
            }

            if(update.isMeat()){
                meatRepository.updateStatusMeat(update.isStock() ? STOCK : NO_STOCK, update.getId());
            }
            return true;
        } catch (Exception ex) {
            throw new ConsumerException(ex, ErrorEnum.ERROR_UPDATE_STATUS_FOOD.getHttpStatus(),
                    ErrorEnum.ERROR_UPDATE_STATUS_FOOD.getErrorCode(),
                    ErrorEnum.ERROR_UPDATE_STATUS_FOOD.getTituloMessage(),
                    ErrorEnum.ERROR_UPDATE_STATUS_FOOD.getErrorMessage());
        }
    }

    @Override
    public FoodResponse getFoodOfDay(boolean stock) {
        return null;
    }

    private FoodResponse mapListFoodResponse(List<MeatEntity> entitiesMeat, List<AccompanimentEntity> entitiesAccompaniment) {

        List<AccompanimentResponse> accompanimentResponses = new ArrayList<>();
        List<MeatResponse> meatResponses = new ArrayList<>();

        entitiesMeat.forEach(entity -> {
            MeatResponse resp = mapMeatResponse(entity);
            meatResponses.add(resp);
        });
        entitiesAccompaniment.forEach(entity -> {
            AccompanimentResponse resp = mapAccompanimentResponse(entity);
            accompanimentResponses.add(resp);
        });

        return new FoodResponse(accompanimentResponses, meatResponses);
    }

    private AccompanimentResponse mapAccompanimentResponse(AccompanimentEntity entity) {
        AccompanimentResponse resp = new AccompanimentResponse();
        resp.setId(entity.getId());
        resp.setDtRegister(entity.getDtRegister());
        resp.setName(entity.getAccompaniment());
        resp.setStatus(entity.getStatus());

        return resp;
    }

    private MeatResponse mapMeatResponse(MeatEntity entity) {
        MeatResponse resp = new MeatResponse();
        resp.setId(entity.getId());
        resp.setName(entity.getMeat());
        resp.setDtRegister(entity.getDtRegister());
        resp.setStatus(entity.getStatus());

        return resp;
    }

    private List<MeatEntity> mapMeatRequestToEntity(List<MeatRequest> meat) {
        List<MeatEntity> entities = new ArrayList<>();
        meat.forEach(
                request -> {
                    MeatEntity entity = new MeatEntity();
                    entity.setId(request.getId());
                    entity.setMeat(request.getMeatName());
                    entity.setDtRegister(request.getDtRegister());
                    entity.setStatus(request.getStatus());
                    entities.add(entity);
                }
        );
        return entities;
    }

    private List<AccompanimentEntity> mapAccompanimentRequestToEntity(List<AccompanimentRequest> accompaniment) {
        List<AccompanimentEntity> entities = new ArrayList<>();
        accompaniment.forEach(
                request -> {
                    AccompanimentEntity entity = new AccompanimentEntity();
                    entity.setId(request.getId());
                    entity.setAccompaniment(request.getAccompanimentName());
                    entity.setDtRegister(request.getDtRegister());
                    entity.setStatus(request.getStatus());
                    entities.add(entity);
                }
        );
    return entities;
    }
}
