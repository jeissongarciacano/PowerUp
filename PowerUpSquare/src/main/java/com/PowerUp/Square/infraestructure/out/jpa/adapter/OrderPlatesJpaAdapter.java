package com.PowerUp.Square.infraestructure.out.jpa.adapter;

import com.PowerUp.Square.infraestructure.out.jpa.entity.OrderPlatesEntity;
import com.PowerUp.Square.infraestructure.out.jpa.mapper.IOderPlatesMapper;
import com.PowerUp.Square.infraestructure.out.jpa.repository.IOrderPlatesRepository;
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
