package com.practice.qifan.rxjavapractice.mapper;

import com.practice.qifan.domain.bean.GankImageBean;
import com.practice.qifan.domain.modelMapper.Mapper;
import com.practice.qifan.rxjavapractice.dagger.scope.PerActivity;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

/**
 * Created by qifan on 2018/2/26.
 * GankModelMapper
 */
@PerActivity
public class GankModelMapper implements Mapper<GankModel> {
    private final GankResultModelMapper mGankResultModelMapper;

    @Inject
    public GankModelMapper(GankResultModelMapper gankResultModelMapper) {
        mGankResultModelMapper = gankResultModelMapper;
    }

    @Override
    public GankModel transform(Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof GankImageBean) {
            GankImageBean bean = (GankImageBean) object;
            GankModel model = new GankModel();
            model.setError(bean.getError());
            model.setGankResultModels(mGankResultModelMapper.transform(bean.getGankImageResultBean()));
            return model;
        }
        return null;
    }

    @Override
    public ArrayList<GankModel> transform(Collection objects) {
        ArrayList<GankModel> models = new ArrayList<>();
        if (objects != null && !objects.isEmpty()) {
            GankModel model;
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
