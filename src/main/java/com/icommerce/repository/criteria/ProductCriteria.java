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
public class ProductCriteria {
    private String name;
    private String brand;
    private Double minPrice;
    private Double maxPrice;
}
