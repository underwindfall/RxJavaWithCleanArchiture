package com.practice.qifan.rxjavapractice.mapper;

import com.practice.qifan.domain.bean.ZhuangbiImageBean;
import com.practice.qifan.domain.modelMapper.Mapper;
import com.practice.qifan.rxjavapractice.dagger.scope.PerActivity;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

/**
 * Created by qifan on 2018/2/20.
 */
@PerActivity
public class ZhuangbiModelMapper implements Mapper<ZhuangbiModel> {
    @Inject
    public ZhuangbiModelMapper() {
    }

    @Override
    public ZhuangbiModel transform(Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof ZhuangbiImageBean) {
            ZhuangbiImageBean bean = (ZhuangbiImageBean) object;
            ZhuangbiModel model = new ZhuangbiModel();
            model.setDescription(bean.getDescription());
            model.setImageUrl(bean.getImageUrl());
            return model;
        }
        return null;
    }

    @Override
    public ArrayList<ZhuangbiModel> transform(Collection objects) {
        ArrayList<ZhuangbiModel> models = new ArrayList<>();
        if (objects != null && !objects.isEmpty()) {
            ZhuangbiModel model;
            for (Object object : objects) {
                model = transform(object);
                if (model != null) {
                    models.add(model);
                }
            }
        }
        return models;
    }
}
