package com.asto.a91recyclingtreasure.core

class Common {
    //常量字段定义在里面
    companion object {
        const val APP_NAME = "91RecyclingTreasure"

        const val BASE_URL = "http://192.168.1.101:7011/"//Debug地址
//        const val BASE_URL = "http://192.168.1.101:7011"//服务器地址
        const val HTTP_SUCCESS = 0 //网络请求成功
        const val HTTP_LOGIN_OUT = 120//重新登录
        const val HTTP_LOGIN_OUT_121 = 121//重新登录

        const val REQUEST_ORDER_LIST_FRAGMENT = 1000//订单List界面
        const val REQUEST_ORDER_DETAILS_LIST_FRAGMENT = 1001//订单详情List界面
        const val RESULT_REFRESH = 2000//返回刷新界面

        const val BEAN_TOKEN = "token"//登录成功返回的token
        const val BEAN_USER = "user"//登录成功返回的user


    }
}