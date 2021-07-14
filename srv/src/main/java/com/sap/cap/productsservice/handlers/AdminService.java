package com.sap.cap.productsservice.handlers;

import com.sap.cds.services.cds.CdsCreateEventContext;
import com.sap.cds.services.cds.CdsReadEventContext;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.sap.cds.services.cds.CdsService.EVENT_CREATE;
import static com.sap.cds.services.cds.CdsService.EVENT_READ;

@Service
@ServiceName("AdminService")
public class AdminService implements EventHandler {

    private Map<Object, Map<String, Object>> products = new HashMap<>();

    @On(event = EVENT_CREATE, entity = "AdminService.Products")
    public void onCreate(CdsCreateEventContext context) {
        context.getCqn().entries()
                .forEach(product -> products.put(product.get("ID"), product));
        context.setResult(context.getCqn().entries());
    }

    @On(event = EVENT_READ, entity = "AdminService.Products")
    public void onRead(CdsReadEventContext context) {
        context.setResult(products.values());
    }
}
