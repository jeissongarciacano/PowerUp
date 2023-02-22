package com.powerup.square.infraestructure.out.jpa.adapter;

import com.powerup.square.infraestructure.out.jpa.entity.OrderPlatesEntity;
import com.powerup.square.infraestructure.out.jpa.mapper.IOderPlatesMapper;
import com.powerup.square.infraestructure.out.jpa.repository.IOrderPlatesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderPlatesJpaAdapter {
    private final IOrderPlatesRepository OrderPlatesRepository;
    private final IOderPlatesMapper OrderPlatesMapper;

    public OrderPlatesEntity saveOrderPlatesEntity(OrderPlatesEntity OrderPlatesEntity){
        return OrderPlatesRepository.save(OrderPlatesEntity);
    }

}
