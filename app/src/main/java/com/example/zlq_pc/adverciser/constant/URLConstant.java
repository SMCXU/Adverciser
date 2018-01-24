package com.example.zlq_pc.adverciser.constant;

/**
 * Created by huangtao on 15/12/10.
 */
public class URLConstant {
//    public static final String BASE_URL = "http://topadvisor.api.test.haodai.com/index.php/";
//    public static final String HOST = "http://jpgw.haodai.com";
    public static final String HOST = "http://guwen.haodai.com";


    public static final String BASE_URL = HOST+"/App/";

    //首页
    public static final String HOME = BASE_URL + "Topic/home2";

    //首页加载更多
    public static final String HOME_LOAD_MORE = BASE_URL + "Topic/getListHome";

    //获取分类tag
    public static final String GET_SORT_TAGS = BASE_URL + "Topic/classifyData";

    //分类顾问列表
    public static final String SORT_BY_TAGS_ADVISOR = BASE_URL + "Topic/getListClassify";

    //搜索
    public static final String SEARCH_ADVISOR = BASE_URL + "Search/search";

    //该公司的入住顾问
    public static final String ADVISOR_BY_COMPANY = BASE_URL + "Search/guwenBycompany";

    //姓名List
    public static final String SEARCH_ADVISOR_BY_NAME = BASE_URL + "Search/guwenList";

    //公司List
    public static final String SEARCH_ADVISOR_BY_COMPANY = BASE_URL + "Search/companyList";

    //获取顾问详情
    public static final String GET_ADVISOR_DETAIL = BASE_URL + "Guwen/guwenInfo";

    //获取话题详情
    public static final String GET_TOPIC_DETAIL = BASE_URL + "Topic/topicInfo";

    //获取顾问的所有评价
    public static final String GET_ALL_COMMENT_DETAIL = BASE_URL + "Comment/guwenComment";

    //创建一个约会
    public static final String CREAT_NEW_ORDER = BASE_URL + "Order133/createOrder";

    //收藏
    public static final String COLLECTION_ADVISOR = BASE_URL + "Favourite/favourite";

    //我收藏的顾问
    public static final String COLLECTION_ADVISOR_LIST = BASE_URL + "Favourite/myFavourite";

    //我的积分
    public static final String MY_JIFEN = BASE_URL + "User/getJifen";

    //分享获取优惠券
    public static final String CREATE_DISCOUNT = BASE_URL + "Coupon/getCoupon";

    //我的优惠券
    public static final String MY_DISCOUNT = BASE_URL + "Coupon/couponList";

    //获取用户基本信息
    public static final String ACCOUNT = BASE_URL + "UserNew/userInfo";

    //金牌顾问查看订单
    public static final String ADVISOR_ORDER = BASE_URL + "Order/orderMy";

    //普通用户查看订单
    public static final String MY_ORDER = BASE_URL + "Order/myOrder";


    //同意约见
    public static final String ACCEPT_ORDER = BASE_URL + "Order/guwenAccept";

    //忽略订单
    public static final String IGNORE_ORDER = BASE_URL + "Order/guwenRefuse";

    //取消约见
    public static final String CANCEL_ORDER = BASE_URL + "Order/userCancel";

    //模拟付款
    public static final String PAY_MONI = BASE_URL + "Order/payMoni";

//    //付款
//    public static final String PAY =BASE_URL+"Pingpp/pay";

    //新支付
    public static final String NEW_PAY =BASE_URL+"NewPay/pay";

    //支付团购
    public static final String PAY_GROUP_BUY =BASE_URL+"GroupPay/pay";

    //获取我的积分和可以使用的一张优惠券
    public static final String GET_COUPON =BASE_URL+ "Coupon/myDiscount";

    //顾问 确认见过
    public static final String CONFIRM_MEET = BASE_URL + "Order/confirmMeet";

    //评价
    public static final String SAVE_COMMENT = BASE_URL + "Comment/userComment";

    //订单状态
    public static final String ORDER_DETAIL = BASE_URL + "Order/orderProgress";

    //登陆
    public static final String LOGIN = BASE_URL + "User/login";

    //获取验证码
    public static final String GET_VALIDATE = BASE_URL + "User/createVerify";

    //注册
    public static final String REGISTER = BASE_URL + "User/register";

    //退出登陆
    public static final String LOGOUT = BASE_URL + "User/logout";

    //密码重置
    public static final String RESET_PWD = BASE_URL + "User/setPwd";

    //修改密码
    public static final String CHANGE_PWD = BASE_URL + "User/changePwd";

    //通过密码修改手机号
    public static final String CHANGE_BIND_PHONE_BY_PWD = BASE_URL + "User/changeTelbypwd";

    //验证码判断是否是原手机
    public static final String VALIDATE_OLD_PHONE_BY_CODE = BASE_URL + "User/changeTelbytelone";

    //通过原手机修改绑定手机
    public static final String CHANGE_BIND_PHONE_BY_OLD_PHONE = BASE_URL + "User/changeTelbyteltwo";

    //上传普通用户头像
    public static final String UPLOAD_NORMAL_AVATER = BASE_URL + "User/saveUavatar";

    //上传申请顾问头像
    public static final String UPLOAD_APPLY_ADVISOR_AVATER = BASE_URL + "Guwen/saveGavatar";

    //上传顾问头像
    public static final String UPLOAD_ADVISOR_AVATER = BASE_URL + "Guwen/editGavatar";

    //顾问和准顾问基本信息
    public static final String ADVISOR_GET_BASE_INFO = BASE_URL + "Guwen/getGbasic";

    //申请成为金牌顾问  基本信息
    public static final String APPLY_ADVISOR_SAVE_BASE_INFO = BASE_URL + "Guwen/saveGbasic";

    //编辑顾问基本信息
    public static final String APPLY_ADVISOR_EDIT_BASE_INFO = BASE_URL + "Guwen/editBasic";

    //判断用户走到第几布
    public static final String APPLY_ADVISOR_STEP = BASE_URL + "Guwen/getSubmitStep";

    //提交个人介绍
    public static final String APPLY_ADVISOR_SAVE_INTRODUCE = BASE_URL + "Guwen/saveGintro";

    //更新个人提交
    public static final String APPLY_ADVISOR_EDIT_INTRODUCE = BASE_URL + "Guwen/editIntro";

    //获取个人介绍
    public static final String APPLY_ADVISOR_INTRODUCE = BASE_URL + "Guwen/getGintro";

    //提交话题
    public static final String APPLY_ADVISOR_SAVE_TOPIC = BASE_URL + "Guwen/topicSet";

    //添加话题
    public static final String APPLY_ADVISOR_ADD_TOPIC = BASE_URL + "Guwen/addTopic";

    //编辑话题
    public static final String APPLY_ADVISOR_EDIT_TOPIC = BASE_URL + "Guwen/editTopic";

    //提交审核
    public static final String APPLY_ADVISOR_SUBMIT = BASE_URL + "Guwen/doSubmit";

    //获取我的话题
    public static final String MY_TOPIC_LIST = BASE_URL + "Guwen/getMyTopic";

    //编辑用户基本信息
    public static final String EDIT_NORMAL_USER_BASE_INFO = BASE_URL + "User/editInfo";

    //定位有关
    //省
    public static final String GET_PROVICE_LIST = BASE_URL + "Index/province";
    //市
    public static final String GET_CITY_LIST = BASE_URL + "Index/city";
    //推荐城市
    public static final String GET_HOT_CITY = BASE_URL + "Index/cityTuijian";

    //发现
    //新鲜
    public static final String NEW_FRAG = BASE_URL + "Topic/topicFind";

    //好评
    public static final String GOOD_COMMENT = BASE_URL + "Comment/commentList";

    //行长圈
    public static final String PRIVATE_PLACEMENT_LIST = BASE_URL + "Simuquan/simuList";

    //发布需求
//    public static final String RELEASE_PRIVATE_PLACEMENT = BASE_URL + "SimuNew/simuSave139";
    public static final String RELEASE_PRIVATE_PLACEMENT = BASE_URL + "SimuNew/simuSave140";

    //行长圈详请
    public static final String PRIVATE_PLACEMENT_DETAIL = BASE_URL + "Simuquan/simuInfo";
    //提交联系方式
    public static final String PRIVATE_PLACEMENT_SUBMIT_CONTACT = BASE_URL + "Simuquan/saveContact";
    //我发布的行长圈
    public static final String MINE_PRIVATE_PLACEMENT = BASE_URL + "Simuquan/mySimuq";
    //我感兴趣的行长圈
    public static final String COLLECTION_PRIVATE_PLACEMENT = BASE_URL + "Simuquan/simuqFavourite";

    //清除消息个数
    public static final String CLEAR_MSG = BASE_URL+"UserNew/setSimuread";

    //检查是否更新
    public static final String CHECK_UPDATE = BASE_URL+"Index/versionAndroid";

    //更新个推id
    public static final String UPDATE_CLIENT_ID = BASE_URL + "User/getClientId";
    //分享
    public static final String SHARE_URL = "http://guwen.haodai.com/Wx/Guwen/guwenInfo?id=";
}
