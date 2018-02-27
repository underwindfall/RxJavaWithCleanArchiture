package com.practice.qifan.rxjavapractice.dagger.component;

import com.practice.qifan.rxjavapractice.dagger.module.ActivityModule;
import com.practice.qifan.rxjavapractice.dagger.module.ImageModule;
import com.practice.qifan.rxjavapractice.dagger.scope.PerActivity;
import com.practice.qifan.rxjavapractice.ui.fragment.Elementary.ElementaryFragment;
import com.practice.qifan.rxjavapractice.ui.fragment.Map.MapFragment;

import dagger.Component;

/**
 * Created by qifan on 2018/2/26.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, ImageModule.class})
public interface ImageComponent extends ActivityComponent {

    void inject(ElementaryFragment elementaryFragment);

    void inject(MapFragment mapFragment);

    final class Initializer {

        private Initializer() {
            throw new UnsupportedOperationException();
        }

        public static ImageComponent init(ApplicationComponent applicationComponent, ActivityModule activityModule) {
            return DaggerImageComponent.builder()
                    .applicationComponent(applicationComponent)
                    .imageModule(new ImageModule())
                    .activityModule(activityModule)
                    .build();
        }
    }
}
