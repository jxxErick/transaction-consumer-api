package com.consumer.management.api.Service.Impl;


import com.consumer.management.api.Exception.ConsumerException;
import com.consumer.management.api.Model.Enum.ErrorEnum;
import com.consumer.management.api.Model.Request.PackedLunchWeightRequest;
import com.consumer.management.api.Model.Response.PackedLunchWeightResponse;
import com.consumer.management.api.Repository.Entity.PackedLunchWeightEntity;
import com.consumer.management.api.Repository.Interface.PackedLunchWeightRepository;
import com.consumer.management.api.Service.Interface.PackedLunchService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class PackedLunchServiceImpl implements PackedLunchService {

    private final PackedLunchWeightRepository packedLunchWeightRepository;

    @Autowired
    public PackedLunchServiceImpl(PackedLunchWeightRepository packedLunchWeightRepository) {
        this.packedLunchWeightRepository = packedLunchWeightRepository;
    }


    @Override
    public PackedLunchWeightResponse getPackedLunchWeight(String name) {
        return null;
    }

    @Override
    public PackedLunchWeightResponse createPackedLunchWeight(PackedLunchWeightRequest request) {
        try{
            PackedLunchWeightEntity entity = packedLunchWeightRepository.save(mapRequestToEntiy(request));
            return mapEntityToResponse(entity);
        } catch (Exception e) {
            throw new ConsumerException(e, ErrorEnum.ERROR_CREATE_PACKED_LUNCH_WEIGHT.getHttpStatus(),
                    ErrorEnum.ERROR_CREATE_PACKED_LUNCH_WEIGHT.getErrorCode(),
                    ErrorEnum.ERROR_CREATE_PACKED_LUNCH_WEIGHT.getTituloMessage(),
                    ErrorEnum.ERROR_CREATE_PACKED_LUNCH_WEIGHT.getErrorMessage());
        }
    }

    @Override
    public List<PackedLunchWeightResponse> listPackedLunchWeights() {
        try{
            List<PackedLunchWeightEntity> entity = (List<PackedLunchWeightEntity>) packedLunchWeightRepository.findAll();
            return mapListEntityToResponse(entity);
        } catch (Exception ex) {
            throw new ConsumerException(ex, ErrorEnum.ERROR_FIND_WEIGHTS.getHttpStatus(),
                    ErrorEnum.ERROR_FIND_WEIGHTS.getErrorCode(),
                    ErrorEnum.ERROR_FIND_WEIGHTS.getTituloMessage(),
                    ErrorEnum.ERROR_FIND_WEIGHTS.getErrorMessage());
        }
    }

    @Override
    public PackedLunchWeightResponse listWeightsByName(String name) {
        try{
            PackedLunchWeightEntity entity = packedLunchWeightRepository.findByName(name);
            return mapEntityToResponse(entity);
        } catch (Exception ex){
            throw new ConsumerException(ex, ErrorEnum.ERROR_FIND_WEIGHTS.getHttpStatus(),
                    ErrorEnum.ERROR_FIND_WEIGHTS.getErrorCode(),
                    ErrorEnum.ERROR_FIND_WEIGHTS.getTituloMessage(),
                    ErrorEnum.ERROR_FIND_WEIGHTS.getErrorMessage());
        }
    }

    private List<PackedLunchWeightResponse> mapListEntityToResponse(List<PackedLunchWeightEntity> entityList) {
        return entityList.stream()
                .map(this::mapEntityToResponse)
                .collect(Collectors.toList());
    }

    private PackedLunchWeightResponse mapEntityToResponse(PackedLunchWeightEntity entity) {
        PackedLunchWeightResponse response = new PackedLunchWeightResponse();

        response.setId(entity.getId());
        response.setQuantityAccompaniment(entity.getQuantityAccompaniment());
        response.setQuantityMeat(entity.getQuantityMeat());
        response.setWeight(entity.getWeight());
        response.setName(entity.getName());
        response.setPrice(entity.getPrice());

        return response;
    }

    private PackedLunchWeightEntity mapRequestToEntiy(PackedLunchWeightRequest request) {
        PackedLunchWeightEntity entity = new PackedLunchWeightEntity();

        entity.setId(0);
        entity.setQuantityAccompaniment(request.getQuantityAccompaniment());
        entity.setQuantityMeat(request.getQuantityMeat());
        entity.setWeight(request.getWeight());
        entity.setName(request.getName());
        entity.setPrice(request.getPrice());

        return entity;

    }
}
