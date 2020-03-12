package com.madreain.morepopup;

import android.os.Parcel;

import androidx.annotation.Keep;

/**
 * @author madreain
 * @date 2020-03-10.
 * module：重写toString方法，toString返回的数据用于展示
 * description：
 */
@Keep
public class TestData {

    private String name;

    public TestData(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
