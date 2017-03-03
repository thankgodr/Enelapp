package com.shixels.thankgodrichard.enellottoapp;

import com.quickblox.users.model.QBUser;

/**
 * Created by thankgodrichard on 1/10/17.
 */

public interface CallBackFunc {
    void success(QBUser user);
    void error(String error);
}
