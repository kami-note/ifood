package com.kryprforge.service;

import com.kryprforge.context.GlobalInfos;
import com.kryprforge.dao.*;
import com.kryprforge.models.*;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private final GlobalInfos globalInfos;
    private final OrderItemDAO orderItemDAO;
    private final ProductDAO productDAO;
    private final CustomerOrderDAO customerOrderDAO;
    private final OrderProductAccompanimentDAO orderProductAccompanimentDAO;
    private final AccompanimentDAO accompanimentDAO;

    public OrderService(GlobalInfos globalInfos, OrderItemDAO orderItemDAO, ProductDAO productDAO, CustomerOrderDAO customerOrderDAO, OrderProductAccompanimentDAO orderProductAccompanimentDAO, AccompanimentDAO accompanimentDAO) {
        this.globalInfos = globalInfos;
        this.orderItemDAO = orderItemDAO;
        this.productDAO = productDAO;
        this.customerOrderDAO = customerOrderDAO;
        this.orderProductAccompanimentDAO = orderProductAccompanimentDAO;
        this.accompanimentDAO = accompanimentDAO;
    }

    public void insertOrder() {
        CustomerOrder customerOrder = createCustomerOrder();
        if (customerOrder == null) {
            logError("Falha ao criar a ordem do cliente.");
            return;
        }

        List<OrderItem> orderItems = createOrderItems(customerOrder);
        saveOrderItems(orderItems);

        List<OrderProductAccompaniment> accompaniments = createOrderProductAccompaniments(orderItems);
        saveOrderAccompaniments(accompaniments);
    }

    private CustomerOrder createCustomerOrder() {
        CustomerOrder customerOrder = new CustomerOrder(10, 1, null);
        Long customerOrderID = customerOrderDAO.save(customerOrder);

        if (customerOrderID != null) {
            customerOrder.setId(customerOrderID);
            return customerOrder;
        }
        return null;
    }

    private List<OrderItem> createOrderItems(CustomerOrder customerOrder) {
        List<Long> selectedProductIds = globalInfos.getSelectedProducts();
        List<OrderItem> orderItems = new ArrayList<>();

        for (Long productId : selectedProductIds) {
            Product product = productDAO.findById(productId);
            if (product != null) {
                OrderItem orderItem = createOrderItem(product, customerOrder);
                orderItems.add(orderItem);
            } else {
                logError("Produto não encontrado: " + productId);
            }
        }
        return orderItems;
    }

    private OrderItem createOrderItem(Product product, CustomerOrder order) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setQuantity(1);
        orderItem.setStatus(Status.ACTIVE);
        orderItem.setOrder(order);
        return orderItem;
    }

    private void saveOrderItems(List<OrderItem> orderItems) {
        if (orderItems.isEmpty()) {
            logError("Nenhum item inserido na ordem.");
        } else {
            for (OrderItem orderItem : orderItems) {
                orderItemDAO.save(orderItem);
            }
            logInfo("Itens de ordem inseridos com sucesso.");
        }
    }

    private List<OrderProductAccompaniment> createOrderProductAccompaniments(List<OrderItem> orderItems) {

        List<Long> selectedAccompanimentIds = globalInfos.getSelectedAccompaniments();


        List<OrderProductAccompaniment> accompaniments = new ArrayList<>();

        for (OrderItem orderItem : orderItems) {
            for (Long accompanimentId : selectedAccompanimentIds) {
                Accompaniment accompaniment = accompanimentDAO.findById(accompanimentId);
                if (accompaniment != null) {
                    OrderProductAccompaniment accompanimentEntry = new OrderProductAccompaniment();
                    accompanimentEntry.setOrderItem(orderItem);
                    accompanimentEntry.setAccompaniment(accompaniment);
                    accompaniments.add(accompanimentEntry);
                } else {
                    logError("Acompanhamento não encontrado: " + accompanimentId);
                }
            }
        }
        return accompaniments;
    }

    private void saveOrderAccompaniments(List<OrderProductAccompaniment> accompaniments) {
        if (accompaniments.isEmpty()) {
            logError("Nenhum acompanhamento inserido.");
        } else {
            for (OrderProductAccompaniment accompaniment : accompaniments) {
                orderProductAccompanimentDAO.save(accompaniment);
            }
            logInfo("Acompanhamentos inseridos com sucesso.");
        }
    }

    private void logError(String message) {
        System.err.println(message);
    }

    private void logInfo(String message) {
        System.out.println(message);
    }
}
