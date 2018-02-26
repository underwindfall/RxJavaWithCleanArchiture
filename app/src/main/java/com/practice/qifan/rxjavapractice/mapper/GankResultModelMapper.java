package com.practice.qifan.rxjavapractice.mapper;

import com.practice.qifan.domain.bean.GankImageResultBean;
import com.practice.qifan.domain.modelMapper.Mapper;
import com.practice.qifan.rxjavapractice.dagger.scope.PerActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

/**
 * Created by qifan on 2018/2/26.
 */
@PerActivity
public class GankResultModelMapper implements Mapper<GankResultModel> {
    @Inject
    GankResultModelMapper() {
    }

    @Override
    public GankResultModel transform(Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof GankImageResultBean) {
            GankImageResultBean bean = (GankImageResultBean) object;
            GankResultModel model = new GankResultModel();
            model.setAuthor(bean.getAuthor());
            model.setId(bean.getId());
            model.setDate(bean.getDate());
            model.setSource(bean.getSource());
            model.setUsed(bean.getUsed());
            model.setUrl(bean.getUrl());
            model.setType(bean.getType());
            String formatCreateTime = formatDate(bean.getCreatedTime());
            String formatPublishTime = formatDate(bean.getPublishedTime());
            model.setCreatedTime(formatCreateTime);
            model.setPublishedTime(formatPublishTime);
            return model;
        }
        return null;
    }

    @Override
    public ArrayList<GankResultModel> transform(Collection objects) {
        ArrayList<GankResultModel> models = new ArrayList<>();
        if (objects != null && !objects.isEmpty()) {
            GankResultModel model;
            for (Object object : objects) {
                model = transform(object);
                if (model != null) {
                    models.add(model);
                }
            }
        }
        return models;
    }


    private String formatDate(String time) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS'Z'");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yy/MM/dd HH:mm:ss", Locale.US);
        try {
            Date date = inputFormat.parse(time);
            time = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            time = "unknown date";
        }

        return time;
    }

}
