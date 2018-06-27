package com.company.projectsales.web.order;

import com.company.projectsales.entity.OrderLine;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.company.projectsales.entity.Order;
import com.haulmont.cuba.gui.data.CollectionDatasource;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.UUID;

public class OrderEdit extends AbstractEditor<Order> {
    @Inject
    private CollectionDatasource<OrderLine, UUID> linesDs;

    @Override
    protected void initNewItem(Order item) {
        linesDs.addCollectionChangeListener(e -> calculateAmount());
    }
    private void calculateAmount(){
        BigDecimal amount = BigDecimal.ZERO;
        for(OrderLine line : linesDs.getItems())
            amount =amount.add(line.getProduct().getPrice().multiply(line.getQuantity()));
        getItem().setAmount(amount);
    }
}