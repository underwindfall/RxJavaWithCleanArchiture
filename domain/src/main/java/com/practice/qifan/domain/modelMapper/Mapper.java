package com.practice.qifan.domain.modelMapper;

/**
 * Created by qifan on 2018/2/20.
 */

import java.util.ArrayList;
import java.util.Collection;

/**
 * Mapper class used to transform objects (in the domain layer) to objects in the
 * app layer.
 *
 * @param <T> Class in the app layer
 */
public interface Mapper<T> {
    /**
     * Transform V into T.
     */
    T transform(Object o);

    /**
     * Transform a Collection of V into a List of T.
     */
    ArrayList<T> transform(Collection list);
}

