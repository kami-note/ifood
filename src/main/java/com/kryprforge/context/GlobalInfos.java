package com.kryprforge.context;

import com.kryprforge.models.Address;

import java.util.ArrayList;
import java.util.List;

public class GlobalInfos {
    private Long selectedRestaurantId;
    private List<Long> selectedProducts = new ArrayList<>();
    private List<Long> selectedAccompaniments = new ArrayList<>();
    private Address deliveryAddress;
    private Long idAddressCurrent;
    private Long paymentMethod;

    public Long getIdAddressCurrent() {
        return idAddressCurrent;
    }

    public void setIdAddressCurrent(Long idAddressCurrent) {
        this.idAddressCurrent = idAddressCurrent;
    }

    public void setSelectedAccompaniments(List<Long> selectedAccompaniments) {
        this.selectedAccompaniments = selectedAccompaniments;
    }

    public void setSelectedProducts(List<Long> selectedProducts) {
        this.selectedProducts = selectedProducts;
    }

    public Long getSelectedRestaurantId() {
        return selectedRestaurantId;
    }

    public void setSelectedRestaurantId(Long selectedRestaurantId) {
        this.selectedRestaurantId = selectedRestaurantId;
    }

    public List<Long> getSelectedProducts() {
        return selectedProducts;
    }

    public void addProduct(Long productId) {
        this.selectedProducts.add(productId);
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Long getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Long paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void resetOrder() {
        clearSelectedAccompaniments();
        clearSelectedProducts();

        selectedRestaurantId = null;
        deliveryAddress = null;
        idAddressCurrent = null;
        paymentMethod = null;
    }

    public void clearSelectedProducts() {
        selectedProducts.clear();
    }

    public void clearSelectedAccompaniments() {
        selectedAccompaniments.clear();
    }

    public void addSelectedProduct(Long productID) {
        selectedProducts.add(productID);
    }

    public void addAccompaniment(Long accompanimentId) {
        selectedAccompaniments.add(accompanimentId);
    }

    public List<Long> getSelectedAccompaniments() {
        return selectedAccompaniments;
    }
}
