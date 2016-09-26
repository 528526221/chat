package com.xulc.chat.okhttp;

import java.util.HashMap;

/**
 * Created by xuliangchun on 2016/9/21.
 */
public class ResponseUtil {
    private static ResponseUtil util;
    private HashMap<Integer,String> errorMsg = new HashMap<>();

    /**
     * 构造对象时将错误列表初始化
     */
    public ResponseUtil() {
        errorMsg.put(0,"成功");
        errorMsg.put(10,"每天同一个商品只能购买一次哦");
        errorMsg.put(100,"支付成功");
        errorMsg.put(100000,"未登录");
        errorMsg.put(100001,"登录状态过期");
        errorMsg.put(100003,"您没有权限执行该操作");
        errorMsg.put(100004,"服务器繁忙");
        errorMsg.put(100005,"服务器内部错误");
        errorMsg.put(100006,"请求的服务不存在");
        errorMsg.put(100007,"数据请求方式错误");
        errorMsg.put(100008,"请求的资源不存在");
        errorMsg.put(100009,"请求的流程非法");
        errorMsg.put(100010,"请求数据太大");
        errorMsg.put(100019,"您尚未加入任何养老院");
        errorMsg.put(100020,"服务端数据正在初始化,请稍后再试");
        errorMsg.put(101,"支付失败");
        errorMsg.put(101000,"账号不存在");
        errorMsg.put(101001,"密码错误");
        errorMsg.put(101002,"账号或密码错误");
        errorMsg.put(101003,"登录时的图片验证码错误");
        errorMsg.put(101004,"短信验证码错误");
        errorMsg.put(101005,"登录失败的次数过多");
        errorMsg.put(101006,"账号已被禁用");
        errorMsg.put(101007,"账号已过期");
        errorMsg.put(101008,"账号状态错误，请联系客服");
        errorMsg.put(101009,"角色类型错误");
        errorMsg.put(101010,"此账号未关联到指定的虚拟养老院，或角色选择错误");
        errorMsg.put(101011,"未找到对应的店铺信息");
        errorMsg.put(101012,"您不是任何服务商的员工，或角色错误。或者您所属的服务商未与当前虚拟养老院建立联系");
        errorMsg.put(101013,"账号的当前密码为系统自动分配，需要在修改密码后登录");
        errorMsg.put(101014,"账号已经存在");
        errorMsg.put(101015,"短信发送失败");
        errorMsg.put(102000,"对方未注册亲伴");
        errorMsg.put(102001,"对方已经解除了与您的好友关系");
        errorMsg.put(102002,"发送者的消息发送功能被禁用");
        errorMsg.put(102003,"对方已经是您的好友");
        errorMsg.put(102004,"申请已过期");
        errorMsg.put(102005,"申请已过期。");
        errorMsg.put(102006,"消息内容类型错误");
        errorMsg.put(102007,"消息内容格式错误");
        errorMsg.put(102008,"接收方不在线");
        errorMsg.put(102009,"视频聊天应答超时，或聊天KEY错误（非法请求）");
        errorMsg.put(102010,"家庭圈不存在.");
        errorMsg.put(102011,"已向对方发出邀请.");
        errorMsg.put(102012,"已经是家庭圈成员.");
        errorMsg.put(102013,"申请已过期");
        errorMsg.put(102014,"您已被管理员移出圈子");
        errorMsg.put(102015,"群成员不存在");
        errorMsg.put(102016,"管理员不能退出圈子.");
        errorMsg.put(102017,"您已拥有该名字的家庭圈，请换个名字");
        errorMsg.put(103000,"设备未注册（设备ID不存在）");
        errorMsg.put(103001,"设备验证失败（序列号错误）");
        errorMsg.put(103002,"设备处于被禁用状态.");
        errorMsg.put(103003,"设备已过有效期");
        errorMsg.put(103004,"设备类型错误");
        errorMsg.put(103005,"设备已与其他人关联");
        errorMsg.put(103006,"设备已经激活");
        errorMsg.put(103007,"激活时填写的手机号已被使用");
        errorMsg.put(103008,"身份证号已存在");
        errorMsg.put(103009,"激活时短信验证码错误");
        errorMsg.put(103011,"一个用户只能绑定一个亲伴设备");
        errorMsg.put(104000,"未找到指定的服务工单");
        errorMsg.put(104001,"无法找到对应的亲伴设备");
        errorMsg.put(104002,"无法通知TV端服务开始.");
        errorMsg.put(104003,"APP开始服务提交的二维码错误,值不匹配.");
        errorMsg.put(104004,"未找到购物车项");
        errorMsg.put(105000,"在线支付失败");
        errorMsg.put(105001,"没有购物车项目的支付记录");
        errorMsg.put(105002,"支付完成，但是创建订单失败");
        errorMsg.put(105003,"余额不足。");
        errorMsg.put(105004,"订单状态错误。");
        errorMsg.put(105005,"支付金额错误。");
        errorMsg.put(105006,"支付方式错误。");
        errorMsg.put(106000,"投票主题不存在.");
        errorMsg.put(106001,"投票项不存在.");
        errorMsg.put(106002,"投票时间还未开始.");
        errorMsg.put(106003,"投票已经结束.");
        errorMsg.put(106004,"您已经投过票了.");
        errorMsg.put(106005,"投票结果查看密码错误.");
        errorMsg.put(106006,"此投票结果仅发起人可查看.");
        errorMsg.put(106007,"此投票结果仅在您投票过后才可查看.");
        errorMsg.put(106008,"报名活动不存在.");
        errorMsg.put(106009,"您已经报过名了.");
        errorMsg.put(106010,"消息不存在.");
        errorMsg.put(106011,"您已经对此消息进行过点赞或批判操作.");
        errorMsg.put(107000,"您已经创建过相同的提醒.");
        errorMsg.put(107001,"没有可提醒的对象,家庭圈中未找到亲伴用户.");
        errorMsg.put(107002,"提醒记录不存在.");
        errorMsg.put(108000,"族谱计算称呼：from人员族谱节点不存在");
        errorMsg.put(108001,"族谱计算称呼：to 人员族谱节点不存在");
        errorMsg.put(108002,"族谱计算称呼：计算失败，返回结果为空");
        errorMsg.put(108003,"一个人只能创建一个族谱.");
    }

    public static ResponseUtil getInstance(){
        if (util==null){
            util = new ResponseUtil();
        }
        return util;
    }

    /**
     * 判断response是否请求数据成功
     * @param response
     * @return
     */
    public boolean isSuccess(BaseResponse response){
        if (response.getStatusCode()==0){
            return true;
        }else {
            return false;
        }
    }

    /**
     * response请求失败时根据错误码返回错误描述
     * @param response
     * @return
     */
    public  String getErrorMsg(BaseResponse response){
        String msg = errorMsg.get(response.getStatusCode());
        if (msg==null){
            msg = String.valueOf(response.getStatusCode()).concat("错误表未定义");
        }
        return msg;
    }
}
