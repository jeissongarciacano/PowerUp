package com.powerup.square.infraestructure.out.jpa.adapter;

import com.powerup.square.infraestructure.out.jpa.entity.OrderEntity;
import com.powerup.square.infraestructure.out.jpa.mapper.IOrderMapper;
import com.powerup.square.infraestructure.out.jpa.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderJpaAdapter {
    private final IOrderRepository orderRepository;
    private final IOrderMapper orderMapper;

    public OrderEntity saveOrderEntity(OrderEntity orderEntity){
        return orderRepository.save(orderEntity);
    }
    public List<OrderEntity> getAllOrder(){
        return orderRepository.findAll();
    }

    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
    }

    public OrderEntity editOrder(OrderEntity orderEntity){
        if(orderRepository.existsById(orderEntity.getId())){
            return orderRepository.save(orderEntity);
        }
        return null;
    }

    public boolean existByID(Long id) {
        return orderRepository.existsById(id);
    }
}
