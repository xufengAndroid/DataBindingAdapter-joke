package com.xufeng.xfproject.contract;

import com.xufeng.xfproject.base.BasePresenter;
import com.xufeng.xfproject.base.BaseView;
import com.xufeng.xfproject.data.dto.JokeDto;

/**
 * Created by xufeng on 2016/12/5.
 */

public interface JokeContract {

    interface View extends BaseView<Presenter>,PageContract.View<JokeDto> {
    }


    interface Presenter extends BasePresenter,PageContract.Presenter{
    }

}
