package com.order_service.service.orderServiceImpl;

import com.order_service.clients.CartClient;
import com.order_service.clients.UserProfileClient;
import com.order_service.dto.*;
import com.order_service.entity.Order;
import com.order_service.entity.OrderItem;
import com.order_service.enums.OrderStatus;
import com.order_service.enums.PaymentMode;
import com.order_service.mapper.OrderItemMapper;
import com.order_service.mapper.OrderMapper;
import com.order_service.mapper.ShippingAddressMapper;
import com.order_service.repo.OrderRepo;
import com.order_service.service.orderService.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final CartClient cartClient;
    private final UserProfileClient userProfileClient;
    private final OrderRepo orderRepo;

    /// create order from cart

    @Override
    public OrderDto orderFromCart(Long cartId) {

        ResponseCartDto cartDto = cartClient.getCartById(cartId).getBody();
        if (cartDto == null) return null;

        // Convert CartItems → OrderItems
        List<OrderItem> orderItems = cartDto.getItems().stream()
                .map(OrderItemMapper::toEntity)
                .toList();

        // Fetch default address from UserProfile Service
        ResponseAddressDto addressDto = userProfileClient.getDefaultAddress(cartDto.getCartId()).getBody();

        ShippingAddressDto shippingAddress = null;
        if (addressDto != null) {
            shippingAddress = ShippingAddressDto.builder()
                    .street(addressDto.getStreet())
                    .city(addressDto.getCity())
                    .state(addressDto.getState())
                    .postalCode(addressDto.getPostalCode())
                    .country(addressDto.getCountry())
                    .build();
        }

        // Create Order
        Order order = Order.builder()
                .userId(cartDto.getUserId())
                .cartId(cartId)
                .items(orderItems)
                .status(OrderStatus.PENDING)
                .paymentMode(PaymentMode.COD)
                .totalAmount(cartDto.getSubtotal())
                .shippingAddress(ShippingAddressMapper.toEntity(shippingAddress)) // snapshot
                .build();

        Order savedOrder = orderRepo.save(order);

        return OrderMapper.toDto(savedOrder);
    }


    @Override
    public ResponseAddressDto addShippingAddress(ShippingAddressDto dto) {
        return null;
    }

}
