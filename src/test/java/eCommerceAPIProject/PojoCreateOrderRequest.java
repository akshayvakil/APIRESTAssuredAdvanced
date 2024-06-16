package eCommerceAPIProject;

import java.util.List;

/**
 * Json is as below
 * {
    "orders": [             
        {
            "country": "India",
            "productOrderedId": "665cb57dae2afd4c0beec097"
        }
    ]
}
 * @author AKSHAY
 *
 */

public class PojoCreateOrderRequest {
	
	private List<SubPojoCreateOrderRequest> orders; //this is main list in json which has attributes country and  productOrderedId

	public List<SubPojoCreateOrderRequest> getOrders() {
		return orders;
	}

	public void setOrders(List<SubPojoCreateOrderRequest> orders) {
		this.orders = orders;
	}
	
	

}
