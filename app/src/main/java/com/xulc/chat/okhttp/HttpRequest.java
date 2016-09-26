package com.xulc.chat.okhttp;

import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * app中所有的接口请求
 * Created by xuliangchun on 2016/9/21.
 */
public class HttpRequest {
    /**
     * 登录
     * @param username
     * @param password
     * @param code
     * @param listener
     */
    public static void login(String username,String password,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        map.put("ttlseconds","604800");
        map.put("apptype","1");
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.getAuthDomain(),HttpInterfaceRequest.applogin,map,code,listener);
    }

    /**
     * 注册
     * @param mobile
     * @param password
     * @param smsCode
     * @param code
     * @param listener
     */
    public static void registAccount(String mobile,String password,String smsCode,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("mobile",mobile);
        map.put("password",password);
        map.put("smsCode",smsCode);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.getApiDomain(),HttpInterfaceRequest.RedistAccount,map,code,listener);
    }


    /**
     * 退出
     * @param code
     * @param listener
     */
    public static void Logout(int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.getAuthDomain(),HttpInterfaceRequest.LogoutUrl,map,code,listener);
    }

    /**
     * 查询用户的vip
     * @param partyId
     * @param code
     * @param listener
     */
    public static void queryUserVip(String partyId,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("partyId",partyId);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.queryUserVip, map, code, listener);
    }


    /**
     * 获取用户资料
     * @param partyId
     * @param code
     * @param listener
     */
    public static void getUserDetail(String partyId,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("partyId",partyId);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.GetUserDetailUrl, map, code, listener);
    }

    /**
     * 上传文件token申请
     * @param code
     * @param listener
     */
    public static void uploadApply(int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("isPublic","true");
        map.put("tokenEffectTimeMinutes","10080");//(7 * 24 * 60)文件上传token的有效期，分钟数，在此时间内，token可一直使用
        map.put("deleteAfterDays","0");//文件上传后，保留多少天，如果填0，表示永久保存。
        map.put("sizeLimitBytes","10485760");//限制上传文件大小（字节），默认为5242880，即5MB。这里是10M
        map.put("imageOnly","false");
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.fileUpload,map,code,listener);
    }


    /**
     * 修改用户信息
     * @param firstName
     * @param imgKey
     * @param province
     * @param city
     * @param area
     * @param address
     * @param gender
     * @param birthDate
     * @param code
     * @param listener
     */
    public static void modifyPersonInfo(String firstName,String imgKey,String province,String city,String area,String address,String gender,String birthDate,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        if (!TextUtils.isEmpty(firstName)){
            map.put("firstName",firstName);
        }
        if (!TextUtils.isEmpty(imgKey)){
            map.put("imgKey",imgKey);
        }
        if (!TextUtils.isEmpty(province)){
            map.put("province",province);
        }
        if (!TextUtils.isEmpty(city)){
            map.put("city",city);
        }
        if (!TextUtils.isEmpty(area)){
            map.put("area",area);
        }
        if (!TextUtils.isEmpty(address)){
            map.put("address",address);
        }
        if (!TextUtils.isEmpty(gender)){
            map.put("gender",gender);
        }
        if (!TextUtils.isEmpty(birthDate)){
            map.put("birthDate",birthDate);
        }
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("reqseq","1");

        HttpUtil.enqueue(HttpInterfaceRequest.ModifyPersonInfo,map,code,listener);
    }

    /**
     * 获取省市区列表
     * @param version
     * @param code
     * @param listener
     */
    public static void getGeoAll(String version,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("version",version);
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.GetGeoALL, map, code, listener);
    }

    /**
     * 修改密码/找回密码通用
     * @param mobile
     * @param newPassword
     * @param smsCode
     * @param code
     * @param listener
     */
    public static void findPwd(String mobile,String newPassword,String smsCode,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("mobile",mobile);
        map.put("newPassword",newPassword);
        map.put("smsCode",smsCode);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.FindPwd, map, code, listener);
    }

    /**
     * 发送验证码
     * @param mobile
     * @param reason  修改密码  3
     * @param code
     * @param listener
     */
    public static void sendSms(String mobile,int reason,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("mobile",mobile);
        map.put("reason",String.valueOf(reason));
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.SendSms, map, code, listener);
    }


    /**
     * 创建讨论组
     * @param name
     * @param desc
     * @param imgKey
     * @param code
     * @param listener
     */
    public static void createCicle(String name,String desc,String imgKey,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("reqseq", "1");
        map.put("name", name);
        if (!TextUtils.isEmpty(desc)){
            map.put("desc", desc);
        }
        if (!TextUtils.isEmpty(imgKey)){
            map.put("imgKey", imgKey);
        }
        HttpUtil.enqueue(HttpInterfaceRequest.CreateFamilyCicle, map, code, listener);
    }

    /**
     * 拉人进讨论组
     * @param familyGroupId
     * @param memberMobiles
     * @param code
     * @param listener
     * @param reqseq
     */
    public static void addFamilyMember(String familyGroupId,String memberMobiles,int code,ResponseListener listener,int reqseq){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("reqseq", String.valueOf(reqseq));
        map.put("familyGroupId", familyGroupId);
        map.put("memberMobiles", memberMobiles);
        HttpUtil.enqueue(HttpInterfaceRequest.AddMember, map, code, listener);
    }

    /**
     * 获取讨论组中的成员列表
     * @param familyGroupId
     * @param code
     * @param listener
     */
    public static void getMemberList(String familyGroupId,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("familyGroupId",familyGroupId);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.get_home_circle_member, map, code, listener);
    }


    /**
     * 修改讨论组名称和图片
     * @param familyGroupId
     * @param familyGroupName
     * @param imgKey
     * @param code
     * @param listener
     */
    public static void modifyCicleInfo(String familyGroupId, String familyGroupName,String imgKey,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("reqseq", "1");
        map.put("familyGroupId", familyGroupId);
        if (!TextUtils.isEmpty(familyGroupName)){
            map.put("familyGroupName", familyGroupName);
        }
        if (!TextUtils.isEmpty(imgKey)){
            map.put("imgKey", imgKey);
        }
        HttpUtil.enqueue(HttpInterfaceRequest.ModifyCicleName, map, code, listener);
    }



    /**
     * 获取最新版本
     * @param userType
     * @param versionCode
     * @param code
     * @param listener
     */
    public static void getLastVersion(UserType userType,int versionCode,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        String appName = "QB_APP";
        if(userType == UserType.TV)
            appName = "QB_TV_BOX";
        map.put("appName",appName);
        map.put("appType","ANDROID");
        map.put("versionCode",String.valueOf(versionCode));
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.getLastVersion, map, code, listener);
    }

    /**
     * 查询登录用户的购物车信息
     * @param code
     * @param listener
     */
    public static void findShoppingCarts(int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.FindShoppingCarts, map, code, listener);
    }

    /**
     * 查询购物车商品
     * @param code
     * @param listener
     */
    public static void getShopCartList(int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.GetShoppingList, map, code, listener);
    }


    /**
     * 更新购物车中商品数量
     * @param shoppinglistId
     * @param shoppinglistItemSeqId
     * @param itemAccount
     * @param code
     * @param listener
     */
    public static void updateShoppingAccount(String shoppinglistId,String shoppinglistItemSeqId,String itemAccount,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("shoppinglistId",shoppinglistId);
        map.put("shoppinglistItemSeqId",shoppinglistItemSeqId);
        map.put("itemAccount",itemAccount);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.UpdateShoppingAccount, map, code, listener);
    }

    /**
     * 删除购物车中某一项产品
     * @param shoppingItemSeqId
     * @param code
     * @param listener
     */
    public static void deleteShoppingItem(String shoppingItemSeqId,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("shoppingItemSeqId",shoppingItemSeqId);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.DeleteShoppingItem, map, code, listener);
    }



    /**
     * 获取好友列表
     * @param code
     * @param listener
     */
    public static void getFriendList(int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.GetFriendList, map, code, listener);
    }

    /**
     * 修改好友备注
     * @param friendUserLoginId
     * @param remarkName
     * @param code
     * @param listener
     * @throws UnsupportedEncodingException
     */
    public static void modifyRemarkName(String friendUserLoginId,String remarkName,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("reqseq", "1");
        map.put("friendUserLoginId", friendUserLoginId);
        map.put("remarkName", remarkName);
        HttpUtil.enqueue(HttpInterfaceRequest.ModifyRemarkName, map, code, listener);
    }

    /**
     * 获取族谱成员列表
     * @param code
     * @param listener
     */
    public static void FamilyMemberList(int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.FamilyMemberList, map, code, listener);
    }

    /**
     * 创建族谱活动
     * @param activityDetail
     * @param partyIds
     * @param code
     * @param listener
     */
    public static void creatFamilyActivity(String activityDetail,String partyIds,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("activityDetail",activityDetail);
        map.put("partyIds",partyIds);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.CreatFamilyActivity, map, code, listener);
    }

    /**
     * 获取关注的老人列表
     * @param code
     * @param listener
     */
    public static void GetRelatives(int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.GetRelatives, map, code, listener);
    }

    /**
     * 获取商城一级分类 （养老院信息以及养老院下方老人）
     * @param code
     * @param listener
     */
    public static void findMyStores(int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.FindMyStores, map, code, listener);
    }

    /**
     * 获取二级分类
     * @param geraId
     * @param code
     * @param listener
     */
    public static void getProductCategory(String geraId,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("geraId",geraId);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.GetProductCategory, map, code, listener);
    }


    /**
     * 好友申请
     * @param receiverId
     * @param applyText
     * @param code
     * @param listener
     */
    public static void applyFriend(String receiverId,String applyText,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("receiverId",receiverId);
        map.put("applyText", applyText);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.applyFriend, map, code, listener);
    }

    /**
     * 删除好友
     * @param receiverId
     * @param code
     * @param listener
     */
    public static void removeFriend(String receiverId,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("receiverId",receiverId);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.removeFriend, map, code, listener);
    }



    /**
     * 同意或拒绝好友申请
     * @param receiverId
     * @param agree
     * @param code
     * @param listener
     * @param reqseq  接口会原样返回
     */
    public static void agreeOrRefuseFriend(String receiverId,boolean agree,int code,ResponseListener listener,int reqseq){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("receiverId", receiverId);
        map.put("reqseq", String.valueOf(reqseq));
        if (agree){
            HttpUtil.enqueue(HttpInterfaceRequest.agreeFriend, map, code, listener);
        }else {
            HttpUtil.enqueue(HttpInterfaceRequest.refuseFriend, map, code, listener);
        }

    }

    /**
     * 同意或拒绝加入族谱
     * @param familyTreeId 族谱ID
     * @param treeNodeId 节点ID
     * @param memberId 成员ID
     * @param agree
     * @param code
     * @param listener
     * @param reqseq
     */
    public static void agreeOrRefuseFamily(String familyTreeId,String treeNodeId,String memberId,boolean agree,int code,ResponseListener listener,int reqseq){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("familyTreeId", familyTreeId);
        map.put("treeNodeId", treeNodeId);
        map.put("memberId", memberId);
        map.put("agree", String.valueOf(agree));
        map.put("reqseq",String.valueOf(reqseq));
        HttpUtil.enqueue(HttpInterfaceRequest.FamilyTreeAgree, map, code, listener);
    }

    /**
     * 同意或拒绝加入养老院
     * @param geraId
     * @param agree
     * @param code
     * @param listener
     * @param reqseq
     */
    public static void agreeOrRefuseGrea(String geraId,boolean agree,int code,ResponseListener listener,int reqseq){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("geraId", geraId);
        map.put("agree", String.valueOf(agree));
        map.put("reqseq",String.valueOf(reqseq));
        HttpUtil.enqueue(HttpInterfaceRequest.agreeOrRefuseJoinGera, map, code, listener);
    }

    /**
     * 查询未完成的服务列表
     * @param partyId
     * @param code
     * @param listener
     */
    public static void GetUnEndTasks(String partyId,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("partyId", partyId);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.GetUnEndTasks, map, code, listener);
    }

    /**
     * 查询可预约的服务列表
     * @param toPartyId
     * @param code
     * @param listener
     */
    public static void GetAppointableTasks(String toPartyId,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("toPartyId", toPartyId);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.GetAppointableTasks, map, code, listener);
    }

    /**
     * 推荐服务列表
     * @param toPartyId
     * @param code
     * @param listener
     */
    public static void RecommendProductes(String toPartyId,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("toPartyId", toPartyId);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.RecommendProductes, map, code, listener);
    }

    /**
     * 查询历史任务列表(分页)
     * @param partyId
     * @param viewIndex
     * @param viewSize
     * @param code
     * @param listener
     */
    public static void GetHistoryTasks(String partyId,int viewIndex,int viewSize,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("partyId", partyId);
        map.put("viewIndex", String.valueOf(viewIndex));
        map.put("viewSize", String.valueOf(viewSize));
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.GetHistoryTasks, map, code, listener);
    }


    /**
     * 获取当天待处理的任务
     * @param code
     * @param listener
     */
    public static void getNeedProcessTaskList(int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("viewIndex", "1");
        map.put("viewSize", "100");
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.GetTodayWorkEffort, map, code, listener);
    }

    /**
     * 正在进行
     * @param code
     * @param listener
     */
    public static void getDoingWorkList(int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.GetDoingWorkList, map, code, listener);
    }

    /**
     * 获取当天抢单列表
     * @param code
     * @param listener
     */
    public static void getGrabWorkEffortList(int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("viewIndex", "1");
        map.put("viewSize", "100");
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.GetGrabWorkEffort, map, code, listener);
    }

    /**
     * 历史任务单
     * @param viewIndex
     * @param viewSize
     * @param code
     * @param listener
     */
    public static void getHistoryWorkList(String partyId,int viewIndex,int viewSize,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("partyId", partyId);
        map.put("viewIndex", String.valueOf(viewIndex));
        map.put("viewSize", String.valueOf(viewSize));
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.GetHistoryWorkList, map, code, listener);
    }

    /**
     * 抢单
     * @param workOrderId
     * @param code
     * @param listener
     */
    public static void RobTask(String workOrderId,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("workOrderId", workOrderId);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.DispatchProductTaskToStaff, map, code, listener);
    }

    /**
     *服务人员查看工单详情
     * @param workOrderId
     * @param code
     * @param listener
     */
    public static void GetWorkEffortDetail(String workOrderId,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("workOrderId", workOrderId);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.GetWorkEffortDetail, map, code, listener);
    }

    /***
     * 获取服务的二维码字符串
     * @param workOrderId
     * @param code
     * @param listener
     */
    public static void getQRCode(String workOrderId,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("workOrderId", workOrderId);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.GetQRCode, map, code, listener);
    }

    /**
     * 开始服务
     * @param workOrderId
     * @param code
     * @param listener
     */
    public static void startWorkEffort(String workOrderId,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("workOrderId", workOrderId);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.StartWorkEffort, map, code, listener);
    }


    /**
     * 结束服务
     * @param workEffortId
     * @param code
     * @param listener
     */
    public static void endWorkEffort(String workEffortId,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("workEffortId", workEffortId);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.EndWorkEffort, map, code, listener);
    }

    /**
     * 获取可转派的同事列表
     * @param workOrderId
     * @param code
     * @param listener
     */
    public static void getColleagues(String workOrderId,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("workOrderId", workOrderId);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.GetColleagues, map, code, listener);
    }

    /**
     * 转派服务
     * @param workOrderId
     * @param code
     * @param listener
     */
    public static void turnAndSendWorkEffort(String workOrderId,String toPartyId,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("workOrderId", workOrderId);
        map.put("toPartyId", toPartyId);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.TurnAndSendWorkEffort, map, code, listener);
    }



    /**
     * 获取指定日期的报告
     * @param someDay
     * @param toParty
     * @param code
     * @param listener
     */
    public static void getDayStatistics(String someDay,String toParty,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("someDay", someDay);
        map.put("toParty", toParty);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.GetDayStatistics, map, code, listener);
    }

    /**
     * 获取指定周的报告
     * @param someYear
     * @param someMonth
     * @param week
     * @param toParty
     * @param code
     * @param listener
     */
    public static void getWeekStatistics(String someYear,String someMonth,String week,String toParty,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("someYear", someYear);
        map.put("someMonth", someMonth);
        map.put("week", week);
        map.put("toParty", toParty);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.GetWeekStatistics, map, code, listener);
    }

    /**
     * 获取指定月的报告
     * @param someYear
     * @param someMonth
     * @param toParty
     * @param code
     * @param listener
     */
    public static void getMonthStatistics(String someYear,String someMonth,String toParty,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("someYear", someYear);
        map.put("someMonth", someMonth);
        map.put("toParty", toParty);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.GetMonthStatistics, map, code, listener);
    }

    /**
     * 获取提醒列表
     * @param code
     * @param listener
     */
    public static void getRemindList(int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.GetRemindList, map, code, listener);
    }


    /**
     * 新增提醒
     * @param remindPeriod 提醒周期。(0:提醒一次,1:每日提醒,2:工作日提醒)
     * @param remindType 提醒类型。CARRIER_CHILDREN：接送小孩。MEDICINE_TIME：按时服药。
     * @param text 提醒文本内容。文本内容和语音内容，两者至少要填一个。
     * @param remindPartyId 被提醒人的partyId。必须是家庭圈成员，并且是TV用户。获取家庭圈成员时，tvUser属性为true的就是TV用户。
     * @param remindTime 提醒时间，毫秒数。
     * @param code
     * @param listener
     */
    public static void addRemind(String remindPeriod,String remindType,String text,String remindPartyId,String remindTime,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("remindPeriod", remindPeriod);
        map.put("remindType", remindType);
        map.put("text", text);
        map.put("remindPartyId", remindPartyId);
        map.put("remindTime", remindTime);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.CreateRemind, map, code, listener);
    }


    /**
     * 删除提醒
     * @param remindId
     * @param code
     * @param listener
     */
    public static void delRemind(String remindId,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("remindId",remindId);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.DelRemind, map, code, listener);
    }

    /**
     * 查询老人信息（社区辅助测量）
     * @param mobile
     * @param cardNum
     * @param code
     * @param listener
     */
    public static void queryOldManInfo(String mobile,String cardNum,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("mobile", mobile);
        map.put("cardNum", cardNum);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.queryOldManInfo, map, code, listener);
    }

    /**
     * 提交老人信息测量
     * @param partyId
     * @param qrCode
     * @param code
     * @param listener
     */
    public static void submitOldManInfo(String partyId,String qrCode,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("partyId", partyId);
        map.put("qrCode", qrCode);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.submitOldManInfo, map, code, listener);
    }

    /**
     * 测量完成
     * @param qrCode
     * @param code
     * @param listener
     */
    public static void completeOldManInfo(String qrCode,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("qrCode", qrCode);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.completeOldManInfo, map, code, listener);
    }

    /**
     * 保存测量数量
     * @param measureData 测量数据对象json串（JSON.toJSONString）
     * @param userPartyId
     * @param code
     * @param listener
     * @param reqseq
     */
    public static void SaveMeasureData(String measureData,String userPartyId,int code,ResponseListener listener,int reqseq){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("measureData", measureData);
        map.put("userPartyId",userPartyId);
        map.put("reqseq",String.valueOf(reqseq));
        HttpUtil.enqueue(HttpInterfaceRequest.SaveHealthMeasureData, map, code, listener);
    }


    /**
     * 子女协助激活TV
     * @param qrcode
     * @param mobile
     * @param name
     * @param familyGroupId
     * @param familyRelation
     * @param remarkName
     * @param code
     * @param listener
     */
    public static void activeFamily(String qrcode,String mobile,String name,String familyGroupId,String familyRelation,String remarkName,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("mobile", mobile);
        map.put("qrcode", qrcode);
        map.put("name", name);
        if (!TextUtils.isEmpty(familyGroupId)){
            map.put("familyGroupId", familyGroupId);
        }
        if (!TextUtils.isEmpty(familyRelation)){
            map.put("familyRelation", familyRelation);
        }
        if (!TextUtils.isEmpty(remarkName)){
            map.put("remarkName", remarkName);
        }
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.ActiveFamily, map, code, listener);
    }

    /**
     * 服务人员协助激活TV
     * @param qrcode
     * @param mobile
     * @param name
     * @param cardId
     * @param gender
     * @param birthDate
     * @param marital
     * @param geraType
     * @param address
     * @param code
     * @param listener
     */
    public static void activeStaff(String qrcode,String mobile,String name,String cardId,String gender,String birthDate,String marital,String geraType,String address,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("mobile", mobile);
        map.put("qrcode", qrcode);
        map.put("name", name);
        map.put("cardId", cardId);
        if (!TextUtils.isEmpty(gender)){
            map.put("gender", gender);
        }
        if (!TextUtils.isEmpty(birthDate)){
            map.put("birthDate", birthDate);
        }
        if (!TextUtils.isEmpty(marital)){
            map.put("marital", marital);
        }
        if (!TextUtils.isEmpty(geraType)){
            map.put("geraType", geraType);
        }
        if (!TextUtils.isEmpty(address)){
            map.put("address", address);
        }
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.ActiveStaff, map, code, listener);
    }

    /**
     * 创建大事记
     * @param eventData
     * @param code
     * @param listener
     */
    public static void createMemberEvent(String eventData,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("eventData", eventData);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.CreateMemberEvent, map, code, listener);
    }


    /**
     * 查询大事记列表 族谱暂未完成  只传partyId 其余两个参数任意
     * @param treeNodeId
     * @param memberId
     * @param partyId
     * @param code
     * @param listener
     */
    public static void queryMemberEvents(String treeNodeId,String memberId,String partyId,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("treeNodeId", treeNodeId);
        map.put("memberId", memberId);
        map.put("partyId", partyId);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.QueryMemberEvents, map, code, listener);
    }

    /**
     * 大事记详情
     * @param eventsId
     * @param code
     * @param listener
     */
    public static void queryMemberEventDetail(String eventsId,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("eventsId", eventsId);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.QueryMemberEventDetail, map, code, listener);
    }

    /**
     * 查询大事记评论
     * @param eventsId
     * @param code
     * @param listener
     */
    public static void QueryEventComments(String eventsId,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("eventsId", eventsId);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.QueryEventComments, map, code, listener);
    }


    /**
     * 删除大事记
     * @param eventId
     * @param code
     * @param listener
     */
    public static void DeleteBigEvent(String eventId
            ,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("eventId", eventId);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.DeleteBigEvent, map, code, listener);
    }

    /**
     * 评论大事记
     * @param commentData json串
     * @param code
     * @param listener
     */
    public static void EventComment(String commentData,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("commentData", commentData);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.EventComment, map, code, listener);
    }


    /**
     * 正在进行的活动
     * @param code
     * @param listener
     */
    public static void queryCurrentActivities(int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.QueryCurrentActivities, map, code, listener);
    }

    /**
     * 历史活动
     * @param code
     * @param listener
     */
    public static void queryHistoryActivities(int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.QueryHistoryActivities, map, code, listener);
    }


    /**
     * 删除活动
     * @param activityId
     * @param code
     * @param listener
     */
    public static void DeleteCallEvent(String activityId,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("activityId", activityId);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.DeleteCallEvent, map, code, listener);
    }

    /**
     * 更新活动
     * @param activityDetail
     * @param code
     * @param listener
     */
    public static void UpdateCallEvent(String activityDetail,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("activityDetail", activityDetail);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.UpdateCallEvent, map, code, listener);
    }

    /**
     * 查询族谱信息
     * @param code
     * @param listener
     */
    public static void queryFamilyTree(int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.queryFamilyTree, map, code, listener);
    }

    /**
     * 修改族谱信息
     * @param familyTree 族谱对象json字符串
     * @param code
     * @param listener
     */
    public static void updateFamilyTree(String familyTree,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("familyTree", familyTree);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.updateFamilyTree, map, code, listener);
    }

    /**
     * 获取我所工作的养老院的信息
     * @param code
     * @param listener
     */
    public static void geraStoreProfil(int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("reqseq", "1");
        HttpUtil.enqueue(HttpInterfaceRequest.geraStoreProfil, map, code, listener);
    }


    /**
     * 修改养老院信息 仅gera_admin角色可修改
     * @param openingTime
     * @param closingTime
     * @param teamLeader
     * @param description
     * @param code
     * @param listener
     */
    public static void geraStoreProfilUpdate(String openingTime,String closingTime,String teamLeader,String description,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        if (!TextUtils.isEmpty(openingTime)){
            map.put("openingTime", openingTime);
        }
        if (!TextUtils.isEmpty(closingTime)){
            map.put("closingTime", closingTime);
        }
        if (!TextUtils.isEmpty(teamLeader)){
            map.put("teamLeader", teamLeader);
        }
        if (!TextUtils.isEmpty(description)){
            map.put("description", description);
        }
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.geraStoreProfilUpdate, map, code, listener);
    }

    /**
     * 发起音视频聊天
     * @param receiverId
     * @param userType
     * @param chatType
     * @param code
     * @param listener
     */
    public static void startVideoChat(String receiverId,UserType userType,MsgType chatType,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        String receiverUserType = userType == UserType.TV?"TV":"APP";
        String mode = chatType == MsgType.VoiceMsg?"voice":"video";
        map.put("receiverId",receiverId);
        map.put("receiverUserType",receiverUserType);
        map.put("mode",mode);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.video_chat_req, map, code, listener);
    }


    /**
     * 账户余额查询
     * @param code
     * @param listener
     */
    public static void queryPaymentBalance(int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.queryPaymentBalance, map, code, listener);
    }

    /**
     * 爱心币消费记录查询
     * @param viewIndex
     * @param viewSize
     * @param code
     * @param listener
     */
    public static void lovecashUserecord(int viewIndex,int viewSize,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("viewIndex", String.valueOf(viewIndex));
        map.put("viewSize", String.valueOf(viewSize));
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.lovecashUserecord, map, code, listener);
    }

    /**
     * 获取一周内签到情况
     * @param code
     * @param listener
     */
    public static void getUserSignInRecordForThisWeek(int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.getUserSignInRecordForThisWeek, map, code, listener);
    }

    /**
     * 提交签到或补签
     * @param signDate
     * @param code
     * @param listener
     */
    public static void checkUserSignIn(long signDate,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("reqseq", "1");
        if (signDate!=0){
            map.put("signDate",String.valueOf(signDate));
        }
        HttpUtil.enqueue(HttpInterfaceRequest.checkUserSignIn, map, code, listener);
    }

    /**
     * 获取分享邀请需要的族谱信息
     * @param code
     * @param listener
     */
    public static void queryInviteInfo(int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("reqseq", "1");
        HttpUtil.enqueue(HttpInterfaceRequest.queryInviteInfo, map, code, listener);
    }

    /**
     * 查询积分
     * @param code
     * @param listener
     */
    public static void queryCredits(int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("reqseq", "1");
        HttpUtil.enqueue(HttpInterfaceRequest.queryCredits, map, code, listener);
    }

    /**
     *  * 查询积分历史记录
     * @param viewIndex
     * @param viewSize
     * @param type 购买产品 CT_BUY_PRODUCT;每日签到 CT_DAILY_SIGN;家族成员邀请 CT_FAMILY_INVITED
     *             好友邀请 CT_FREIND_INVITED;爱心币充值 CT_LOVING_PAY;分享产品 CT_SHARE_PRODUCT
     *             消费积分 CT_SPEND_CREDITS";新用户注册 CT_USER_REGIST
     * @param code
     * @param listener
     */
    public static void queryCreditRecord(int viewIndex,int viewSize,String type,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("viewIndex", String.valueOf(viewIndex));
        map.put("viewSize", String.valueOf(viewSize));
        map.put("type", type);
        map.put("reqseq", "1");
        HttpUtil.enqueue(HttpInterfaceRequest.queryCreditRecord, map, code, listener);
    }

    /**
     * 获取我的订单
     * @param code
     * @param listener
     */
    public static void getMyOrders(int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("reqseq", "1");
        HttpUtil.enqueue(HttpInterfaceRequest.GetMyOrders, map, code, listener);
    }

    /**
     * 获取商品列表
     * @param geraId
     * @param categoryId
     * @param viewIndex
     * @param viewSize
     * @param code
     * @param listener
     */
    public static void getProductList(String geraId,String categoryId, int viewIndex,
                                      int viewSize,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("viewIndex", String.valueOf(viewIndex));
        map.put("viewSize", String.valueOf(viewSize));
        map.put("geraId", geraId);
        map.put("geraId", geraId);
        map.put("categoryId", categoryId);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.GetProductList, map, code, listener);
    }

    /**
     * 商品详情
     * @param productId
     * @param code
     * @param listener
     */
    public static void getProductDetail(String productId,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("productId", productId);
        map.put("reqseq", "1");
        HttpUtil.enqueue(HttpInterfaceRequest.GetProductDetail, map, code, listener);
    }

    /**
     * 普通用户查看任务详情
     * @param workOrderId
     * @param code
     * @param listener
     */
    public static void GetTasksDetail(String workOrderId,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("workOrderId", workOrderId);
        map.put("reqseq", "1");
        HttpUtil.enqueue(HttpInterfaceRequest.GetTasksDetail, map, code, listener);
    }

    /**
     * 服务人员信息
     * @param workOrderId
     * @param code
     * @param listener
     */
    public static void GetStaffInfo(String workOrderId,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("workOrderId", workOrderId);
        map.put("reqseq", "1");
        HttpUtil.enqueue(HttpInterfaceRequest.GetStaffInfo, map, code, listener);
    }

    /**
     * 服务评价
     * @param workOrderId
     * @param evaluation
     * @param evaluateContent
     * @param code
     * @param listener
     */
    public static void ServiceEvaluation(String workOrderId,int evaluation,String evaluateContent,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("workOrderId", workOrderId);
        map.put("evaluation", String.valueOf(evaluation));
        map.put("evaluateContent", evaluateContent);
        map.put("reqseq", "1");
        HttpUtil.enqueue(HttpInterfaceRequest.ServiceEvaluation, map, code, listener);
    }

    /**
     * 加入购物车
     * @param productId
     * @param customerId
     * @param code
     * @param listener
     */
    public static void addToCart(String productId,String customerId,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("productId", productId);
        map.put("customerId", customerId);
        map.put("reqseq", "1");
        HttpUtil.enqueue(HttpInterfaceRequest.AddToCart, map, code, listener);
    }

    /**
     * 可预约的详情
     * @param orderId
     * @param orderItemSeqId
     * @param code
     * @param listener
     */
    public static void AppointableTaskDetail(String orderId,String orderItemSeqId,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("orderId", orderId);
        map.put("orderItemSeqId", orderItemSeqId);
        map.put("reqseq", "1");
        HttpUtil.enqueue(HttpInterfaceRequest.AppointableTaskDetail, map, code, listener);
    }

    /**
     * 预约服务时间
     * @param orderId
     * @param orderItemSeqId
     * @param beginTime
     * @param endTime
     * @param code
     * @param listener
     */
    public static void AppointTime(String orderId,String orderItemSeqId,String beginTime,String endTime,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("orderId", orderId);
        map.put("orderItemSeqId", orderItemSeqId);
        map.put("beginTime", beginTime);
        map.put("endTime", endTime);
        map.put("reqseq", "1");
        HttpUtil.enqueue(HttpInterfaceRequest.AppointTime, map, code, listener);
    }

    /**
     * 取消服务预约
     * @param workOrderId
     * @param cancelReason
     * @param code
     * @param listener
     */
    public static void CancelAppoint(String workOrderId,String cancelReason,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("workOrderId", workOrderId);
        map.put("cancelReason", cancelReason);
        map.put("reqseq", "1");
        HttpUtil.enqueue(HttpInterfaceRequest.CancelAppoint, map, code, listener);
    }


    /**
     * 购物车创建订单
     * @param itmes
     * @param code
     * @param listener
     */
    public static void createOrder(List<String> itmes,int code,ResponseListener listener){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < itmes.size(); i++)
        {
            if (i==itmes.size()-1){
                sb.append(itmes.get(i));
            }else {
                sb.append(itmes.get(i));
                sb.append(',');
            }

        }
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("itmes", sb.toString());
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.createOrder, map, code, listener);
    }

    /**
     * 从产品ID创建订单
     * @param productId 产品ID。目前只有爱心币，写死为“CL000033”
     * @param count 购买数量。
     * @param buyForPartyId 为谁购买，如果为空，表示给自己买。
     * @param code
     * @param listener
     */
    public static void createOrderById(String productId,String count,String buyForPartyId,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("productId", productId);
        map.put("count", count);
        map.put("buyForPartyId", buyForPartyId);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.createOrderById, map, code, listener);
    }

    /**
     * 获取支付宝或微信支付参数
     * @param orderId
     * @param paymentMethod
     * @param code
     * @param listener
     */
    public static void payMoney(String orderId,String paymentMethod,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("orderId", orderId);
        map.put("paymentMethod", paymentMethod);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.PayMoney, map, code, listener);
    }

    /**
     * 查询订单支付状态
     * @param orderId
     * @param code
     * @param listener
     */
    public static void QueryPayStatus(String orderId,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("orderId", orderId);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.QueryPayStatus, map, code, listener);
    }

    /**
     * 查询订单详情
     * @param orderId
     * @param code
     * @param listener
     */
    public static void OrderDetail(String orderId,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("orderId", orderId);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.OrderDetail, map, code, listener);
    }

    /**
     * 报名活动详情
     * @param enrollActivityId
     * @param code
     * @param listener
     */
    public static void enrollActivityDetail(String enrollActivityId,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("enrollActivityId", enrollActivityId);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.enrollActivityDetail, map, code, listener);
    }

    /**
     * 报名参加活动
     * @param enrollActivityId
     * @param personCount
     * @param code
     * @param listener
     */
    public static void enrollJoin(String enrollActivityId,String personCount,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("enrollActivityId", enrollActivityId);
        map.put("personCount", personCount);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.enrollJoin, map, code, listener);
    }

    /**
     * 社区消息详情
     * @param groupNewsActivityId
     * @param code
     * @param listener
     */
    public static void groupNewsDetail(String groupNewsActivityId,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("groupNewsActivityId", groupNewsActivityId);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.groupNewsDetail, map, code, listener);
    }

    /**
     * 消息点赞或批判
     * @param groupNewsActivityId
     * @param support
     * @param code
     * @param listener
     */
    public static void groupNewsUpdate(String groupNewsActivityId,boolean support,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("groupNewsActivityId", groupNewsActivityId);
        map.put("support", String.valueOf(support));
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.groupNewsUpdate, map, code, listener);
    }

    /**
     * 查看投票活动详情
     * @param voteActivityId
     * @param code
     * @param listener
     */
    public static void voteActivityDetail(String voteActivityId,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("voteActivityId", voteActivityId);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.voteActivityDetail, map, code, listener);
    }

    /**
     * 投票
     * @param voteActivityId
     * @param voteItemId
     * @param code
     * @param listener
     */
    public static void vote(String voteActivityId,String voteItemId,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("voteActivityId", voteActivityId);
        map.put("voteItemId", voteItemId);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.vote, map, code, listener);
    }

    /**
     * 投票结果
     * @param voteActivityId
     * @param viewPassword
     * @param code
     * @param listener
     */
    public static void voteResult(String voteActivityId,String viewPassword,int code,ResponseListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWGlobal.instance().getUser().TOKEN);
        map.put("voteActivityId", voteActivityId);
        map.put("viewPassword", viewPassword);
        map.put("reqseq","1");
        HttpUtil.enqueue(HttpInterfaceRequest.voteResult, map, code, listener);
    }









}
