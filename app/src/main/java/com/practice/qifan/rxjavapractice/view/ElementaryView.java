package com.practice.qifan.rxjavapractice.view;

import com.practice.qifan.rxjavapractice.mapper.ZhuangbiModel;

import java.util.Collection;

/**
 * Created by qifan on 2018/2/20.
 */

public interface ElementaryView {
    void renderUserList(Collection<ZhuangbiModel> userModelCollection);

    /**
     * View a {@link ZhuangbiModel} profile/details.
     *
     * @param zhuangbiModel The user that will be shown.
     */
    void viewUZhuangbi(ZhuangbiModel zhuangbiModel);
}
