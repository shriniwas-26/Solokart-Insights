package com.billing.project.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.billing.project.custom_exception.ResourceNotFoundException;
import com.billing.project.dto.ApiResponse;
import com.billing.project.dto.OrderDto;
import com.billing.project.dto.OrderRequest;
import com.billing.project.dto.OrderStatusDto;
import com.billing.project.dto.UserResp;
import com.billing.project.entities.Item;
import com.billing.project.entities.Order;
import com.billing.project.entities.OrderItem;
import com.billing.project.entities.OrderStatus;
import com.billing.project.entities.User;
import com.billing.project.repos.ItemRepository;
import com.billing.project.repos.OrderItemRepository;
import com.billing.project.repos.OrderRepository;
import com.billing.project.repos.UserRepository;
import com.billing.project.services.ItemService;
import com.billing.project.services.OrderService;
import com.billing.project.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;
	private final UserService userService;
	private final ModelMapper modelMapper;
	private final UserRepository userRepository;
	private final OrderItemRepository orderItemRepository;
	private final ItemService itemService;

	@Override
	public List<OrderDto> getAll() {
		return orderRepository.findAll().stream().map(order -> modelMapper.map(order, OrderDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public OrderDto getById(Long id) {
		Order order = orderRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
		return modelMapper.map(order, OrderDto.class);
	}

	@Override
	public List<OrderDto> getByUserId(Long userId) {
		return orderRepository.findByUserId(userId).stream().map(order -> modelMapper.map(order, OrderDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public OrderDto create(OrderRequest orderRequest) {

		User user = userRepository.findById(orderRequest.getUserId()).orElseThrow(
				() -> new ResourceNotFoundException("Cannot create order because user not found in DB...."));

		Order order = modelMapper.map(orderRequest, Order.class);
		order.setStatus(OrderStatus.PENDING);
		order.setUser(user);

		// Create and add order items
		List<OrderItem> orderItems = orderRequest.getOrderItems().stream().map(itemRequest -> {
			Item item = itemService.getItemById(itemRequest.getItemId());
			OrderItem orderItem = new OrderItem();
			orderItem.setItem(item);
			orderItem.setQuantity(itemRequest.getQuantity());
			orderItem.setPriceAtOrderTime(item.getPrice() * itemRequest.getQuantity());

			// This is the crucial part - setting both sides of the relationship
			orderItem.setOrder(order);
			
			return orderItem;
		}).collect(Collectors.toList());

		
		order.setOrderItems(orderItems);

		double total = orderRequest.getOrderItems().stream().mapToDouble(oItem -> {

			Item item = itemService.getItemById(oItem.getItemId());
			return item.getPrice() * oItem.getQuantity();

		}

		).sum();
		order.setTotalAmount(total);

		orderRepository.save(order);
		OrderDto orderDto = modelMapper.map(order, OrderDto.class);
		orderDto.setUser(modelMapper.map(user, UserResp.class));
		return orderDto;

//        // Verify user exists
//        User user = userRepository.findById(orderDto.getUserId())
//        		.orElseThrow(()-> new ResourceNotFoundException("Cannot create order because user not found in DB...."));
//
//        Order order = modelMapper.map(orderDto, Order.class);
//        order.setStatus(OrderStatus.PENDING);
//
//        // Calculate total amount from order items if not provided
//        
//            double total = orderDto.getOrderItems().stream()
//                    .mapToDouble(item -> item.getPriceAtOrderTime() * item.getQuantity())
//                    .sum();
//            order.setTotalAmount(total);
//        
//
//        Order savedOrder = orderRepository.save(order);

//        return new ApiResponse("Order created successfully....");
	}

	@Override
	public ApiResponse delete(Long id) {
		// TODO Auto-generated method stub
		if (!orderRepository.existsById(id)) {
			throw new ResourceNotFoundException("Order not found with id: " + id);
		}
		orderRepository.deleteById(id);
		return new ApiResponse("Order deleted successfully....");
	}

	@Override
	public OrderDto updateOrderStatus(Long id, OrderStatusDto orderStatus) {
		// TODO Auto-generated method stub
		Order order = orderRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
		order.setStatus(orderStatus.getStatus());
		orderRepository.save(order);
		return modelMapper.map(order, OrderDto.class);
	}

}
