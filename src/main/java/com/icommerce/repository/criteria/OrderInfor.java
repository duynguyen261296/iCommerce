package com.icommerce.repository.criteria;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author kaiser
 */

@Getter
@Setter
@Builder
public class OrderInfor {
    private String fullName;
    private String phone;
    private String address;
    private String email;
    private String productName;
    private Integer quantity;
}
