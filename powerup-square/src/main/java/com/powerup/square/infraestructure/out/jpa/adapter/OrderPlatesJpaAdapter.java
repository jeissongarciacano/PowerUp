package com.powerup.square.infraestructure.out.jpa.adapter;

import com.powerup.square.domain.model.OrderPlates;
import com.powerup.square.domain.spi.IOrderPlatesPersistencePort;
import com.powerup.square.infraestructure.out.jpa.entity.OrderPlatesEntity;
import com.powerup.square.infraestructure.out.jpa.mapper.IOrderMapper;
import com.powerup.square.infraestructure.out.jpa.mapper.IOrderPlatesMapper;
import com.powerup.square.infraestructure.out.jpa.repository.IOrderPlatesRepository;
import com.powerup.square.infraestructure.out.jpa.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderPlatesJpaAdapter implements IOrderPlatesPersistencePort {
    private final IOrderPlatesRepository orderPlatesRepository;
    private final IOrderPlatesMapper orderPlatesMapper;
    private final IOrderMapper orderMapper;

    @Override
    public void saveOrderPlates(List<OrderPlates> orderPlates) {
        List<OrderPlatesEntity> orderPlatesEntities = new ArrayList<>();
        for (int i = 0; i < orderPlates.size(); i++) {
            orderPlatesEntities.add(orderPlatesMapper.toEntity(orderPlates.get(i)));
            orderPlatesEntities.get(i).setId(-1L);
        }
        orderPlatesRepository.saveAll(orderPlatesEntities);
    }

    @Override
    public List<OrderPlates> getAllOrderPlatesByOrderId(Long id) {
        return orderPlatesRepository.findAllByOrder(id).stream().map(orderPlatesEntity -> orderPlatesMapper.toOrderPlates(orderPlatesEntity)).collect(Collectors.toList());
    }
}
